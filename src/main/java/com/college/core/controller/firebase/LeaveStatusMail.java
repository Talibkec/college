package com.college.core.controller.firebase;

import com.college.firebase.LeaveMailService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/** Leave status from the attendance app: mails the status and adds the leave to the college calendar. */
@Controller
public class LeaveStatusMail {

    private static final Logger logger = LoggerFactory.getLogger(LeaveStatusMail.class);

    private final LeaveMailService leaveMailService;

    public LeaveStatusMail(LeaveMailService leaveMailService) {
        this.leaveMailService = leaveMailService;
    }

    @ResponseBody
    @RequestMapping(value = "/rs", method = RequestMethod.GET)
    public String LeaveStatusMailController(@RequestParam("params") String params) throws IOException {
        LeaveStatusDetail detail = new Gson().fromJson(params, LeaveStatusDetail.class);
        logger.debug("Leave status mail requested with parameters {}", params);
        leaveMailService.sendLeaveStatus(detail);
        leaveMailService.addEventToCalendar(detail);
        return "";
    }
}
