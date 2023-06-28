package com.livecricketscores.bean.matchesList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Match{
    private MatchInfo matchInfo;
    private MatchScore matchScore;
}
