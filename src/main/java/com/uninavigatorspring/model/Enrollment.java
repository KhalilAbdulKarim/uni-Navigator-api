package com.uninavigatorspring.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enrollmentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UserId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CourseId", nullable = false)
    private Course course;

    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date enrollmentDate;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('Enrolled', 'Dropped')", nullable = false)
    private Status status;

    public enum Status {
        Enrolled, Dropped
    }

    public Enrollment() {
    }

    public Enrollment(User user, Course course, Date enrollmentDate, Status status) {
        this.user = user;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    public Integer getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Integer enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
