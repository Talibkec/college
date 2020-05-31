package com.college.repository;


import com.college.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    @Query("select u from User u where u.username like lower(CONCAT('%', :userName, '%'))")
    List<User> searchUserName(@Param("userName") String userName);


}
