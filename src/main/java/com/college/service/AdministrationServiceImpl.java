package com.college.service;


import com.college.core.entity.Administration;
import com.college.core.model.AdministrationDTO;
import com.college.repository.AdministrationRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;


@Service
public class AdministrationServiceImpl implements AdministrationService {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    AdministrationRepository administrationRepository;
    @Override
    public List<AdministrationDTO> getAllAdministration() {
        List<Administration> administration= administrationRepository.findAll();
        Type targetListType = new TypeToken<List<AdministrationDTO>>() {}.getType();
        return modelMapper.map(administration, targetListType);
    }





    @Override
    @CacheEvict(value = "administrationCache", allEntries = true)
    public void saveAdministration(AdministrationDTO administrationDTO) {
        Administration administration = modelMapper.map(administrationDTO,Administration.class);
        administrationRepository.save(administration);
    }

    @Override
    @CacheEvict(value = "administrationCache", allEntries = true)
    public void deleteItem(Long id) {
        administrationRepository.deleteById(id);
    }
}
