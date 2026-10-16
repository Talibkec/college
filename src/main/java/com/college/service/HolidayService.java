package com.college.service;

import com.college.core.model.HolidayDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HolidayService {
    List<HolidayDTO> getAllDownload();

    HolidayDTO getDownloadDocument(Long id);

    void saveDownloadBoard(HolidayDTO responsibilityDocDTO);

    void deleteItem(Long id);
}
