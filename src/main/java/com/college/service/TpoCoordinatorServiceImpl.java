package com.college.service;

import com.college.core.entity.TpoCoordinator;
import com.college.core.entity.User;
import com.college.core.model.TpoDTO;
import com.college.repository.TpoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Service
public class TpoCoordinatorServiceImpl implements TpoCoordinatorService {

    @Autowired
     TpoRepository tpoRepository;

    @Autowired
     ModelMapper modelMapper;

    @Override
    public List<TpoDTO> getAllTpoCoordinators() {
        List<TpoCoordinator> tpoCoordinators = tpoRepository.findAll();
        Type targetListType = new TypeToken<List<TpoDTO>>() {}.getType();
        return modelMapper.map(tpoCoordinators, targetListType);
    }

    @Override
    public TpoDTO getTpoCoordinator(String studentName) {
        TpoCoordinator tpoCoordinator = tpoRepository.findByStudentName(studentName);
        return modelMapper.map(tpoCoordinator, TpoDTO.class);
    }

    @Override
    public void saveTpoCoordinator(TpoDTO tpoDTO) {
        TpoCoordinator tpoCoordinator = modelMapper.map(tpoDTO, TpoCoordinator.class);
        tpoRepository.save(tpoCoordinator);
    }




    @Override
    public List<TpoDTO> searchTpoCoordinatorByName(String studentName) {
        List<TpoCoordinator> tpoCoordinators = tpoRepository.findByStudentNameContaining(studentName);
        Type targetListType = new TypeToken<List<TpoDTO>>() {}.getType();
        return modelMapper.map(tpoCoordinators, targetListType);
    }

    @Override
    public List<TpoDTO> searchTpoCoordinatorByEmail(String email) {
        List<TpoCoordinator> tpoCoordinators = tpoRepository.findByEmailContaining(email);
        Type targetListType = new TypeToken<List<TpoDTO>>() {}.getType();
        return modelMapper.map(tpoCoordinators, targetListType);
    }

    @Override
    public TpoDTO findTpoCoordinatorByEmail(String email) {
        TpoCoordinator tpoCoordinator = tpoRepository.findByEmail(email);
        return modelMapper.map(tpoCoordinator, TpoDTO.class);
    }

    @Override
    public TpoDTO searchTpoCoordinator(String studentName) {
        TpoCoordinator tpoCoordinator = tpoRepository.findByStudentName(studentName);
        return modelMapper.map(tpoCoordinator, TpoDTO.class);
    }

    @Override
    public List<TpoDTO> getTpoCoordinatorsByDeptId(Long deptId) {
        List<TpoCoordinator> tpoCoordinators = tpoRepository.findByDepartmentId(deptId);
        Type targetListType = new TypeToken<List<TpoDTO>>() {}.getType();
        return modelMapper.map(tpoCoordinators, targetListType);
    }

    @Override
    public Map<Long, List<Map<String, String>>> getTpoCoordinatorNamesByDeptId() {
        // Implement logic here if required.

        return null;
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        // Implement password reset token logic if required.
    }

    @Override
    public TpoCoordinator searchByEmail(String email) {
        return null;
    }


    @Override
    public void deleteTpoCoordinator(Long id) {
        tpoRepository.delete(id);
    }
}
