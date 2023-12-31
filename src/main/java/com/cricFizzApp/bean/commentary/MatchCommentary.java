package com.cricFizzApp.bean.commentary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
@NoArgsConstructor
public class MatchCommentary {
    private ArrayList<CommentaryList> commentaryList;
    private MatchHeader matchHeader;
    private Miniscore miniscore;
    private ArrayList<CommentarySnippetList> commentarySnippetList;
    private String page;
    private boolean enableNoContent;
    private ArrayList<MatchVideo> matchVideos;
    private int responseLastUpdated;
}
