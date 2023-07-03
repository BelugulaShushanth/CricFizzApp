package com.livecricketscores.bean.commentary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LatestPerformance{
    private int runs;
    private int wkts;
    private String label;
}
