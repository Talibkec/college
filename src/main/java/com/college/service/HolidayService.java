package com.college.service;

import com.college.core.entity.Holiday;
import com.college.core.entity.ResponsibilityDoc;
import com.college.core.model.HolidayDTO;
import com.college.core.model.ResponsibilityDocDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HolidayService {
    List<Holiday> getAllDownload();

    HolidayDTO getDownloadDocument(Long id);


    void saveDownloadBoard(HolidayDTO responsibilityDocDTO);

    void deleteItem(Long id);
}
