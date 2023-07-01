package com.livecricketscores.bean.matchLeanBack;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class MatchHeader{
    private int matchId;
    private String matchDescription;
    private String matchFormat;
    private String matchType;
    private boolean complete;
    private boolean domestic;
    private String matchStartTimestamp;
    private long matchCompleteTimestamp;
    private boolean dayNight;
    private int year;
    private int dayNumber;
    private String state;
    private String status;
    private TossResults tossResults;
    private Result result;
    private RevisedTarget revisedTarget;
    private ArrayList<Object> playersOfTheMatch;
    private ArrayList<Object> playersOfTheSeries;
    private ArrayList<MatchTeamInfo> matchTeamInfo;
    private boolean isMatchNotCovered;
    private Team1 team1;
    private Team2 team2;
    private String seriesDesc;
    private int seriesId;
    private String seriesName;
    private String alertType;
    private boolean livestreamEnabled;
}
