package com.uninavigatorspring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uninavigatorspring.model.User;
import com.uninavigatorspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody User user) throws JsonProcessingException {
        User authenticatedUser = userService.authenticateUser(user.getUsername(), user.getPassword());
        if (authenticatedUser != null) {
            System.out.println("Authenticated User: " + new ObjectMapper().writeValueAsString(authenticatedUser));
            return ResponseEntity.ok(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            if (user.getRole() == null) {
                user.setRole(User.Role.Student);
            }
            if (user.getRequestStatus() == null) {
                user.setRequestStatus(User.RequestStatus.None);
            }

            User savedUser = userService.createUser(
                    user.getUsername(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getRole(),
                    user.getDob() != null ? user.getDob().toLocalDate() : LocalDate.now(),
                    user.getRequestStatus()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error registering user: " + e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/instructor-requests")
    public ResponseEntity<List<User>> getAllInstructorRequests() {
        List<User> instructorRequests = userService.getAllInstructorRequests();
        System.out.println("Returning instructor requests: {}"+ instructorRequests);
        return ResponseEntity.ok(instructorRequests);
    }


    @PostMapping("/request-instructor-status/{userId}")
    public ResponseEntity<?> requestInstructorStatus(@PathVariable Integer userId) {
        if (userService.requestInstructorStatus(userId)) {
            return ResponseEntity.ok("Instructor status requested successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PostMapping("/approve-instructor-request/{userId}")
    public ResponseEntity<?> approveInstructorRequest(@PathVariable Integer userId) {
        if (userService.approveInstructorRequest(userId)) {
            return ResponseEntity.ok("Instructor status approved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PostMapping("/decline-instructor-request/{userId}")
    public ResponseEntity<?> declineInstructorRequest(@PathVariable Integer userId) {
        if (userService.declineInstructorRequest(userId)) {
            return ResponseEntity.ok("Instructor status declined successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }



}
