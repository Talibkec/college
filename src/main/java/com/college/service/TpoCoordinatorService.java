package com.college.service;

import com.college.core.entity.TpoCoordinator;
import com.college.core.entity.User;
import com.college.core.model.TpoDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface TpoCoordinatorService {

    List<TpoDTO> getAllTpoCoordinators();

    TpoDTO getTpoCoordinator(String studentName);


    void saveTpoCoordinator(TpoDTO tpoDTO);




    List<TpoDTO> searchTpoCoordinatorByName(String studentName);

    List<TpoDTO> searchTpoCoordinatorByEmail(String email);

    TpoDTO findTpoCoordinatorByEmail(String email);

    TpoDTO searchTpoCoordinator(String studentName);

    List<TpoDTO> getTpoCoordinatorsByDeptId(Long deptId);

    Map<Long, List<Map<String, String>>> getTpoCoordinatorNamesByDeptId();

    void createPasswordResetTokenForUser(User user, String token);

    TpoCoordinator searchByEmail(String email);

    void deleteTpoCoordinator(Long id);
}

