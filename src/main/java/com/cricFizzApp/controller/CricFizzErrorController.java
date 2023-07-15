package com.cricFizzApp.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CricFizzErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest httpServletRequest){
        Object status = httpServletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        ModelAndView mv = new ModelAndView();
//        String errorMsg = "";
//        if(status != null){
//            int statusCode = Integer.parseInt(status.toString());
//            if(statusCode == HttpStatus.NOT_FOUND.value()){
//                errorMsg = "Page Not Found";
//            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//                errorMsg = "Internal Server Error";
//            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
//                errorMsg = "Page is forbidden";
//            }
//        }
        mv.addObject("statusCode", Integer.parseInt(status.toString()));
        mv.setViewName("ErrorPage");
        return mv;
    }
}
