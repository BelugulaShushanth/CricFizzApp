package com.cricFizzApp.bean.commentary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TossResults{
    private int tossWinnerId;
    private String tossWinnerName;
    private String decision;
}
