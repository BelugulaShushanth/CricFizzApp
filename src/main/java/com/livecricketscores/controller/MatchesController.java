package com.livecricketscores.controller;

import com.livecricketscores.bean.Matches;
import com.livecricketscores.services.CricBuzzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/matches")
public class MatchesController {

    @Autowired
    private CricBuzzService cricBuzzService;
    @GetMapping("/getMatches/{event}")
    public ModelAndView getLiveMatches(@PathVariable("event") String event){
        Matches liveMatches = cricBuzzService.getMatches(event);
        ModelAndView mv = new ModelAndView();
        mv.addObject("typeMatches",liveMatches.getTypeMatches());
        mv.setViewName("ViewMatches");
        return mv;
    }
}
