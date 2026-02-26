package com.edigest.my.first.project.controller;

import com.edigest.my.first.project.entity.User;
import com.edigest.my.first.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public ResponseEntity<Map<String, Object>> healthCheck() {

        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP ✅🥰😎");
        response.put("message", "Application is running successfully");
        response.put("timestamp", LocalDateTime.now());
        response.put("service", "Journal Management System");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user){

        boolean saved = userService.saveNewUser(user);

        if(saved){
            return ResponseEntity.ok("User saved successfully");
        }else{
            return ResponseEntity.status(500).body("User NOT saved");
        }
    }
}
