package com.cricFizzApp.services;

import com.cricFizzApp.bean.otp.OTPBean;
import com.cricFizzApp.bean.otp.Status;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@FeignClient("OTP-SERVICE")
public interface OTPClient {

    @PostMapping("/api/otp/sendOTP")
    @Async("asyncExecutor")
    CompletableFuture<OTPBean> sendOTP(@RequestParam("mailId") String otp);

    @PostMapping("/api/otp/verifyOTP")
    Status verifyOTP(@RequestBody Map<String,String> otpVerify);

}
