package com.college;

import com.college.core.model.NoticeBoardDTO;
import com.college.service.NoticeBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class NoticeBoardHelper {

    private Integer pazeSize = 10;
    @Autowired
    NoticeBoardService noticeBoardService;
    public List<NoticeBoardDTO> getNotices(String deptno, Integer pageNo) {
        List<NoticeBoardDTO> noticeBoardDTOS = null;

        Pageable pageable = new PageRequest(pageNo, pazeSize);

        if("CSE".equalsIgnoreCase(deptno)){
            return noticeBoardService.getCseNotices(pageable);
        }

        else if("EEE".equalsIgnoreCase(deptno)){
            return  noticeBoardService.getEecNotices(pageable);
        }

        else if("CIVIL".equalsIgnoreCase(deptno)){
            return  noticeBoardService.getCivilNotices(pageable);
        }
        else if("MECHANICAL".equalsIgnoreCase(deptno)){
            return noticeBoardService.getMechNotices(pageable);
        }

        else if("ASH".equalsIgnoreCase(deptno)){
            return  noticeBoardService.getApplidScienceNotices(pageable);
        }

        return noticeBoardService.getAllNotice(pageable);
    }

    public String getViewName(String deptno) {

        String viewName = "category/notices.jsp";
        if("CSE".equalsIgnoreCase(deptno)){
            viewName = "department/cse/notice.jsp";
        }

        else if("EEE".equalsIgnoreCase(deptno)){
           viewName = "department/eee/notice.jsp";
        }

        else if("CIVIL".equalsIgnoreCase(deptno)){
            viewName = "department/civil/notice.jsp";
        }
        else if("MECHANICAL".equalsIgnoreCase(deptno)){
            viewName = "department/mechanical/notice.jsp";
        }

        else if("ASH".equalsIgnoreCase(deptno)){
            viewName = "department/ash/notice.jsp";
        }
        return  viewName;
    }
}
