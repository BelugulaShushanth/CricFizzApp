package com.livecricketscores.bean.matchScoreCard.wickets;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Wkt2{
    private int batId;
    private String batName;
    private int wktNbr;
    private double wktOver;
    private int wktRuns;
    private int ballNbr;
}
