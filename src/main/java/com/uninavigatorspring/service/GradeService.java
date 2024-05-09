package com.uninavigatorspring.service;

import com.uninavigatorspring.model.Course;
import com.uninavigatorspring.model.Grade;
import com.uninavigatorspring.model.User;
import com.uninavigatorspring.repository.CourseRepository;
import com.uninavigatorspring.repository.GradeRepository;
import com.uninavigatorspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    public List<Grade> getGradesByUser(int userId) {
        return gradeRepository.findByUserUserId(userId);
    }

    public List<Grade> getGradesByCourse(int courseId) {
        return gradeRepository.findByCourseCourseId(courseId);
    }

    public Grade createGrade(int userId, int courseId, String assignmentName, BigDecimal grade, Date date) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Course> course = courseRepository.findById(courseId);
        if (user.isPresent() && course.isPresent()) {
            Grade newGrade = new Grade(user.get(), course.get(), assignmentName, grade, date);
            return gradeRepository.save(newGrade);
        }
        return null;
    }

    public Grade updateGrade(int gradeId, int userId, int courseId, String assignmentName, BigDecimal grade, Date date) {
        Optional<Grade> gradeOptional = gradeRepository.findById(gradeId);
        if (gradeOptional.isPresent()) {
            Grade existingGrade = gradeOptional.get();
            existingGrade.setUser(userRepository.findById(userId).orElse(null));
            existingGrade.setCourse(courseRepository.findById(courseId).orElse(null));
            existingGrade.setAssignmentName(assignmentName);
            existingGrade.setGrade(grade);
            existingGrade.setDate(date);
            return gradeRepository.save(existingGrade);
        }
        return null;
    }

    public boolean deleteGrade(int gradeId) {
        if (gradeRepository.existsById(gradeId)) {
            gradeRepository.deleteById(gradeId);
            return true;
        } else {
            return false;
        }
    }
}
