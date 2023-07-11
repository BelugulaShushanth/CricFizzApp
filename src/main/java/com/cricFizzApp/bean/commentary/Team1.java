package com.cricFizzApp.bean.commentary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class Team1{
    private int id;
    private String name;
    private ArrayList<Object> playerDetails;
    private String shortName;
}
