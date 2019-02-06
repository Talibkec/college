package com.college.core.controller;

import com.college.core.model.FacultyDocumentsDTO;
import com.college.service.FacultyDocumentsService;
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

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;


@Controller
public class FileUploadFacultycontroller {
    @Autowired
    FacultyDocumentsService facultyDocumentsService;
    private final Logger logger = LoggerFactory.getLogger(FileUploadFacultycontroller.class);
   // private final String UPLOADED_FOLDER = getPath();


    @RequestMapping(value = "/{id}/image")
    public @ResponseBody byte[] getImage(@PathVariable("id") Long id) throws IOException {

        FacultyDocumentsDTO facultyDocumentsDTO = facultyDocumentsService.getFacultyDocument(id);
        InputStream in = new ByteArrayInputStream(facultyDocumentsDTO.getDocument());
        return IOUtils.toByteArray(in);
    }

    @ResponseBody
    @RequestMapping(value="auth/api/facultyfileUpload", method= RequestMethod.POST)
    public ResponseEntity<?> uploadFile(
            @RequestParam("facultydocumentsFile") MultipartFile uploadfile,
            @RequestParam("facultydocumentsHeader") String facultydocumentsHeader) {
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
        String userName = null;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = ((UserDetails)principal);
            userName = userDetails.getUsername();
        } else {
            userName = principal.toString();
        }
        //fileName = UploadFileUtility.saveUploadedFiles(Arrays.asList(uploadfile), UPLOADED_FOLDER);
        saveFacultydocumentsDetails(userName, facultydocumentsHeader, fileName, uploadfile);

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
