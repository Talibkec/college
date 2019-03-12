package com.college.service;

import com.college.core.entity.Role;
import com.college.core.entity.User;
import com.college.core.model.UserRoleDTO;
import com.college.repository.RoleRepository;
import com.college.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Long roleId = Long.parseLong(user.getRole());
        HashSet<Role> roles= new HashSet<>();
        roles.add(roleRepository.findOne(roleId));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUserRole(UserRoleDTO userRoleDTO) {
        Set<Role> roles= new HashSet<>();
        User user = userRepository.findOne(userRoleDTO.getUserId());
        Role role = roleRepository.findOne(userRoleDTO.getRoleId());
        Role newRole = new Role();
        newRole.setId(userRoleDTO.getRoleId());
        newRole.setName(role.getName());
        roles = user.getRoles();
        roles.add(newRole);
        user.setRoles(roles);
        userRepository.save(user);

    }

    @Override
    public void deleteUserRole(UserRoleDTO userRoleDTO)
    {
        Long id = userRoleDTO.getRoleId();
        User user = userRepository.findOne(userRoleDTO.getUserId());
        Set<Role> roles = user.getRoles();
        Iterator<Role> it = roles.iterator();
        while(it.hasNext()) {
            Role r =  it.next();
            if (r.getId() == userRoleDTO.getRoleId()) {
                it.remove();
            }
        }
        userRepository.save(user);
    }
}