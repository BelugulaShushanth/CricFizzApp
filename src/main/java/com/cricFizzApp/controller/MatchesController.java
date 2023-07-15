package com.cricFizzApp.controller;

import com.cricFizzApp.bean.commentary.MatchCommentary;
import com.cricFizzApp.bean.matchLeanBack.MatchLeanBack;
import com.cricFizzApp.bean.matchScoreCard.MatchScoreCard;
import com.cricFizzApp.bean.matchesList.Matches;
import com.cricFizzApp.services.CricBuzzService;
import com.cricFizzApp.utils.CricUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/matches")
public class MatchesController {

    private final Logger logger = LoggerFactory.getLogger(MatchesController.class);

    @Autowired
    private CricBuzzService cricBuzzService;

    @Autowired
    private CricUtils cricUtils;

    @GetMapping("")
    public ModelAndView getLiveMatches(HttpSession httpSession){
        ModelAndView mv = new ModelAndView();
        try {
            Matches liveMatches = cricBuzzService.getMatches("live");
            if(liveMatches != null) {
                mv.addObject("typeMatches", liveMatches.getTypeMatches());
                if (httpSession.getAttribute("typeMatches") != null){
                    httpSession.removeAttribute("typeMatches");
                }
                httpSession.setAttribute("typeMatches",liveMatches);
                mv.setViewName("ViewMatches");
            }
            else{
                mv.addObject("matchStatus", "No Live Matches Found");
                mv.setViewName("NoMatches");
            }
            logger.info("Matches Data: {}", cricUtils.objectMapper().writeValueAsString(liveMatches));
        }
        catch (Exception e){
            logger.error("Exception in MatchesController:getMatches e:{}",e.getMessage());
        }
        return mv;
    }
    @GetMapping("/getMatches/{event}")
    public ModelAndView getMatches(@PathVariable("event") String event,HttpSession httpSession){
        logger.info("Incoming Event: {}",event);
        ModelAndView mv = new ModelAndView();
        try {
            Matches liveMatches = cricBuzzService.getMatches(event);
            if(liveMatches != null) {
                mv.addObject("typeMatches", liveMatches.getTypeMatches());
                if (httpSession.getAttribute("typeMatches") != null){
                    httpSession.removeAttribute("typeMatches");
                }
                httpSession.setAttribute("typeMatches",liveMatches);
                mv.setViewName("ViewMatches");
            }
            else{
                mv.addObject("matchStatus", "No "+event+" Matches Found");
                mv.setViewName("NoMatches");
            }
            logger.info("Matches Data: {}", cricUtils.objectMapper().writeValueAsString(liveMatches));
        }
        catch (Exception e){
            logger.error("Exception in MatchesController:getMatches e:{}",e.getMessage());
        }
        return mv;
    }

    @GetMapping("/getMatch/{matchId}/{matchVenue}")
    public ModelAndView getMatch(@PathVariable("matchId") String matchId, @PathVariable("matchVenue") String matchVenue
            ,HttpSession httpSession){
        logger.info("Incoming MatchId: {}",matchId);
        ModelAndView mv = new ModelAndView();
        try {
            MatchLeanBack matchLeanBack = cricBuzzService.getMatchLeanBack(matchId);
            MatchCommentary matchCommentary = cricBuzzService.getMatchCommentary(matchId);
            if(matchLeanBack == null){
                Matches matches = (Matches) httpSession.getAttribute("typeMatches");
                matchLeanBack = cricUtils.mapMatchLeanBack(matchLeanBack,matches,Integer.parseInt(matchId));
            }
            mv.addObject("matchLeanBack", matchLeanBack);
            mv.addObject("matchVenue",matchVenue);
            mv.addObject("matchCommentary",matchCommentary);
            mv.setViewName("ViewMatchCommentary");
            logger.info("MatchLeanBack Data: {}", cricUtils.objectMapper().writeValueAsString(matchLeanBack));
            logger.info("MatchCommentary Data: {}", cricUtils.objectMapper().writeValueAsString(matchCommentary));
        }
        catch (Exception e){
            logger.error("Exception in MatchesController:getMatch e:{}",e.getMessage());
        }
        return mv;
    }

    @GetMapping("/getMatchScoreCard/{matchId}/{matchVenue}")
    public ModelAndView getMatchScoreCard(@PathVariable("matchId") String matchId,
                                          @PathVariable("matchVenue") String matchVenue,
                                          HttpSession httpSession){
        logger.info("Incoming MatchId: {}",matchId);
        ModelAndView mv = new ModelAndView();
        try {
            MatchScoreCard matchScoreCard = cricBuzzService.getMatchesScoreCard(matchId);
            if(matchScoreCard == null){
                Matches matches = (Matches) httpSession.getAttribute("typeMatches");
                matchScoreCard = cricUtils.mapMatchScoreCard(matches,Integer.parseInt(matchId));
            }
            mv.addObject("matchScoreCard", matchScoreCard);
            mv.addObject("matchVenue",matchVenue);
            mv.setViewName("ViewMatchScoreCard");
            logger.info("MatchScoreCard: {}",matchScoreCard);
            logger.info("MatchScoreCard Data: {}", cricUtils.objectMapper().writeValueAsString(matchScoreCard));
        }
        catch (Exception e){
            logger.error("Exception in MatchesController:getMatchScoreCard e:{}",e.getMessage());
        }
        return mv;
    }
}
