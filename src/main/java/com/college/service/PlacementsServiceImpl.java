package com.college.service;

import com.college.core.entity.Placements;
import com.college.core.entity.Staff;
import com.college.core.entity.User;
import com.college.core.model.PlacementsDTO;
import com.college.core.model.StaffDTO;
import com.college.repository.PlacementsRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
@Service
public class PlacementsServiceImpl implements PlacementsService {
    @Autowired
    PlacementsRepository placementsRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<PlacementsDTO> getAllPlacements() {
        List<Placements> placements = placementsRepository.findAll();
        Type targetListType = new TypeToken<List<PlacementsDTO>>() {
        }.getType();
        return modelMapper.map(placements, targetListType);
    }

    @Override
    public PlacementsDTO getPlacements(String userName) {
        return null;
    }

    @Override
    public void savePlacements(PlacementsDTO placementsDTO) {
        System.out.println("Insertion Order inservice layer saveFaculty method ");
        Placements placements = modelMapper.map(placementsDTO, Placements.class);
        placementsRepository.save(placements);
    }

    @Override
    public PlacementsDTO getPlacementsById(Long id) {
        return null;
    }

    @Override
    public List<PlacementsDTO> searchPlacementsName(String PlacementsName) {
        return null;
    }

    @Override
    public List<String> searchPlacementsNames(String PlacementsName) {
        return null;
    }

    @Override
    public List<PlacementsDTO> searchPlacementsEmail(String PlacementsEmail) {
        return null;
    }

    @Override
    public PlacementsDTO searchPlacements(String PlacementsName) {
        return null;
    }

    @Override
    public List<PlacementsDTO> getPlacementsByName(String PlacementsName) {
        return null;
    }

    @Override
    public List<PlacementsDTO> getPlacementsByDeptNo(Long deptno) {
        return null;
    }

    @Override
    public Map<Long, List<Map<String, String>>> getPlacementsNameByDeptNo() {
        return null;
    }

    @Override
    public PlacementsDTO findUserByEmail(String userEmail) {
        return null;
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {

    }

    @Override
    public Placements searchByEmail(String PlacementsEmail) {
        return null;
    }

    @Override
    public void deletePlacements(long PlacementsId) {
        placementsRepository.delete(PlacementsId);
    }
}
