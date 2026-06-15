package com.edigest.my.first.project.RequestAI;

import lombok.Data;
import java.util.List;

@Data
public class AIRequest {

    private String model;
    private List<Message> messages;

    @Data
    public static class Message {
        private String role;
        private String content;
    }
}