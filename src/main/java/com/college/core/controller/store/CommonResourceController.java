package com.college.core.controller.store;

import com.college.FacultyHelper;
import com.college.core.controller.ControllerUtility;
import com.college.core.entity.FacultyKeyPropValues;
import com.college.core.entity.User;
import com.college.core.model.*;
import com.college.repository.FacultyKeyPropsRepository;
import com.college.repository.FacultyKeyPrpsValueRepository;
import com.college.service.*;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.jndi.toolkit.url.Uri;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.RedirectViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.*;


@Controller
public class CommonResourceController {

    @Autowired
    ProductService productService;
    @Autowired
    FacultyService facultyService;
    @Autowired
    AdministrationService administrationService;
    @Autowired
    FacultyHelper facultyHelper;
    @Autowired
    FacultyDocumentsService facultyDocumentsService;
    @Autowired
    FacultyKeyPropsRepository facultyKeyPropsRepository;
    @Autowired
    FacultyKeyPrpsValueRepository facultyKeyPrpsValueRepository;
    @Autowired
    Gson gson;
    @Autowired
    UserService userService;
    @Autowired
    DepartmentService departmentService;


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
     @RequestMapping(value = "/common/store/facultyEmailAutocomplete", method = RequestMethod.GET)
      public List<String> facultyEmailAutocomplete(@RequestParam("facultyEmail") String facultyEmail) {
      List<FacultyDTO> facultyEmails = facultyService.searchFacultyEmail(facultyEmail);
       return facultyHelper.facultyEmails(facultyEmails); 
}

    @ResponseBody
    @RequestMapping(value="/user/userNameAutocomplete", method =RequestMethod.GET)
    public List<String> userNameAutocomplete(@RequestParam("userName") String userName){
            List<String> usernames = userService.searchUserName(userName);
            return  usernames;
    }

    @ResponseBody
    @RequestMapping(value="/faculty/facultyNameAutocompletes", method = RequestMethod.GET)
    public List<String> facultyNameAutocompletes(@RequestParam("facultyName") String facultyName) {
        List<FacultyDTO> facultyNames = facultyService.searchFacultyName(facultyName);
        return facultyHelper.facultyNames(facultyNames);
    }

    @ResponseBody
    @RequestMapping(value = "/faculty/searchFacultyNames", method = RequestMethod.GET)
    public String searchFacultyByNames(@RequestParam("facultyName") String facultyNames, Model model) {

        List<FacultyDTO> faculties = facultyService.getFacultyByName(facultyNames);
        JsonObject obj = null;
        if (faculties.size() > 0) {
            obj = new JsonObject();
            
            FacultyDTO facultyDTO = faculties.get(0);
            obj.addProperty("name", facultyDTO.getFacultyName());
            obj.addProperty("id", facultyDTO.getFacultyId());
        }
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/common/store/searchFacultyName", method = RequestMethod.GET)
    public List<FacultyDTO> searchFacultyName(@RequestParam("facultyName") String facultyName) {
        List<FacultyDTO> facultyNames = facultyService.searchFacultyName(facultyName);
        return facultyNames;
    }

    @ResponseBody
    @RequestMapping(value = "/common/store/sm/searchFacultyName", method = RequestMethod.GET)
    public String searchFacultyByName(@RequestParam("facultyName") String facultyNames, Model model) {

        List<FacultyDTO> faculties = facultyService.getFacultyByName(facultyNames);
        JsonObject obj = null;
        if (faculties.size() > 0) {
            obj = new JsonObject();
            FacultyDTO facultyDTO = faculties.get(0);
            obj.addProperty("name", facultyDTO.getFacultyName());
            obj.addProperty("id", facultyDTO.getFacultyId());
        }
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    @ResponseBody
    @RequestMapping(value="/uploadfile/editfacultydetails",method = RequestMethod.POST)
    public  ResponseEntity<?>editFacultyDetails(
            @RequestParam("facultyPersonalEmail") String facultyPersonalEmail,
            @RequestParam("facultyOfficialEmail") String facultyOfficialEmail,
            @RequestParam("facultyMobNo") Long facultyMobNo,
            @RequestParam("facultyName") String facultyName,
            @RequestParam("facultyId") Long facultyId,
            @RequestParam("newProps") String newProps,
            @RequestParam("oldProps") String oldProps,
            @RequestParam("oldKeyOrder") List<String> oldKeyOrder,
            @RequestParam("newKeyOrder") List<String> newKeyOrder,
            @RequestParam("facultyDetails") String facultyDetails){
        
        
        System.out.println("oldKeyOrder = " + oldKeyOrder);
        System.out.println("newKeyOrder = " + newKeyOrder);
        FacultyDTO newFacultyDTO = new FacultyDTO();
        FacultyDTO gsonFacultyObject = gson.fromJson(facultyDetails, FacultyDTO.class);
        FacultyKeyPropsDTO newFacultyKeyPropsDTO = new FacultyKeyPropsDTO();
        Type type = new TypeToken<LinkedHashMap<String, List<String>>>(){}.getType();
        FacultyDTO oldFacultyDTO = facultyService.getFacultyById(facultyId);
        removeDefaultValues(gsonFacultyObject, oldFacultyDTO);
        BeanUtils.copyProperties(oldFacultyDTO, newFacultyDTO);
        //addProperties(newFacultyDTO, newPropsMap, newKeyOrder, newFacultyKeyPropsDTO);
        if (!StringUtils.isEmpty(facultyOfficialEmail)) {
            newFacultyDTO.setFacultyOfficialEmail(facultyOfficialEmail);
        }
        if (!StringUtils.isEmpty(facultyPersonalEmail)) {
            newFacultyDTO.setFacultyPersonalEmail(facultyPersonalEmail);
        }
        if (!StringUtils.isEmpty(facultyMobNo)) {
            newFacultyDTO.setFacultyMobNo(facultyMobNo);
        }
        if (!StringUtils.isEmpty(facultyName)) {
            newFacultyDTO.setFacultyName(facultyName);
        }
        facultyService.saveFaculty(newFacultyDTO);
        
        
        return new ResponseEntity( new HttpHeaders(),HttpStatus.OK);

    }

    private void removeDefaultValues(FacultyDTO gsonFacultyObject, FacultyDTO oldFacultyDTO) {
        oldFacultyDTO.getFacultyKeyProps().clear();
        Iterator<FacultyKeyPropsDTO> keyIterator = gsonFacultyObject.getFacultyKeyProps().iterator();
        List<FacultyKeyPropsDTO> keyPropsDTOS = new ArrayList<>();
        while(keyIterator.hasNext() ){
            FacultyKeyPropsDTO keyDTO = keyIterator.next();
            if(!StringUtils.isEmpty(keyDTO.getKeyPropertyName())) {
                if (keyDTO.getKeyPropertyId() == -9999) {
                    keyDTO.setKeyPropertyId(null);
                }
                Iterator<FacultyKeyPropValuesDTO> valueIterator = keyDTO.getKeyPropVals().iterator();
                List<FacultyKeyPropValuesDTO> valDTOs = new ArrayList<>();
                while (valueIterator.hasNext()) {
                    FacultyKeyPropValuesDTO valueDTO = valueIterator.next();
                    if (valueDTO.getKeyPropValuesId() == -9999) {
                        valueDTO.setKeyPropValuesId(null);
                        
                    }
                    
                    valueDTO.setFacultyKeyProps(keyDTO);
                    valDTOs.add(valueDTO);
                    System.out.println("Print Property Value in controller layer"+valueDTO.getKeyPropVal());
                }
                keyDTO.getKeyPropVals().addAll(valDTOs);
                
                keyDTO.setFaculty(oldFacultyDTO);
                keyPropsDTOS.add(keyDTO);
            }
        }
        oldFacultyDTO.getFacultyKeyProps().addAll(keyPropsDTOS);
    }



    @ResponseBody
    @RequestMapping(value = "/uploadfile/facultyFileUpload", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(
            @RequestParam("facultydocumentsFile") MultipartFile uploadfile,
            @RequestParam("facultydocumentsHeader") String facultydocumentsHeader,
            @RequestParam("isProfilePic") String isProfilePic,
            @RequestParam("facultyPersonalEmail") String facultyPersonalEmail,
            @RequestParam("facultyOfficialEmail") String facultyOfficialEmail,
            @RequestParam("facultyMobNo") Long facultyMobNo,
            @RequestParam("facultyName") String facultyName,
            @RequestParam(name = "isLink", required = false, defaultValue = "false") boolean isLink,
            @RequestParam(name = "linkAddress", required = false) String linkAddress) {
        String fileName = uploadfile.getOriginalFilename();
        if (!isLink && (uploadfile.isEmpty() || StringUtils.isEmpty(facultydocumentsHeader))) {
            String msg = "";
            if (uploadfile.isEmpty()) {
                msg = "Please select a file.";
            } else {
                msg = "Please give notice heading";
            }
            return new ResponseEntity(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        String userName = ControllerUtility.getUserName();
        if ("Yes".equalsIgnoreCase(isProfilePic)) {
            FacultyDTO facultyDTO = facultyService.getFaculty(userName);
            facultyDTO.setFileType(fileName);
            try {
                facultyDTO.setFacultyPhoto(uploadfile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            facultyDTO.setFileType(FilenameUtils.getExtension(fileName));
            if (StringUtils.isEmpty(facultyOfficialEmail)) {
                facultyDTO.setFacultyOfficialEmail(facultyDTO.getFacultyOfficialEmail());
            } else {
                facultyDTO.setFacultyOfficialEmail(facultyOfficialEmail);
            }
            if (StringUtils.isEmpty(facultyPersonalEmail)) {
                facultyDTO.setFacultyPersonalEmail(facultyDTO.getFacultyPersonalEmail());
            } else {
                facultyDTO.setFacultyPersonalEmail(facultyPersonalEmail);
            }
            if (StringUtils.isEmpty(facultyMobNo)) {
                facultyDTO.setFacultyMobNo(facultyDTO.getFacultyMobNo());
            } else {
                facultyDTO.setFacultyMobNo(facultyMobNo);
            }
            if (StringUtils.isEmpty(facultyName)) {
                facultyDTO.setFacultyName(facultyDTO.getFacultyName());
            } else {
                facultyDTO.setFacultyName(facultyName);
            }

            facultyService.saveFaculty(facultyDTO);

        } else {
            saveFacultydocumentsDetails(userName, facultydocumentsHeader, fileName, uploadfile, isLink, linkAddress);
        }

        String notice = "http://localhost/wp-content/uploads/facultydocuments/" + fileName;
        notice = notice + "," + facultydocumentsHeader;
        return new ResponseEntity(notice, new HttpHeaders(), HttpStatus.OK);

    }

    private void saveFacultydocumentsDetails(String username, String facultydocumentsHeader, String fileName,
                                             MultipartFile uploadFile, Boolean isLink, String linkAddress) {
        FacultyDocumentsDTO facultyDocumentsDTO = new FacultyDocumentsDTO();
        facultyDocumentsDTO.setHeadLine(facultydocumentsHeader);
        facultyDocumentsDTO.setUploadedFileName(fileName);
        facultyDocumentsDTO.setUploadedBy(username);
        facultyDocumentsDTO.setDate(new Date());
        facultyDocumentsDTO.setLink(isLink);
        facultyDocumentsDTO.setLinkAddress(linkAddress);
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

    @RequestMapping(value = "/facultyDetails", method = RequestMethod.GET)
    public ModelAndView displayFacultyDetails(@RequestParam("facultyId") Long facultyId) {
        ModelAndView model = facultyHelper.getFacultyDetailsbyId(facultyId);
        model.setViewName("facultydetails.jsp");
        return model;
    }

    @RequestMapping(value = "faculty")
    public ModelAndView getFaculty(@RequestParam("deptno") Long deptno) throws IOException {
        ModelAndView mv = new ModelAndView();
        List<FacultyDTO> facultyList = facultyService.getFacultyByDeptNo(deptno);
        Iterator<FacultyDTO> iterator = facultyList.iterator();
        String hodName = departmentService.getHodName(deptno);
        int index = 0;
        FacultyDTO hod = null;
        while(iterator.hasNext()){
            FacultyDTO dto = iterator.next();
            String url = null;
            if (dto.getFacultyPhoto() != null && "png".equalsIgnoreCase(dto.getFileType())){
                url = "data:image/png;base64," + new String(Base64.encode(dto.getFacultyPhoto()));
            }
            else if(dto.getFacultyPhoto() != null && "jpg".equalsIgnoreCase(dto.getFileType())){
                url = "data:image/jpg;base64," + new String(Base64.encode(dto.getFacultyPhoto()));
            }
            else if(dto.getFacultyPhoto() != null && "jpeg".equalsIgnoreCase(dto.getFileType())){
                url = "data:image/jpeg;base64," + new String(Base64.encode(dto.getFacultyPhoto()));
            }

            dto.setImageURL(url);
            if(dto.getFacultyName()!= null && dto.getFacultyName().contains("HOD")){
                iterator.remove();
            }
            else if(dto.getFacultyName()!= null && dto.getFacultyName().equalsIgnoreCase(hodName)){
               hod = dto;
               iterator.remove();
            }
        }
        if(hod != null){
            facultyList.add(0, hod);
        }
        mv.addObject("facultyList", facultyList);
        mv.setViewName("facultylist.jsp");
        return mv;
    }   

    @RequestMapping(value = "/uploadfile/editfacultydetails")
    public ModelAndView editfaculty(@RequestParam("facultyId") Long facultyId, Authentication authentication) {
        ModelAndView mv = new ModelAndView();
        
        FacultyDTO facultyDTO = facultyService.getFacultyById(facultyId);
        String loggedInUserName= authentication.getName();
        String userName= facultyDTO.getUser().getUsername();

        if(userName.equalsIgnoreCase(loggedInUserName)){
            
            mv.setViewName("editfacultydetails.jsp");
        }
        else{
            mv.setViewName("/error-403.jsp");
        }
        mv.addObject("facultyDetails",facultyDTO);
        
        
        return mv;
    }

    @RequestMapping(value="/auth/uploadfile/addfaculty")
    public  ModelAndView addFaculty(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("addfaculty.jsp");

        return mv;
    }

    @ResponseBody
    @RequestMapping(value="/uploadfile/addfaculty",method = RequestMethod.POST)
    public ResponseEntity<?> addFaculty(
            @RequestParam("facultyPersonalEmail") String facultyPersonalEmail,
            @RequestParam("facultyOfficialEmail") String facultyOfficialEmail,
            @RequestParam("facultyMobNo") Long facultyMobNo,
            @RequestParam("facultyName") String facultyName,
            @RequestParam("deptId") Long deptId,
            @RequestParam("userName") String username){
        FacultyDTO facultyDTO = new FacultyDTO();
        facultyDTO.setDepartmentId(deptId);
        facultyDTO.setFacultyOfficialEmail(facultyOfficialEmail);
        facultyDTO.setFacultyPersonalEmail(facultyPersonalEmail);
        facultyDTO.setFacultyMobNo(facultyMobNo);
        facultyDTO.setFacultyName(facultyName);
        User user = userService.findByUsername(username);
        facultyDTO.setUser(user);
        List<String> allUserName = userService.getAllUserName();
        if(allUserName.equals(username)) {
            facultyService.saveFaculty(facultyDTO);
        }
        else{
            System.out.println("Faculty not Registered !");
        }
    

        return new ResponseEntity( new HttpHeaders(), HttpStatus.OK);

    }

}
