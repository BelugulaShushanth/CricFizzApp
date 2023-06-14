package com.livecricketscores.config;

import com.fasterxml.jackson.databind.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class RestTemplateConfig {
   @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setObjectMapper(getObjectMapper());
        messageConverters.add(messageConverter);
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", Charset.forName("UTF-8"))));
        messageConverters.add(stringHttpMessageConverter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
        return mapper;
    }
}
