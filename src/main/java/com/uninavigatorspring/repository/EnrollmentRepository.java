package com.uninavigatorspring.repository;

import com.uninavigatorspring.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    List<Enrollment> findByUserUserId(Integer userId);
    List<Enrollment> findByCourseCourseId(Integer courseId);
    Optional<Enrollment> findByCourseCourseIdAndUserUserId(int courseId, int userId);

}
