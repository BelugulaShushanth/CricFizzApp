package com.cricFizzApp.controller;

import com.cricFizzApp.services.AlertParamDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private AlertParamDataService alertParamDataService;

    @GetMapping("/loginUser")
    public String authenticateUser(){
        return "LoginUser";
    }

}
