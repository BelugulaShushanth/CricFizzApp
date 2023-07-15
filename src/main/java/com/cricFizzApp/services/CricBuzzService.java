package com.cricFizzApp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cricFizzApp.bean.commentary.MatchCommentary;
import com.cricFizzApp.bean.matchLeanBack.MatchLeanBack;
import com.cricFizzApp.bean.matchScoreCard.MatchScoreCard;
import com.cricFizzApp.bean.matchesList.Matches;
import com.cricFizzApp.utils.CricUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CricBuzzService {

    private final Logger logger = LoggerFactory.getLogger(CricBuzzService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CricUtils cricUtils;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${host.cricbuzz}")
    private String cricbuzzHost;

    @Value("${cricbuzz.call-api}")
    private String callCricbuzz;

    @Value("${endpoint.get-matches}")
    private String matchesEndpont;

    @Value("${cricbuzz.sample.matchesList.data.location}")
    private String matchesFileLoc;

    @Value("${endpoint.get-match}")
    private String matchEndpoint;

    @Value("${endpoint.get-match-scorecard}")
    private String matchScoreCardEndpoint;

    @Value("${cricbuzz.sample.matchesScoreCard.data.location}")
    private String matchScoreCardLoc;

    @Value("${endpoint.get-match-leanback}")
    private String matchLeanBackEndpoint;

    @Value("${cricbuzz.sample.matchesLeanBack.data.location}")
    private String matchLeanBackLocation;

    @Value("${endpoint.get-match-commentary}")
    private String matchCommentaryEndpoint;

    @Value("${cricbuzz.sample.matchCommentary.data.location}")
    private String matchCommentaryLocation;

    public Matches getMatches(String event){
        logger.info("Started getMatches");
        Matches matches = null;
        try{
            HttpHeaders httpHeaders = cricUtils.getHeaders();
            String cricbuzzURL = "https://" + cricbuzzHost + matchesEndpont + event;
            logger.info("cricbuzzURL: {}", cricbuzzURL);
            logger.info("callCricbuzz: {}", callCricbuzz);
            HttpEntity<String> httpEntity = new HttpEntity<>(cricbuzzURL,httpHeaders);
            if(callCricbuzz.equalsIgnoreCase("true")){
                ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(cricbuzzURL, HttpMethod.GET, httpEntity, JsonNode.class);
                if (responseEntity.getBody() != null && responseEntity.getStatusCode().is2xxSuccessful()){
                    JsonNode jsonNode = responseEntity.getBody();
                    matches = objectMapper.treeToValue(jsonNode, Matches.class);
                }
            }
            else{
                matches = objectMapper.treeToValue(objectMapper.readTree(cricUtils.readJsonFile(matchesFileLoc)), Matches.class);
            }

            if(event.equalsIgnoreCase("upcoming")) {
                cricUtils.mapMatchStartDateinMillsToMins(matches);
            }
        }
        catch (Exception e){
            if(e.getMessage().contains("429")){
                System.out.println("Limit Exceeded");
            }
            logger.error("Exception in CricBuzzService:getMatches", e);
        }
        return matches;
    }

    public MatchScoreCard getMatchesScoreCard(String matchId){
        logger.info("Started getMatchesScoreCard");
        MatchScoreCard matchScoreCard = null;
        try{
            HttpHeaders httpHeaders = cricUtils.getHeaders();
            String cricbuzzURL = "https://" + cricbuzzHost + matchEndpoint + matchId + matchScoreCardEndpoint;
            logger.info("cricbuzzURL: {}", cricbuzzURL);
            logger.info("callCricbuzz: {}", callCricbuzz);
            HttpEntity<String> httpEntity = new HttpEntity<>(cricbuzzURL,httpHeaders);
            if(callCricbuzz.equalsIgnoreCase("true")){
                ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(cricbuzzURL, HttpMethod.GET, httpEntity, JsonNode.class);
                if (responseEntity.getBody() != null && responseEntity.getStatusCode().is2xxSuccessful()){
                    JsonNode jsonNode = responseEntity.getBody();
                    matchScoreCard = objectMapper.treeToValue(jsonNode, MatchScoreCard.class);
                }
            }
            else{
                matchScoreCard = cricUtils.objectMapper().treeToValue(cricUtils.objectMapper().readTree(cricUtils.readJsonFile(matchScoreCardLoc)), MatchScoreCard.class);
            }
            if(matchScoreCard != null) {
                cricUtils.mapMatchStartDateinMillsToDate(matchScoreCard);
                cricUtils.sortMatchScoreCard(matchScoreCard);
                cricUtils.mapInningsIdWithTeamName(matchScoreCard);
            }
        }
        catch (Exception e){
            logger.error("Exception in CricBuzzService:getMatches", e);
        }
        return matchScoreCard;
    }

    public MatchLeanBack getMatchLeanBack(String matchId){
        logger.info("Started getMatchLeanBack");
        MatchLeanBack matchLeanBack = null;
        try{
            HttpHeaders httpHeaders = cricUtils.getHeaders();
            String cricbuzzURL = "https://" + cricbuzzHost + matchEndpoint + matchId + matchLeanBackEndpoint;
            logger.info("cricbuzzURL: {}", cricbuzzURL);
            logger.info("callCricbuzz: {}", callCricbuzz);
            HttpEntity<String> httpEntity = new HttpEntity<>(cricbuzzURL,httpHeaders);
            if(callCricbuzz.equalsIgnoreCase("true")){
                ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(cricbuzzURL, HttpMethod.GET, httpEntity, JsonNode.class);
                if (responseEntity.getBody() != null && responseEntity.getStatusCode().is2xxSuccessful()){
                    JsonNode jsonNode = responseEntity.getBody();
                    matchLeanBack = objectMapper.treeToValue(jsonNode, MatchLeanBack.class);
                }
            }
            else{
                matchLeanBack = cricUtils.objectMapper().treeToValue(cricUtils.objectMapper().readTree(cricUtils.readJsonFile(matchLeanBackLocation)), MatchLeanBack.class);
            }
            if(matchLeanBack != null) {
                cricUtils.mapMatchStartDateinMillsToDate(matchLeanBack);
                cricUtils.sortInnings(matchLeanBack);
            }
        }
        catch (Exception e){
            logger.error("Exception in CricBuzzService:getMatches", e);
        }
        return matchLeanBack;
    }

    public MatchCommentary getMatchCommentary(String matchId){
        logger.info("Started getMatchCommentary");
        MatchCommentary matchCommentary = null;
        try{
            HttpHeaders httpHeaders = cricUtils.getHeaders();
            String cricbuzzURL = "https://" + cricbuzzHost + matchEndpoint + matchId + matchCommentaryEndpoint;
            logger.info("cricbuzzURL: {}", cricbuzzURL);
            logger.info("callCricbuzz: {}", callCricbuzz);
            HttpEntity<String> httpEntity = new HttpEntity<>(cricbuzzURL,httpHeaders);
            if(callCricbuzz.equalsIgnoreCase("true")){
                ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(cricbuzzURL, HttpMethod.GET, httpEntity, JsonNode.class);
                if (responseEntity.getBody() != null && responseEntity.getStatusCode().is2xxSuccessful()){
                    JsonNode jsonNode = responseEntity.getBody();
                    matchCommentary = objectMapper.treeToValue(jsonNode, MatchCommentary.class);
                }
            }
            else{
                matchCommentary = cricUtils.objectMapper().treeToValue(cricUtils.objectMapper().readTree(cricUtils.readJsonFile(matchCommentaryLocation)), MatchCommentary.class);
            }
            if(matchCommentary != null) {
                cricUtils.modifyCommentryData(matchCommentary);
            }
        }
        catch (Exception e){
            logger.error("Exception in CricBuzzService:getMatches", e);
        }
        return matchCommentary;
    }


}
