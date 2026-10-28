package com.college.core.tags;

import com.college.core.entity.PageBlock;
import com.college.service.PageBlockService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.util.List;

/**
 * {@code <kec:blocks/>}: the headings, texts, images and documents editors added to this page (key from the page URL),
 * plus an "Edit this page" link for its editors. Prints nothing at all when there is neither, so pages without added
 * content stay byte-identical. The markup is in /jsp/common/blocks.jsp.
 */
public class BlocksTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        Object forwarded = request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
        String uri = forwarded != null ? forwarded.toString() : request.getRequestURI();
        String pageKey = PageKeys.fromPath(uri.substring(request.getContextPath().length()), request.getParameter("dept_id"),
                request.getParameter("facultyId"));
        if (pageKey == null || PageKeys.area(pageKey) == null) {
            return;
        }
        PageBlockService blocks = WebApplicationContextUtils
                .getRequiredWebApplicationContext(pageContext.getServletContext()).getBean(PageBlockService.class);
        List<PageBlock> visible = blocks.visibleBlocks(pageKey);
        boolean editor = blocks.access(SecurityContextHolder.getContext().getAuthentication(), pageKey) != null;
        if (visible.isEmpty() && !editor) {
            return;
        }
        request.setAttribute("pageBlocks", visible);
        request.setAttribute("pageBlocksKey", pageKey);
        request.setAttribute("pageBlocksEditor", editor);
        try {
            pageContext.include("/jsp/common/blocks.jsp");
        } catch (ServletException e) {
            throw new JspException(e);
        }
    }
}
