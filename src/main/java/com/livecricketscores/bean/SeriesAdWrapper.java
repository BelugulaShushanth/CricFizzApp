package com.livecricketscores.bean;

import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class SeriesAdWrapper{
    public int seriesId;
    public String seriesName;
    public ArrayList<Match> matches;
}
