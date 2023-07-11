package com.cricFizzApp.bean.commentary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InningsScoreList{
    private int inningsId;
    private int batTeamId;
    private String batTeamName;
    private int score;
    private int wickets;
    private double overs;
    private boolean isDeclared;
    private boolean isFollowOn;
    private int ballNbr;
}
