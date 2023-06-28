package com.livecricketscores.bean.matchScoreCard.bowlers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Bowl2{
    private int bowlerId;
    private String bowlName;
    private String bowlShortName;
    private boolean isCaptain;
    private boolean isKeeper;
    private double overs;
    private int maidens;
    private int runs;
    private int wickets;
    private double economy;
    private int no_balls;
    private int wides;
    private int dots;
    private int balls;
    private double runsPerBall;
    private boolean isOverseas;
    private String inMatchChange;
    private String playingXIChange;
}
