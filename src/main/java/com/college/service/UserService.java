package com.college.service;

import com.college.core.entity.User;
import com.college.core.model.UserRoleDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    void save(User user);

    User findByUsername(String username);

    void saveUserRole(UserRoleDTO userRoleDTO);

    /** Gives the user the HOD role and makes the faculty member the HOD of the department. */
    void assignHodRole(UserRoleDTO userRoleDTO);

    List<String> searchUserName(String userName);

    void deleteUserRole(UserRoleDTO userRoleDTO);
    
    List<String> getAllUserName();
}
