package com.uninavigatorspring.repository;

import com.uninavigatorspring.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
    List<Grade> findByUserUserId(Integer userId);
    List<Grade> findByCourseCourseId(Integer courseId);
}
