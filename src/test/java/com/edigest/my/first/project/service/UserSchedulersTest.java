package com.edigest.my.first.project.service;

import com.edigest.my.first.project.scheduler.UserScheduler;
import com.edigest.my.first.project.sentiment.SentimentData;
import com.edigest.my.first.project.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserSchedulersTest {

    @Autowired
    private UserScheduler userScheduler;

    // ✅ Kafka ko mock kar diya
    @MockBean
    private KafkaTemplate<String, SentimentData> kafkaTemplate;

    // ✅ Email ko mock kar diya
    @MockBean
    private EmailService emailService;

    @Test
    public void testFetchUsersAndSendSaMail(){
        userScheduler.fetchUsersAndSendSaMail();
        }
}