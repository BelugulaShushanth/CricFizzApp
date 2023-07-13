package com.cricFizzApp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/loggedInUser/oAuth2")
    public ModelAndView oAuthauthenticatedUser(@AuthenticationPrincipal OAuth2User principal){
        ModelAndView mv = new ModelAndView();
        mv.addObject("userName",principal.getAttributes().get("name"));
        mv.setViewName("ViewSubscription");
        return mv;
    }

    @GetMapping("/loggedInUser/db")
    public ModelAndView dbAuthenticatedUser(HttpServletRequest httpServletRequest){
        ModelAndView mv = new ModelAndView();
        mv.addObject("userName",httpServletRequest.getRemoteUser());
        mv.setViewName("ViewSubscription");
        return mv;
    }

    @GetMapping("/loginUser")
    public String authenticateUser(){
        return "LoginUser";
    }

}
