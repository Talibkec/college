package com.college.service;

import com.college.core.entity.Achievement;
import com.college.core.model.AchievementDTO;
import com.college.core.model.ImageSlideDTO;
import com.college.repository.AchievementRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AchievementSlideServiceImpl implements AchievementSlideService {

    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    AchievementRepository achievementRepository;
    @Override
    //@CacheEvict(value="achievementSlideImageCache",allEntries = true)
    public  void saveAchievementSlide(AchievementDTO achievementDTO){

        Achievement achievement = modelMapper.map(achievementDTO, Achievement.class);
        achievementRepository.save(achievement);
    }

    @Override
    public AchievementDTO getImages(Long id) {
        AchievementDTO achievementDTO = null;
        final Optional<Achievement> repository = achievementRepository.findById(id);
        if (repository.isPresent()) {
            achievementDTO= modelMapper.map(repository.get(), AchievementDTO.class);
        }
        return achievementDTO;
    }

    @Override
    //@Cacheable(value = "achievementSlideImageCache")
    public List<AchievementDTO> getAllImages() {

        List<Achievement> achievements = achievementRepository.getAllImages();
        Type targetListType = new TypeToken<List<AchievementDTO>>() {
        }.getType();

        return modelMapper.map(achievements, targetListType);
    }

    @Override
   // @CacheEvict(value="deleteAchievementCache",allEntries = true)
    public  void deleteAchievementImage(Long id){
        achievementRepository.deleteById(id);
    }
}
