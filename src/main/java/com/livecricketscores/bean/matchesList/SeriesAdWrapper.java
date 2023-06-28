package com.livecricketscores.bean.matchesList;

import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class SeriesAdWrapper{
    private int seriesId;
    private String seriesName;
    private ArrayList<Match> matches;
}
