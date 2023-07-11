package com.cricFizzApp.bean.commentary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class CommentarySnippetList{
    private int commId;
    private int matchId;
    private int inningsId;
    private String infraType;
    private String headline;
    private int imageId;
    private String itemId;
    private long timestamp;
    private ArrayList<Object> parsedContent;
    private ArrayList<String> videoGeo;
    private String videoType;
    private String url;
    private boolean isLive;
    private int videoId;
    private String mappingId;
    private String videoUrl;
    private String adTag;
    private ArrayList<Category> categories;
    private String language;
    private int planId;
    private ArrayList<Tag> tags;
    private boolean isPremiumFree;
}
