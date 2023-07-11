package com.cricFizzApp.bean.matchesList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchInfo{
    private int matchId;
    private int seriesId;
    private String seriesName;
    private String matchDesc;
    private String matchFormat;
    private String startDate;
    private String endDate;
    private String state;
    private String status;
    private Team1 team1;
    private Team2 team2;
    private VenueInfo venueInfo;
    private int currBatTeamId;
    private String seriesStartDt;
    private String seriesEndDt;
    private boolean isTimeAnnounced;
    private String stateTitle;
}
