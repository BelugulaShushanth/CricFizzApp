package com.cricFizzApp.utils;

import com.fasterxml.jackson.databind.*;
import com.cricFizzApp.bean.commentary.*;
import com.cricFizzApp.bean.matchLeanBack.InningsScoreList;
import com.cricFizzApp.bean.matchLeanBack.MatchLeanBack;
import com.cricFizzApp.bean.matchScoreCard.MatchScoreCard;
import com.cricFizzApp.bean.matchScoreCard.ScoreCard;
import com.cricFizzApp.bean.matchScoreCard.batsmen.BatTeamDetails;
import com.cricFizzApp.bean.matchesList.Matches;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CricUtils {

    private final Logger logger = LoggerFactory.getLogger(CricUtils.class);

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
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
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
                                    match1.getMatchInfo().setStartDate(mapMillsToDateTime(match1.getMatchInfo().getStartDate()));
                                    return match1;
                                })
                                .forEach(match1 -> match1.getMatchInfo().getStartDate())
                        ));
    }

    private String mapMillsToMins(String startMills) {
        long currentMills = new Date().getTime();
        return String.valueOf(TimeUnit.MILLISECONDS.toHours(Long.parseLong(startMills) - currentMills));
    }

    public void mapMatchStartDateinMillsToDate(MatchScoreCard matchScoreCard){
        matchScoreCard.getMatchHeader().setMatchStartTimestamp(mapMillsToDateTime(matchScoreCard.getMatchHeader().getMatchStartTimestamp()));
    }
    public void mapMatchStartDateinMillsToDate(MatchLeanBack matchLeanBack){
        matchLeanBack.getMatchHeader().setMatchStartTimestamp(mapMillsToDateTime(matchLeanBack.getMatchHeader().getMatchStartTimestamp()));
    }

    private String mapMillsToDateTime(String matchStartTimestamp) {
        String dateTime = null;
        Instant instant = Instant.ofEpochMilli(Long.parseLong(matchStartTimestamp));
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.of("Asia/Kolkata"));
        dateTime = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).format(zonedDateTime);
        dateTime = dateTime.replace(":00 ","");
        return dateTime;
    }

    public String readJsonFile(String jsonFileLoc){
        StringBuilder jsonString = new StringBuilder();
        try {
            List<String> list = Files.lines(Paths.get(jsonFileLoc))
                    .collect(Collectors.toList());
            for (String s : list){
                jsonString.append(s);
            }
        } catch (IOException e) {
            logger.error("Exception in CricUtils:readJsonFile", e);
        }
        return jsonString.toString();
    }

    public void sortInnings(MatchLeanBack matchLeanBack) {
        List<InningsScoreList> inningsScoreLists = matchLeanBack.getMiniscore().getMatchScoreDetails().getInningsScoreList()
                .stream()
                .sorted((i1, i2) -> i1.compare(i1, i2))
                .collect(Collectors.toList());
        matchLeanBack.getMiniscore().getMatchScoreDetails().setInningsScoreList((ArrayList<InningsScoreList>) inningsScoreLists);
    }

    public void mapInningsIdWithTeamName(MatchScoreCard matchScoreCard) {
        List<ScoreCard> scoreCardList = matchScoreCard.getScoreCard()
                .stream()
                .map(scoreCard -> {
                    int inningsId = scoreCard.getInningsId();
                    BatTeamDetails batTeamDetails = scoreCard.getBatTeamDetails();
                    switch (inningsId) {
                        case 1:
                            batTeamDetails.setBatTeamShortName(batTeamDetails.getBatTeamName() + " " + inningsId + "st Innings");
                            break;
                        case 2:
                            batTeamDetails.setBatTeamShortName(batTeamDetails.getBatTeamName() + " " + inningsId + "nd Innings");
                            break;
                        case 3:
                            batTeamDetails.setBatTeamShortName(batTeamDetails.getBatTeamName() + " " + inningsId + "rd Innings");
                            break;
                        case 4:
                            batTeamDetails.setBatTeamShortName(batTeamDetails.getBatTeamName() + " " + inningsId + "th Innings");
                            break;
                    }
                    return scoreCard;
                }).collect(Collectors.toList());
        matchScoreCard.setScoreCard((ArrayList<ScoreCard>) scoreCardList);
    }

    public void sortMatchScoreCard(MatchScoreCard matchScoreCard) {
        List<ScoreCard> scoreCardList = matchScoreCard.getScoreCard()
                .stream()
                .sorted((sc1, sc2) -> sc1.compare(sc1, sc2))
                .collect(Collectors.toList());
        matchScoreCard.setScoreCard((ArrayList<ScoreCard>) scoreCardList);
    }

    public void modifyCommentryData(MatchCommentary matchCommentary) {
        List<CommentaryList> commentaryLists = matchCommentary.getCommentaryList()
                .stream()
                .map(this::mapCommFormat)
                .collect(Collectors.toList());
        matchCommentary.setCommentaryList((ArrayList<CommentaryList>) commentaryLists);
    }

    private CommentaryList mapCommFormat(CommentaryList comm) {
        CommentaryFormats commentaryFormats = comm.getCommentaryFormats();
        Bold bold = commentaryFormats.getBold();
        Italic italic = commentaryFormats.getItalic();
        if(bold != null && bold.getFormatId() != null && bold.getFormatValue() != null) {
            for (int i = 0; i < bold.getFormatId().size(); i++) {
                String formatId = bold.getFormatId().get(i);
                String formatValue = bold.getFormatValue().get(i);
                if (comm.getCommText().contains(formatId)) {
                    String replaced = comm.getCommText().replace(formatId, "<b>" + formatValue + "</b>");
                    comm.setCommText(replaced);
                }
            }
        }
        if(italic != null && italic.getFormatId() != null && italic.getFormatValue() != null) {
            for (int i = 0; i < italic.getFormatId().size(); i++) {
                String formatId = italic.getFormatId().get(i);
                String formatValue = italic.getFormatValue().get(i);
                if (comm.getCommText().contains(formatId)) {
                    String replaced = comm.getCommText().replace(formatId, "<i>" + formatValue + "</i>");
                    comm.setCommText(replaced);
                }
            }
        }
        if(comm.getCommText().contains("\\n \\n")){
            comm.setCommText(comm.getCommText().replace("\\n \\n","\\n"));
        }
        if(comm.getCommText().contains("\\n \\n-")){
            comm.setCommText(comm.getCommText().replace("\\n \\n-","\\n-"));
        }
        if(comm.getCommText().contains("\\n")){
            comm.setCommText(comm.getCommText().replace("\\n","<br>"));
        }
        if(comm.getCommText().contains("\\n-")){
            comm.setCommText(comm.getCommText().replace("\\n-","<br>-"));
        }

        return comm;
    }
}