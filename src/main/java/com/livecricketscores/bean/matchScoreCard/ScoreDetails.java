package com.livecricketscores.bean.matchScoreCard;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScoreDetails{
    private int ballNbr;
    private boolean isDeclared;
    private boolean isFollowOn;
    private double overs;
    private int revisedOvers;
    private double runRate;
    private int runs;
    private int wickets;
    private double runsPerBall;
}
