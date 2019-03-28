package com.college.core.controller.store;

import com.college.FacultyHelper;
import com.college.core.controller.ControllerUtility;
import com.college.core.model.FacultyDTO;
import com.college.core.model.FacultyDocumentsDTO;
import com.college.service.FacultyDocumentsService;
import com.college.service.FacultyService;
import com.college.service.ProductService;
import com.google.gson.JsonObject;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class CommonResourceController {

    @Autowired
    ProductService productService;
    @Autowired
    FacultyService facultyService;
    @Autowired
    FacultyHelper facultyHelper;
    @Autowired
    FacultyDocumentsService facultyDocumentsService;

    @ResponseBody
    @RequestMapping(value = "common/store/getProductName", method = RequestMethod.GET)
    public List<String> getProductName(@RequestParam("prodName") String prodName) {
        List<String> productNames = productService.prodName(prodName);
        return productNames;
    }

    @ResponseBody
    @RequestMapping(value = "common/store/getVendorName", method = RequestMethod.GET)
    public List<String> getVendorName(@RequestParam("vendorName") String vendorName) {
        List<String> productNames = productService.vendorName(vendorName);
        return productNames;
    }

    @ResponseBody
    @RequestMapping(value = "/common/store/facultyNameAutocomplete", method = RequestMethod.GET)
    public List<String> facultyNameAutocomplete(@RequestParam("facultyName") String facultyName) {
        List<FacultyDTO> facultyNames = facultyService.searchFacultyName(facultyName);
        return facultyHelper.facultyNames(facultyNames);
    }

    @ResponseBody
    @RequestMapping(value = "/common/store/searchFacultyName", method = RequestMethod.GET)
    public List<FacultyDTO> searchFacultyName(@RequestParam("facultyName") String facultyName) {
        List<FacultyDTO> facultyNames = facultyService.searchFacultyName(facultyName);
        return facultyNames;
    }

    @ResponseBody
    @RequestMapping(value = "/common/store/sm/searchFacultyName", method = RequestMethod.GET)
    public String searchFacultyByName(@RequestParam("facultyName") String facultyNames, Model model){

        List<FacultyDTO> faculties = facultyService.getFacultyByName(facultyNames);
        JsonObject obj = null;
        if(faculties.size() > 0) {
            obj = new JsonObject();
            FacultyDTO facultyDTO = faculties.get(0);
            obj.addProperty("name", facultyDTO.getFacultyName());
            obj.addProperty("id", facultyDTO.getFacultyId());
        }
        if(obj == null){
            return null;
        }
        return  obj.toString();
    }

    @ResponseBody
    @RequestMapping(value="/uploadfile/facultyFileUpload", method= RequestMethod.POST)
    public ResponseEntity<?> uploadFile(
            @RequestParam("facultydocumentsFile") MultipartFile uploadfile,
            @RequestParam("facultydocumentsHeader") String facultydocumentsHeader,
            @RequestParam("isProfilePic") String isProfilePic,
            @RequestParam("facultyPersonalEmail") String facultyPersonalEmail,
            @RequestParam("facultyOfficialEmail") String facultyOfficialEmail,
            @RequestParam("facultyMobNo") Long facultyMobNo,
            @RequestParam("facultyName") String facultyName) {
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
            return new ResponseEntity(msg,  new HttpHeaders(), HttpStatus.BAD_REQUEST);
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

        String notice = "http://keck.ac.in/wp-content/uploads/facultydocuments/" + fileName;
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

    @RequestMapping(value = "/uploadfile/facultyFileUpload", method = RequestMethod.GET)
    public ModelAndView displayFacultyFileUploadpage(@RequestParam("isProfilePic") String isProfilePic) {
        ModelAndView model = new ModelAndView();
        model.setViewName("facultyfileupload.jsp");
        model.addObject("isProfilePic", isProfilePic);
        return model;
    }
}
