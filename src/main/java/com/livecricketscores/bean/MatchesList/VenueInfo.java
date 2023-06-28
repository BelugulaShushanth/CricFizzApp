package com.livecricketscores.bean.MatchesList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VenueInfo{
    private int id;
    private String ground;
    private String city;
    private String timezone;
}
