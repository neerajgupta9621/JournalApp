package com.edigest.my.first.project.service;

import com.edigest.my.first.project.RequestAI.AIRequest;
import com.edigest.my.first.project.api.response.AIResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class AIService {

    @Value("${ai.api.url}")
    private String apiUrl;

    @Value("${ai.api.key}")
    private String apiKey;

    private final WebClient webClient = WebClient.builder().build();

    public String ask(String prompt) {

        AIRequest request = new AIRequest();

        // Model
        request.setModel("openai/gpt-4o-mini");

        // Message
        AIRequest.Message message = new AIRequest.Message();
        message.setRole("user");
        message.setContent(prompt);

        request.setMessages(List.of(message));

        try {

            AIResponse response = webClient.post()
                    .uri(apiUrl)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header("HTTP-Referer", "https://journalapp-1-ek5e.onrender.com")
                    .header("X-Title", "Journal App")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(AIResponse.class)
                    .block();

            if (response != null
                    && response.getChoices() != null
                    && !response.getChoices().isEmpty()) {

                return response.getChoices()
                        .get(0)
                        .getMessage()
                        .getContent();
            }

            return "No AI response.";

        } catch (Exception e) {

            e.printStackTrace();

            return "AI Error : " + e.getMessage();
        }
    }
}