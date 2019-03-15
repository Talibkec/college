package com.college.service;

import com.college.core.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    List<Role> getALLRoles();

    Role getRole(String sm);
}
