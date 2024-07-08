package com.college.service;

import com.college.core.entity.Placements;
import com.college.core.entity.User;
import com.college.core.model.PlacementsDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
public interface PlacementsService {

    List<PlacementsDTO> getAllPlacements();

    PlacementsDTO getPlacements(String userName);

    void savePlacements(PlacementsDTO PlacementsDTO);

    PlacementsDTO getPlacementsById(Long id);

    List<PlacementsDTO> searchPlacementsName(String PlacementsName);

    List<String> searchPlacementsNames(String PlacementsName);

    List<PlacementsDTO> searchPlacementsEmail(String PlacementsEmail);


    PlacementsDTO searchPlacements(String PlacementsName);

    List<PlacementsDTO> getPlacementsByName(String PlacementsName);

    List<PlacementsDTO> getPlacementsByDeptNo(Long deptno);

    Map<Long, List<Map<String, String>>> getPlacementsNameByDeptNo();

    PlacementsDTO findUserByEmail(String userEmail);

    void createPasswordResetTokenForUser(User user, String token);

    Placements searchByEmail(String PlacementsEmail);

    void deletePlacements(long PlacementsId);
}
