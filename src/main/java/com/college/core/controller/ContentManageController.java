package com.college.core.controller;

import com.college.core.config.KECUrlAuthenticationSuccessHandler;
import com.college.core.model.AreaAccess;
import com.college.service.ContentPermissionService;
import com.college.service.SiteSettingService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/** Website content editing for every role (docs/DYNAMIC_CONTENT_FINDINGS.md §1.3, §1.4). */
@Controller
public class ContentManageController {
    private final ContentPermissionService contentPermissionService;
    private final KECUrlAuthenticationSuccessHandler landingPages;
    private final SiteSettingService siteSettingService;

    public ContentManageController(ContentPermissionService contentPermissionService,
                                   KECUrlAuthenticationSuccessHandler landingPages,
                                   SiteSettingService siteSettingService) {
        this.contentPermissionService = contentPermissionService;
        this.landingPages = landingPages;
        this.siteSettingService = siteSettingService;
    }

    /** Dashboard: one tile per content area the user may edit. */
    @GetMapping("/manage")
    public ModelAndView dashboard(Authentication authentication, HttpServletResponse response) throws IOException {
        List<AreaAccess> areas = contentPermissionService.editableAreas(authentication);
        if (areas.isEmpty()) {
            // sendError, not an exception: CustomExceptionHandler would turn an exception into an empty 200
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
        ModelAndView mv = new ModelAndView("manage/dashboard.jsp");
        mv.addObject("areas", areas);
        // edit screens per area (more are added with each step of the plan)
        mv.addObject("settingAreas", siteSettingService.areasWithSettings());
        mv.addObject("isAdmin", ControllerUtility.getRole().contains("Admin"));
        return mv;
    }

    /** Target of the header's "Dashboard" link: the user's own landing page (was /auth/uploadDashboard for everybody). */
    @GetMapping("/mydashboard")
    public String myDashboard(Authentication authentication) {
        return "redirect:" + landingPages.determineTargetUrl(authentication);
    }
}
