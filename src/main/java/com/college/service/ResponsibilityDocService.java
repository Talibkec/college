package com.college.service;

import com.college.core.entity.Download;
import com.college.core.entity.ResponsibilityDoc;
import com.college.core.model.DownloadDTO;
import com.college.core.model.ResponsibilityDocDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResponsibilityDocService {
    List<ResponsibilityDoc> getAllDownload();

    ResponsibilityDocDTO getDownloadDocument(Long id);


    void saveDownloadBoard(ResponsibilityDocDTO responsibilityDocDTO);

    void deleteItem(Long id);
}
