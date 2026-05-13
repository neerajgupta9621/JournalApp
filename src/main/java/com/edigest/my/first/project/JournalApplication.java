package com.edigest.my.first.project;

import com.edigest.my.first.project.scheduler.UserScheduler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

	// ✅ Mongo Transaction Manager
	@Bean
	public PlatformTransactionManager falana(MongoDatabaseFactory dbfactory){
		return new MongoTransactionManager(dbfactory);
	}

	// ✅ RestTemplate
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	// 🔥 FINAL FIX (IMPORTANT)
	@Bean
	public CommandLineRunner run(UserScheduler scheduler) {
		return args -> {
			scheduler.fetchUsersAndSendSaMail(); // ✅ DB se data lega
			System.out.println("🔥 Scheduler Triggered");
		};
	}
}