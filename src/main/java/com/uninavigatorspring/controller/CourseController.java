package com.uninavigatorspring.controller;

import com.uninavigatorspring.model.Course;
import com.uninavigatorspring.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {
        return courseService.getAllCourses()
                .stream()
                .filter(course -> course.getCourseId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/createCourse")
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        if (course == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course details are not provided.");
        }

        if (course.getInstructor() == null || course.getInstructor().getUserId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Instructor ID is missing or invalid.");
        }

        try {
            Course createdCourse = courseService.createCourse(
                    course.getCourseName(),
                    course.getInstructor().getUserId(),
                    course.getSchedule(),
                    course.getDescription(),
                    course.getCapacity(),
                    course.getStartDate(),
                    course.getEndDate()
            );

            if (createdCourse != null) {
                return ResponseEntity.ok(createdCourse);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create the course.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating course: " + e.getMessage());
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Integer id, @RequestBody Course course) {
        return ResponseEntity.ok(courseService.updateCourse(
                id,
                course.getCourseName(),
                course.getInstructor().getUserId(),
                course.getSchedule(),
                course.getDescription(),
                course.getCapacity(),
                course.getStartDate(),
                course.getEndDate()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Integer id) {
        boolean deleted = courseService.deleteCourse(id);
        return deleted ? ResponseEntity.ok("Deleted successfully") : ResponseEntity.notFound().build();
    }
}
