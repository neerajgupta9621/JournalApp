package com.edigest.my.first.project.sentiment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SentimentProducerService {

    @Autowired
    private KafkaTemplate<String, SentimentData> kafkaTemplate;

    public void send(SentimentData data) {

        kafkaTemplate.send("weekly-sentiments", data.getEmail(), data);

        System.out.println("Message sent to Kafka: " + data);
    }
}