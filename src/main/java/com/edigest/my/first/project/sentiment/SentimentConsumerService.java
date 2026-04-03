package com.edigest.my.first.project.sentiment;

import com.edigest.my.first.project.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SentimentConsumerService {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "weekly-sentiments", groupId = "weekly-sentiment-group")
    public void consume(SentimentData data) {

        emailService.sendEmail(
                data.getEmail(),
                "Sentiment Report 📊",
                data.getSentiment()
        );

        System.out.println("Email sent to: " + data.getEmail());
    }
}
