package com.edigest.my.first.project.controller;

import com.edigest.my.first.project.cache.AppCache;
import com.edigest.my.first.project.dto.UserDTO;
import com.edigest.my.first.project.entity.User;
import com.edigest.my.first.project.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin APIs")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppCache appCache;
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userService.getAll();

            if (users == null || users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body("No users found");
            }

            return ResponseEntity.ok(users);

        } catch (Exception e) {
            e.printStackTrace(); // logs real error in console

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to load users: " + e.getMessage());
        }
    }


    @PostMapping("/create-admin-user")
    public void createUser(@RequestBody User user){
        userService.saveAdmin(user);
    }

    @GetMapping("clear-app-cache")
    public void clearAppCache(){
       appCache.init();
    }

}
