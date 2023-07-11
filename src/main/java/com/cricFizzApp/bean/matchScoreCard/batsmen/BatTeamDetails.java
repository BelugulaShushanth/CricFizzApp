package com.cricFizzApp.bean.matchScoreCard.batsmen;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BatTeamDetails{
    private int batTeamId;
    private String batTeamName;
    private String batTeamShortName;
    private BatsmenData batsmenData;
}
