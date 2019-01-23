package com.college.service;

import com.college.core.entity.User;

public interface UserService {
     void save(User user);
     User findByUsername(String username);
}
