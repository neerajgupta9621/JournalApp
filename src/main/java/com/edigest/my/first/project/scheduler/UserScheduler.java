package com.edigest.my.first.project.scheduler;

import com.edigest.my.first.project.cache.AppCache;
import com.edigest.my.first.project.entity.JournalEntry;
import com.edigest.my.first.project.entity.User;
import com.edigest.my.first.project.enums.Sentiment;
import com.edigest.my.first.project.repository.UserRepositoryImpl;
import com.edigest.my.first.project.sentiment.SentimentData;
import com.edigest.my.first.project.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private AppCache appCache;

    @Autowired
    private KafkaTemplate<String, SentimentData> kafkaTemplate;

    // 👉 Test ke liye manually call kar sakta hai
    @Scheduled(cron = "0 0 9 ? * SUN") //Har Sunday 9 bje email send hoga
   //@Scheduled(cron = "0 * * ? * *")// hr minute run hoga

    public void fetchUsersAndSendSaMail() {

        List<User> users = userRepository.getUserForSA();

        for (User user : users) {

            List<JournalEntry> journalEntries = user.getJournalEntries();

            // 🔥 NULL HANDLE FIX (main issue solved)
            List<Sentiment> sentiments = journalEntries.stream()
                    .filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                    .map(x -> x.getSentiment() != null ? x.getSentiment() : Sentiment.ANGRY)
                    .collect(Collectors.toList());

            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();

            for (Sentiment sentiment : sentiments) {
                sentimentCounts.put(sentiment,
                        sentimentCounts.getOrDefault(sentiment, 0) + 1);
            }

            // 🔥 Most frequent sentiment find
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;

            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            // 🔥 FINAL SAFE VALUE
            String finalSentiment = (mostFrequentSentiment != null)
                    ? mostFrequentSentiment.toString()
                    : "NEUTRAL";

            // 🔥 Kafka message
            SentimentData sentimentData = SentimentData.builder()
                    .email(user.getEmail())
                    .sentiment("Sentiment for last 7 days: " + finalSentiment)
                    .build();

            kafkaTemplate.send("weekly-sentiments", sentimentData.getEmail(), sentimentData);

            System.out.println("Sent to Kafka: " + sentimentData);
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache() {
        appCache.init();
    }
}