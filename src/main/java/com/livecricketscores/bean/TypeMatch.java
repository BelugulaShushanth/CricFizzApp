package com.livecricketscores.bean;

import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TypeMatch{
    public String matchType;
    public ArrayList<SeriesMatch> seriesMatches;
}
