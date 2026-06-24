package com.edigest.my.first.project.controller;
import com.edigest.my.first.project.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AIController {

    @Autowired
    private AIService aiService;

    @PostMapping("/chat")
    public String chat(@RequestBody Map<String, String> body) {

        String prompt = body.get("prompt");

        return aiService.ask(prompt);
    }
}