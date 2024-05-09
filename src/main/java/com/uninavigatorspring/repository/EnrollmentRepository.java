package com.uninavigatorspring.repository;

import com.uninavigatorspring.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    List<Enrollment> findByUserUserId(Integer userId);
    List<Enrollment> findByCourseCourseId(Integer courseId);
}
