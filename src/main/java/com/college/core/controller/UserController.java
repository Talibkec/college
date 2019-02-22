package com.college.core.controller;

import com.college.core.entity.User;
import com.college.core.model.FacultyDocumentsDTO;
import com.college.core.model.NoticeBoardDTO;
import com.college.service.NoticeBoardService;
import com.college.service.RoleService;
import com.college.service.SecurityService;
import com.college.service.UserService;
import com.college.validator.UserValidator;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    NoticeBoardService noticeBoardService;

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/auth/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("roles", roleService.getALLRoles());
        return "registration";
    }

    @RequestMapping(value = "/auth/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout, HttpServletRequest req, HttpServletResponse res) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public ModelAndView welcome(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        List<NoticeBoardDTO>  list = noticeBoardService.getAllNotice();
        modelAndView.addObject("noticeList",getNoticeList(list, false));
        modelAndView.addObject("scrollingNoticeList", getNoticeList(list, true));
        modelAndView.addObject("Role", ControllerUtility.getRole());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    private List<NoticeBoardDTO> getNoticeList(List<NoticeBoardDTO> list, boolean scrollable) {
        List<NoticeBoardDTO> scrollingNotices = new ArrayList<>();
        Date today = new Date();
        for(NoticeBoardDTO dto : list){
            dto.setFileType(("."+ FilenameUtils.getExtension(dto.getUploadedFileName())));
            dto.setNoticeAge(getDifferenceDays(dto.getDate(), today));
            if(scrollable){
                if(dto.getIsScrollable() != null &&  dto.getIsScrollable() == 1)
                    dto.setFileType(("."+FilenameUtils.getExtension(dto.getUploadedFileName())));

                    scrollingNotices.add(dto);
            }
            else if(!scrollable){
                if(dto.getIsScrollable()== null )
                    scrollingNotices.add(dto);

            }
        }

        return  scrollingNotices;
    }
    @RequestMapping(value ="/{id}/notice")
    public @ResponseBody byte[] getdocuments(@PathVariable("id")Long id) throws IOException{
        NoticeBoardDTO noticeBoardDTO =noticeBoardService.getNoticeDocument(id);
        if(noticeBoardDTO.getNotice()!= null){
            InputStream in = new ByteArrayInputStream(noticeBoardDTO.getNotice());
            return  IOUtils.toByteArray(in);
        }
        return null;
    }

    @RequestMapping(value = "/auth/{fileName}/{id}", method = RequestMethod.GET)
    public void deleteItem(@PathVariable("fileName") String fileName, @PathVariable("id") Long id, HttpServletResponse res) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        noticeBoardService.deleteItem(id);
        deleteFileDromDisk(fileName);
        res.sendRedirect("/");
    }

    private void deleteFileDromDisk(String fileName) {
        File file = new File("http://keck.ac.in/wp-content/uploads/notice/" + fileName);
        file.delete();
    }


    public int getDifferenceDays(Date d1, Date d2) {
        int daysdiff = 0;
        long diff = d2.getTime() - d1.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
        daysdiff = (int) diffDays;
        return daysdiff;
    }


}
