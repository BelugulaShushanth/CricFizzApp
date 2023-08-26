package com.cricFizzApp.clients;

import com.cricFizzApp.bean.otp.OTPBean;
import com.cricFizzApp.bean.otp.Status;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "api-gateway")
public interface OTPClient {

    @PostMapping("/otp-service/api/otp/sendOTP")
    OTPBean sendOTP(@RequestParam("mailId") String mailId);

    @PostMapping("/otp-service/api/otp/verifyOTP")
    Status verifyOTP(@RequestBody Map<String,String> otpVerify);

}
