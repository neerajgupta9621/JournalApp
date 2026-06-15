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
        request.setModel("openai/gpt-4o-mini");

        AIRequest.Message message = new AIRequest.Message();
        message.setRole("user");
        message.setContent(prompt);

        request.setMessages(List.of(message));

        AIResponse response = webClient.post()
                .uri(apiUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .header("HTTP-Referer", "http://localhost:8081")
                .header("X-Title", "Journal App")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AIResponse.class)
                .block();

        if (response != null &&
                response.getChoices() != null &&
                !response.getChoices().isEmpty()) {

            return response.getChoices()
                    .get(0)
                    .getMessage()
                    .getContent();
        }

        return "No AI response";
    }
}