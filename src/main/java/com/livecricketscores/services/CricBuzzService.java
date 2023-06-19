package com.livecricketscores.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.livecricketscores.bean.Matches;
import com.livecricketscores.utils.CricUtils;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CricBuzzService {

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
    @Value("${endpoint.get-live-matches}")
    private String getLiveMatches;
    @Value("${cricbuzz.sample.data.location}")
    private String sampleFileLoc;

    public Matches getLiveMatches(){
        Matches matches = null;
        try{
            HttpHeaders httpHeaders = cricUtils.getHeaders();
            String cricbuzzURL = "https://" + cricbuzzHost + getLiveMatches;
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
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return matches;
    }
}
