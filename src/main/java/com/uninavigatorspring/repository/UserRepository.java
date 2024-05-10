package com.uninavigatorspring.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.uninavigatorspring.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsernameAndPassword(String username, String password);

//    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
//    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    List<User> findAllByRequestStatus(User.RequestStatus status);

    boolean existsByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.requestStatus = 'Requested' WHERE u.userId = :userId")
    int requestInstructorStatus(Integer userId);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.role = 'Student', u.requestStatus = 'Declined' WHERE u.userId = :userId")
    int declineInstructorRequest(Integer userId);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.role = 'Instructor', u.requestStatus = 'Approved' WHERE u.userId = :userId")
    int approveInstructorRequest(Integer userId);

    List<User> findAllByRole(User.Role role);



}
