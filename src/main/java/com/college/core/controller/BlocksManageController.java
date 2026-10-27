package com.college.core.controller;

import com.college.core.entity.PageBlock;
import com.college.core.tags.PageKeys;
import com.college.service.PageBlockService;
import com.college.service.PageSectionService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** {@code /manage/blocks}: headings, subheadings, texts, images, documents and links added to a page. */
@Controller
public class BlocksManageController {
    private final PageBlockService pageBlockService;
    private final PageSectionService pageSectionService;

    public BlocksManageController(PageBlockService pageBlockService, PageSectionService pageSectionService) {
        this.pageBlockService = pageBlockService;
        this.pageSectionService = pageSectionService;
    }

    /**
     * Without {@code page}: the pages with added content and a field to open any page by its address ({@code url}).
     * With {@code page}: the blocks of the page, {@code add=<type>} or {@code edit=<id>} opens the form.
     */
    @GetMapping("/manage/blocks")
    public ModelAndView blocks(Authentication authentication, HttpServletResponse response,
                               @RequestParam(value = "page", required = false) String page,
                               @RequestParam(value = "url", required = false) String url,
                               @RequestParam(value = "add", required = false) String add,
                               @RequestParam(value = "edit", required = false) Long edit,
                               @RequestParam(value = "saved", required = false) String saved) throws IOException {
        if (page == null && url != null) {
            String key = PageKeys.fromPath(path(url));
            if (key == null || pageBlockService.access(authentication, key) == null) {
                ModelAndView mv = new ModelAndView("manage/blocks.jsp");
                mv.addObject("pages", pageBlockService.pagesWithBlocks(authentication));
                mv.addObject("error", "You cannot add content to that page (or the address is not a page of this website).");
                mv.addObject("url", url);
                return mv;
            }
            return new ModelAndView("redirect:/manage/blocks?page=" + key);
        }
        if (page == null) {
            ModelAndView mv = new ModelAndView("manage/blocks.jsp");
            mv.addObject("pages", pageBlockService.pagesWithBlocks(authentication));
            return mv;
        }
        String access = pageBlockService.access(authentication, page);
        if (access == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
        PageBlock editBlock = null;
        if (edit != null) {
            editBlock = pageBlockService.block(edit);
            if (editBlock == null || !editBlock.getPageKey().equals(page) || !pageBlockService.canChange(authentication, editBlock)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return null;
            }
        }
        return view(authentication, page, access, editBlock, editBlock == null ? add : editBlock.getBlockType(), null, saved != null);
    }

    @PostMapping("/manage/blocks/save")
    public ModelAndView save(Authentication authentication, HttpServletResponse response,
                             @RequestParam("page") String page,
                             @RequestParam(value = "blockId", required = false) Long blockId,
                             @RequestParam(value = "type", required = false) String type,
                             @RequestParam(value = "heading", required = false) String heading,
                             @RequestParam(value = "html", required = false) String html,
                             @RequestParam(value = "link", required = false) String link,
                             @RequestParam(value = "display", required = false) String display,
                             @RequestParam(value = "visible", required = false) String visible,
                             @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        String access = pageBlockService.access(authentication, page);
        PageBlock existing = pageBlockService.block(blockId);
        if (access == null || (blockId != null && (existing == null || !existing.getPageKey().equals(page)
                || !pageBlockService.canChange(authentication, existing)))) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
        boolean hasFile = file != null && !file.isEmpty();
        if (PageBlock.LINK.equals(existing != null ? existing.getBlockType() : type)) {
            html = link; // the address of a link block is stored in body_html
        }
        String error = pageBlockService.save(authentication, page, blockId, type, heading, html, display, visible != null,
                hasFile ? file.getOriginalFilename() : null, hasFile ? file.getBytes() : null);
        if (error != null) {
            PageBlock entered = new PageBlock(); // only for showing the form again; the stored block is not touched
            if (existing != null) {
                entered.setBlockId(existing.getBlockId());
                entered.setFileId(existing.getFileId());
            }
            entered.setBlockType(existing != null ? existing.getBlockType() : type);
            entered.setHeading(heading);
            entered.setBodyHtml(html);
            entered.setDisplay(display);
            entered.setVisible(visible != null);
            return view(authentication, page, access, entered, entered.getBlockType(), error, false);
        }
        return new ModelAndView("redirect:/manage/blocks?saved=1&page=" + page);
    }

    @PostMapping("/manage/blocks/delete")
    public String delete(Authentication authentication, HttpServletResponse response, @RequestParam("page") String page,
                         @RequestParam("blockId") Long blockId) throws IOException {
        PageBlock block = changeable(authentication, response, page, blockId);
        if (block == null) return null;
        pageBlockService.delete(block);
        return "redirect:/manage/blocks?saved=1&page=" + page;
    }

    @PostMapping("/manage/blocks/move")
    public String move(Authentication authentication, HttpServletResponse response, @RequestParam("page") String page,
                       @RequestParam("blockId") Long blockId, @RequestParam("direction") int direction) throws IOException {
        PageBlock block = changeable(authentication, response, page, blockId);
        if (block == null) return null;
        pageBlockService.move(block, direction);
        return "redirect:/manage/blocks?saved=1&page=" + page;
    }

    private PageBlock changeable(Authentication authentication, HttpServletResponse response, String page, Long blockId)
            throws IOException {
        PageBlock block = pageBlockService.block(blockId);
        if (block == null || !block.getPageKey().equals(page) || !pageBlockService.canChange(authentication, block)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
        return block;
    }

    private ModelAndView view(Authentication authentication, String page, String access, PageBlock editBlock,
                              String formType, String error, boolean saved) {
        List<PageBlock> blocks = pageBlockService.allBlocks(page);
        Set<Long> changeable = new HashSet<>();
        for (PageBlock block : blocks) {
            if (pageBlockService.canChange(authentication, block)) changeable.add(block.getBlockId());
        }
        ModelAndView mv = new ModelAndView("manage/blocks.jsp");
        mv.addObject("page", page);
        mv.addObject("pageUrl", PageKeys.url(page));
        mv.addObject("access", access);
        mv.addObject("blocks", blocks);
        // the page's existing texts (page_section) the user may change: edited in /manage/sections
        mv.addObject("sections", pageSectionService.editableSectionsOfPage(authentication, page));
        mv.addObject("changeable", changeable);
        mv.addObject("editBlock", editBlock);
        mv.addObject("formType", formType);
        mv.addObject("error", error);
        mv.addObject("saved", saved);
        return mv;
    }

    /** "/about/history", "about/history" or a full address of this site → "/about/history". */
    private static String path(String url) {
        String u = url.trim();
        try {
            if (u.startsWith("http://") || u.startsWith("https://")) {
                u = URI.create(u).getPath();
            }
        } catch (IllegalArgumentException e) {
            return null;
        }
        if (u == null) return null;
        u = u.replaceAll("[?#].*", "");
        return u.startsWith("/") ? u : "/" + u;
    }
}
