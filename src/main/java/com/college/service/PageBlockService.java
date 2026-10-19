package com.college.service;

import com.college.core.entity.ContentFile;
import com.college.core.entity.PageBlock;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

/** Headings, subheadings, texts, images and documents that editors add to pages (table {@code page_block}). */
@Service
public interface PageBlockService {

    /** Visible blocks of a page in order (with file name/type/size); empty if none or the table is missing. */
    List<PageBlock> visibleBlocks(String pageKey);

    /** All blocks of a page (also hidden ones), for the edit screen. */
    List<PageBlock> allBlocks(String pageKey);

    /** "E" (edit everything), "A" (add, change own blocks) or null for the page's content area. */
    String access(Authentication authentication, String pageKey);

    /** True if the user may change or delete this block (edit access, or add access and own block). */
    boolean canChange(Authentication authentication, PageBlock block);

    PageBlock block(Long blockId);

    /**
     * Creates ({@code blockId} null) or changes a block. {@code fileData} may be null when changing (keeps the file).
     * Returns a message for the editor if the input is not valid; nothing is saved then.
     */
    String save(Authentication authentication, String pageKey, Long blockId, String type, String heading, String html,
                String display, boolean visible, String fileName, byte[] fileData);

    void delete(PageBlock block);

    /** -1 up, +1 down among the blocks of the page. */
    void move(PageBlock block, int direction);

    /** The file if it may be shown to this user (used by a visible block, or the user edits its area), else null. */
    ContentFile servableFile(Authentication authentication, Long fileId);

    /** Pages that have blocks and that the user may edit. */
    List<String> pagesWithBlocks(Authentication authentication);
}
