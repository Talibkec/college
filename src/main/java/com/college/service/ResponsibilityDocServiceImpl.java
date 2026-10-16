package com.college.service;
import com.college.core.entity.ResponsibilityDoc;
import com.college.core.model.ResponsibilityDocDTO;
import com.college.repository.ResponsibilityDocRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsibilityDocServiceImpl implements ResponsibilityDocService{
    Logger logger = LoggerFactory.getLogger(NoticeBoardServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();
    private final ResponsibilityDocRepository responsibilityDocRepository;

    public ResponsibilityDocServiceImpl(ResponsibilityDocRepository responsibilityDocRepository) {
        this.responsibilityDocRepository = responsibilityDocRepository;
    }

    @Override
    public List<ResponsibilityDocDTO> getAllDownload() {
        return responsibilityDocRepository.getAllDocuments().stream().map(ResponsibilityDocServiceImpl::toListItem).toList();
    }

    @Override
    public ResponsibilityDocDTO getDownloadDocument(Long id) {
        ResponsibilityDocDTO downloadDTO = null;
        ResponsibilityDoc responsibilityDoc = responsibilityDocRepository.findById(id).orElse(null);
        if(responsibilityDoc != null){
            downloadDTO = modelMapper.map(responsibilityDoc , ResponsibilityDocDTO.class);
        }
        return downloadDTO;
    }

    @Override
    public void saveDownloadBoard(ResponsibilityDocDTO responsibilityDocDTO) {
        ResponsibilityDoc responsibilityDoc = modelMapper.map(responsibilityDocDTO , ResponsibilityDoc.class);
        responsibilityDocRepository.save(responsibilityDoc);
    }

    @Override
    public void deleteItem(Long id) {
        responsibilityDocRepository.deleteById(id);
    }

    /** List item for the views: same values as the entity, without the file content. */
    private static ResponsibilityDocDTO toListItem(ResponsibilityDoc entity) {
        ResponsibilityDocDTO dto = new ResponsibilityDocDTO();
        dto.setId(entity.getId());
        dto.setFileName(entity.getFileName());
        dto.setFileType(entity.getFileType());
        dto.setUploadedBy(entity.getUploadedBy());
        dto.setDate(entity.getDate());
        return dto;
    }
}
