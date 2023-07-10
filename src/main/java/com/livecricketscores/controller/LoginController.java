package com.livecricketscores.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/loggedInUser")
    public ModelAndView authenticatedUser(@AuthenticationPrincipal OAuth2User principal){
//        System.out.println(principal.);
        ModelAndView mv = new ModelAndView();
        mv.addObject("userName",principal.getAttributes().get("name"));
        mv.setViewName("ViewSubscription");
        return mv;
    }

    @GetMapping("/loginUser")
    public String authenticateUser(){
        return "LoginUser";
    }
}
