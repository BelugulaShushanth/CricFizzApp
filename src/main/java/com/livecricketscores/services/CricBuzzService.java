package com.livecricketscores.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.livecricketscores.bean.Matches;
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

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
    private String getMatches;
    @Value("${cricbuzz.sample.data.location}")
    private String sampleFileLoc;

    public Matches getMatches(String event){
        logger.info("Started getMatches");
        Matches matches = null;
        try{
            HttpHeaders httpHeaders = cricUtils.getHeaders();
            String cricbuzzURL = "https://" + cricbuzzHost + "/" + getMatches + "/" + event;
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
                StringBuilder matchesJson = new StringBuilder();
                List<String> list = Files.lines(Paths.get(sampleFileLoc))
                        .collect(Collectors.toList());
                for (String s : list){
                    matchesJson.append(s);
                }
                JsonNode jsonNode = objectMapper.readTree(matchesJson.toString());
                matches = objectMapper.treeToValue(jsonNode, Matches.class);
            }

            if(event.equalsIgnoreCase("upcoming")) {
                mapMatchStartDateinMillsToMins(matches);
            }
        }
        catch (Exception e){
            logger.error("Exception in CricBuzzService:getMatches", e);
        }
        return matches;
    }

    private void mapMatchStartDateinMillsToMins(Matches matches) {

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
