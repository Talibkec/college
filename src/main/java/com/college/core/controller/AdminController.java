package com.college.core.controller;

import com.college.KECDateHelper;
import com.college.core.entity.User;
import com.college.core.model.FacultyDTO;
import com.college.core.model.NoticeBoardDTO;
import com.college.core.model.UserRoleDTO;
import com.college.service.FacultyService;
import com.college.service.NoticeBoardService;
import com.college.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.hibernate3.SpringSessionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
//@RequestMapping("admin")
public class AdminController {
    @Autowired
    FacultyService facultyService;
    @Autowired
    UserService userService;
    @Autowired
    NoticeBoardService noticeBoardService;
    @RequestMapping(value = "/auth/uploadDashboard")
    public ModelAndView getUploadDashboard() {
        List<NoticeBoardDTO> list = noticeBoardService.getAllNotice(new PageRequest(0, 10));
        ModelAndView mv = new ModelAndView();
        mv.addObject("Role", ControllerUtility.getRole());
        mv.addObject("noticeList", getNoticeList(list, false));
        mv.setViewName("admin/UploadDashboard.jsp");
        return mv;

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

    @RequestMapping(value = "/hod/hodDashboard")
    public ModelAndView getHodDashboard() {
        List<NoticeBoardDTO> list = noticeBoardService.getAllNotice(new PageRequest(0, 10));
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/HodDashboard.jsp");
        return mv;

    }
    @RequestMapping(value = "/hod/addfaculty")
    public ModelAndView getHodAddFaculty() {
        //List<NoticeBoardDTO> list = noticeBoardService.getAllNotice(new PageRequest(0, 10));
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/HodAddFaculty.jsp");
        return mv;

    }
    @RequestMapping(value = "/auth/uploadAICTEapproval")
    public ModelAndView uploadAICTEapproval() {
        ModelAndView model = new ModelAndView();
        //model.addObject("users", getUsers());
        model.setViewName("uploadAICTEapproval.jsp");
        return model;

    }
    @ResponseBody
    @RequestMapping(value="/hod/uploadfile/addfaculty",method = RequestMethod.POST)
    public String addFaculty(
            @RequestParam("facultyPersonalEmail") String facultyPersonalEmail,
            @RequestParam("facultyOfficialEmail") String facultyOfficialEmail,
            @RequestParam("facultyMobNo") Long facultyMobNo,
            @RequestParam("facultyName") String facultyName,
            @RequestParam("deptId") Long deptId,
            @RequestParam("userName") String username){
        String message="";
        boolean error = false;
        FacultyDTO facultyDTO = new FacultyDTO();
        facultyDTO.setDepartmentId(deptId);


        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(facultyOfficialEmail);
        if(!m.matches()){
            error = true;
            message = " Invalid Official Email address entered";
        }
        if(!facultyOfficialEmail.substring(facultyOfficialEmail.indexOf("@") + 1).equals("keck.ac.in")){
            error = true;
            message = " Official email doesn't belong to keck.ac.in ";
        }
        facultyDTO.setFacultyOfficialEmail(facultyOfficialEmail);
        facultyDTO.setFacultyPersonalEmail(facultyPersonalEmail);
        facultyDTO.setFacultyMobNo(facultyMobNo);
        facultyDTO.setFacultyName(facultyName);


        User user = userService.findByUsername(username);
        if("".equals(username)) {
            message = "Faculty username can't be null";
            //System.out.println("Faculty username can't be null:-  " + username);
            error = true;
        }
        if(user != null){
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
        if(!error){
            message = "Faculty added successfully you can loging using username= " + username + " password :- " + username;
            User user2= new User();
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
    @RequestMapping(value = "/auth/uploadDownload")
    public ModelAndView uploadDownload() {
        ModelAndView model = new ModelAndView();
        //model.addObject("users", getUsers());
        model.setViewName("uploadDownload.jsp");
        return model;

    }
    @RequestMapping(value = "/auth/uploadResponsibilityDoc")
    public ModelAndView uploadResponsibilityDoc() {
        ModelAndView model = new ModelAndView();
        //model.addObject("users", getUsers());
        model.setViewName("uploadResponsibilityDoc.jsp");
        return model;

    }
    @RequestMapping(value = "/auth/uploadHoliday")
    public ModelAndView uploadHoliday() {
        ModelAndView model = new ModelAndView();
        //model.addObject("users", getUsers());
        model.setViewName("uploadHoliday.jsp");
        return model;

    }
}
