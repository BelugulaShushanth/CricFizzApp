package com.livecricketscores.bean;

import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Matches {
    public ArrayList<TypeMatch> typeMatches;
    public Filters filters;
    public AppIndex appIndex;
    public String responseLastUpdated;
}
