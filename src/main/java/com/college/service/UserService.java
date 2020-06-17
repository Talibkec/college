package com.college.service;

import com.college.core.entity.User;
import com.college.core.model.UserRoleDTO;

import java.util.List;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    void saveUserRole(UserRoleDTO userRoleDTO);

    List<String> searchUserName(String userName);

    void deleteUserRole(UserRoleDTO userRoleDTO);
    
    List<String> getAllUserName();
}
