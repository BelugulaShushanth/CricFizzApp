package com.cricFizzApp.bean.matchScoreCard.batsmen;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Bat2{
    private int batId;
    private String batName;
    private String batShortName;
    private boolean isCaptain;
    private boolean isKeeper;
    private int runs;
    private int balls;
    private int dots;
    private int fours;
    private int sixes;
    private int mins;
    private double strikeRate;
    private String outDesc;
    private int bowlerId;
    private int fielderId1;
    private int fielderId2;
    private int fielderId3;
    private int ones;
    private int twos;
    private int threes;
    private int fives;
    private int boundaries;
    private int sixers;
    private String wicketCode;
    private boolean isOverseas;
    private String inMatchChange;
    private String playingXIChange;
}
