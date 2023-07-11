package com.cricFizzApp.bean.matchScoreCard;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Result{
    private String resultType;
    private String winningTeam;
    private int winningteamId;
    private int winningMargin;
    private boolean winByRuns;
    private boolean winByInnings;
}
