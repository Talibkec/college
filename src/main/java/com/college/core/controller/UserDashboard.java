package com.college.core.controller;

import com.college.KECDateHelper;
import com.college.core.entity.User;
import com.college.core.model.FacultyDTO;
import com.college.core.model.ImageSlideDTO;
import com.college.core.model.NoticeBoardDTO;
import com.college.core.model.UserRoleDTO;
import com.college.service.FacultyService;
import com.college.service.ImageSlideService;
import com.college.service.NoticeBoardService;
import com.college.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
//@RequestMapping("user")
public class UserDashboard {
    @Autowired
    FacultyService facultyService;
    @Autowired
    UserService userService;
    @Autowired
    NoticeBoardService noticeBoardService;
    @Autowired
    ImageSlideService imageSlideService;

    @RequestMapping(value = "/user/dashboard")
    public ModelAndView getdashboard() {
        List<NoticeBoardDTO> list = noticeBoardService.getAllNotice(new PageRequest(0, 10));
        ModelAndView mv = new ModelAndView();
        mv.addObject("Role", ControllerUtility.getRole());
        mv.addObject("noticeList", getNoticeList(list, false));
        mv.setViewName("user/dashboard.jsp");
        return mv;

    }
    @RequestMapping(value = "/user/addPlacement")
    public ModelAndView addPlacement() {
        List<NoticeBoardDTO> list = noticeBoardService.getAllNotice(new PageRequest(0, 10));
        ModelAndView mv = new ModelAndView();
        mv.addObject("Role", ControllerUtility.getRole());
        mv.addObject("noticeList", getNoticeList(list, false));
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
    private List<NoticeBoardDTO> getNoticeList(List<NoticeBoardDTO> list, boolean scrollable) {
        List<NoticeBoardDTO> scrollingNotices = new ArrayList<>();

        for (NoticeBoardDTO dto : list) {
            dto.setFileType(("." + FilenameUtils.getExtension(dto.getUploadedFileName())));
            dto.setNoticeAge(KECDateHelper.getNoticeAge(dto));
            if (scrollable) {
                if (dto.getIsScrollable() != null && dto.getIsScrollable() == 1)
                    scrollingNotices.add(dto);
            } else if (!scrollable) {
                if (dto.getIsScrollable() == null)
                    scrollingNotices.add(dto);

            }
        }

        return scrollingNotices;
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

    private final Logger logger = LoggerFactory.getLogger(FileUploadCintroller.class);
    @ResponseBody
    @RequestMapping(value = "/user/api/upload", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(
            @RequestParam("noticeFile") MultipartFile uploadfile, @RequestParam("noticeHeader") String noticeHeader,
            @RequestParam("noticeType") String noticeType, @RequestParam("isScrollable") Boolean isScrollable, @RequestParam("date") String date) {
        logger.debug("Single file upload!");
        String fileName = uploadfile.getOriginalFilename();

     /*   if (uploadfile.isEmpty() || StringUtils.isEmpty(noticeHeader)) {
            String msg = "";
            if (uploadfile.isEmpty()) {
                msg = "Please select a file.";
            } else {
                msg = "Please give notice heading";
            }
            return new ResponseEntity(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }*/


        String userName = null;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = ((UserDetails) principal);
            userName = userDetails.getUsername();
        } else {
            userName = principal.toString();
        }
        //fileName = UploadFileUtility.saveUploadedFiles(Arrays.asList(uploadfile), UPLOADED_FOLDER);
        saveNoticeDetails(userName, noticeHeader, noticeType, fileName, isScrollable, uploadfile, date);


        String notice = "/wp-content/uploads/notice/" + fileName;
        notice = notice + "," + noticeHeader;
        notice = notice + "," + noticeType;
        return new ResponseEntity(notice, new HttpHeaders(), HttpStatus.OK);

    }
    private void saveNoticeDetails(String userName, String noticeHeader, String noticeType, String fileName, Boolean isScrollable, MultipartFile uploadfile, String date) {
        NoticeBoardDTO noticeBoardDTO = new NoticeBoardDTO();
        noticeBoardDTO.setHeadLine(noticeHeader);
        noticeBoardDTO.setNoticeType(noticeType);
        noticeBoardDTO.setUploadedBy(userName);
        setNoticeUploadDate(noticeBoardDTO, date);
        noticeBoardDTO.setUploadedFileName(fileName);
        if (isScrollable) {
            noticeBoardDTO.setIsScrollable(1);
        }
        try {
            noticeBoardDTO.setNotice(uploadfile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        noticeBoardDTO.setFileType(FilenameUtils.getExtension(fileName));
        noticeBoardService.saveNoticeBoard(noticeBoardDTO);


    }
    private static final SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
    private void setNoticeUploadDate(NoticeBoardDTO noticeBoardDTO, String date) {

        try {
            if (StringUtils.isEmpty(date)) {
                date = DATE_TIME_FORMATTER.format(new Date());
            }
            noticeBoardDTO.setDate(DATE_TIME_FORMATTER.parse(date));
        } catch (ParseException e) {
            logger.error("Could not parse the date, Please contact to admin");
        }


    }

    @ResponseBody
    @RequestMapping(value = "/user/api/uploadslideimage", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(
            @RequestParam("slideImage") MultipartFile uploadfile, @RequestParam("caption") String caption) {
        logger.debug("Single file upload!");
        String fileName = uploadfile.getOriginalFilename();
        String fileType = uploadfile.getContentType();
        if (uploadfile.isEmpty() || StringUtils.isEmpty(caption)) {
            String msg = "";
            if (uploadfile.isEmpty()) {
                msg = "Please select a file.";
            } else {
                msg = "Please give a proper caption";
            }
            return new ResponseEntity(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        try {
            String msg;
            BufferedImage image = ImageIO.read(uploadfile.getInputStream());
            int h = image.getHeight();
            int b = image.getWidth();
            logger.info("Image resolution is " + h + "  " + b);
            if(b != 2000 || h != 550){
                msg = "Kindly Resize Your image in 2000 * 550 format";
                return new ResponseEntity(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //fileName = UploadFileUtility.saveUploadedFiles(Arrays.asList(uploadfile), UPLOADED_FOLDER);
        saveImageSlide(uploadfile, caption, fileName, fileType);
        //return new ResponseEntity(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);


        return new ResponseEntity("Document uploaded successfully", new HttpHeaders(), HttpStatus.OK);

    }
    private void saveImageSlide(MultipartFile uploadfile, String caption, String fileName, String fileType) {
        ImageSlideDTO imageSlideDTO = new ImageSlideDTO();
        imageSlideDTO.setCaption(caption);
        imageSlideDTO.setFileName(fileName);
        imageSlideDTO.setFileType(fileType);
        try {
            imageSlideDTO.setImage(uploadfile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageSlideDTO.setFileType(FilenameUtils.getExtension(fileName));
        imageSlideService.saveImageSlide(imageSlideDTO);
    }
}
