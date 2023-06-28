package com.livecricketscores.bean.matchesList;

import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TypeMatch{
    private String matchType;
    private ArrayList<SeriesMatch> seriesMatches;
}
