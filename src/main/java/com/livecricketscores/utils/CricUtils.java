package com.livecricketscores.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.livecricketscores.bean.matchesList.Matches;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class CricUtils {

    @Value("${host.cricbuzz}")
    private String cricbuzzHost;
    @Value("${rapidapi.Key}")
    private String rapiAPIKey;

    public HttpHeaders getHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.add("X-RapidAPI-Key", rapiAPIKey);
        httpHeaders.add("X-RapidAPI-Host", cricbuzzHost);
        return httpHeaders;
    }

    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
        return objectMapper;
    }

    public void mapMatchStartDateinMillsToMins(Matches matches) {

        matches.getTypeMatches()
                .stream()
                .filter(typeMatch -> typeMatch != null && typeMatch.getSeriesMatches() != null)
                .forEach(match -> match.getSeriesMatches()
                        .stream()
                        .filter(seriesMatch -> seriesMatch != null && seriesMatch.getSeriesAdWrapper() != null && seriesMatch.getSeriesAdWrapper().getMatches() != null)
                        .forEach(seriesMatch -> seriesMatch.getSeriesAdWrapper().getMatches()
                                .stream()
                                .filter(match1 -> match1 != null && match1.getMatchInfo() != null && match1.getMatchInfo().getStartDate() != null)
                                .map(match1 -> {
                                    match1.getMatchInfo().setStartDate(mapMillsToMins(match1.getMatchInfo().getStartDate()));
                                    return match1;
                                })
                                .forEach(match1 -> match1.getMatchInfo().getStartDate())
                        ));
    }

    private String mapMillsToMins(String startMills) {
        long currentMills = new Date().getTime();
        return String.valueOf(TimeUnit.MILLISECONDS.toHours(Long.parseLong(startMills) - currentMills));
    }
}
