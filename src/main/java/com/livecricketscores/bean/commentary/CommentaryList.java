package com.livecricketscores.bean.commentary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentaryList{
    private String commText;
    private Object timestamp;
    private int ballNbr;
    private int inningsId;
    private String event;
    private String batTeamName;
    private CommentaryFormats commentaryFormats;
    private double overNumber;
    private OverSeparator overSeparator;
}
