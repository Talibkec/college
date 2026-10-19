package com.college.core.tags;

import com.college.service.SiteSettingService;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

/**
 * {@code <kec:setting key="contact.phone">06452-239122</kec:setting>}: prints the value of the setting, or the tag body
 * (today's text) when the setting has no value or the table is not there yet. The value is escaped with
 * {@link HtmlText#escapeMarkup}.
 */
public class SettingTag extends SimpleTagSupport {
    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        SiteSettingService settings = WebApplicationContextUtils
                .getRequiredWebApplicationContext(pageContext.getServletContext()).getBean(SiteSettingService.class);
        String value = settings.value(key);
        if (value != null) {
            pageContext.getOut().write(HtmlText.escapeMarkup(value));
        } else if (getJspBody() != null) {
            getJspBody().invoke(null);
        }
    }
}
