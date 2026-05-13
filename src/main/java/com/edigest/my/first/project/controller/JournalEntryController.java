package com.edigest.my.first.project.controller;

import com.edigest.my.first.project.entity.JournalEntry;
import com.edigest.my.first.project.entity.User;
import com.edigest.my.first.project.service.JournalEntryService;
import com.edigest.my.first.project.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
@Tag(name = "Journal APIs")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    // ✅ GET ALL JOURNALS
    @GetMapping
    @Operation(summary = "Get all journal entries of a user")
    public ResponseEntity<?> getAllJournalEntriesOfUser() {

        try {

            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();

            String userName = authentication.getName();

            System.out.println("USERNAME: " + userName);

            User user = userService.findByUserName(userName);

            // ✅ NULL CHECK
            if (user == null) {

                return new ResponseEntity<>(
                        "User Not Found",
                        HttpStatus.NOT_FOUND
                );
            }

            List<JournalEntry> all = user.getJournalEntries();

            return new ResponseEntity<>(all, HttpStatus.OK);

        } catch (Exception e) {

            e.printStackTrace();

            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // ✅ CREATE JOURNAL
    @PostMapping
    public ResponseEntity<?> createEntry(
            @RequestBody JournalEntry myEntry) {

        try {

            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();

            String userName = authentication.getName();

            System.out.println("USERNAME: " + userName);

            User user = userService.findByUserName(userName);

            // ✅ NULL CHECK
            if (user == null) {

                return new ResponseEntity<>(
                        "User Not Found",
                        HttpStatus.NOT_FOUND
                );
            }

            journalEntryService.saveEntry(myEntry, userName);

            return new ResponseEntity<>(
                    myEntry,
                    HttpStatus.CREATED
            );

        } catch (Exception e) {

            e.printStackTrace();

            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // ✅ GET BY ID
    @GetMapping("id/{myId}")
    public ResponseEntity<?> getJournalEntryById(
            @PathVariable String myId) {

        try {

            ObjectId objectId = new ObjectId(myId);

            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();

            String userName = authentication.getName();

            User user = userService.findByUserName(userName);

            if (user == null) {

                return new ResponseEntity<>(
                        "User Not Found",
                        HttpStatus.NOT_FOUND
                );
            }

            List<JournalEntry> collect =
                    user.getJournalEntries()
                            .stream()
                            .filter(x -> x.getId().equals(objectId))
                            .collect(Collectors.toList());

            if (!collect.isEmpty()) {

                Optional<JournalEntry> journalEntry =
                        journalEntryService.findById(objectId);

                if (journalEntry.isPresent()) {

                    return new ResponseEntity<>(
                            journalEntry.get(),
                            HttpStatus.OK
                    );
                }
            }

            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );

        } catch (Exception e) {

            e.printStackTrace();

            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // ✅ DELETE
    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(
            @PathVariable String myId) {

        try {

            ObjectId objectId = new ObjectId(myId);

            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();

            String userName = authentication.getName();

            boolean removed =
                    journalEntryService.deleteById(objectId, userName);

            if (removed) {

                return new ResponseEntity<>(
                        HttpStatus.NO_CONTENT
                );
            }

            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );

        } catch (Exception e) {

            e.printStackTrace();

            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // ✅ UPDATE
    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateJournalById(
            @PathVariable String myId,
            @RequestBody JournalEntry newEntry) {

        try {

            ObjectId objectId = new ObjectId(myId);

            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();

            String userName = authentication.getName();

            User user = userService.findByUserName(userName);

            if (user == null) {

                return new ResponseEntity<>(
                        "User Not Found",
                        HttpStatus.NOT_FOUND
                );
            }

            List<JournalEntry> collect =
                    user.getJournalEntries()
                            .stream()
                            .filter(x -> x.getId().equals(objectId))
                            .toList();

            if (!collect.isEmpty()) {

                Optional<JournalEntry> journalEntry =
                        journalEntryService.findById(objectId);

                if (journalEntry.isPresent()) {

                    JournalEntry old = journalEntry.get();

                    old.setTitle(
                            newEntry.getTitle() != null &&
                                    !newEntry.getTitle().isEmpty()
                                    ? newEntry.getTitle()
                                    : old.getTitle()
                    );

                    old.setContent(
                            newEntry.getContent() != null &&
                                    !newEntry.getContent().isEmpty()
                                    ? newEntry.getContent()
                                    : old.getContent()
                    );

                    journalEntryService.saveEntry(old);

                    return new ResponseEntity<>(
                            old,
                            HttpStatus.OK
                    );
                }
            }

            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );

        } catch (Exception e) {

            e.printStackTrace();

            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}