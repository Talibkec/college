package com.college.service;

import com.college.core.entity.User;
import com.college.core.model.UserRoleDTO;

public interface UserService {
     void save(User user);
     User findByUsername(String username);
     void saveUserRole(UserRoleDTO userRoleDTO);
     void deleteUserRole(UserRoleDTO userRoleDTO);
}
