package com.college.service;

import com.college.core.entity.ContentChangeLog;
import com.college.core.entity.PageSection;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

/** Editable HTML blocks of pages (table {@code page_section}). */
@Service
public interface PageSectionService {

    /** The section "page/section" (e.g. "about.history/main"), or null (page shows its own HTML). */
    PageSection section(String key);

    /** Sections the user may edit, by page. */
    List<PageSection> editableSections(Authentication authentication);

    /** The section if the user may edit it, else null. */
    PageSection editableSection(Authentication authentication, Long sectionId);

    /** Sanitises and saves the HTML; returns the stored HTML. */
    String save(PageSection section, String html, boolean visible);

    /** Saved versions of the section, newest first. */
    List<ContentChangeLog> history(Long sectionId);

    /**
     * Restores a version from the history: {@code before} = the text before that change (e.g. the original text),
     * otherwise the text saved by that change. Returns false if the entry does not belong to the section.
     */
    boolean restore(PageSection section, Long logId, boolean before);

    List<String> areasWithSections();
}
