package com.uninavigatorspring.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import com.uninavigatorspring.model.Project;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(length = 255, nullable = false)
    private String username;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 255, nullable = false)
    private String email;

    @Column(length = 255, nullable = true)
    private String firstName;

    @Column(length = 255, nullable = true)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('Student', 'Instructor', 'Admin')", nullable = false)
    private Role role = Role.Student;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dob;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('None', 'Requested', 'Approved', 'Declined')", nullable = false)
    private RequestStatus requestStatus = RequestStatus.None;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "project_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Project> projects = new HashSet<>();

    public User(String username, String password, String email, String firstName, String lastName, Role role, Date date, RequestStatus requestStatus) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.dob = date;
        this.requestStatus = requestStatus;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public enum Role {
        Student, Instructor, Admin
    }

    public enum RequestStatus {
        None, Requested, Approved, Declined
    }

    public User() {
        this.role = Role.Student;
        this.requestStatus = RequestStatus.None;
        this.dob = Date.valueOf(LocalDate.now());
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", dob=" + dob +
                ", requestStatus=" + requestStatus +
                '}';
    }

}
