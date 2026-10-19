package com.college.service;

import org.springframework.stereotype.Service;

/** Records who changed what (table {@code content_change_log}). */
@Service
public interface ContentChangeLogService {

    /** {@code oldValue}/{@code newValue}: a readable or JSON form of the row before/after the change (no file bytes). */
    void record(String areaCode, String entity, String entityId, String action, String oldValue, String newValue);
}
