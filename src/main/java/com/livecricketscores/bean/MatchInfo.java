package com.livecricketscores.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchInfo{
    public int matchId;
    public int seriesId;
    public String seriesName;
    public String matchDesc;
    public String matchFormat;
    public String startDate;
    public String endDate;
    public String state;
    public String status;
    public Team1 team1;
    public Team2 team2;
    public VenueInfo venueInfo;
    public int currBatTeamId;
    public String seriesStartDt;
    public String seriesEndDt;
    public boolean isTimeAnnounced;
    public String stateTitle;
}
