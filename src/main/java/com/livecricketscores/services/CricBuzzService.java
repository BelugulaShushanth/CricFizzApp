package com.livecricketscores.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.livecricketscores.bean.matchScoreCard.MatchScoreCard;
import com.livecricketscores.bean.matchesList.Matches;
import com.livecricketscores.utils.CricUtils;
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

    @Value("${endpoint.get-match-scorecard}")
    private String matchScoreCardEndpoint;

    @Value("${cricbuzz.sample.matchesScoreCard.data.location}")
    private String matchScoreCardLoc;

    public Matches getMatches(String event){
        logger.info("Started getMatches");
        Matches matches = null;
        try{
            HttpHeaders httpHeaders = cricUtils.getHeaders();
            String cricbuzzURL = "https://" + cricbuzzHost + "/" + matchesEndpont + "/" + event;
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
            logger.error("Exception in CricBuzzService:getMatches", e);
        }
        return matches;
    }

    public MatchScoreCard getMatchesScoreCard(String matchId){
        logger.info("Started getMatchesScoreCard");
        MatchScoreCard matcheScoreCard = null;
        try{
            HttpHeaders httpHeaders = cricUtils.getHeaders();
            String cricbuzzURL = "https://" + cricbuzzHost + "/" + matchScoreCardEndpoint + "/" + matchId + "/hscard";
            logger.info("cricbuzzURL: {}", cricbuzzURL);
            logger.info("callCricbuzz: {}", callCricbuzz);
            HttpEntity<String> httpEntity = new HttpEntity<>(cricbuzzURL,httpHeaders);
            if(callCricbuzz.equalsIgnoreCase("true")){
                ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(cricbuzzURL, HttpMethod.GET, httpEntity, JsonNode.class);
                if (responseEntity.getBody() != null && responseEntity.getStatusCode().is2xxSuccessful()){
                    JsonNode jsonNode = responseEntity.getBody();
                    matcheScoreCard = objectMapper.treeToValue(jsonNode, MatchScoreCard.class);
                }
            }
            else{
                matcheScoreCard = cricUtils.objectMapper().treeToValue(cricUtils.objectMapper().readTree(cricUtils.readJsonFile(matchScoreCardLoc)), MatchScoreCard.class);
            }
            cricUtils.mapMatchStartDateinMillsToDate(matcheScoreCard);
        }
        catch (Exception e){
            logger.error("Exception in CricBuzzService:getMatches", e);
        }
        return matcheScoreCard;
    }


}
