package com.uninavigatorspring.controller;

import com.uninavigatorspring.model.Enrollment;
import com.uninavigatorspring.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Integer id) {
        return enrollmentService.getAllEnrollments()
                .stream()
                .filter(enrollment -> enrollment.getEnrollmentId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByUser(userId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByCourse(@PathVariable Integer courseId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByCourse(courseId));
    }

    @PostMapping
    public ResponseEntity<Enrollment> createEnrollment(
            @RequestParam int userId,
            @RequestParam int courseId,
            @RequestParam Date enrollmentDate,
            @RequestParam Enrollment.Status status
    ) {
        Enrollment enrollment = enrollmentService.createEnrollment(userId, courseId, enrollmentDate, status);
        return enrollment != null ? ResponseEntity.ok(enrollment) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(
            @PathVariable Integer id,
            @RequestParam int userId,
            @RequestParam int courseId,
            @RequestParam Date enrollmentDate,
            @RequestParam Enrollment.Status status
    ) {
        Enrollment enrollment = enrollmentService.updateEnrollment(id, userId, courseId, enrollmentDate, status);
        return enrollment != null ? ResponseEntity.ok(enrollment) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEnrollment(@PathVariable Integer id) {
        boolean deleted = enrollmentService.deleteEnrollment(id);
        return deleted ? ResponseEntity.ok("Deleted successfully") : ResponseEntity.notFound().build();
    }
}
