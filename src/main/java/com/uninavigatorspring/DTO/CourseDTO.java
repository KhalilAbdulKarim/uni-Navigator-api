//package com.uninavigatorspring.DTO;
//
//import com.uninavigatorspring.model.Course;
//
//import java.sql.Date;
//
//public class CourseDTO {
//    private Integer courseId;
//    private String courseName;
//    private String instructorName;
//    private String schedule;
//    private String description;
//    private Integer capacity;
//    private Date startDate;
//    private Date endDate;
//
//    // Constructor to initialize from a Course entity
//    public CourseDTO(Course course) {
//        this.courseId = course.getCourseId();
//        this.courseName = course.getCourseName();
//        this.instructorName = course.getInstructor().getFirstName() + " " + course.getInstructor().getLastName();
//        this.schedule = course.getSchedule();
//        this.description = course.getDescription();
//        this.capacity = course.getCapacity();
//        this.startDate = course.getStartDate();
//        this.endDate = course.getEndDate();
//    }
//
//    // Getters and setters for each property
//    public Integer getCourseId() {
//        return courseId;
//    }
//
//    public void setCourseId(Integer courseId) {
//        this.courseId = courseId;
//    }
//
//    public String getCourseName() {
//        return courseName;
//    }
//
//    public void setCourseName(String courseName) {
//        this.courseName = courseName;
//    }
//
//    public String getInstructorName() {
//        return instructorName;
//    }
//
//    public void setInstructorName(String instructorName) {
//        this.instructorName = instructorName;
//    }
//
//    public String getSchedule() {
//        return schedule;
//    }
//
//    public void setSchedule(String schedule) {
//        this.schedule = schedule;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public Integer getCapacity() {
//        return capacity;
//    }
//
//    public void setCapacity(Integer capacity) {
//        this.capacity = capacity;
//    }
//
//    public Date getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(Date startDate) {
//        this.startDate = startDate;
//    }
//
//    public Date getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(Date endDate) {
//        this.endDate = endDate;
//    }
//
//
//}
