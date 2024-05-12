package com.uninavigatorspring.service;

import com.uninavigatorspring.model.User;
import com.uninavigatorspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(String username, String password, String email, String firstName, String lastName, User.Role role, LocalDate dob, User.RequestStatus requestStatus) {
        User newUser = new User(username, password, email, firstName, lastName, role, Date.valueOf(dob), requestStatus);
        System.out.println("Creating user: " + newUser.toString());
        return userRepository.save(newUser);
    }

    public User updateUser(int userId, String username, String email, String password, String firstName, String lastName, LocalDate dob) {
        Optional<User> userOptional = userRepository.findById(userId);
        userOptional.ifPresent(user -> {
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setDob(Date.valueOf(dob));
            userRepository.save(user);
        });
        return userOptional.orElse(null);
    }

    public User authenticateUser(String username, String plainPassword) {
        return userRepository.findByUsernameAndPassword(username, plainPassword);
    }

    public boolean doesUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean requestInstructorStatus(int userId) {
        return userRepository.requestInstructorStatus(userId) > 0;
    }

    public boolean declineInstructorRequest(int userId) {
        return userRepository.declineInstructorRequest(userId) > 0;
    }

    public boolean approveInstructorRequest(int userId) {
        return userRepository.approveInstructorRequest(userId) > 0;
    }

    public List<User> getAllInstructorRequests() {
        return userRepository.findAllByRequestStatus(User.RequestStatus.Requested);
    }

    public List<User> getAllInstructors() {
        return userRepository.findAllByRole(User.Role.Instructor);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllStudents() {
        return userRepository.findAllByRole(User.Role.Student);
    }

    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

}
