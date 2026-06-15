package com.edigest.my.first.project.api.response;

import lombok.Data;
import java.util.List;

@Data
public class AIResponse {

    private List<Choice> choices;

    @Data
    public static class Choice {
        private Message message;
    }

    @Data
    public static class Message {
        private String content;
    }
}