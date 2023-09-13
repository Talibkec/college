package com.college.service;

import com.college.ProductTransformer;
import com.college.core.entity.Department;
import com.college.core.entity.Faculty;
import com.college.core.entity.PasswordResetToken;
import com.college.core.entity.User;
import com.college.core.model.FacultyDTO;
import com.college.core.model.FacultyKeyPropsDTO;
import com.college.repository.FacultyRepository;
import com.college.repository.PasswordResetTokenRepository;
import com.college.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacultyServiceImpl implements FacultyService {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    PasswordResetTokenRepository passwordTokenRepository;

    @Override
    public List<FacultyDTO> getAllFaculty() {
        List<Faculty> faculties = facultyRepository.findAll();
        Type targetListType = new TypeToken<List<FacultyRepository>>() {
        }.getType();
        return modelMapper.map(faculties, targetListType);
    }



    @Override
    public FacultyDTO getFaculty(String userName) {
        Faculty faculty = facultyRepository.findFacltyByUserName(userName);
        return modelMapper.map(faculty, FacultyDTO.class);
    }

    @Override
    public void saveFaculty(FacultyDTO facultyDTO) {

        System.out.println("Insertion Order inservice layer saveFaculty method ");
        for(FacultyKeyPropsDTO keyPropsDTO:facultyDTO.getFacultyKeyProps()){
            System.out.println(keyPropsDTO.getKeyPropertyName());
        }
        Faculty faculty = modelMapper.map(facultyDTO, Faculty.class);
        facultyRepository.save(faculty);
    }

    @Override
    public FacultyDTO getFacultyById(Long id) {
        FacultyDTO facultyDTO = null;
        Faculty faculty = facultyRepository.findOne(id);
        if (faculty != null) {
            facultyDTO = modelMapper.map(faculty, FacultyDTO.class);
        }
        System.out.println("Fetching Order inservice layer method ");
        for(FacultyKeyPropsDTO keyPropsDTO:facultyDTO.getFacultyKeyProps()){
            System.out.println(keyPropsDTO.getKeyPropertyName());
        }
        return facultyDTO;
    }

    @Override
    public List<FacultyDTO> searchFacultyName(String facultyName) {
        List<FacultyDTO> facultyDTO = null;
        List<Faculty> faculty = facultyRepository.searchFacultyName(facultyName);
        Type targetListType = new TypeToken<List<FacultyDTO>>() {
        }.getType();
        if (faculty != null) {
            facultyDTO = modelMapper.map(faculty, targetListType);
        }
        return facultyDTO;

    }

    @Override
    public List<String> searchFacultyNames(String facultyName) {
        facultyName = facultyName.toLowerCase();
        List<Faculty> faculties = facultyRepository.searchFacultyName(facultyName);
        List<String> facultyNames= ProductTransformer.getFacultyName(faculties);
        return facultyNames;
    }


    public List<FacultyDTO> searchFacultyEmail(String facultyEmail) {
        List<FacultyDTO> facultyDTO = null;
        List<Faculty> faculty = facultyRepository.searchFacultyEmail(facultyEmail);
        Type targetListType = new TypeToken<List<FacultyDTO>>() {
        }.getType();
        if (faculty != null) {
            facultyDTO = modelMapper.map(faculty, targetListType);
        }
        return facultyDTO;

    }

    @Override
    public FacultyDTO searchFaculty(String facultyName) {
        FacultyDTO facultyDTO = null;
        Faculty faculty = facultyRepository.searchFacultyByName(facultyName);
        if (faculty != null) {
            facultyDTO = modelMapper.map(faculty, FacultyDTO.class);
        }
        return facultyDTO;

    }

    @Override
    public List<FacultyDTO> getFacultyByName(String facultyName) {
        List<FacultyDTO> facultyDTO = null;
        List<Faculty> faculty = facultyRepository.getFacultyByName(facultyName);
        Type targetListType = new TypeToken<List<FacultyDTO>>() {
        }.getType();
        if (faculty != null) {
            facultyDTO = modelMapper.map(faculty, targetListType);
        }
        return facultyDTO;

    }

    @Override
    public List<FacultyDTO> getFacultyByDeptNo(Long deptno) {
        List<FacultyDTO> facultyDTO = null;
        List<Faculty> faculty = facultyRepository.getFacultyByDeptNo(deptno);
        Type targetListType = new TypeToken<List<FacultyDTO>>() {
        }.getType();
        if (faculty != null) {
            facultyDTO = modelMapper.map(faculty, targetListType);
        }
        return facultyDTO;

    }

    @Override
    @Cacheable(value = "facultyNameByDeptNoCache")
    public Map<Long, List<Map<String, String>>> getFacultyNameByDeptNo() {

        Map<Long, List<Map<String, String>>> facultyNamesMap = new HashMap<>();
        List<Faculty> faculties = facultyRepository.getFacultiesName();
        for (Faculty f : faculties) {
            String deptId = "";
            if (f.getDepartmentId() != null) {
                deptId = f.getDepartmentId().toString();
            }

            List<Map<String, String>> facultyNames = null;
            if (facultyNamesMap.get(f.getDepartmentId()) == null) {
                facultyNames = new ArrayList<>();
            } else {
                facultyNames = facultyNamesMap.get(f.getDepartmentId());
            }
            Map<String, String> fMap = new HashMap<>();
            fMap.put(f.getFacultyName(), f.getFacultyId().toString());
            facultyNames.add(fMap);
            facultyNamesMap.put(f.getDepartmentId(), facultyNames);
        }

        return facultyNamesMap;

    }


    @Override
    public FacultyDTO findUserByEmail(String userEmail) {
        Faculty faculty = null;
        faculty = facultyRepository.findUserByEmail(userEmail);
        FacultyDTO facultyDTO = null;
        if (faculty != null) {
            facultyDTO = modelMapper.map(faculty, FacultyDTO.class);
        }

        return facultyDTO;
    }
    @Override
    public Faculty searchByEmail(String facultyEmail) {
        return facultyRepository.findUserByEmail(facultyEmail);

    }

    @Override
    public void deleteFaculty(long facultyId) {
        facultyRepository.delete(facultyId);
    }


    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }
}
