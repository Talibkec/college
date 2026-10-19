package com.college.service;

import com.college.core.controller.ControllerUtility;
import com.college.core.entity.ContentChangeLog;
import com.college.core.entity.PageSection;
import com.college.core.model.AreaAccess;
import com.college.core.tags.PageHtml;
import com.college.repository.ContentChangeLogRepository;
import com.college.repository.PageSectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PageSectionServiceImpl implements PageSectionService {
    private static final Logger logger = LoggerFactory.getLogger(PageSectionServiceImpl.class);
    static final String ENTITY = "page_section";

    private final PageSectionRepository pageSectionRepository;
    private final ContentPermissionService contentPermissionService;
    private final ContentChangeLogService contentChangeLogService;
    private final ContentChangeLogRepository contentChangeLogRepository;

    /** Sections by "page/section"; replaced after every change (changes made by SQL need a restart). */
    private volatile Map<String, PageSection> cache;

    public PageSectionServiceImpl(PageSectionRepository pageSectionRepository,
                                  ContentPermissionService contentPermissionService,
                                  ContentChangeLogService contentChangeLogService,
                                  ContentChangeLogRepository contentChangeLogRepository) {
        this.pageSectionRepository = pageSectionRepository;
        this.contentPermissionService = contentPermissionService;
        this.contentChangeLogService = contentChangeLogService;
        this.contentChangeLogRepository = contentChangeLogRepository;
    }

    @Override
    public PageSection section(String key) {
        Map<String, PageSection> sections = cache;
        if (sections == null) {
            sections = load();
            cache = sections;
        }
        return sections.get(key);
    }

    private Map<String, PageSection> load() {
        Map<String, PageSection> sections = new HashMap<>();
        try {
            for (PageSection section : pageSectionRepository.findAll()) {
                sections.put(section.getPageKey() + "/" + section.getSectionKey(), section);
            }
        } catch (DataAccessException e) {
            logger.warn("page_section not readable, pages show their built-in texts: {}", e.getMostSpecificCause().getMessage());
        }
        return Collections.unmodifiableMap(sections);
    }

    @Override
    public List<PageSection> editableSections(Authentication authentication) {
        Set<String> areas = new HashSet<>();
        for (AreaAccess access : contentPermissionService.editableAreas(authentication)) {
            if (access.isEdit()) {
                areas.add(access.getArea().getAreaCode());
            }
        }
        return areas.isEmpty() ? Collections.emptyList() : pageSectionRepository.findByAreaCodeInOrderByPageKeyAscSortOrderAsc(areas);
    }

    @Override
    public List<PageSection> editableSectionsOfPage(Authentication authentication, String pageKey) {
        List<PageSection> sections = new ArrayList<>();
        for (PageSection section : editableSections(authentication)) {
            if (section.getPageKey().equals(pageKey)) {
                sections.add(section);
            }
        }
        return sections;
    }

    @Override
    public PageSection editableSection(Authentication authentication, Long sectionId) {
        for (PageSection section : editableSections(authentication)) {
            if (section.getSectionId().equals(sectionId)) {
                return section;
            }
        }
        return null;
    }

    @Override
    public String save(PageSection section, String html, boolean visible) {
        String before = section.getBodyHtml();
        // unchanged text (e.g. only "visible" changed) is kept as it is, so the original markup stays byte-identical
        String clean = html != null && html.equals(before) ? before : PageHtml.sanitize(html == null ? "" : html);
        boolean wasVisible = section.isVisible();
        if (Objects.equals(clean, before) && wasVisible == visible) {
            return clean;
        }
        section.setBodyHtml(clean);
        section.setVisible(visible);
        section.setUpdatedBy(ControllerUtility.getPrincipalName());
        section.setUpdatedAt(new Date());
        pageSectionRepository.save(section);
        contentChangeLogService.record(section.getAreaCode(), ENTITY, String.valueOf(section.getSectionId()), "update",
                before, clean);
        cache = null;
        return clean;
    }

    @Override
    public List<ContentChangeLog> history(Long sectionId) {
        return contentChangeLogRepository.findByEntityAndEntityIdOrderByLogIdDesc(ENTITY, String.valueOf(sectionId));
    }

    @Override
    public boolean restore(PageSection section, Long logId, boolean before) {
        ContentChangeLog log = contentChangeLogRepository.findById(logId).orElse(null);
        if (log == null || !ENTITY.equals(log.getEntity()) || !String.valueOf(section.getSectionId()).equals(log.getEntityId())) {
            return false;
        }
        String html = before ? log.getOldValue() : log.getNewValue();
        String current = section.getBodyHtml();
        // the original text came from the seed (not sanitised by us): store it unchanged so the page is identical again
        String restored = before && log.getOldValue() != null && isFirstChange(section, logId) ? html : PageHtml.sanitize(html);
        section.setBodyHtml(restored);
        section.setUpdatedBy(ControllerUtility.getPrincipalName());
        section.setUpdatedAt(new Date());
        pageSectionRepository.save(section);
        contentChangeLogService.record(section.getAreaCode(), ENTITY, String.valueOf(section.getSectionId()), "restore",
                current, restored);
        cache = null;
        return true;
    }

    private boolean isFirstChange(PageSection section, Long logId) {
        List<ContentChangeLog> all = history(section.getSectionId());
        return !all.isEmpty() && all.get(all.size() - 1).getLogId().equals(logId);
    }

    @Override
    public List<String> areasWithSections() {
        Set<String> areas = new TreeSet<>();
        try {
            for (PageSection section : pageSectionRepository.findAll()) {
                areas.add(section.getAreaCode());
            }
        } catch (DataAccessException e) {
            return Collections.emptyList();
        }
        return new ArrayList<>(areas);
    }
}
