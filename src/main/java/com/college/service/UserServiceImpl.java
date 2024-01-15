package com.college.service;
import com.college.ProductTransformer;
import com.college.core.entity.Role;
import com.college.core.entity.User;
import com.college.core.model.UserRoleDTO;
import com.college.repository.DepartmentRepository;
import com.college.repository.RoleRepository;
import com.college.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public void save(User user) {

        if (!StringUtils.isEmpty(user.getRole())) {
            Optional<Role> role = roleRepository.findById(Long.parseLong(user.getRole()));
            if (user.getRoles() == null) {
                user.setRoles(new HashSet<>());
            }
            user.getRoles().add(role.get());
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUserRole(UserRoleDTO userRoleDTO) {
        Set<Role> roles = new HashSet<>();
        Optional<User> user = userRepository.findById(userRoleDTO.getUserId());
        Optional<Role> role = roleRepository.findById(userRoleDTO.getRoleId());
        Role newRole = new Role();
        newRole.setId(userRoleDTO.getRoleId());
        newRole.setName(role.get().getName());
        roles = user.get().getRoles();
        roles.add(newRole);
        user.get().setRoles(roles);
        userRepository.save(user.get());
        if(role.get().getName().equals("HOD"))
        {
            departmentRepository.updateHodName( userRoleDTO.getFacultyName(), userRoleDTO.getDepartmentId());
        }

    }

    @Override
    public void deleteUserRole(UserRoleDTO userRoleDTO) {
        Long id = userRoleDTO.getRoleId();
        Optional<User> user = userRepository.findById(userRoleDTO.getUserId());
        Optional<Role> role = roleRepository.findById(userRoleDTO.getRoleId());
        Set<Role> roles = user.get().getRoles();
        Iterator<Role> it = roles.iterator();
        while (it.hasNext()) {
            Role r = it.next();
            if (r.getId() == userRoleDTO.getRoleId()) {
                it.remove();
            }
        }
        if(role.get().getName().equals("HOD")){
            departmentRepository.deleteHodName(userRoleDTO.getDepartmentId());
        }
        userRepository.save(user.get());
    }
    @Override
    public List<String> searchUserName(String userName){
        userName = userName.toLowerCase();
        List<User> user = userRepository.searchUserName(userName);
        List<String> userNames= ProductTransformer.getUserName(user);
        return userNames;
    }

    @Override
    public  List<String> getAllUserName(){
        List<User> getUserNames = userRepository.findAll();
        List<String> allUserNames =ProductTransformer.getAllUserNames(getUserNames);
        return  allUserNames;
    }
}
