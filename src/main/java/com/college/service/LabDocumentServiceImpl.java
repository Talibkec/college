package com.college.service;
import com.college.core.entity.LabDocument;
import com.college.core.model.LabDocumentDTO;
import com.college.repository.LabDocumentRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabDocumentServiceImpl implements LabDocumentService{

    Logger logger = LoggerFactory.getLogger(NoticeBoardServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();
    private final LabDocumentRepository labDocumentRepository;

    public LabDocumentServiceImpl(LabDocumentRepository labDocumentRepository) {
        this.labDocumentRepository = labDocumentRepository;
    }

    @Override
    public List<LabDocumentDTO> getAllDownload() {
        return labDocumentRepository.getAllDocuments().stream().map(LabDocumentServiceImpl::toListItem).toList();
    }

    @Override
    public LabDocumentDTO getDownloadDocument(Long id) {
        LabDocumentDTO downloadDTO = null;
        LabDocument holiday  = labDocumentRepository.findById(id).orElse(null);
        if(holiday != null){
            downloadDTO = modelMapper.map(holiday , LabDocumentDTO.class);
        }
        return downloadDTO;
    }

    @Override
    public void saveDownloadBoard(LabDocumentDTO holidayDTO) {
        LabDocument responsibilityDoc = modelMapper.map(holidayDTO , LabDocument.class);
        labDocumentRepository.save(responsibilityDoc);
    }

    @Override
    public void deleteItem(Long id) {
        labDocumentRepository.deleteById(id);
    }

    @Override
    public List<LabDocumentDTO> getAllLabDocByDepId(Integer deptId) {
        return labDocumentRepository.getLabByDeptId(deptId).stream().map(LabDocumentServiceImpl::toListItem).toList();
    }

    /** List item for the views (the list queries select only these columns, not the file content). */
    private static LabDocumentDTO toListItem(LabDocument entity) {
        LabDocumentDTO dto = new LabDocumentDTO();
        dto.setId(entity.getId());
        dto.setFileName(entity.getFileName());
        dto.setFileType(entity.getFileType());
        dto.setUploadedBy(entity.getUploadedBy());
        dto.setDate(entity.getDate());
        return dto;
    }
}
