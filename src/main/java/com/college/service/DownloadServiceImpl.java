package com.college.service;
import com.college.core.entity.Download;
import com.college.core.model.DownloadDTO;
import com.college.repository.DownloadRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DownloadServiceImpl implements DownloadService{
    Logger logger = LoggerFactory.getLogger(NoticeBoardServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();
    private final DownloadRepository downloadRepository;

    public DownloadServiceImpl(DownloadRepository downloadRepository) {
        this.downloadRepository = downloadRepository;
    }

    @Override
    public List<DownloadDTO> getAllDownload() {
        return downloadRepository.getAllDocuments().stream().map(DownloadServiceImpl::toListItem).toList();
    }

    @Override
    public List<DownloadDTO> getAllNBADocuments() {
        return downloadRepository.getAllNBADocuments().stream().map(DownloadServiceImpl::toListItem).toList();
    }

    @Override
    public DownloadDTO getDownloadDocument(Long id) {
        DownloadDTO downloadDTO = null;
        Download download = downloadRepository.findById(id).orElse(null);
        if(download != null){
            downloadDTO = modelMapper.map(download , DownloadDTO.class);
        }
        return downloadDTO;
    }

    @Override
    public void saveDownloadBoard(DownloadDTO DownloadDTO) {
        Download download = modelMapper.map(DownloadDTO , Download.class);
        downloadRepository.save(download);
    }

    @Override
    public void deleteItem(Long id) {
       downloadRepository.deleteById(id);
    }

    /** List item for the views: same values as the entity, without the file content. */
    private static DownloadDTO toListItem(Download entity) {
        DownloadDTO dto = new DownloadDTO();
        dto.setId(entity.getId());
        dto.setFileName(entity.getFileName());
        dto.setFileType(entity.getFileType());
        dto.setUploadedBy(entity.getUploadedBy());
        dto.setDate(entity.getDate());
        dto.setDocType(entity.getDocType());
        return dto;
    }
}
