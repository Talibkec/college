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

/** Leave application from the attendance app: mails the application PDF to the applicant. */
@Controller
public class LeaveMail {

    private static final Logger logger = LoggerFactory.getLogger(LeaveMail.class);

    private final LeaveMailService leaveMailService;

    public LeaveMail(LeaveMailService leaveMailService) {
        this.leaveMailService = leaveMailService;
    }

    @ResponseBody
    @RequestMapping(value = "/rl", method = RequestMethod.GET)
    public String LeaveMailController(@RequestParam("params") String params) {
        LeaveRequestDetail detail = new Gson().fromJson(params, LeaveRequestDetail.class);
        logger.debug("Leave application requested with parameters {}", params);
        leaveMailService.sendLeaveApplication(detail);
        return "";
    }
}
