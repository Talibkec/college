package com.college;

import com.college.core.model.NoticeBoardDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class KECDateHelper {
    private static final Logger logger = LoggerFactory.getLogger(KECDateHelper.class);
    private static final SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");


    public static Integer getNoticeAge(NoticeBoardDTO dto) {
        int daysdiff = 999;
        long today = new Date().getTime();
        if (dto.getDate() != null) {
            long diff = dto.getDate().getTime() - today;
            long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
            daysdiff = (int) diffDays;
        }
        return daysdiff;
    }
}
