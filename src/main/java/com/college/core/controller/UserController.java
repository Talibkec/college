package com.college.core.controller;

import com.college.KECDateHelper;
import com.college.NoticeBoardHelper;
import com.college.core.entity.User;
import com.college.core.model.AchievementDTO;
import com.college.core.model.ImageSlideDTO;
import com.college.core.model.NoticeBoardDTO;
import com.college.service.*;
import com.college.validator.UserValidator;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
import java.util.ArrayList;
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

    @Autowired
    NoticeBoardHelper noticeBoardHelper;

    @Autowired
    ImageSlideService imageSlideService;
    @Autowired
    AchievementSlideService achievementSlideService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/auth/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("roles", roleService.getALLRoles());
        return "registration.jsp";
    }

    @RequestMapping(value = "/auth/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
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

        return "login.jsp";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public ModelAndView welcome(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        List<NoticeBoardDTO> list = noticeBoardService.getAllNotice(new PageRequest(0, 10));
        List<ImageSlideDTO> imageList = imageSlideService.getAllImages();
        List<AchievementDTO> achievementImageList = achievementSlideService.getAllImages();
        modelAndView.addObject("achievementImageList", getAllAchievementImage(achievementImageList));
        modelAndView.addObject("imageList", getImageList(imageList));
        ImageSlideDTO imageSlideDTO = new ImageSlideDTO();
        modelAndView.addObject("noticeList", getNoticeList(list, false));
        modelAndView.addObject("scrollingNoticeList", getNoticeList(list, true));
        modelAndView.addObject("Role", ControllerUtility.getRole());
        if (imageSlideDTO.getFileType() != null)
            modelAndView.addObject("fileExtension", "." + imageSlideDTO.getFileType());
        modelAndView.addObject("imageSlideId", imageSlideDTO.getImageSlideId());
        modelAndView.addObject("caption", imageSlideDTO.getCaption());

        modelAndView.setViewName("index.jsp");
        return modelAndView;
    }

    @RequestMapping(value = "forgotPassword", method = RequestMethod.GET)
    public ModelAndView forgotPassword() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forgotPassword.html");
        return modelAndView;
    }

    @RequestMapping(value = "thymeleaf", method = RequestMethod.GET)
    public ModelAndView thymleaf(Model model, @RequestParam("name") String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", name);
        modelAndView.setViewName("sample.html");
        return modelAndView;
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

    private List<ImageSlideDTO> getImageList(List<ImageSlideDTO> imageList) {
        List<ImageSlideDTO> slidingImage = new ArrayList<>();

        for (ImageSlideDTO dto : imageList) {
            dto.setFileType(("." + FilenameUtils.getExtension(dto.getFileName())));
            slidingImage.add(dto);

        }
        return slidingImage;
    }

    private List<AchievementDTO> getAllAchievementImage(List<AchievementDTO> achievementList) {

        List<AchievementDTO> achievementImage = new ArrayList<>();
        for (AchievementDTO dto : achievementList) {
            dto.setFileType("." + FilenameUtils.getExtension(dto.getFileName()));
            achievementImage.add(dto);
        }
        return achievementImage;
    }

    @RequestMapping(value = "/{id}/notice")
    public @ResponseBody
    byte[] getdocuments(@PathVariable("id") Long id) throws IOException {
        NoticeBoardDTO noticeBoardDTO = noticeBoardService.getNoticeDocument(id);
        if (noticeBoardDTO.getNotice() != null) {
            InputStream in = new ByteArrayInputStream(noticeBoardDTO.getNotice());
            return IOUtils.toByteArray(in);
        }
        return null;
    }

    @RequestMapping(value = "/{id}/achievementImage")
    public @ResponseBody
    byte[] getachievementImage(@PathVariable("id") Long id) throws IOException {
        AchievementDTO achievementDTO = achievementSlideService.getImages(id);
    if(achievementDTO.getAchievementImage()!=null){InputStream in = new ByteArrayInputStream(achievementDTO.getAchievementImage());
  return IOUtils.toByteArray(in);
}return null;}
   @RequestMapping(value = "/{id}/slideImage")
    public @ResponseBody
    byte[] getimages(@PathVariable("id") Long imageSlideId) throws IOException {
        ImageSlideDTO imageSlideDTO = imageSlideService.getImages(imageSlideId);
        if (imageSlideDTO.getImage() != null) {
            InputStream in = new ByteArrayInputStream(imageSlideDTO.getImage());
            return IOUtils.toByteArray(in);
        }
        return null;
    }

    @RequestMapping(value = "/auth/deleteSlideImage/{id}", method = RequestMethod.GET)
    public void deleteSlideImage(@PathVariable("id") Long imageSlideId, HttpServletResponse res) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        imageSlideService.deleteSlideImage(imageSlideId);
        res.sendRedirect("/");
    }

    @RequestMapping(value = "/hod/{fileName}/{id}", method = RequestMethod.GET)
    public void deleteItem(@PathVariable("fileName") String fileName, @PathVariable("id") Long id, HttpServletResponse res) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        noticeBoardService.deleteItem(id);
        deleteFileDromDisk(fileName);
        res.sendRedirect("/");
    }


    @RequestMapping(value = "/auth/{fileName}/{id}", method = RequestMethod.GET)
    public void deleteNoticeItem(@PathVariable("fileName") String fileName, @PathVariable("id") Long id, HttpServletResponse res) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        noticeBoardService.deleteItem(id);
        deleteFileDromDisk(fileName);
        res.sendRedirect("/");
    }

    private void deleteFileDromDisk(String fileName) {
        File file = new File("http://localhost/wp-content/uploads/notice/" + fileName);
        file.delete();
    }

    @RequestMapping(value = "/pagination/next/getNotices", method = RequestMethod.GET)
    public ModelAndView getNextPageNotice(@RequestParam("deptno") String deptno, @RequestParam("pageSize") Integer pageSize) {
        ModelAndView modalAndView = new ModelAndView();
        List<NoticeBoardDTO> notices = noticeBoardHelper.getNotices(deptno, pageSize);
        if (notices != null && notices.size() < 10) {
            pageSize--;
        }
        ControllerUtility.getNoticelist(notices);
        modalAndView.addObject("noticeList", notices);
        modalAndView.addObject("pageSize", pageSize);
        String viewName = noticeBoardHelper.getViewName(deptno);
        modalAndView.setViewName(viewName);
        return modalAndView;
    }

    @RequestMapping(value = "/pagination/next/getTenders", method = RequestMethod.GET)
    public ModelAndView getNextPageTender(@RequestParam("pageSize") Integer pageSize) {
        ModelAndView modalAndView = new ModelAndView();
        List<NoticeBoardDTO> tenders = noticeBoardHelper.getTenders(pageSize);
        if (tenders != null && tenders.size() < 10) {
            pageSize--;
        }
        ControllerUtility.getNoticelist(tenders);
        modalAndView.addObject("noticeList", tenders);
        modalAndView.addObject("pageSize", pageSize);
        String viewName = noticeBoardHelper.getTenderViewName();
        modalAndView.setViewName(viewName);
        return modalAndView;
    }

    @RequestMapping(value = "/pagination/prev/getNotices", method = RequestMethod.GET)
    public ModelAndView getPreviousPageNotice(@RequestParam("deptno") String deptno, @RequestParam("pageSize") Integer pageSize) {
        if (pageSize < 0) {
            pageSize = 0;
        }
        ModelAndView modalAndView = new ModelAndView();
        List<NoticeBoardDTO> cseNotices = noticeBoardHelper.getNotices(deptno, pageSize);
        ControllerUtility.getNoticelist(cseNotices);
        modalAndView.addObject("noticeList", cseNotices);
        modalAndView.addObject("pageSize", pageSize);
        String viewName = noticeBoardHelper.getViewName(deptno);
        modalAndView.setViewName(viewName);
        return modalAndView;
    }

    @RequestMapping(value = "/pagination/prev/getTenders", method = RequestMethod.GET)
    public ModelAndView getPreviousPageNotice(@RequestParam("pageSize") Integer pageSize) {
        if (pageSize < 0) {
            pageSize = 0;
        }
        ModelAndView modalAndView = new ModelAndView();
        List<NoticeBoardDTO> tenders = noticeBoardHelper.getTenders(pageSize);
        ControllerUtility.getNoticelist(tenders);
        modalAndView.addObject("noticeList", tenders);
        modalAndView.addObject("pageSize", pageSize);
        String viewName = noticeBoardHelper.getTenderViewName();
        modalAndView.setViewName(viewName);
        return modalAndView;
    }

}
