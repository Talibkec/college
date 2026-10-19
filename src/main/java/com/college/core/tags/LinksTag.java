package com.college.core.tags;

import com.college.service.LinkService;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

/**
 * {@code <kec:links list="top_bar" var="links"/>}: puts the visible entries of a link list into the request attribute
 * {@code var}, or removes it when the page should show its own links (list empty, switched off, table missing).
 * The JSP then renders {@code <c:choose><c:when test="${not empty links}">...</c:when><c:otherwise>today's
 * links</c:otherwise></c:choose>}. Request attribute {@link #PREVIEW} shows switched-off lists (preview in /manage/links).
 */
public class LinksTag extends SimpleTagSupport {
    public static final String PREVIEW = "kec.linksPreview";
    private String list;
    private String var;

    public void setList(String list) {
        this.list = list;
    }

    public void setVar(String var) {
        this.var = var;
    }

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        LinkService links = WebApplicationContextUtils
                .getRequiredWebApplicationContext(pageContext.getServletContext()).getBean(LinkService.class);
        boolean preview = Boolean.TRUE.equals(pageContext.getRequest().getAttribute(PREVIEW));
        Object items = links.render(list, preview);
        if (items == null) {
            pageContext.removeAttribute(var, PageContext.REQUEST_SCOPE);
        } else {
            pageContext.setAttribute(var, items, PageContext.REQUEST_SCOPE);
        }
    }
}
