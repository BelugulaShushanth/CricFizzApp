package com.livecricketscores.controller;

import com.livecricketscores.bean.Matches;
import com.livecricketscores.services.CricBuzzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/matches")
public class MatchesController {

    @Autowired
    private CricBuzzService cricBuzzService;
    @GetMapping("/getLiveMatches")
    public Matches getLiveMatches(){
        return cricBuzzService.getLiveMatches();
    }
}
