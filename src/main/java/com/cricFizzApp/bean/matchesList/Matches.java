package com.cricFizzApp.bean.matchesList;

import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Matches {
    private ArrayList<TypeMatch> typeMatches;
    private Filters filters;
    private AppIndex appIndex;
    private String responseLastUpdated;
}
