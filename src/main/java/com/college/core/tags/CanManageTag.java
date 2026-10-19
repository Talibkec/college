package com.college.core.tags;

import com.college.service.ContentPermissionService;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

/**
 * {@code <kec:canManage var="x"/>}: sets the request attribute {@code x} to true if the logged-in user may change any
 * part of the website (shows the header's "Website Content" button). Prints nothing.
 */
public class CanManageTag extends SimpleTagSupport {
    private String var;

    public void setVar(String var) {
        this.var = var;
    }

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean can = false;
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            can = !WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext())
                    .getBean(ContentPermissionService.class).editableAreas(authentication).isEmpty();
        }
        pageContext.getRequest().setAttribute(var, can);
    }
}
