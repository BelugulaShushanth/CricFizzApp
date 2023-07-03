package com.livecricketscores.bean.commentary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class MatchUdrs{
    private int matchId;
    private int inningsId;
    private Date timestamp;
    private int team1Id;
    private int team1Remaining;
    private int team1Successful;
    private int team1Unsuccessful;
    private int team2Id;
    private int team2Remaining;
    private int team2Successful;
    private int team2Unsuccessful;
}
