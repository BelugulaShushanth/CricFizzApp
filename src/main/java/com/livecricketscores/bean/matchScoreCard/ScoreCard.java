package com.livecricketscores.bean.matchScoreCard;

import com.livecricketscores.bean.matchScoreCard.batsmen.BatTeamDetails;
import com.livecricketscores.bean.matchScoreCard.bowlers.BowlTeamDetails;
import com.livecricketscores.bean.matchScoreCard.partnership.PartnershipsData;
import com.livecricketscores.bean.matchScoreCard.wickets.WicketsData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScoreCard{
    private int matchId;
    private int inningsId;
    private Object timeScore;
    private BatTeamDetails batTeamDetails;
    private BowlTeamDetails bowlTeamDetails;
    private ScoreDetails scoreDetails;
    private ExtrasData extrasData;
    private PpData ppData;
    private WicketsData wicketsData;
    private PartnershipsData partnershipsData;
}
