package com.uninavigatorspring.repository;

import com.uninavigatorspring.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByInstructorUserId(Integer instructorId);

    List<Course> findByCourseNameContainingIgnoreCase(String courseName);
}
