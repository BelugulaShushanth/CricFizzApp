package com.cricFizzApp.bean.commentary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
@NoArgsConstructor
public class MatchScoreDetails{
    private int matchId;
    private ArrayList<InningsScoreList> inningsScoreList;
    private TossResults tossResults;
    private ArrayList<MatchTeamInfo> matchTeamInfo;
    private boolean isMatchNotCovered;
    private String matchFormat;
    private String state;
    private String customStatus;
    private int highlightedTeamId;
}
