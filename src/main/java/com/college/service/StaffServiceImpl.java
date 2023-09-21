package com.college.service;

import com.college.core.entity.Faculty;
import com.college.core.entity.Staff;
import com.college.core.entity.User;
import com.college.core.model.FacultyDTO;
import com.college.core.model.FacultyKeyPropsDTO;
import com.college.core.model.StaffDTO;
import com.college.repository.FacultyRepository;
import com.college.repository.StaffRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    StaffRepository staffRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<StaffDTO> getAllStaff() {
        List<Staff> staffs = staffRepository.findAll();
        Type targetListType = new TypeToken<List<StaffDTO>>() {
        }.getType();
        return modelMapper.map(staffs, targetListType);
    }

    @Override
    public StaffDTO getStaff(String userName) {
        return null;
    }

    @Override
    public void saveStaff(StaffDTO StaffDTO) {
        System.out.println("Insertion Order inservice layer saveFaculty method ");
        Staff staff = modelMapper.map(StaffDTO, Staff.class);
        staffRepository.save(staff);
    }

    @Override
    public StaffDTO getStaffById(Long id) {
        StaffDTO staffDTO = null;
        Staff staff = staffRepository.findOne(id);
        if (staff != null) {
            staffDTO = modelMapper.map(staff, StaffDTO.class);
        }
        System.out.println("Fetching Order inservice layer method ");
//        for(FacultyKeyPropsDTO keyPropsDTO:StaffDTO.getFacultyKeyProps()){
//            System.out.println(keyPropsDTO.getKeyPropertyName());
//        }
        return staffDTO;
    }

    @Override
    public List<StaffDTO> searchStaffName(String StaffName) {
        return null;
    }

    @Override
    public List<String> searchStaffNames(String StaffName) {
        return null;
    }

    @Override
    public List<StaffDTO> searchStaffEmail(String StaffEmail) {
        return null;
    }

    @Override
    public StaffDTO searchStaff(String StaffName) {
        return null;
    }

    @Override
    public List<StaffDTO> getStaffByName(String StaffName) {
        return null;
    }

    @Override
    public List<StaffDTO> getStaffByDeptNo(Long deptno) {
        return null;
    }

    @Override
    public Map<Long, List<Map<String, String>>> getStaffNameByDeptNo() {
        return null;
    }

    @Override
    public StaffDTO findUserByEmail(String userEmail) {
        return null;
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {

    }

    @Override
    public Staff searchByEmail(String StaffEmail) {
        return null;
    }

    @Override
    public void deleteStaff(long StaffId) {
        staffRepository.delete(StaffId);
    }
}
