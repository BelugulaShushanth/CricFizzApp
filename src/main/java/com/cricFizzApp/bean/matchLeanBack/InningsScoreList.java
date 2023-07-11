package com.cricFizzApp.bean.matchLeanBack;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;

@Getter
@Setter
@NoArgsConstructor
public class InningsScoreList implements Comparator{
    private int inningsId;
    private int batTeamId;
    private String batTeamName;
    private int score;
    private int wickets;
    private double overs;
    private boolean isDeclared;
    private boolean isFollowOn;
    private int ballNbr;

    @Override
    public int compare(Object o1, Object o2) {
        InningsScoreList i1 = (InningsScoreList) o1;
        InningsScoreList i2 = (InningsScoreList) o2;
        if(i1.getInningsId() == i2.getInningsId()){
            return 0;
        } else if (i1.getInningsId() > i2.getInningsId()) {
            return 1;
        }
        return -1;
    }
}
