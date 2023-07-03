package com.livecricketscores.bean.commentary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class MatchVideo{
    private String infraType;
    private String headline;
    private String commTimestamp;
    private String itemId;
    private String appLinkUrl;
    private int imageId;
    private String mappingId;
    private String videoUrl;
    private String adTag;
    private String language;
    private int videoId;
    private String videoType;
    private ArrayList<Category> category;
    private ArrayList<Tag> tags;
}
