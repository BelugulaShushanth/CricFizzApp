package com.cricFizzApp.services;

import com.cricFizzApp.bean.alert.AlertDetails;
import com.cricFizzApp.utils.CricUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class KafkaAsyncProducerService {

    @Value("${spring.kafka.topic-name}")
    private String topic;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private CricUtils cricUtils;

    @Async
    public void publishMessage(AlertDetails alertDetails){
        try {
            kafkaTemplate.send(topic,alertDetails.getAlertId(),cricUtils.objectMapper().writeValueAsString(alertDetails));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
