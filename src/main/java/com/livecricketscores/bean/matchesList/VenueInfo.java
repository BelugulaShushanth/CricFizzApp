package com.livecricketscores.bean.matchesList;

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
