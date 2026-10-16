package com.college.core.controller;

import com.college.FacultyHelper;
import com.college.core.model.FacultyDTO;
import com.college.service.AccessService;
import com.college.service.DepartmentService;
import com.college.service.FacultyProfileService;
import com.college.service.FacultyService;
import com.college.service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Faculty pages: department faculty list, profile, profile editing, profile photo and documents, faculty search.
 * (Moved unchanged out of store/CommonResourceController, where these core features used to live.)
 */
@Controller
public class FacultyProfileController {

    private static final Logger logger = LoggerFactory.getLogger(FacultyProfileController.class);

    private final FacultyService facultyService;
    private final FacultyProfileService facultyProfileService;
    private final FacultyHelper facultyHelper;
    private final UserService userService;
    private final DepartmentService departmentService;
    private final AccessService accessService;
    private final Gson gson;

    public FacultyProfileController(FacultyService facultyService,
            FacultyProfileService facultyProfileService,
            FacultyHelper facultyHelper,
            UserService userService,
            DepartmentService departmentService,
            AccessService accessService,
            Gson gson) {
        this.facultyService = facultyService;
        this.facultyProfileService = facultyProfileService;
        this.facultyHelper = facultyHelper;
        this.userService = userService;
        this.departmentService = departmentService;
        this.accessService = accessService;
        this.gson = gson;
    }

    @RequestMapping(value = "/faculty")
    public ModelAndView getFaculty(@RequestParam("deptno") Long deptno, Authentication authentication) {
        ModelAndView mv = new ModelAndView();
        String hodName = departmentService.getHodName(deptno);
        // department 8 shows the faculty of department 3
        if (deptno == 8) {
            deptno = 3L;
        }
        if (Objects.equals(hodName, "")) {
            mv.setViewName("invalidDeptId.jsp");
            return mv;
        }
        // 0 = normal user, 1 = HOD of this department, 2 = Admin
        int userType = accessService.facultyPageUserType(authentication, deptno);
        mv.addObject("facultyList", facultyProfileService.getFacultyList(deptno, hodName));
        mv.addObject("userType", userType);
        mv.setViewName("facultylist.jsp");
        return mv;
    }

    @RequestMapping(value = "/deleteFaculty")
    public void deleteFaculty(@RequestParam("facultyId") Long facultyId, @RequestParam("deptno") Long departmentId,
                              Authentication authentication, HttpServletResponse res) {
        int accessAllow = accessService.facultyPageUserType(authentication, departmentId);
        facultyService.getFacultyById(facultyId); // fails for an unknown id, as before
        if (accessAllow > 0) {
            facultyService.deleteFaculty(facultyId);
        }
        try {
            res.sendRedirect("/faculty?deptno=" + departmentId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/facultyDetails", method = RequestMethod.GET)
    public ModelAndView displayFacultyDetails(@RequestParam("facultyId") Long facultyId) {
        ModelAndView model = facultyHelper.getFacultyDetailsbyId(facultyId);
        model.setViewName("facultydetails.jsp");
        return model;
    }

    @RequestMapping(value = "/uploadfile/editfacultydetails")
    public ModelAndView editfaculty(@RequestParam("facultyId") Long facultyId, Authentication authentication) {
        ModelAndView mv = new ModelAndView();
        FacultyDTO facultyDTO = facultyService.getFacultyById(facultyId);
        String userName = facultyDTO.getUser().getUsername();
        if (userName.equalsIgnoreCase(authentication.getName())) {
            mv.setViewName("editfacultydetails.jsp");
        } else {
            mv.setViewName("/error-403.jsp");
        }
        mv.addObject("facultyDetails", facultyDTO);
        return mv;
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
        logger.debug("oldKeyOrder = {}", oldKeyOrder);
        logger.debug("newKeyOrder = {}", newKeyOrder);
        FacultyDTO submitted = gson.fromJson(facultyDetails, FacultyDTO.class);
        facultyProfileService.updateFacultyDetails(facultyId, facultyPersonalEmail, facultyOfficialEmail, facultyMobNo,
                facultyName, submitted);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/uploadfile/facultyFileUpload", method = RequestMethod.GET)
    public ModelAndView displayFacultyFileUploadpage(@RequestParam("isProfilePic") String isProfilePic) {
        ModelAndView model = new ModelAndView();
        model.setViewName("facultyfileupload.jsp");
        model.addObject("isProfilePic", isProfilePic);
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/uploadfile/facultyFileUpload", method = RequestMethod.POST)
    public ResponseEntity<String> uploadFile(
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
            String msg = uploadfile.isEmpty() ? "Please select a file." : "Please give notice heading";
            return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        String userName = ControllerUtility.getUserName();
        if ("Yes".equalsIgnoreCase(isProfilePic)) {
            facultyProfileService.saveProfilePhoto(userName, uploadfile, facultyPersonalEmail, facultyOfficialEmail,
                    facultyMobNo, facultyName);
        } else {
            facultyProfileService.saveDocument(userName, facultydocumentsHeader, uploadfile, isLink, linkAddress);
        }
        // response kept from the original code (the path is not a real file)
        String body = "/wp-content/uploads/facultydocuments/" + fileName + "," + facultydocumentsHeader;
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/uploadNoticeFaculty", method = RequestMethod.GET)
    public ModelAndView uploadNoticeFaculty() {
        ModelAndView model = new ModelAndView();
        model.setViewName("uploadNoticeFaculty.jsp");
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/user/userNameAutocomplete", method = RequestMethod.GET)
    public List<String> userNameAutocomplete(@RequestParam("userName") String userName) {
        return userService.searchUserName(userName);
    }

    @ResponseBody
    @RequestMapping(value = "/faculty/facultyNameAutocompletes", method = RequestMethod.GET)
    public List<String> facultyNameAutocompletes(@RequestParam("facultyName") String facultyName) {
        return facultyHelper.facultyNames(facultyService.searchFacultyName(facultyName));
    }

    @ResponseBody
    @RequestMapping(value = "/faculty/facultyNameAutocompletes123", method = RequestMethod.GET)
    public List<FacultyDTO> facultyNameAutocompletes123(@RequestParam("facultyName") String facultyName) {
        List<FacultyDTO> facultyNames = facultyService.searchFacultyName(facultyName);
        for (FacultyDTO dto : facultyNames) {
            dto.setFacultyKeyProps(null);
            dto.setFacultyPhoto(null);
            dto.setFacultyId(dto.getUser().getId());
            dto.setUser(null);
        }
        return facultyNames;
    }

    @ResponseBody
    @RequestMapping(value = "/faculty/searchFacultyNames", method = RequestMethod.GET)
    public String searchFacultyByNames(@RequestParam("facultyName") String facultyNames) {
        List<FacultyDTO> faculties = facultyService.getFacultyByName(facultyNames);
        if (faculties.isEmpty()) {
            return null;
        }
        FacultyDTO facultyDTO = faculties.get(0);
        JsonObject obj = new JsonObject();
        obj.addProperty("name", facultyDTO.getFacultyName());
        obj.addProperty("id", facultyDTO.getFacultyId());
        return obj.toString();
    }
}
