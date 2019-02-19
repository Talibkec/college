package com.college.core.controller;

import com.college.core.model.FacultyDTO;
import com.college.core.model.FacultyDocumentsDTO;
import com.college.service.FacultyDocumentsService;
import com.college.service.FacultyService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
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
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;


@Controller
public class FileUploadFacultycontroller {
    @Autowired
    FacultyDocumentsService facultyDocumentsService;

    @Autowired
    FacultyService facultyService;
    private final Logger logger = LoggerFactory.getLogger(FileUploadFacultycontroller.class);
   // private final String UPLOADED_FOLDER = getPath();


    @RequestMapping(value = "/{id}/image")
    public @ResponseBody byte[] getImage(@PathVariable("id") Long id) throws IOException {

        FacultyDTO facultyDTO = facultyService.getFacultyById(id);
        if(facultyDTO.getFacultyPhoto() != null) {
            InputStream in = new ByteArrayInputStream(facultyDTO.getFacultyPhoto());
            return IOUtils.toByteArray(in);
        }
        return null;
    }
    @RequestMapping(value ="/{id}/documents")
    public @ResponseBody byte[] getdocuments( @PathVariable("id")Long id) throws IOException{
        FacultyDocumentsDTO facultyDocumentsDTO =facultyDocumentsService.getFacultyDocument(id);
        if(facultyDocumentsDTO.getDocument()!= null){
            InputStream in = new ByteArrayInputStream(facultyDocumentsDTO.getDocument());
            return  IOUtils.toByteArray(in);
        }
        return null;
    }


    @RequestMapping(value = "auth/deleteFacultyDoc/{id}")
    public  void deleteFacultyDoc(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) throws IOException {

        facultyDocumentsService.deleteFacultyDoc(id);
        String url = request.getHeader("Referer");
        if(StringUtils.isEmpty(url)){
            url = "/";
        }
        response.sendRedirect(url);
    }

    @ResponseBody
    @RequestMapping(value="auth/api/facultyfileUpload", method= RequestMethod.POST)
    public ResponseEntity<?> uploadFile(
            @RequestParam("facultydocumentsFile") MultipartFile uploadfile,
            @RequestParam("facultydocumentsHeader") String facultydocumentsHeader,
            @RequestParam("isProfilePic") String isProfilePic,
            @RequestParam("facultyPersonalEmail") String facultyPersonalEmail,
            @RequestParam("facultyOfficialEmail") String facultyOfficialEmail,
            @RequestParam("facultyMobNo") Long facultyMobNo,
            @RequestParam("facultyName") String facultyName) {
        logger.debug("Single file Upload");
        String fileName = uploadfile.getOriginalFilename();
        if (uploadfile.isEmpty() || StringUtils.isEmpty(facultydocumentsHeader)) {
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
        String userName = ControllerUtility.getUserName();
        if("Yes".equalsIgnoreCase(isProfilePic)){
            FacultyDTO facultyDTO = facultyService.getFaculty(userName);
            facultyDTO.setFileType(fileName);
            try {
                facultyDTO.setFacultyPhoto(uploadfile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            facultyDTO.setFileType(FilenameUtils.getExtension(fileName));
            if(StringUtils.isEmpty(facultyOfficialEmail)){
                facultyDTO.setFacultyOfficialEmail(facultyDTO.getFacultyOfficialEmail());
            }
            else {
                facultyDTO.setFacultyOfficialEmail(facultyOfficialEmail);
            }
            if(StringUtils.isEmpty(facultyPersonalEmail)){
                facultyDTO.setFacultyPersonalEmail(facultyDTO.getFacultyPersonalEmail());
            }
            else {
                facultyDTO.setFacultyPersonalEmail(facultyPersonalEmail);
            }
            if(StringUtils.isEmpty(facultyMobNo)){
                facultyDTO.setFacultyMobNo(facultyDTO.getFacultyMobNo());
            }
            else {
                facultyDTO.setFacultyMobNo(facultyMobNo);
            }
            if(StringUtils.isEmpty(facultyName)){
                facultyDTO.setFacultyName(facultyDTO.getFacultyName());
            }
            else{
                facultyDTO.setFacultyName(facultyName);
            }

            facultyService.saveFaculty(facultyDTO);
        }
        else {
            saveFacultydocumentsDetails(userName, facultydocumentsHeader, fileName, uploadfile);
        }

        String notice = "http://localhost/wp-content/uploads/facultydocuments/" + fileName;
        notice = notice + "," + facultydocumentsHeader;
        return new ResponseEntity(notice, new HttpHeaders(), HttpStatus.OK);

    }
    private void saveFacultydocumentsDetails(String username, String facultydocumentsHeader, String fileName, MultipartFile uploadFile){
        FacultyDocumentsDTO facultyDocumentsDTO =new FacultyDocumentsDTO();
        facultyDocumentsDTO.setHeadLine(facultydocumentsHeader);
        facultyDocumentsDTO.setUploadedFileName(fileName);
        facultyDocumentsDTO.setUploadedBy(username);
        facultyDocumentsDTO.setDate(new Date());
        try {
            facultyDocumentsDTO.setDocument(uploadFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        facultyDocumentsService.saveFacultydocumentsDetails(facultyDocumentsDTO);


    }

   /* public String getPath() {
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
                + "uploads" + File.separatorChar + "facultydocuments" + File.separatorChar;
        logger.info("Full path of the folder is " + reponsePath);
        return reponsePath;
    }*/

}
