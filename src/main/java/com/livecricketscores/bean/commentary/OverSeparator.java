package com.livecricketscores.bean.commentary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class OverSeparator{
    private int score;
    private int wickets;
    private int inningsId;
    private String o_summary;
    private int runs;
    private ArrayList<Integer> batStrikerIds;
    private ArrayList<String> batStrikerNames;
    private int batStrikerRuns;
    private int batStrikerBalls;
    private ArrayList<Integer> batNonStrikerIds;
    private ArrayList<String> batNonStrikerNames;
    private int batNonStrikerRuns;
    private int batNonStrikerBalls;
    private ArrayList<Integer> bowlIds;
    private ArrayList<String> bowlNames;
    private int bowlOvers;
    private int bowlMaidens;
    private int bowlRuns;
    private int bowlWickets;
    private long timestamp;
    private double overNum;
    private String batTeamName;
    private String event;
}
