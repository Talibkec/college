package com.college.core.controller;

import com.college.core.entity.LinkItem;
import com.college.core.tags.LinksTag;
import com.college.service.LinkService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** {@code /manage/links}: menus, top bar, social icons, footer links and page sidebars (treatment T3, area "menu"). */
@Controller
public class LinksManageController {
    private final LinkService linkService;

    public LinksManageController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/manage/links")
    public ModelAndView links(Authentication authentication, HttpServletResponse response,
                              @RequestParam(value = "list", required = false) String list,
                              @RequestParam(value = "edit", required = false) Long edit,
                              @RequestParam(value = "saved", required = false) String saved) throws IOException {
        String listCode = list == null ? "main_menu" : list;
        if (!linkService.canEdit(authentication, listCode)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
        LinkItem item = linkService.item(edit);
        return view(listCode, item != null && listCode.equals(item.getListCode()) ? item : null, null, saved != null);
    }

    @PostMapping("/manage/links/save")
    public ModelAndView save(Authentication authentication, HttpServletRequest request, HttpServletResponse response,
                             @RequestParam("list") String list) throws IOException {
        if (!linkService.canEdit(authentication, list)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
        LinkItem item = new LinkItem();
        Long linkId = longParam(request, "linkId");
        LinkItem existing = linkService.item(linkId);
        if (existing != null && !list.equals(existing.getListCode())) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        item.setLinkId(existing == null ? null : linkId);
        item.setListCode(list);
        item.setParentId(longParam(request, "parentId"));
        item.setGroupLabel(request.getParameter("groupLabel"));
        item.setLabel(request.getParameter("label"));
        item.setUrl(request.getParameter("url"));
        item.setOpenNewTab(request.getParameter("openNewTab") != null);
        item.setVisible(request.getParameter("visible") != null);
        item.setIconClass(request.getParameter("iconClass"));
        item.setCssClass(request.getParameter("cssClass"));
        String error = linkService.save(item);
        if (error != null) {
            return view(list, item, error, false);
        }
        return new ModelAndView("redirect:/manage/links?saved=1&list=" + list);
    }

    @PostMapping("/manage/links/delete")
    public String delete(Authentication authentication, HttpServletResponse response,
                         @RequestParam("list") String list, @RequestParam("linkId") Long linkId) throws IOException {
        return change(authentication, response, list, linkId, () -> linkService.delete(linkId));
    }

    @PostMapping("/manage/links/move")
    public String move(Authentication authentication, HttpServletResponse response, @RequestParam("list") String list,
                       @RequestParam("linkId") Long linkId, @RequestParam("direction") int direction) throws IOException {
        return change(authentication, response, list, linkId, () -> linkService.move(linkId, direction));
    }

    /** Switches a list between "from the database" and "the page's own links". */
    @PostMapping("/manage/links/enabled")
    public String enabled(Authentication authentication, HttpServletResponse response, @RequestParam("list") String list,
                          @RequestParam("enabled") String enabled) throws IOException {
        if (!linkService.canEdit(authentication, list)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
        linkService.setEnabled(list, "yes".equals(enabled));
        return "redirect:/manage/links?saved=1&list=" + list;
    }

    /** The home page with every list from the database, also the switched-off ones (e.g. the main menu). */
    @GetMapping("/manage/links/preview")
    public String preview(Authentication authentication, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!linkService.canEdit(authentication, "main_menu")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
        request.setAttribute(LinksTag.PREVIEW, Boolean.TRUE);
        return "forward:/";
    }

    private String change(Authentication authentication, HttpServletResponse response, String list, Long linkId,
                          Runnable action) throws IOException {
        LinkItem item = linkService.item(linkId);
        if (!linkService.canEdit(authentication, list) || (item != null && !list.equals(item.getListCode()))) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
        action.run();
        return "redirect:/manage/links?saved=1&list=" + list;
    }

    private ModelAndView view(String list, LinkItem editItem, String error, boolean saved) {
        List<LinkItem> items = linkService.allItems(list);
        List<LinkItem> parents = new ArrayList<>();
        for (LinkItem item : items) {
            if (item.getParentId() == null) parents.add(item);
        }
        ModelAndView mv = new ModelAndView("manage/links.jsp");
        mv.addObject("lists", linkService.lists());
        mv.addObject("list", list);
        mv.addObject("listName", linkService.lists().get(list));
        mv.addObject("items", items);
        mv.addObject("parents", parents);
        mv.addObject("editItem", editItem);
        mv.addObject("enabled", linkService.enabled(list));
        mv.addObject("error", error);
        mv.addObject("saved", saved);
        return mv;
    }

    private static Long longParam(HttpServletRequest request, String name) {
        String value = request.getParameter(name);
        if (value == null || value.trim().isEmpty()) return null;
        try {
            return Long.valueOf(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
