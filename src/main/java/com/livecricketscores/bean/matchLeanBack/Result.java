package com.livecricketscores.bean.matchLeanBack;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Result{
    private String winningTeam;
    private boolean winByRuns;
    private boolean winByInnings;
}
