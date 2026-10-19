package com.college.core.tags;

import com.college.core.entity.PageSection;
import com.college.service.PageSectionService;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

/**
 * {@code <kec:section key="about.history/main">today's HTML</kec:section>}: prints the stored (sanitised) HTML of the
 * section, nothing if the section is hidden, or the body when there is no row (or the table does not exist).
 */
public class SectionTag extends SimpleTagSupport {
    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        PageSectionService sections = WebApplicationContextUtils
                .getRequiredWebApplicationContext(pageContext.getServletContext()).getBean(PageSectionService.class);
        PageSection section = sections.section(key);
        if (section == null || section.getBodyHtml() == null) {
            if (getJspBody() != null) {
                getJspBody().invoke(null);
            }
        } else if (section.isVisible()) {
            pageContext.getOut().write(section.getBodyHtml());
        }
    }
}
