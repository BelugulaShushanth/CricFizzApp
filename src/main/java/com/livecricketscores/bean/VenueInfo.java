package com.livecricketscores.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VenueInfo{
    public int id;
    public String ground;
    public String city;
    public String timezone;
}
