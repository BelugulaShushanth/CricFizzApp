package com.livecricketscores.controller;

import com.livecricketscores.bean.Matches;
import com.livecricketscores.services.CricBuzzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/v1/matches")
public class MatchesController {

    @Autowired
    private CricBuzzService cricBuzzService;
    @GetMapping("/getLiveMatches")
    public ModelAndView getLiveMatches(){
        Matches liveMatches = cricBuzzService.getLiveMatches();
        ModelAndView mv = new ModelAndView();
        mv.addObject("matches",liveMatches.getTypeMatches());
        mv.setViewName("ViewLiveMatches");
        return mv;
    }
}
