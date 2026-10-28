package com.college.core.controller;

import com.college.core.entity.LinkItem;
import com.college.service.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * New pages {@code /page/<name>}: a page exists while a visible main-menu entry links to it (/manage/links). Title =
 * the entry's label; sidebar = the sub-entries of its top-level entry; content = page blocks (key {@code page.<name>},
 * area {@code menu}), added with "Edit this page".
 */
@Controller
public class CustomPageController {
    private final LinkService linkService;

    public CustomPageController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/page/{name:[a-z0-9-]{1,60}}")
    public ModelAndView page(@PathVariable String name, HttpServletResponse response) throws IOException {
        String url = "/page/" + name;
        List<LinkItem> path = linkService.menuPath(url);
        if (path == null) {
            response.sendError(HttpStatus.NOT_FOUND.value());
            return null;
        }
        LinkItem top = path.get(0);
        ModelAndView mv = new ModelAndView("page/custom.jsp");
        mv.addObject("pageTitle", path.get(path.size() - 1).getLabel());
        mv.addObject("sidebarTitle", top.getLabel());
        mv.addObject("sidebarLinks", top.getChildren());
        mv.addObject("pageUrl", url);
        return mv;
    }
}
