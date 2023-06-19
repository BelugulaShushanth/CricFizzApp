package com.livecricketscores.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Collections;

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
}
