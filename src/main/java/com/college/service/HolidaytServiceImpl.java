package com.college.service;
import com.college.core.entity.Holiday;
import com.college.core.entity.ResponsibilityDoc;
import com.college.core.model.HolidayDTO;
import com.college.core.model.ResponsibilityDocDTO;
import com.college.repository.HolidayRepository;
import com.college.repository.ResponsibilityDocRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidaytServiceImpl implements HolidayService{
    Logger logger = LoggerFactory.getLogger(NoticeBoardServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    HolidayRepository holidayRepository;
    @Override
    public List<Holiday> getAllDownload() {
        return holidayRepository.getAllDocuments();
    }

    @Override
    public HolidayDTO getDownloadDocument(Long id) {
        HolidayDTO downloadDTO = null;
        Holiday holiday  = holidayRepository.findOne(id);
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
        holidayRepository.delete(id);
    }
}
