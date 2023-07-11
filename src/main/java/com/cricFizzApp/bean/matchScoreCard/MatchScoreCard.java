package com.cricFizzApp.bean.matchScoreCard;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class MatchScoreCard {
    private ArrayList<ScoreCard> scoreCard;
    private MatchHeader matchHeader;
    private boolean isMatchComplete;
    private String status;
    private ArrayList<Object> videos;
    private int responseLastUpdated;
}
