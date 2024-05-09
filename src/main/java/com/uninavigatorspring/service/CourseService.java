package com.uninavigatorspring.service;

import com.uninavigatorspring.model.Course;
import com.uninavigatorspring.model.User;
import com.uninavigatorspring.repository.CourseRepository;
import com.uninavigatorspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getCoursesByInstructor(int instructorId) {
        return courseRepository.findByInstructorUserId(instructorId);
    }

    public Course getCourseByName(String courseName) {
        return courseRepository.findByCourseNameContainingIgnoreCase(courseName)
                .stream()
                .findFirst()
                .orElse(null);
    }

    public Course createCourse(String courseName, int instructorId, String schedule, String description, int capacity, Date startDate, Date endDate) {
        Optional<User> instructor = userRepository.findById(instructorId);
        if (instructor.isPresent()) {
            Course course = new Course();
            course.setCourseName(courseName);
            course.setInstructor(instructor.get());
            course.setSchedule(schedule);
            course.setDescription(description);
            course.setCapacity(capacity);
            course.setStartDate(startDate);
            course.setEndDate(endDate);
            return courseRepository.save(course);
        } else {
            return null;
        }
    }

    public Course updateCourse(int courseId, String courseName, int instructorId, String schedule, String description, int capacity, Date startDate, Date endDate) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            course.setCourseName(courseName);
            course.setInstructor(userRepository.findById(instructorId).orElse(null));
            course.setSchedule(schedule);
            course.setDescription(description);
            course.setCapacity(capacity);
            course.setStartDate(startDate);
            course.setEndDate(endDate);
            return courseRepository.save(course);
        }
        return null;
    }
    public boolean deleteCourse(int courseId) {
        if (courseRepository.existsById(courseId)) {
            courseRepository.deleteById(courseId);
            return true;
        } else {
            return false;
        }
    }
}
