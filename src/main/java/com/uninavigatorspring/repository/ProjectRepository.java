package com.uninavigatorspring.repository;
import com.uninavigatorspring.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT p FROM Project p JOIN p.users u WHERE u.userId = :userId")
    List<Project> findProjectsByUserId(@Param("userId") int userId);
}