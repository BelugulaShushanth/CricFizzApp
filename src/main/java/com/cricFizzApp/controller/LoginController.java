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

    @GetMapping("/loggedInUser/oAuth2")
    public ModelAndView oAuthauthenticatedUser(@AuthenticationPrincipal OAuth2User principal){
        ModelAndView mv = new ModelAndView();
        mv.addObject("userName",principal.getAttributes().get("name"));
        mv.setViewName("ManageAlerts");
        return mv;
    }

    @GetMapping("/loggedInUser/db")
    public ModelAndView dbAuthenticatedUser(HttpServletRequest httpServletRequest){
        ModelAndView mv = new ModelAndView();
        mv.addObject("userName","Test User");
        //httpServletRequest.getRemoteUser()
        mv.addObject("seriesMap",alertParamDataService
                                            .getSeriesData(httpServletRequest.getSession(),"live"));
        mv.addObject("isLive",true);
        mv.setViewName("ManageAlerts");
        return mv;
    }

    @GetMapping("/loginUser")
    public String authenticateUser(){
        return "LoginUser";
    }

}
