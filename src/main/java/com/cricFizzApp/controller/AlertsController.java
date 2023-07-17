package com.cricFizzApp.controller;

import com.cricFizzApp.services.AlertParamDataService;
import com.cricFizzApp.utils.CricConstants;
import com.cricFizzApp.utils.CricUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/alerts")
public class AlertsController {

    @Autowired
    private AlertParamDataService alertParamDataService;

    @Autowired
    private CricUtils cricUtils;

    @GetMapping("/dashboard")
    private ModelAndView alertsDashboard(@AuthenticationPrincipal OAuth2User principal,
                                        HttpServletRequest httpServletRequest){

        ModelAndView mv = new ModelAndView();

        mv.addObject("userName",cricUtils.getUserName(principal,httpServletRequest));

        httpServletRequest.getSession().removeAttribute("live");
        httpServletRequest.getSession().removeAttribute("upcoming");

        mv.addObject("seriesMap",alertParamDataService
                .getSeriesData(httpServletRequest.getSession(),"live"));
        mv.addObject("isLive",true);

        mv.setViewName("ManageAlerts");
        return mv;
    }

    @GetMapping("/getAlertParamsData")
    public ModelAndView getAlertParametersSeriesData(@AuthenticationPrincipal OAuth2User principal,
                                                     HttpServletRequest httpServletRequest,
                                                     @RequestParam("matchType") Optional<String> matchType,
                                                     @RequestParam("seriesId") Optional<Integer> seriesId){
        ModelAndView mv = new ModelAndView();

        mv.addObject("userName",cricUtils.getUserName(principal,httpServletRequest));
        mv.addObject("alertTypeMap", CricConstants.getAlertTypes());
        mv.addObject("timePeriodMap", CricConstants.getTimePeriod());

        if(matchType.isPresent()) {

            mv.addObject("seriesMap", alertParamDataService
                    .getSeriesData(httpServletRequest.getSession(), matchType.get()));

            if(matchType.get().equals("live")){
                mv.addObject("isLive",true);
            }
            else{
                mv.addObject("isLive",false);
            }

            if(seriesId.isPresent()){
                mv.addObject("matchesMap", alertParamDataService
                        .getMatchesData(httpServletRequest.getSession(), matchType.get() , seriesId.get()));
                mv.addObject("selectedSeriesId",seriesId.get());
            }

        }


        mv.setViewName("ManageAlerts");
        return mv;
    }


}
