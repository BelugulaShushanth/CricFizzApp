package com.livecricketscores.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchScore{
    public Team1Score team1Score;
    public Team2Score team2Score;
}
