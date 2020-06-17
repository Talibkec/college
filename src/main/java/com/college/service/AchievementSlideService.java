package com.college.service;

import com.college.core.model.AchievementDTO;
import org.modelmapper.internal.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AchievementSlideService {
    void saveAchievementSlide(AchievementDTO achievementDTO);

    AchievementDTO getImages(Long id);

    List<AchievementDTO> getAllImages();

    void deleteAchievementImage(Long id);
}
