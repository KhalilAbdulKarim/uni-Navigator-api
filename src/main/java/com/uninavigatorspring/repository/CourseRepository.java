package com.uninavigatorspring.repository;

import com.uninavigatorspring.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByInstructorUserId(Integer instructorId);

    List<Course> findByCourseNameContainingIgnoreCase(String courseName);

    @Query("SELECT c FROM Course c JOIN FETCH c.instructor")
    List<Course> findAllWithInstructor();

}
