package com.livecricketscores.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CricUtils {
    public HttpHeaders getHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.add("X-RapidAPI-Key", "f8e2a72987msh135110eef99c8ddp1455fcjsn550b1995ac99");
        httpHeaders.add("X-RapidAPI-Host", "cricbuzz-cricket.p.rapidapi.com");
        return httpHeaders;
    }
}
