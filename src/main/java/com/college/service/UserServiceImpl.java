package com.college.service;

import com.college.ProductTransformer;
import com.college.core.entity.Role;
import com.college.core.entity.User;
import com.college.core.model.UserRoleDTO;
import com.college.repository.DepartmentRepository;
import com.college.repository.RoleRepository;
import com.college.repository.UserRepository;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.net.ftp.FtpDirEntry;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
            Role role = roleRepository.findOne(Long.parseLong(user.getRole()));
            if (user.getRoles() == null) {
                user.setRoles(new HashSet<>());
            }
            user.getRoles().add(role);
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
        User user = userRepository.findOne(userRoleDTO.getUserId());
        Role role = roleRepository.findOne(userRoleDTO.getRoleId());
        Role newRole = new Role();
        newRole.setId(userRoleDTO.getRoleId());
        newRole.setName(role.getName());
        roles = user.getRoles();
        roles.add(newRole);
        user.setRoles(roles);
        userRepository.save(user);
        if(role.getName().equals("HOD"))
        {
            departmentRepository.updateHodName(userRoleDTO.getFacultyName(), userRoleDTO.getDepartmentId());
        }

    }

    @Override
    public void deleteUserRole(UserRoleDTO userRoleDTO) {
        Long id = userRoleDTO.getRoleId();
        User user = userRepository.findOne(userRoleDTO.getUserId());
        Set<Role> roles = user.getRoles();
        Iterator<Role> it = roles.iterator();
        while (it.hasNext()) {
            Role r = it.next();
            if (r.getId() == userRoleDTO.getRoleId()) {
                it.remove();
            }
        }
        userRepository.save(user);
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