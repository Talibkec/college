package com.college.service;
import com.college.core.entity.Holiday;
import com.college.core.model.HolidayDTO;
import com.college.repository.HolidayRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidaytServiceImpl implements HolidayService{
    Logger logger = LoggerFactory.getLogger(NoticeBoardServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();
    private final HolidayRepository holidayRepository;

    public HolidaytServiceImpl(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    @Override
    public List<HolidayDTO> getAllDownload() {
        return holidayRepository.getAllDocuments().stream().map(HolidaytServiceImpl::toListItem).toList();
    }

    @Override
    public HolidayDTO getDownloadDocument(Long id) {
        HolidayDTO downloadDTO = null;
        Holiday holiday  = holidayRepository.findById(id).orElse(null);
        if(holiday != null){
            downloadDTO = modelMapper.map(holiday , HolidayDTO.class);
        }
        return downloadDTO;
    }

    @Override
    public void saveDownloadBoard(HolidayDTO holidayDTO) {
        Holiday responsibilityDoc = modelMapper.map(holidayDTO , Holiday.class);
        holidayRepository.save(responsibilityDoc);
    }

    @Override
    public void deleteItem(Long id) {
        holidayRepository.deleteById(id);
    }

    /** List item for the views: same values as the entity, without the file content. */
    private static HolidayDTO toListItem(Holiday entity) {
        HolidayDTO dto = new HolidayDTO();
        dto.setId(entity.getId());
        dto.setFileName(entity.getFileName());
        dto.setFileType(entity.getFileType());
        dto.setUploadedBy(entity.getUploadedBy());
        dto.setDate(entity.getDate());
        return dto;
    }
}
