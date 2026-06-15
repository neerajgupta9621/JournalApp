package com.edigest.my.first.project.controller;

import com.edigest.my.first.project.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
public class AIController {

    @Autowired
    private AIService aiService;

    @PostMapping("/chat")
    public String chat(@RequestBody String prompt) {
        return aiService.ask(prompt);
    }
}