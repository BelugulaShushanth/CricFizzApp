package com.cricFizzApp.bean.matchScoreCard.bowlers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BowlTeamDetails{
    private int bowlTeamId;
    private String bowlTeamName;
    private String bowlTeamShortName;
    private BowlersData bowlersData;
}
