package com.cricFizzApp.controller;

import com.cricFizzApp.bean.otp.OTPBean;
import com.cricFizzApp.bean.otp.Status;
import com.cricFizzApp.bean.user.User;
import com.cricFizzApp.repository.UserRepository;
import com.cricFizzApp.services.OTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CreateAccountController {
    
    @Autowired
    private OTPClient otpClient;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/verifyAccount")
    public String createAccountVerify(Model model){
        OTPBean otpBean = new OTPBean();
        Status status = new Status();
        status.setStatusMessage("Not Verified");
        otpBean.setMailStatus(status);
        model.addAttribute("mail",otpBean);
        model.addAttribute("isOTPInput",false);
        return "VerifyAccount";
    }

    @PostMapping("/verifyAccount")
    public String createAccountVerify(@ModelAttribute OTPBean otpBean, Model model, HttpSession httpSession){
        model.addAttribute("mail",otpBean);
        if(userRepository.findById(otpBean.getMailId()).isEmpty()) {
            if (otpBean.getOtp() == null) {
                OTPBean sendOTPResponse = otpClient.sendOTP(otpBean.getMailId());
                if (!sendOTPResponse.getMailStatus().getHttpStatus().is2xxSuccessful()) {
                    model.addAttribute("isMailValid", false);
                } else {
                    model.addAttribute("isOTPInput", true);
                    httpSession.setAttribute("sendOTPResponse", sendOTPResponse);
                }
            } else {
                OTPBean sendOTPResponse = (OTPBean) httpSession.getAttribute("sendOTPResponse");
                Map<String, String> otpVerifyDetails = new HashMap<>();
                otpVerifyDetails.put("transactionId", sendOTPResponse.getTransactionId().toString());
                otpVerifyDetails.put("otp", otpBean.getOtp());
                Status status = otpClient.verifyOTP(otpVerifyDetails);
                if (!status.getHttpStatus().is2xxSuccessful()
                        && status.getStatusMessage().equalsIgnoreCase("Invalid OTP")) {
                    model.addAttribute("isOTPValid", false);
                    model.addAttribute("isOTPInput", true);
                } else {
                    User user = new User();
                    user.setUserId(sendOTPResponse.getMailId());
                    user.setIsVerified(true);
                    model.addAttribute("user", user);
                    httpSession.removeAttribute("otpResponse");
                    return "CreateAccount";
                }
            }
        }
        else{
            model.addAttribute("isAccountExists",true);
        }

        return "VerifyAccount";
    }

    @PostMapping("/createAccount")
    public String createAccount(@ModelAttribute User user, Model model){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userSaved = userRepository.save(user);
        return "AccountCreated";
    }

}
