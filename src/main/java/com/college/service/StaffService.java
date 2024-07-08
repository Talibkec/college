package com.college.service;

import com.college.core.entity.Staff;
import com.college.core.entity.User;
import com.college.core.model.StaffDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface StaffService {

    List<StaffDTO> getAllStaff();

    StaffDTO getStaff(String userName);

    void saveStaff(StaffDTO StaffDTO);

    StaffDTO getStaffById(Long id);

    List<StaffDTO> searchStaffName(String StaffName);

    List<String> searchStaffNames(String StaffName);

    List<StaffDTO> searchStaffEmail(String StaffEmail);


    StaffDTO searchStaff(String StaffName);

    List<StaffDTO> getStaffByName(String StaffName);

    List<StaffDTO> getStaffByDeptNo(Long deptno);

    Map<Long, List<Map<String, String>>> getStaffNameByDeptNo();

    StaffDTO findUserByEmail(String userEmail);

    void createPasswordResetTokenForUser(User user, String token);

    Staff searchByEmail(String StaffEmail);

    void deleteStaff(long StaffId);
}
