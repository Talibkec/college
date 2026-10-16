package com.college.service;

import com.college.core.model.DownloadDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DownloadService {
    List<DownloadDTO> getAllDownload();

    List<DownloadDTO> getAllNBADocuments();

    DownloadDTO getDownloadDocument(Long id);


    void saveDownloadBoard(DownloadDTO DownloadBoardDTO);

    void deleteItem(Long id);
}
