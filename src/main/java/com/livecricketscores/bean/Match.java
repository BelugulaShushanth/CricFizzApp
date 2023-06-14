package com.livecricketscores.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Match{
    public MatchInfo matchInfo;
    public MatchScore matchScore;
}
