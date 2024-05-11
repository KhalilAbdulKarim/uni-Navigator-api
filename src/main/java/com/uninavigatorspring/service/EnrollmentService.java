package com.uninavigatorspring.service;

import com.uninavigatorspring.model.Course;
import com.uninavigatorspring.model.Enrollment;
import com.uninavigatorspring.model.User;
import com.uninavigatorspring.repository.CourseRepository;
import com.uninavigatorspring.repository.EnrollmentRepository;
import com.uninavigatorspring.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public List<Enrollment> getEnrollmentsByUser(int userId) {
        return enrollmentRepository.findByUserUserId(userId);
    }

    public List<Enrollment> getEnrollmentsByCourse(int courseId) {
        return enrollmentRepository.findByCourseCourseId(courseId);
    }


    public Enrollment createEnrollment(int userId, int courseId, Date enrollmentDate, Enrollment.Status status) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Course> course = courseRepository.findById(courseId);
        if (user.isPresent() && course.isPresent()) {
            Enrollment enrollment = new Enrollment(user.get(), course.get(), enrollmentDate, status);
            return enrollmentRepository.save(enrollment);
        }
        return null;
    }

    public Enrollment updateEnrollment(int enrollmentId, int userId, int courseId, Date enrollmentDate, Enrollment.Status status) {
        Optional<Enrollment> enrollmentOptional = enrollmentRepository.findById(enrollmentId);
        if (enrollmentOptional.isPresent()) {
            Enrollment enrollment = enrollmentOptional.get();
            enrollment.setUser(userRepository.findById(userId).orElse(null));
            enrollment.setCourse(courseRepository.findById(courseId).orElse(null));
            enrollment.setEnrollmentDate(enrollmentDate);
            enrollment.setStatus(status);
            return enrollmentRepository.save(enrollment);
        }
        return null;
    }

    public boolean deleteEnrollment(int enrollmentId) {
        if (enrollmentRepository.existsById(enrollmentId)) {
            enrollmentRepository.deleteById(enrollmentId);
            return true;
        } else {
            return false;
        }
    }

    public Enrollment getEnrollmentById(int enrollmentId) {
        return enrollmentRepository.findById(enrollmentId).orElse(null);
    }

    public boolean enrollStudent(int courseId, int userId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Course> course = courseRepository.findById(courseId);
        if (user.isPresent() && course.isPresent()) {
            Enrollment enrollment = new Enrollment(user.get(), course.get(), new Date(System.currentTimeMillis()), Enrollment.Status.Enrolled);
            enrollmentRepository.save(enrollment);
            return true;
        }
        return false;
    }


    @Transactional
    public boolean dropStudent(int courseId, int userId) {
        Optional<Enrollment> enrollment = enrollmentRepository.findByCourseCourseIdAndUserUserId(courseId, userId);
        if (enrollment.isPresent()) {
            enrollmentRepository.delete(enrollment.get());
            return true;
        }
        return false;
    }
}
