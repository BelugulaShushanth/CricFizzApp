package com.livecricketscores.bean.commentary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Category{
    @JsonProperty("ID")
    private int iD;
    private String name;
    private int imageID;
}
