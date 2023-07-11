package com.cricFizzApp.bean.matchScoreCard;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayersOfTheMatch{
    private int id;
    private String name;
    private String fullName;
    private String nickName;
    private boolean captain;
    private boolean keeper;
    private boolean substitute;
    private String teamName;
    private int faceImageId;
}
