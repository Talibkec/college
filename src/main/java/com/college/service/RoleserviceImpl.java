package com.college.service;

import com.college.core.entity.Role;
import com.college.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleserviceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getALLRoles() {
        return roleRepository.findAll();
    }
}
