package com.college.core.controller.store;

import com.college.FacultyHelper;
import com.college.core.controller.ControllerUtility;
import com.college.core.entity.Role;
import com.college.core.entity.User;
import com.college.core.model.*;
import com.college.repository.FacultyKeyPropsRepository;
import com.college.repository.FacultyKeyPrpsValueRepository;
import com.college.service.*;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.BeanUtils;

import java.util.regex.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;


@Controller
public class CommonResourceController {

    @Autowired
    ProductService productService;
    @Autowired
    FacultyService facultyService;
    @Autowired
    StaffService staffService;
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
    @Autowired
    PlacementsService placementsService;

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
    @RequestMapping(value = "/user/userNameAutocomplete", method = RequestMethod.GET)
    public List<String> userNameAutocomplete(@RequestParam("userName") String userName) {
        List<String> usernames = userService.searchUserName(userName);
        return usernames;
    }

    @ResponseBody
    @RequestMapping(value = "/faculty/facultyNameAutocompletes", method = RequestMethod.GET)
    public List<String> facultyNameAutocompletes(@RequestParam("facultyName") String facultyName) {
        List<FacultyDTO> facultyNames = facultyService.searchFacultyName(facultyName);
        return facultyHelper.facultyNames(facultyNames);
    }

    @ResponseBody
    @RequestMapping(value = "/faculty/facultyNameAutocompletes123", method = RequestMethod.GET)
    public List<FacultyDTO> facultyNameAutocompletes123(@RequestParam("facultyName") String facultyName) {
        List<FacultyDTO> facultyNames = facultyService.searchFacultyName(facultyName);
        JsonObject jsn;
        if (!facultyNames.isEmpty()) {
            for (FacultyDTO dto : facultyNames) {
                dto.setFacultyKeyProps(null);
                dto.setFacultyPhoto(null);
                dto.setFacultyId(dto.getUser().getId());
                dto.setUser(null);
            }
        }
        return facultyNames;
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

            facultyDTO.getUser().getUsername();
            obj.addProperty("name", facultyDTO.getFacultyName());
            obj.addProperty("id", facultyDTO.getFacultyId());
        }
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/uploadfile/editfacultydetails", method = RequestMethod.POST)
    public ResponseEntity<?> editFacultyDetails(
            @RequestParam("facultyPersonalEmail") String facultyPersonalEmail,
            @RequestParam("facultyOfficialEmail") String facultyOfficialEmail,
            @RequestParam("facultyMobNo") Long facultyMobNo,
            @RequestParam("facultyName") String facultyName,
            @RequestParam("facultyId") Long facultyId,
            @RequestParam("newProps") String newProps,
            @RequestParam("oldProps") String oldProps,
            @RequestParam("oldKeyOrder") List<String> oldKeyOrder,
            @RequestParam("newKeyOrder") List<String> newKeyOrder,
            @RequestParam("facultyDetails") String facultyDetails) {


        System.out.println("oldKeyOrder = " + oldKeyOrder);
        System.out.println("newKeyOrder = " + newKeyOrder);
        FacultyDTO newFacultyDTO = new FacultyDTO();
        FacultyDTO gsonFacultyObject = gson.fromJson(facultyDetails, FacultyDTO.class);
        FacultyKeyPropsDTO newFacultyKeyPropsDTO = new FacultyKeyPropsDTO();
        Type type = new TypeToken<LinkedHashMap<String, List<String>>>() {
        }.getType();
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


        return new ResponseEntity(new HttpHeaders(), HttpStatus.OK);

    }

    private void removeDefaultValues(FacultyDTO gsonFacultyObject, FacultyDTO oldFacultyDTO) {
        oldFacultyDTO.getFacultyKeyProps().clear();
        Iterator<FacultyKeyPropsDTO> keyIterator = gsonFacultyObject.getFacultyKeyProps().iterator();
        List<FacultyKeyPropsDTO> keyPropsDTOS = new ArrayList<>();
        while (keyIterator.hasNext()) {
            FacultyKeyPropsDTO keyDTO = keyIterator.next();
            if (!StringUtils.isEmpty(keyDTO.getKeyPropertyName())) {
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
                    System.out.println("Print Property Value in controller layer" + valueDTO.getKeyPropVal());
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

        String notice = "/wp-content/uploads/facultydocuments/" + fileName;
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

    @RequestMapping(value = "deleteFaculty")
    public void deleteFaculty(@RequestParam("facultyId") Long facultyId, @RequestParam("deptno") Long departmentId, Authentication authentication, HttpServletResponse res) {
        int accessAllow = 0;
        String loggedInusername = "";
        if (authentication != null) {
            loggedInusername = authentication.getName();
            accessAllow = getUserType(accessAllow, loggedInusername , departmentId);
        }
        FacultyDTO facultyDTO = facultyService.getFacultyById(facultyId);
        if (accessAllow > 0 ) {
            facultyService.deleteFaculty(facultyId);
        }
        try {
            res.sendRedirect("/faculty?deptno=" + departmentId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @RequestMapping(value = "faculty")
    public ModelAndView getFaculty(@RequestParam("deptno") Long deptno, Authentication authentication) throws IOException {
        ModelAndView mv = new ModelAndView();
        // userType will be
        // 0 :- Normal User
        // 1:- HOD
        //3:- Admin
        int userType = 0;
        String hodName = departmentService.getHodName(deptno);
        System.out.println(hodName);

        if (authentication != null) {
            String loggedInusername = authentication.getName();
            userType = getUserType(userType, loggedInusername , deptno);
        }

        long l = System.currentTimeMillis();


        List<FacultyDTO> facultyList = facultyService.getFacultyByDeptNo(deptno);
        Iterator<FacultyDTO> iterator = facultyList.iterator();


        int index = 0;
        FacultyDTO hod = null;
        while (iterator.hasNext()) {
            FacultyDTO dto = iterator.next();
            String url = null;
            if (dto.getFacultyPhoto() != null && "png".equalsIgnoreCase(dto.getFileType())) {
                url = "data:image/png;base64," + new String(Base64.encode(dto.getFacultyPhoto()));
            } else if (dto.getFacultyPhoto() != null && "jpg".equalsIgnoreCase(dto.getFileType())) {
                url = "data:image/jpg;base64," + new String(Base64.encode(dto.getFacultyPhoto()));
            } else if (dto.getFacultyPhoto() != null && "jpeg".equalsIgnoreCase(dto.getFileType())) {
                url = "data:image/jpeg;base64," + new String(Base64.encode(dto.getFacultyPhoto()));
            } else {
                url = "data:image/png;base64,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";
            }

            dto.setImageURL(url);
            if (dto.getFacultyName() != null && dto.getFacultyName().contains("HOD")) {
                iterator.remove();
            } else if (dto.getFacultyName() != null && dto.getFacultyName().equalsIgnoreCase(hodName)) {
                hod = dto;
                iterator.remove();
            }
        }

        long l1 = System.currentTimeMillis();
        System.out.println("Time = " + (l1 - l) / 1000);
        if (hod != null) {
            facultyList.add(0, hod);
        }
        mv.addObject("facultyList", facultyList);
        mv.addObject("userType", userType);
        mv.setViewName("facultylist.jsp");
        return mv;
    }

    private int getUserType(int userType, String loggedInusername , long deptId) {
        User loggedInUser = userService.findByUsername(loggedInusername);
        Set<Role> role = loggedInUser.getRoles();
        for (Role r : role) {
            if("Admin".equalsIgnoreCase(r.getName())){
                return 2;
            }

        }
        for (Role r : role) {
            if("HOD".equalsIgnoreCase(r.getName()) && deptId == facultyService.getFaculty(loggedInusername).getDepartmentId()){
                return 1;
            }
        }
        return 0;
    }

    @RequestMapping(value = "/uploadfile/editfacultydetails")
    public ModelAndView editfaculty(@RequestParam("facultyId") Long facultyId, Authentication authentication) {
        ModelAndView mv = new ModelAndView();
        FacultyDTO facultyDTO = facultyService.getFacultyById(facultyId);
        String loggedInUserName = authentication.getName();
        String userName = facultyDTO.getUser().getUsername();

        if (userName.equalsIgnoreCase(loggedInUserName)) {

            mv.setViewName("editfacultydetails.jsp");
        } else {
            mv.setViewName("/error-403.jsp");
        }
        mv.addObject("facultyDetails", facultyDTO);
        return mv;
    }

    @RequestMapping(value = "/auth/uploadfile/addfaculty", method = RequestMethod.GET)
    public ModelAndView addFaculty() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "No any message for now");
        mv.setViewName("addfaculty.jsp");
        return mv;
    }
    @RequestMapping(value = "/auth/uploadfile/addstaff", method = RequestMethod.GET)
    public ModelAndView addStaff() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "No any message for now");
        mv.setViewName("addstaff.jsp");
        return mv;
    }
    @RequestMapping(value = "/auth/uploadfile/addPlacements", method = RequestMethod.GET)
    public ModelAndView addPlacements() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "No any message for now");
        mv.setViewName("addPlacements.jsp");
        return mv;
    }
    @ResponseBody
    @RequestMapping(value = "/auth/uploadfile/addfaculty", method = RequestMethod.POST)
    public String addFaculty(
            @RequestParam("facultyPersonalEmail") String facultyPersonalEmail,
            @RequestParam("facultyOfficialEmail") String facultyOfficialEmail,
            @RequestParam("facultyMobNo") Long facultyMobNo,
            @RequestParam("facultyName") String facultyName,
            @RequestParam("deptId") Long deptId,
            @RequestParam("userName") String username) {
        String message = "";
        boolean error = false;
        FacultyDTO facultyDTO = new FacultyDTO();
        facultyDTO.setDepartmentId(deptId);


        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(facultyOfficialEmail);
//        Matcher m2 = pattern.matcher(facultyPersonalEmail);
        if (!m.matches()) {
            error = true;
            message = " Invalid Official Email address entered";
        }
//        if(!m2.matches()){
//            error = true;
//            message = " Invalid Personal Email address entered";
//        }
        if (!facultyOfficialEmail.substring(facultyOfficialEmail.indexOf("@") + 1).equals("keck.ac.in")) {
            error = true;
            message = " Official email doesn't belong to keck.ac.in ";
        }
//        if(facultyMobNo < 1000000000L || facultyMobNo > 9999999999L){
//            error = true;
//            message = " Mobile Number must be of 10 digit length ";
//        }
        facultyDTO.setFacultyOfficialEmail(facultyOfficialEmail);
        facultyDTO.setFacultyPersonalEmail(facultyPersonalEmail);
        facultyDTO.setFacultyMobNo(facultyMobNo);
        facultyDTO.setFacultyName(facultyName);

        User user = userService.findByUsername(username);
        if ("".equals(username)) {
            message = "Faculty username can't be null";
            //System.out.println("Faculty username can't be null:-  " + username);
            error = true;
        }
        if (user != null) {
            message = "Faculty already registered with given username :-  " + username;
            //System.out.println("Faculty already registered with given username :-  " + username);
            error = true;
        }
        //List<String> allUserName = userService.getAllUserName();

//        for(String s : allUserName) {
//            if(username.equals(s)){
//                System.out.println("Faculty already registered with given username :-  " + username);
//               error = true;
//            }
//        }
        if (!error) {
            message = "Faculty added successfully you can loging using username= " + username + " password :- " + username;
            User user2 = new User();
            user2.setUsername(username);
            user2.setPassword(username);
            user2.setRole("2");
            userService.save(user2);

            UserRoleDTO userRoleDTO = new UserRoleDTO();
            userRoleDTO.setRoleId(2L);
            userRoleDTO.setUserId(user2.getId());
            userService.saveUserRole(userRoleDTO);

            facultyDTO.setUser(user2);
            //System.out.println("Saving the detail of faculty with username :-   " + username);
            facultyService.saveFaculty(facultyDTO);
        }

        return message;


    }




    @ResponseBody
    @RequestMapping(value = "/auth/uploadfile/addstaff", method = RequestMethod.POST)
    public String addStaff(
            @RequestParam("facultyPersonalEmail") String facultyPersonalEmail,
            @RequestParam("facultyOfficialEmail") String facultyOfficialEmail,
            @RequestParam("facultyMobNo") Long facultyMobNo,
            @RequestParam("facultyName") String facultyName,
            @RequestParam("slideImage") MultipartFile uploadfile
          ) {
        String message = "";
        boolean error = false;
        StaffDTO staffDTO = new StaffDTO();

        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(facultyOfficialEmail);
//        Matcher m2 = pattern.matcher(facultyPersonalEmail);
//        if (!m.matches()) {
//            error = true;
//            message = " Invalid Official Email address entered";
//        }
//        if(!m2.matches()){
//            error = true;
//            message = " Invalid Personal Email address entered";
//        }
//        if (!facultyOfficialEmail.substring(facultyOfficialEmail.indexOf("@") + 1).equals("keck.ac.in")) {
//            error = false;
//            message = " Official email doesn't belong to keck.ac.in ";
//        }
//        if(facultyMobNo < 1000000000L || facultyMobNo > 9999999999L){
//            error = true;
//            message = " Mobile Number must be of 10 digit length ";
//        }

        String fileName = uploadfile.getOriginalFilename();
        if (uploadfile.isEmpty()) {
            String msg = "";
            if (uploadfile.isEmpty()) {
                msg = "Please select a file.";
            } else {
                msg = "Please give notice heading";
            }
            return msg;
        }

        try {
            staffDTO.setFileType(FilenameUtils.getExtension(fileName));
            staffDTO.setStaffPhoto(uploadfile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }


        staffDTO.setStaffOfficialEmail(facultyOfficialEmail);
        staffDTO.setStaffPersonalEmail(facultyPersonalEmail);
        staffDTO.setStaffMobNo(facultyMobNo);
        staffDTO.setStaffName(facultyName);


        //List<String> allUserName = userService.getAllUserName();

//        for(String s : allUserName) {
//            if(username.equals(s)){
//                System.out.println("Faculty already registered with given username :-  " + username);
//               error = true;
//            }
//        }
        if (!error) {
            message = "Staff added successfully  ";

            //System.out.println("Saving the detail of faculty with username :-   " + username);
            staffService.saveStaff(staffDTO);
        }

        return message;


    }



    @ResponseBody
    @RequestMapping(value = "/auth/uploadfile/addPlacements", method = RequestMethod.POST)
    public String addPlacements(
            @RequestParam("studentName") String studentName,
            @RequestParam("companyName") String companyName,
            @RequestParam("studentSession") String studentSession,
            @RequestParam("deptId") Long deptId,
            @RequestParam("registrationNo") Long registrationNo
    ) {
        String message = "";
        boolean error = false;
        PlacementsDTO placementsDTO = new PlacementsDTO();


        placementsDTO.setStudentName(studentName);
        placementsDTO.setCompanyName(companyName);
        placementsDTO.setStudentSession(studentSession);
        placementsDTO.setReigstrationNo(registrationNo);
        placementsDTO.setDepartmentId(deptId);


        //List<String> allUserName = userService.getAllUserName();

//        for(String s : allUserName) {
//            if(username.equals(s)){
//                System.out.println("Faculty already registered with given username :-  " + username);
//               error = true;
//            }
//        }
        if (!error) {
            message = "Placement added successfully  ";

            //System.out.println("Saving the detail of faculty with username :-   " + username);
            placementsService.savePlacements(placementsDTO);
        }

        return message;


    }
}
