package com.college.service;

import com.college.core.controller.ControllerUtility;
import com.college.core.entity.ContentChangeLog;
import com.college.repository.ContentChangeLogRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ContentChangeLogServiceImpl implements ContentChangeLogService {
    private final ContentChangeLogRepository contentChangeLogRepository;

    public ContentChangeLogServiceImpl(ContentChangeLogRepository contentChangeLogRepository) {
        this.contentChangeLogRepository = contentChangeLogRepository;
    }

    @Override
    public void record(String areaCode, String entity, String entityId, String action, String oldValue, String newValue) {
        ContentChangeLog log = new ContentChangeLog();
        log.setAreaCode(areaCode);
        log.setEntity(entity);
        log.setEntityId(entityId);
        log.setAction(action);
        log.setOldValue(oldValue);
        log.setNewValue(newValue);
        log.setChangedBy(ControllerUtility.getPrincipalName());
        log.setChangedAt(new Date());
        contentChangeLogRepository.save(log);
    }
}
