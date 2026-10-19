package com.college.core.controller;

import com.college.core.entity.SiteSetting;
import com.college.service.SiteSettingService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/** {@code /manage/settings}: texts such as phone, e-mail and footer lines (treatment T1). */
@Controller
public class SettingsManageController {
    private final SiteSettingService siteSettingService;

    public SettingsManageController(SiteSettingService siteSettingService) {
        this.siteSettingService = siteSettingService;
    }

    @GetMapping("/manage/settings")
    public ModelAndView settings(Authentication authentication, HttpServletResponse response,
                                 @RequestParam(value = "area", required = false) String area,
                                 @RequestParam(value = "saved", required = false) String saved) throws IOException {
        List<SiteSetting> settings = siteSettingService.editableSettings(authentication);
        if (settings.isEmpty()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
        return view(settings, area, Collections.emptyMap(), Collections.emptyMap(), saved != null);
    }

    /**
     * Fields: {@code value:<key>} (the text) and {@code original:<key>} (checkbox "show the page's own text").
     * On validation errors the form is shown again with the entered values; nothing is saved.
     */
    @PostMapping("/manage/settings")
    public ModelAndView save(Authentication authentication, HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "area", required = false) String area) throws IOException {
        List<SiteSetting> settings = siteSettingService.editableSettings(authentication);
        if (settings.isEmpty()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
        Map<String, String> values = new LinkedHashMap<>();
        Map<String, String> entered = new HashMap<>();
        for (SiteSetting setting : settings) {
            String key = setting.getSettingKey();
            String value = request.getParameter("value:" + key);
            if (request.getParameter("original:" + key) != null) {
                values.put(key, null);
            } else if (value != null) {
                values.put(key, value);
                entered.put(key, value);
            }
        }
        Map<String, String> errors = siteSettingService.save(authentication, values);
        if (!errors.isEmpty()) {
            return view(siteSettingService.editableSettings(authentication), area, errors, entered, false);
        }
        return new ModelAndView("redirect:/manage/settings?saved=1" + (area == null ? "" : "&area=" + area.replaceAll("[^A-Za-z0-9._-]", "")));
    }

    private static ModelAndView view(List<SiteSetting> settings, String area, Map<String, String> errors,
                                     Map<String, String> entered, boolean saved) {
        ModelAndView mv = new ModelAndView("manage/settings.jsp");
        mv.addObject("settings", settings);
        mv.addObject("area", area);
        mv.addObject("errors", errors);
        mv.addObject("entered", entered);
        mv.addObject("saved", saved);
        return mv;
    }
}
