package com.college.core.controller;

import com.college.core.model.NoticeBoardDTO;
import com.college.service.NoticeBoardService;
import com.college.service.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
//@RequestMapping("user")
public class UserDashboard {
    private final NoticeBoardService noticeBoardService;
    private final UploadService uploadService;

    public UserDashboard(NoticeBoardService noticeBoardService, UploadService uploadService) {
        this.noticeBoardService = noticeBoardService;
        this.uploadService = uploadService;
    }

    @RequestMapping(value = "/user/dashboard")
    public ModelAndView getdashboard() {
        List<NoticeBoardDTO> list = noticeBoardService.getAllNotice(PageRequest.of(0, 10));
        ModelAndView mv = new ModelAndView();
        mv.addObject("Role", ControllerUtility.getRole());
        mv.addObject("noticeList", ControllerUtility.filterNotices(list, false));
        mv.setViewName("user/dashboard.jsp");
        return mv;

    }
    @RequestMapping(value = "/user/addPlacement")
    public ModelAndView addPlacement() {
        List<NoticeBoardDTO> list = noticeBoardService.getAllNotice(PageRequest.of(0, 10));
        ModelAndView mv = new ModelAndView();
        mv.addObject("Role", ControllerUtility.getRole());
        mv.addObject("noticeList", ControllerUtility.filterNotices(list, false));
        mv.setViewName("addPlacement.jsp");
        return mv;

    }
    @RequestMapping(value = "/user/uploadfile/upload", method = RequestMethod.GET)
    public ModelAndView displayUploadFilePage() {
        ModelAndView model = new ModelAndView();
        //model.addObject("users", getUsers());
        model.setViewName("uploadfileByFaculty.jsp");
        return model;
    }



    @RequestMapping(value = "/user/uploadAICTEapproval")
    public ModelAndView uploadAICTEapproval() {
        ModelAndView model = new ModelAndView();
        //model.addObject("users", getUsers());
        model.setViewName("uploadAICTEapproval.jsp");
        return model;

    }
    @RequestMapping(value = "/user/uploadfile/uploadslideimage", method = RequestMethod.GET)
    public ModelAndView displaySlideImage() {
        ModelAndView model = new ModelAndView();
        //model.addObject("users", getUsers());
        model.setViewName("uploadslideimageByFaculty.jsp");
        return model;
    }

    @RequestMapping(value = "/user/uploadDownload")
    public ModelAndView uploadDownload() {
        ModelAndView model = new ModelAndView();
        //model.addObject("users", getUsers());
        model.setViewName("uploadDownload.jsp");
        return model;

    }
    @RequestMapping(value = "/user/uploadResponsibilityDoc")
    public ModelAndView uploadResponsibilityDoc() {
        ModelAndView model = new ModelAndView();
        //model.addObject("users", getUsers());
        model.setViewName("uploadResponsibilityDoc.jsp");
        return model;

    }
    @RequestMapping(value = "/user/uploadHoliday")
    public ModelAndView uploadHoliday() {
        ModelAndView model = new ModelAndView();
        //model.addObject("users", getUsers());
        model.setViewName("uploadHoliday.jsp");
        return model;

    }

    private static final Logger logger = LoggerFactory.getLogger(UserDashboard.class);

    @ResponseBody
    @RequestMapping(value = "/user/api/upload", method = RequestMethod.POST)
    public ResponseEntity<String> uploadFile(
            @RequestParam("noticeFile") MultipartFile uploadfile, @RequestParam("noticeHeader") String noticeHeader,
            @RequestParam("noticeType") String noticeType, @RequestParam("link") String link,
            @RequestParam("isScrollable") Boolean isScrollable, @RequestParam("date") String date) {
        logger.debug("Single file upload!");
        uploadService.saveNotice(ControllerUtility.getPrincipalName(), noticeHeader, noticeType, link, isScrollable,
                uploadfile, date);
        String body = ControllerUtility.noticeUploadResponse(uploadfile.getOriginalFilename(), noticeHeader, noticeType);
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.OK);
    }

    /** Faculty slide upload: same as the admin upload with flag 0 (desktop, 2000x550). */
    @ResponseBody
    @RequestMapping(value = "/user/api/uploadslideimage", method = RequestMethod.POST)
    public ResponseEntity<String> uploadFile(
            @RequestParam("slideImage") MultipartFile uploadfile, @RequestParam("caption") String caption) {
        logger.debug("Single file upload!");
        String problem = uploadService.slideImageProblem(uploadfile, caption, 0);
        if (problem != null) {
            return new ResponseEntity<>(problem, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        uploadService.saveSlideImage(uploadfile, caption, 0);
        return new ResponseEntity<>("Document uploaded successfully", new HttpHeaders(), HttpStatus.OK);
    }
}
