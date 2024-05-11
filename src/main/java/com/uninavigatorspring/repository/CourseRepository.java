package com.uninavigatorspring.repository;

import com.uninavigatorspring.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findByInstructorUserId(Integer instructorId);

    @Query("SELECT c FROM Course c WHERE c.courseName LIKE %:courseName%")
    List<Course> findByCourseNameContainingIgnoreCase(@Param("courseName") String courseName);

    @Query("SELECT c FROM Course c JOIN FETCH c.instructor")
    List<Course> findAllWithInstructor();

    @Query("SELECT c FROM Course c JOIN FETCH c.instructor WHERE c.instructor.userId = :instructorId")
    List<Course> findCoursesByInstructor(@Param("instructorId") int instructorId);

}
