package com.uninavigatorspring.controller;

import com.uninavigatorspring.model.Grade;
import com.uninavigatorspring.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping
    public ResponseEntity<List<Grade>> getAllGrades() {
        return ResponseEntity.ok(gradeService.getAllGrades());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grade> getGradeById(@PathVariable Integer id) {
        return gradeService.getAllGrades()
                .stream()
                .filter(grade -> grade.getGradeId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Grade>> getGradesByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(gradeService.getGradesByUser(userId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Grade>> getGradesByCourse(@PathVariable Integer courseId) {
        return ResponseEntity.ok(gradeService.getGradesByCourse(courseId));
    }

    @PostMapping
    public ResponseEntity<Grade> createGrade(
            @RequestParam int userId,
            @RequestParam int courseId,
            @RequestParam String assignmentName,
            @RequestParam(required = false) BigDecimal grade,
            @RequestParam(required = false) Date date
    ) {
        Grade newGrade = gradeService.createGrade(userId, courseId, assignmentName, grade, date);
        return newGrade != null ? ResponseEntity.status(HttpStatus.CREATED).body(newGrade) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(
            @PathVariable Integer id,
            @RequestParam int userId,
            @RequestParam int courseId,
            @RequestParam String assignmentName,
            @RequestParam(required = false) BigDecimal grade,
            @RequestParam(required = false) Date date
    ) {
        Grade updatedGrade = gradeService.updateGrade(id, userId, courseId, assignmentName, grade, date);
        return updatedGrade != null ? ResponseEntity.ok(updatedGrade) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGrade(@PathVariable Integer id) {
        boolean deleted = gradeService.deleteGrade(id);
        return deleted ? ResponseEntity.ok("Deleted successfully") : ResponseEntity.notFound().build();
    }
}
