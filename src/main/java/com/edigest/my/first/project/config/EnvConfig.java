package com.edigest.my.first.project.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class EnvConfig {

    @PostConstruct
    public void loadEnv() {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        System.setProperty("GOOGLE_CLIENT_ID", dotenv.get("GOOGLE_CLIENT_ID"));
        System.setProperty("GOOGLE_CLIENT_SECRET", dotenv.get("GOOGLE_CLIENT_SECRET"));
        System.setProperty("MONGODB_URI", dotenv.get("MONGODB_URI"));
        System.setProperty("REDIS_URI", dotenv.get("REDIS_URI"));
        System.setProperty("MAIL_USERNAME", dotenv.get("MAIL_USERNAME"));
        System.setProperty("MAIL_PASSWORD", dotenv.get("MAIL_PASSWORD"));
        System.setProperty("KAFKA_SERVERS", dotenv.get("KAFKA_SERVERS"));
        System.setProperty("WEATHER_API_KEY", dotenv.get("WEATHER_API_KEY"));
    }
}