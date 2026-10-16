package com.college.core.controller.firebase;

import com.college.firebase.AttendanceReportService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/** Attendance report request from the attendance app (the report is built and mailed asynchronously). */
@Controller
public class FacultyGenerateReport {

    private static final Logger logger = LoggerFactory.getLogger(FacultyGenerateReport.class);

    private final AttendanceReportService attendanceReportService;

    public FacultyGenerateReport(AttendanceReportService attendanceReportService) {
        this.attendanceReportService = attendanceReportService;
    }

    @ResponseBody
    @RequestMapping(value = "/rn", method = RequestMethod.GET)
    public String FirebaseReportController(@RequestParam("params") String params) {
        FacultyReportDetail detail = new Gson().fromJson(params, FacultyReportDetail.class);
        logger.debug("Attendance report requested with parameters {}", params);
        attendanceReportService.requestReport(detail);
        return "";
    }
}
