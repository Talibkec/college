package com.college.core.controller;

import com.college.service.NoticeBoardService;
import com.college.sms.MessageSender;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.catalina.connector.ClientAbortException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

@Controller
public class DashboardController {

    Logger logger = LoggerFactory.getLogger(DashboardController.class);
    @Autowired
    NoticeBoardService noticeBoardService;


    @Autowired
    MessageSender messageSender;
    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    private static final SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");


    //http://localhost/wp-content/themes/kec/rti-kec/rti-act
    @RequestMapping(value = "/wp-content/themes/kec/rti-kec/rti-act", method = RequestMethod.GET)
    public ModelAndView approvalkec() {
        ModelAndView model = new ModelAndView();
        //model.addObject("users", getUsers());
        model.setViewName("rti-kec/rti-act");
        return model;
    }

    @RequestMapping(value = "/auth/uploadfile/upload", method = RequestMethod.GET)
    public ModelAndView displayUploadFilePage() {
        ModelAndView model = new ModelAndView();
        //model.addObject("users", getUsers());
        model.setViewName("uploadfile.jsp");
        return model;
    }

    @RequestMapping(value = "/hod/uploadfile/upload", method = RequestMethod.GET)
    public ModelAndView displayUploadFilePageHOD() {
        ModelAndView model = new ModelAndView();
        model.addObject("Role", ControllerUtility.getRole());
        model.addObject("UserName", ControllerUtility.getUserName());
        //model.addObject("users", getUsers());
        model.setViewName("HODFileUpload.jsp");
        return model;
    }

    @RequestMapping(value = "/wp-content/themes/kec/admin/homepage", method = RequestMethod.GET)
    public ModelAndView adminHomePage() {
        ModelAndView model = new ModelAndView();
        //model.addObject("users", getUsers());
        model.setViewName("admin/adminhomepage");
        return model;
    }


    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView dashboard() {
        ModelAndView model = new ModelAndView();
        Boolean isauth = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        if (isauth) {
            System.out.println("I am already authenticated.");
        }
        //model.addObject("users", getUsers());
        model.setViewName("dashboard.jsp");
        return model;
    }

    @RequestMapping(value = "/auth/sendMessage", method = RequestMethod.GET)
    public ModelAndView sendMessageGET() {
        ModelAndView model = new ModelAndView();
        model.setViewName("sms.jsp");
        return model;
    }

    @RequestMapping(value = "/auth/sendMessage", method = RequestMethod.POST)
    public ModelAndView sendMessagePOST(@RequestParam("smsGroup") String smsGroup,
                                        @RequestParam("message") String message, @RequestParam("numberInput") String numberInput) {

        ModelAndView model = new ModelAndView();
        if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            model.setViewName("login.jsp");
        }

        messageSender.sendSMSGroup(smsGroup, message, numberInput);
        model.setViewName("sms.jsp");
        return model;
    }

    @RequestMapping(value = "/exception", method = RequestMethod.GET)
    public void testException() throws ClientAbortException {
        throw new ClientAbortException("Broken pipe has occured");
    }

    @ResponseBody
    @RequestMapping(value = "/homePageImage/{imageName}/{imageType}", method = RequestMethod.GET)
    @Cacheable(cacheNames = "homepageimage", key = "#name")
    public byte[] image(@PathVariable("imageName") String name, @PathVariable("imageType") String type, HttpServletRequest request) throws IOException {

        logger.info("Image is being served from disk");
        String imagePath = "/sites/default/files/compressed/" + name + "." + type;
        InputStream is = request.getSession().getServletContext().getResourceAsStream(imagePath);
        return IOUtils.toByteArray(is);
    }

}
