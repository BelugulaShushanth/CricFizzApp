package com.livecricketscores.controller;

import com.livecricketscores.bean.MatchesList.Matches;
import com.livecricketscores.services.CricBuzzService;
import com.livecricketscores.utils.CricUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/matches")
public class MatchesController {

    private final Logger logger = LoggerFactory.getLogger(MatchesController.class);

    @Autowired
    private CricBuzzService cricBuzzService;

    @Autowired
    private CricUtils cricUtils;

    @GetMapping("")
    public ModelAndView getLiveMatches(){
        ModelAndView mv = new ModelAndView();
        try {
            Matches liveMatches = cricBuzzService.getMatches("live");
            mv.addObject("typeMatches",liveMatches.getTypeMatches());
            mv.setViewName("ViewMatches");
            logger.info("Matches Data: {}", cricUtils.objectMapper().writeValueAsString(liveMatches));
        }
        catch (Exception e){
            logger.error("Exception in MatchesController:getMatches e:{}",e.getMessage());
        }
        return mv;
    }
    @GetMapping("/getMatches/{event}")
    public ModelAndView getMatches(@PathVariable("event") String event){
        logger.info("Incoming Event: {}",event);
        ModelAndView mv = new ModelAndView();
        try {
            Matches liveMatches = cricBuzzService.getMatches(event);
            mv.addObject("typeMatches",liveMatches.getTypeMatches());
            mv.setViewName("ViewMatches");
            logger.info("Matches Data: {}", cricUtils.objectMapper().writeValueAsString(liveMatches));
        }
        catch (Exception e){
            logger.error("Exception in MatchesController:getMatches e:{}",e.getMessage());
        }
        return mv;
    }
}
