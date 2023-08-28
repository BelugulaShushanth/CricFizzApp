package com.cricFizzApp.services;

import com.cricFizzApp.bean.alert.AlertDetails;
import com.cricFizzApp.repository.AlertsRepository;
import com.cricFizzApp.utils.CricUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class KafkaAsyncProducerService {

    private final Logger logger = LoggerFactory.getLogger(KafkaAsyncProducerService.class);

    @Value("${spring.kafka.topic-name}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private AlertsRepository alertsRepository;

    @Autowired
    private CricUtils cricUtils;

    @Async
    public void publishMessage(AlertDetails alertDetails){
        try {
            String key = alertDetails.getAlertId();
            String payload = cricUtils.objectMapper().writeValueAsString(alertDetails);
            ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(topic, key, payload);
            logger.info("Alert Published: Done: {} AlertId: {} Committed-Offset: {}",listenableFuture.isDone(),key,
                                            listenableFuture.get().getRecordMetadata().offset());
        } catch (Exception e) {
            logger.error("Exception in KafkaAsyncProducerService:publishMessage() : {}",e.getMessage());
        }
    }
}
