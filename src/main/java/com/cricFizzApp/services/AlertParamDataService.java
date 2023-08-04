package com.cricFizzApp.services;

import com.cricFizzApp.bean.matchesList.Match;
import com.cricFizzApp.bean.matchesList.Matches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class AlertParamDataService {

    @Autowired
    private CricBuzzService cricBuzzService;

    public Map<Integer,String> getSeriesData(HttpSession httpSession,String matchType) {

        Matches matches = null;
        if (httpSession.getAttribute(matchType) == null){
            matches = cricBuzzService.getMatches(matchType);
            if (matchType.equals("live")) {
                httpSession.setAttribute("live", matches);
            } else if (matchType.equals("upcoming")) {
                httpSession.setAttribute("upcoming", matches);
            }
        }
        else {
            matches = (Matches) httpSession.getAttribute(matchType);
        }
        Map<Integer,String> seriesMap = new LinkedHashMap<>();
        matches.getTypeMatches()
                .stream()
                .filter(match -> match != null && match.getSeriesMatches() != null)
                .forEach(series -> series.getSeriesMatches()
                                    .stream()
                                    .filter(seriesMatch -> seriesMatch != null && seriesMatch.getSeriesAdWrapper() != null
                                                            && isLiveMatches(matchType,seriesMatch.getSeriesAdWrapper().getMatches()))
                                    .forEach(seriesMatch -> {
                                        seriesMap.put(seriesMatch.getSeriesAdWrapper().getSeriesId(),
                                                seriesMatch.getSeriesAdWrapper().getSeriesName());
                                    }));

        return seriesMap;
    }

    public Map<Integer,String> getMatchesData(HttpSession httpSession, String matchType, Long seriesId){
        Matches matches = (Matches) httpSession.getAttribute(matchType);
        if(matches != null) {
            Map<Integer, String> matchesMap = new LinkedHashMap<>();
            matches.getTypeMatches()
                    .stream()
                    .filter(match -> match != null && match.getSeriesMatches() != null)
                    .forEach(series -> series.getSeriesMatches()
                            .stream()
                            .filter(seriesMatch -> seriesMatch != null
                                    && seriesMatch.getSeriesAdWrapper() != null
                                    && seriesMatch.getSeriesAdWrapper().getSeriesId() == seriesId)
                            .forEach(seriesMatch -> seriesMatch.getSeriesAdWrapper().getMatches()
                                    .stream().filter(Objects::nonNull)
                                                    .filter(match -> match != null && match.getMatchInfo() != null
                                                            && match.getMatchInfo().getTeam1() != null
                                                            && match.getMatchInfo().getTeam2() != null
                                                            && matchType.equalsIgnoreCase("upcoming") ||
                                                            match.getMatchInfo().getState().equalsIgnoreCase("In Progress"))
                                                    .forEach(match -> matchesMap.put(match.getMatchInfo().getMatchId(),
                                                                      match.getMatchInfo().getTeam1().getTeamName()
                                                                      +" Vs "+match.getMatchInfo().getTeam2().getTeamName()
                                                                      +" "+match.getMatchInfo().getMatchDesc()))
                            ));
            return matchesMap;
        }

        return null;
    }

    private boolean isLiveMatches(String matchType, ArrayList<Match> matches) {

        if(matchType.equalsIgnoreCase("upcoming")){
            return true;
        }

        return matches .stream()
                .anyMatch(match -> match.getMatchInfo().getState()
                        .equalsIgnoreCase("In Progress"));
    }
}
