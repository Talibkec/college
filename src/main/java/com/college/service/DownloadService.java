package com.college.service;

import com.college.core.entity.Download;
import com.college.core.model.DownloadDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DownloadService {
    List<Download> getAllDownload();

    DownloadDTO getDownloadDocument(Long id);


    void saveDownloadBoard(DownloadDTO DownloadBoardDTO);

    void deleteItem(Long id);
}
