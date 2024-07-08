package com.college.service;
import com.college.core.model.AdministrationDTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface AdministrationService {
    List<AdministrationDTO> getAllAdministration();

    void saveAdministration(AdministrationDTO administrationDTO);

    void deleteItem(Long id);

}

