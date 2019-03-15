package com.college.core.controller;
import com.college.core.model.NoticeBoardDTO;
import com.college.service.NoticeBoardService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;

@Controller
public class FileUploadCintroller {

    @Autowired
    NoticeBoardService noticeBoardService;
    private final Logger logger = LoggerFactory.getLogger(FileUploadCintroller.class);
    //private final String UPLOADED_FOLDER = getPath();
    @ResponseBody
    @RequestMapping(value = "/auth/api/upload", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(
            @RequestParam("noticeFile") MultipartFile uploadfile, @RequestParam("noticeHeader") String noticeHeader,
            @RequestParam("noticeType") String noticeType, @RequestParam("isScrollable") Boolean isScrollable,@RequestParam("date")String date) {
        logger.debug("Single file upload!");
        String fileName = uploadfile.getOriginalFilename();

        if (uploadfile.isEmpty() || StringUtils.isEmpty(noticeHeader)) {
            String msg = "";
            if(uploadfile.isEmpty())
            {
                msg = "Please select a file.";
            }
            else {
                msg = "Please give notice heading";
            }
                return new ResponseEntity(msg,  new HttpHeaders(),HttpStatus.BAD_REQUEST);
        }


            String userName = null;

            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = ((UserDetails)principal);
                userName = userDetails.getUsername();
            } else {
                userName = principal.toString();
            }
            //fileName = UploadFileUtility.saveUploadedFiles(Arrays.asList(uploadfile), UPLOADED_FOLDER);
            saveNoticeDetails(userName, noticeHeader, noticeType,fileName,isScrollable,uploadfile,date);


        String notice = "http://localhost/wp-content/uploads/notice/" + fileName;
        notice = notice + "," + noticeHeader;
        notice = notice + "," + noticeType;
        return new ResponseEntity(notice, new HttpHeaders(), HttpStatus.OK);

    }

    private void saveNoticeDetails(String userName, String noticeHeader, String noticeType, String fileName, Boolean isScrollable, MultipartFile uploadfile, String date) {
        NoticeBoardDTO noticeBoardDTO = new NoticeBoardDTO();
        noticeBoardDTO.setHeadLine(noticeHeader);
        noticeBoardDTO.setNoticeType(noticeType);
        noticeBoardDTO.setUploadedBy(userName);
        noticeBoardDTO.setDate(date);
        noticeBoardDTO.setUploadedFileName(fileName);
        if(isScrollable) {
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
    /*public String getPath() {
        String path = this.getClass().getClassLoader().getResource("").getPath();
        String fullPath = null;
        try {
            fullPath = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("Enable to retrive the path , Please contact admin");
        }
        String pathArr[] = fullPath.split("/WEB-INF/classes/");
        fullPath = pathArr[0];
        String reponsePath = "";
        reponsePath = new File(fullPath).getPath() + File.separatorChar + "wp-content" + File.separatorChar
                + "uploads" + File.separatorChar + "notice" + File.separatorChar;
        logger.info("Full path of the folder is " + reponsePath);
        return reponsePath;
    }
*/
}
