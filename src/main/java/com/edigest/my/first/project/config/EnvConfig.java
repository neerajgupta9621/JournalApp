package com.edigest.my.first.project.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;

@Configuration
public class EnvConfig {

    @PostConstruct
    public void loadEnv() {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        setIfPresent("GOOGLE_CLIENT_ID", dotenv);
        setIfPresent("GOOGLE_CLIENT_SECRET", dotenv);
        setIfPresent("MONGODB_URI", dotenv);
        setIfPresent("REDIS_URI", dotenv);
        setIfPresent("MAIL_USERNAME", dotenv);
        setIfPresent("MAIL_PASSWORD", dotenv);
        setIfPresent("KAFKA_SERVERS", dotenv);
        setIfPresent("WEATHER_API_KEY", dotenv);
        System.out.println("Mongo URI: " + System.getProperty("MONGODB_URI"));
    }

    private void setIfPresent(String key, Dotenv dotenv) {
        String value = dotenv.get(key);
        if (value != null && !value.isEmpty()) {
            System.setProperty(key, value);
            System.out.println(key + " loaded ✅");
        } else {
            System.out.println(key + " NOT FOUND ❌");
        }
    }
}