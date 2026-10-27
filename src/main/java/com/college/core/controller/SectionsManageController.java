package com.college.core.controller;

import com.college.core.entity.PageSection;
import com.college.core.tags.PageHtml;
import com.college.service.PageBlockService;
import com.college.service.PageSectionService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/** {@code /manage/sections}: page texts (treatment T2) with preview, history and restore. */
@Controller
public class SectionsManageController {
    private final PageSectionService pageSectionService;
    private final PageBlockService pageBlockService;

    public SectionsManageController(PageSectionService pageSectionService, PageBlockService pageBlockService) {
        this.pageSectionService = pageSectionService;
        this.pageBlockService = pageBlockService;
    }

    @GetMapping("/manage/sections")
    public ModelAndView sections(Authentication authentication, HttpServletResponse response,
                                 @RequestParam(value = "area", required = false) String area,
                                 @RequestParam(value = "id", required = false) Long id,
                                 @RequestParam(value = "saved", required = false) String saved) throws IOException {
        List<PageSection> sections = pageSectionService.editableSections(authentication);
        if (sections.isEmpty()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
        ModelAndView mv = new ModelAndView("manage/sections.jsp");
        mv.addObject("sections", sections);
        mv.addObject("area", area);
        mv.addObject("saved", saved != null);
        if (id != null) {
            PageSection section = pageSectionService.editableSection(authentication, id);
            if (section == null) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return null;
            }
            mv.addObject("section", section);
            mv.addObject("html", section.getBodyHtml());
            mv.addObject("history", pageSectionService.history(id));
            mv.addObject("blockAccess", pageBlockService.access(authentication, section.getPageKey()));
        }
        return mv;
    }

    @PostMapping("/manage/sections/save")
    public String save(Authentication authentication, HttpServletResponse response, @RequestParam("id") Long id,
                       @RequestParam("html") String html,
                       @RequestParam(value = "visible", required = false) String visible) throws IOException {
        PageSection section = pageSectionService.editableSection(authentication, id);
        if (section == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
        pageSectionService.save(section, html, visible != null);
        return "redirect:/manage/sections?saved=1&id=" + id;
    }

    /** Shows the entered HTML (sanitised, as it would be stored) inside the site layout; nothing is saved. */
    @PostMapping("/manage/sections/preview")
    public ModelAndView preview(Authentication authentication, HttpServletResponse response, @RequestParam("id") Long id,
                                @RequestParam("html") String html) throws IOException {
        PageSection section = pageSectionService.editableSection(authentication, id);
        if (section == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
        ModelAndView mv = new ModelAndView("manage/sectionPreview.jsp");
        mv.addObject("section", section);
        mv.addObject("previewHtml", PageHtml.sanitize(html));
        return mv;
    }

    @PostMapping("/manage/sections/restore")
    public String restore(Authentication authentication, HttpServletResponse response, @RequestParam("id") Long id,
                          @RequestParam("logId") Long logId,
                          @RequestParam(value = "before", required = false) String before) throws IOException {
        PageSection section = pageSectionService.editableSection(authentication, id);
        if (section == null || !pageSectionService.restore(section, logId, before != null)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
        return "redirect:/manage/sections?saved=1&id=" + id;
    }
}
