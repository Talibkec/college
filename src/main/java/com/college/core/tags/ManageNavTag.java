package com.college.core.tags;

import com.college.service.ContentPermissionService;
import com.college.service.CourseFileService;
import com.college.service.LinkService;
import com.college.service.PageSectionService;
import com.college.service.SiteSettingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code <kec:manageNav/>}: the button bar of the content screens (/manage/**, /auth/roles/**) with one button per
 * screen the user may open, so nobody has to type the addresses. The markup is in /jsp/manage/nav.jsp.
 */
public class ManageNavTag extends SimpleTagSupport {

    /** One button; {@code active} marks the screen being shown. */
    public static class Item {
        private final String label;
        private final String url;
        private final String icon;
        private final boolean active;

        Item(String label, String url, String icon, boolean active) {
            this.label = label;
            this.url = url;
            this.icon = icon;
            this.active = active;
        }

        public String getLabel() { return label; }
        public String getUrl() { return url; }
        public String getIcon() { return icon; }
        public boolean isActive() { return active; }
    }

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        Object forwarded = request.getAttribute(jakarta.servlet.RequestDispatcher.FORWARD_REQUEST_URI);
        String path = (forwarded != null ? forwarded.toString() : request.getRequestURI()).substring(request.getContextPath().length());
        request.setAttribute("manageNav", items(WebApplicationContextUtils
                .getRequiredWebApplicationContext(pageContext.getServletContext()), path));
        try {
            pageContext.include("/jsp/manage/nav.jsp");
        } catch (ServletException e) {
            throw new JspException(e);
        }
    }

    static List<Item> items(ApplicationContext context, String path) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Item> items = new ArrayList<>();
        boolean admin = authentication != null && authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).anyMatch("Admin"::equals);
        if (!context.getBean(ContentPermissionService.class).editableAreas(authentication).isEmpty()) {
            items.add(new Item("Overview", "/manage", "fa-th-large", path.equals("/manage")));
            if (!context.getBean(PageSectionService.class).editableSections(authentication).isEmpty()) {
                items.add(new Item("Page texts", "/manage/sections", "fa-file-alt", path.startsWith("/manage/sections")));
            }
            items.add(new Item("Add to a page", "/manage/blocks", "fa-plus-circle", path.startsWith("/manage/blocks")));
            if (!context.getBean(CourseFileService.class).manageableSites(authentication).isEmpty()) {
                items.add(new Item("Course files", "/manage/course-files", "fa-book-open", path.startsWith("/manage/course-files")));
            }
            if (!context.getBean(SiteSettingService.class).editableSettings(authentication).isEmpty()) {
                items.add(new Item("Contact & footer texts", "/manage/settings", "fa-cog", path.startsWith("/manage/settings")));
            }
            if (context.getBean(LinkService.class).canEdit(authentication, "main_menu")) {
                items.add(new Item("Menus & links", "/manage/links", "fa-bars", path.startsWith("/manage/links")));
            }
        }
        if (admin) {
            items.add(new Item("User roles", "/auth/roles", "fa-users", path.equals("/auth/roles") || path.startsWith("/auth/roles/user")));
            items.add(new Item("Who may edit what", "/auth/roles/areas", "fa-key", path.startsWith("/auth/roles/areas")));
            items.add(new Item("Admin dashboard", "/auth/uploadDashboard", "fa-tachometer-alt", false));
        }
        items.add(new Item("View website", "/", "fa-home", false));
        return items;
    }
}
