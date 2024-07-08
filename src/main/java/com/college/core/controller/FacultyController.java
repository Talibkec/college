// package com.college.core.controller;
//
//import com.college.FacultyHelper;
//import com.college.core.entity.Faculty;
//import com.college.core.entity.User;
//import com.college.core.model.FacultyDTO;
//import com.college.core.model.FacultyDocumentsDTO;
//import com.college.core.model.FacultyKeyPropValuesDTO;
//import com.college.core.model.FacultyKeyPropsDTO;
//import com.college.service.FacultyDocumentsService;
//import com.college.service.FacultyService;
//import com.college.service.UserService;
//import com.google.gson.Gson;
//import org.apache.commons.io.FilenameUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.jws.WebParam;
//import java.io.IOException;
//import java.util.Date;
//import java.util.List;
//
//@Controller
//public class FacultyController {
//    @Autowired
//    FacultyService facultyService;
//    @Autowired
//    FacultyHelper facultyHelper;
//    @Autowired
//    FacultyDocumentsService facultyDocumentsService;
//    @Autowired
//    UserService userService;
//    @ResponseBody
//    @RequestMapping(value="/uploadfile/editfacultydetails",method = RequestMethod.POST)
//    public ResponseEntity<?> editFacultyDetails(
//            @RequestParam("facultyPersonalEmail") String facultyPersonalEmail,
//            @RequestParam("facultyOfficialEmail") String facultyOfficialEmail,
//            @RequestParam("facultyMobNo") Long facultyMobNo,
//            @RequestParam("facultyName") String facultyName,
//            @RequestParam("propertykeyname[]") String keyPropertyName,
//            @RequestParam("propertykeyvalue") String keyPropVal,
//            @RequestParam("facultyId") Long facultyId){
//        FacultyDTO facultyDTO = facultyService.getFacultyById(facultyId);
//        FacultyKeyPropsDTO facultyKeyPropsDTO = new FacultyKeyPropsDTO();
//        FacultyKeyPropValuesDTO facultyKeyPropValuesDTO = new FacultyKeyPropValuesDTO();
//        facultyKeyPropsDTO.setKeyPropertyName(keyPropertyName);
//        facultyKeyPropValuesDTO.setKeyPropVal(keyPropVal);
//        facultyKeyPropsDTO.getKeyPropVals().add(facultyKeyPropValuesDTO);
//        facultyDTO.getFacultyKeyProps().add(facultyKeyPropsDTO);
//        if (StringUtils.isEmpty(facultyOfficialEmail)) {
//            facultyDTO.setFacultyOfficialEmail(facultyDTO.getFacultyOfficialEmail());
//        } else {
//            facultyDTO.setFacultyOfficialEmail(facultyOfficialEmail);
//        }
//        if (StringUtils.isEmpty(facultyPersonalEmail)) {
//            facultyDTO.setFacultyPersonalEmail(facultyDTO.getFacultyPersonalEmail());
//        } else {
//            facultyDTO.setFacultyPersonalEmail(facultyPersonalEmail);
//        }
//        if (StringUtils.isEmpty(facultyMobNo)) {
//            facultyDTO.setFacultyMobNo(facultyDTO.getFacultyMobNo());
//        } else {
//            facultyDTO.setFacultyMobNo(facultyMobNo);
//        }
//        if (StringUtils.isEmpty(facultyName)) {
//            facultyDTO.setFacultyName(facultyDTO.getFacultyName());
//        } else {
//            facultyDTO.setFacultyName(facultyName);
//        }
//
//        facultyService.saveFaculty(facultyDTO);
//        return new ResponseEntity( new HttpHeaders(), HttpStatus.OK);
//
//    }
//
//    /*@ResponseBody
//    @RequestMapping(value = "/uploadfile/facultyFileUpload", method = RequestMethod.POST)
//    public ResponseEntity<?> uploadFile(
//            @RequestParam("facultydocumentsFile") MultipartFile uploadfile,
//            @RequestParam("facultydocumentsHeader") String facultydocumentsHeader,
//            @RequestParam("isProfilePic") String isProfilePic,
//            @RequestParam("facultyPersonalEmail") String facultyPersonalEmail,
//            @RequestParam("facultyOfficialEmail") String facultyOfficialEmail,
//            @RequestParam("facultyMobNo") Long facultyMobNo,
//            @RequestParam("facultyName") String facultyName,
//            @RequestParam("propertykeyname[]") String keyPropertyName,
//            @RequestParam("propertykeyvalue") String keyPropVal,
//            @RequestParam(name = "isLink", required = false, defaultValue = "false") boolean isLink,
//            @RequestParam(name = "linkAddress", required = false) String linkAddress) {
//        String fileName = uploadfile.getOriginalFilename();
//        if (!isLink && (uploadfile.isEmpty() || StringUtils.isEmpty(facultydocumentsHeader))) {
//            String msg = "";
//            if (uploadfile.isEmpty()) {
//                msg = "Please select a file.";
//            } else {
//                msg = "Please give notice heading";
//            }
//            return new ResponseEntity(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
//        }
//        String userName = ControllerUtility.getUserName();
//        if ("Yes".equalsIgnoreCase(isProfilePic)) {
//            FacultyDTO facultyDTO = facultyService.getFaculty(userName);
//            FacultyKeyPropsDTO facultyKeyPropsDTO = new FacultyKeyPropsDTO();
//            FacultyKeyPropValuesDTO facultyKeyPropValuesDTO = new FacultyKeyPropValuesDTO();
//            facultyKeyPropsDTO.setKeyPropertyName(keyPropertyName);
//            facultyKeyPropValuesDTO.setKeyPropVal(keyPropVal);
//            facultyKeyPropsDTO.getKeyPropVals().add(facultyKeyPropValuesDTO);
//            facultyDTO.getFacultyKeyProps().add(facultyKeyPropsDTO);
//            facultyDTO.setFileType(fileName);
//            try {
//                facultyDTO.setFacultyPhoto(uploadfile.getBytes());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            facultyDTO.setFileType(FilenameUtils.getExtension(fileName));
//            if (StringUtils.isEmpty(facultyOfficialEmail)) {
//                facultyDTO.setFacultyOfficialEmail(facultyDTO.getFacultyOfficialEmail());
//            } else {
//                facultyDTO.setFacultyOfficialEmail(facultyOfficialEmail);
//            }
//            if (StringUtils.isEmpty(facultyPersonalEmail)) {
//                facultyDTO.setFacultyPersonalEmail(facultyDTO.getFacultyPersonalEmail());
//            } else {
//                facultyDTO.setFacultyPersonalEmail(facultyPersonalEmail);
//            }
//            if (StringUtils.isEmpty(facultyMobNo)) {
//                facultyDTO.setFacultyMobNo(facultyDTO.getFacultyMobNo());
//            } else {
//                facultyDTO.setFacultyMobNo(facultyMobNo);
//            }
//            if (StringUtils.isEmpty(facultyName)) {
//                facultyDTO.setFacultyName(facultyDTO.getFacultyName());
//            } else {
//                facultyDTO.setFacultyName(facultyName);
//            }
//
//            facultyService.saveFaculty(facultyDTO);
//
//        } else {
//            saveFacultydocumentsDetails(userName, facultydocumentsHeader, fileName, uploadfile, isLink, linkAddress);
//        }
//
//        String notice = "/wp-content/uploads/facultydocuments/" + fileName;
//        notice = notice + "," + facultydocumentsHeader;
//        return new ResponseEntity(notice, new HttpHeaders(), HttpStatus.OK);
//
//    }*/
//
//    private void saveFacultydocumentsDetails(String username, String facultydocumentsHeader, String fileName,
//                                             MultipartFile uploadFile, Boolean isLink, String linkAddress) {
//        FacultyDocumentsDTO facultyDocumentsDTO = new FacultyDocumentsDTO();
//        FacultyKeyPropsDTO facultyKeyPropsDTO = new FacultyKeyPropsDTO();
//        FacultyKeyPropValuesDTO facultyKeyPropValuesDTO = new FacultyKeyPropValuesDTO();
//        facultyDocumentsDTO.setHeadLine(facultydocumentsHeader);
//        facultyDocumentsDTO.setUploadedFileName(fileName);
//        facultyDocumentsDTO.setUploadedBy(username);
//        facultyDocumentsDTO.setDate(new Date());
//        facultyDocumentsDTO.setLink(isLink);
//        facultyDocumentsDTO.setLinkAddress(linkAddress);
//        try {
//            facultyDocumentsDTO.setDocument(uploadFile.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        facultyDocumentsService.saveFacultydocumentsDetails(facultyDocumentsDTO);
//    }
//
//    @RequestMapping(value = "/uploadfile/facultyFileUpload", method = RequestMethod.GET)
//    public ModelAndView displayFacultyFileUploadpage(@RequestParam("isProfilePic") String isProfilePic) {
//        ModelAndView model = new ModelAndView();
//        model.setViewName("facultyfileupload.jsp");
//        model.addObject("isProfilePic", isProfilePic);
//        return model;
//    }
//
//    @RequestMapping(value = "/facultyDetails", method = RequestMethod.GET)
//    public ModelAndView displayFacultyDetails(@RequestParam("facultyId") Long facultyId) {
//        ModelAndView model = facultyHelper.getFacultyDetailsbyId(facultyId);
//        model.setViewName("facultydetails.jsp");
//        return model;
//    }
//
//    @RequestMapping(value = "faculty")
//    public ModelAndView getFaculty(@RequestParam("deptno") Long deptno) {
//        ModelAndView mv = new ModelAndView();
//        List<FacultyDTO> facultyList = facultyService.getFacultyByDeptNo(deptno);
//        mv.addObject("facultyList", facultyList);
//        mv.setViewName("facultylist.jsp");
//        return mv;
//    }
//    @RequestMapping(value = "/uploadfile/editfacultydetails")
//    public ModelAndView editfaculty(@RequestParam("facultyId") Long facultyId) {
//        ModelAndView mv = new ModelAndView();
//        FacultyDTO facultyDTO = facultyService.getFacultyById(facultyId);
//        mv.setViewName("editfacultydetails.jsp");
//        mv.addObject("facultyDetails",facultyDTO);
//        return mv;
//    }
//
//    @RequestMapping(value="/auth/uploadfile/addfaculty")
//    public  ModelAndView addFaculty(){
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("addfaculty.jsp");
//
//        return mv;
//    }
//
//
//    @ResponseBody
//    @RequestMapping(value="/uploadfile/addfaculty",method = RequestMethod.POST)
//    public ResponseEntity<?> addFaculty(
//            @RequestParam("facultyPersonalEmail") String facultyPersonalEmail,
//            @RequestParam("facultyOfficialEmail") String facultyOfficialEmail,
//            @RequestParam("facultyMobNo") Long facultyMobNo,
//            @RequestParam("facultyName") String facultyName,
//            @RequestParam("deptId") Long deptId,
//            @RequestParam("userName") String username){
//            FacultyDTO facultyDTO = new FacultyDTO();
//            facultyDTO.setFacultyId(100L);
//            facultyDTO.setDepartmentId(deptId);
//            facultyDTO.setFacultyOfficialEmail(facultyOfficialEmail);
//            facultyDTO.setFacultyPersonalEmail(facultyPersonalEmail);
//            facultyDTO.setFacultyMobNo(facultyMobNo);
//            facultyDTO.setFacultyName(facultyName);
//             facultyDTO.getUser().setUsername(username);
//            facultyService.saveFaculty(facultyDTO);
//
//        return new ResponseEntity( new HttpHeaders(), HttpStatus.OK);
//
//    }
//
//}
//
//
