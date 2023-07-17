package com.cricFizzApp.controller;

import com.cricFizzApp.services.AlertParamDataService;
import com.cricFizzApp.utils.CricConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/alerts")
public class AlertsController {

    @Autowired
    private AlertParamDataService alertParamDataService;

    @GetMapping("/getAlertParamsData")
    public ModelAndView getAlertParametersSeriesData(HttpSession httpSession,
                                               @RequestParam("matchType") Optional<String> matchType,
                                               @RequestParam("seriesId") Optional<Integer> seriesId){
        ModelAndView mv = new ModelAndView();

        mv.addObject("alertTypeMap", CricConstants.getAlertTypes());
        mv.addObject("timePeriodMap", CricConstants.getTimePeriod());

        if(matchType.isPresent()) {

            mv.addObject("seriesMap", alertParamDataService
                    .getSeriesData(httpSession, matchType.get()));

            if(matchType.get().equals("live")){
                mv.addObject("isLive",true);
            }
            else{
                mv.addObject("isLive",false);
            }

            if(seriesId.isPresent()){
                mv.addObject("matchesMap", alertParamDataService
                        .getMatchesData(httpSession, matchType.get() , seriesId.get()));
                mv.addObject("selectedSeriesId",seriesId.get());
            }

        }


        mv.setViewName("ManageAlerts");
        return mv;
    }


}
