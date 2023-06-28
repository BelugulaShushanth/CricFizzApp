package com.livecricketscores.bean.MatchesList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchScore{
    private Team1Score team1Score;
    private Team2Score team2Score;
}
