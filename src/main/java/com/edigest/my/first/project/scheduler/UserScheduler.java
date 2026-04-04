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
import java.util.List;

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

    // 👉 Sunday 9 AM
     @Scheduled(cron = "0 0 9 ? * SUN")
    // 👉 Testing ke liye (har minute)
    //@Scheduled(cron = "0 * * ? * *")
    public void fetchUsersAndSendSaMail() {

        List<User> users = userRepository.getUserForSA();

        for (User user : users) {

            if (user.getJournalEntries() == null) continue;

            List<JournalEntry> entries = user.getJournalEntries();

            int happy = 0, sad = 0, angry = 0;

            for (JournalEntry entry : entries) {

                if (entry.getDate().isAfter(LocalDateTime.now().minusDays(7))) {

                    if (entry.getSentiment() == Sentiment.HAPPY) happy++;
                    else if (entry.getSentiment() == Sentiment.SAD) sad++;
                    else angry++;
                }
            }

            // 👉 find max
            String finalSentiment = "NEUTRAL";

            if (happy >= sad && happy >= angry) finalSentiment = "HAPPY";
            else if (sad >= happy && sad >= angry) finalSentiment = "SAD";
            else finalSentiment = "ANGRY";

            SentimentData data = SentimentData.builder()
                    .email(user.getEmail())
                    .sentiment("Sentiment for last 7 days: " + finalSentiment)
                    .build();

            try {
                kafkaTemplate.send("weekly-sentiments", data.getEmail(), data);
                System.out.println("Sent to Kafka: " + data);

            } catch (Exception e) {
                // 👉 fallback email
                System.out.println("Kafka failed, sending email");

                emailService.sendEmail(
                        data.getEmail(),
                        "Weekly Sentiment Report",
                        data.getSentiment()
                );
            }
        }
    }

    // 🧹 Cache clear
    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache() {
        appCache.init();
    }
}