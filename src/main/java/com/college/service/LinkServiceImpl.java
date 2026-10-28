package com.college.service;

import com.college.core.controller.ControllerUtility;
import com.college.core.entity.LinkItem;
import com.college.repository.LinkItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

@Service
public class LinkServiceImpl implements LinkService {
    private static final Logger logger = LoggerFactory.getLogger(LinkServiceImpl.class);
    /** Relative path, anchor, http(s), mailto or tel; never javascript:/data: or protocol-relative "//host". */
    private static final Pattern URL = Pattern.compile("(/(?!/)|#|https?://|mailto:|tel:)[^\\s<>\"']*");
    private static final Pattern CSS = Pattern.compile("[A-Za-z0-9 _:./-]*");
    static final String AREA = "menu";

    private static final Map<String, String> LISTS = new LinkedHashMap<>();

    static {
        LISTS.put("main_menu", "Main menu (desktop, mobile and side menu)");
        LISTS.put("top_bar", "Top bar links (above the menu)");
        LISTS.put("social", "Social media icons (header)");
        LISTS.put("footer", "Footer links");
        LISTS.put("about_sidebar", "Sidebar: About KEC");
        LISTS.put("academic_sidebar", "Sidebar: Academics");
        LISTS.put("facilities_sidebar", "Sidebar: Facilities");
        LISTS.put("tp_sidebar", "Sidebar: Training & Placement");
        LISTS.put("approval_sidebar", "Sidebar: Approval");
        LISTS.put("rti_sidebar", "Sidebar: Right To Information");
    }

    private final LinkItemRepository linkItemRepository;
    private final SiteSettingService siteSettingService;
    private final ContentPermissionService contentPermissionService;
    private final ContentChangeLogService contentChangeLogService;

    /** Visible entries per list as trees; replaced after every change (changes made by SQL need a restart). */
    private volatile Map<String, List<LinkItem>> cache;

    public LinkServiceImpl(LinkItemRepository linkItemRepository, SiteSettingService siteSettingService,
                           ContentPermissionService contentPermissionService,
                           ContentChangeLogService contentChangeLogService) {
        this.linkItemRepository = linkItemRepository;
        this.siteSettingService = siteSettingService;
        this.contentPermissionService = contentPermissionService;
        this.contentChangeLogService = contentChangeLogService;
    }

    @Override
    public Map<String, String> lists() {
        return Collections.unmodifiableMap(LISTS);
    }

    @Override
    public List<LinkItem> render(String listCode, boolean preview) {
        if (!preview && !enabled(listCode)) {
            return null;
        }
        Map<String, List<LinkItem>> lists = cache;
        if (lists == null) {
            lists = load();
            cache = lists;
        }
        List<LinkItem> items = lists.get(listCode);
        return items == null || items.isEmpty() ? null : items;
    }

    private Map<String, List<LinkItem>> load() {
        Map<String, List<LinkItem>> lists = new HashMap<>();
        try {
            List<LinkItem> visible = linkItemRepository.findByVisibleTrueOrderBySortOrderAscLinkIdAsc();
            Map<Long, LinkItem> byId = new HashMap<>();
            for (LinkItem item : visible) {
                item.setChildren(new ArrayList<>());
                byId.put(item.getLinkId(), item);
            }
            for (LinkItem item : visible) {
                if (item.getParentId() == null) {
                    lists.computeIfAbsent(item.getListCode(), k -> new ArrayList<>()).add(item);
                } else if (byId.containsKey(item.getParentId())) {
                    byId.get(item.getParentId()).getChildren().add(item);
                } // children of a hidden parent are hidden too
            }
        } catch (DataAccessException e) {
            logger.warn("link_item not readable, pages show their built-in links: {}", e.getMostSpecificCause().getMessage());
        }
        return lists;
    }

    @Override
    public boolean enabled(String listCode) {
        return !"no".equals(siteSettingService.value("links." + listCode + ".enabled"));
    }

    @Override
    public List<LinkItem> allItems(String listCode) {
        List<LinkItem> all = linkItemRepository.findByListCodeOrderBySortOrderAscLinkIdAsc(listCode);
        List<LinkItem> ordered = new ArrayList<>();
        for (LinkItem parent : all) {
            if (parent.getParentId() == null) {
                ordered.add(parent);
                for (LinkItem child : all) {
                    if (parent.getLinkId().equals(child.getParentId())) {
                        ordered.add(child);
                    }
                }
            }
        }
        return ordered;
    }

    @Override
    public LinkItem item(Long linkId) {
        return linkId == null ? null : linkItemRepository.findById(linkId).orElse(null);
    }

    @Override
    public boolean canEdit(Authentication authentication, String listCode) {
        return LISTS.containsKey(listCode) && contentPermissionService.can(authentication, AREA, "E");
    }

    @Override
    public String save(LinkItem item) {
        String label = item.getLabel() == null ? "" : item.getLabel().trim();
        String url = item.getUrl() == null ? "" : item.getUrl().trim();
        if (label.isEmpty() || label.length() > 255) {
            return "Please enter a label (at most 255 characters).";
        }
        if (url.isEmpty() || url.length() > 1000 || !URL.matcher(url).matches()) {
            return "The link must start with / (a page of this site), #, http://, https://, mailto: or tel:.";
        }
        if (!CSS.matcher(nz(item.getIconClass())).matches() || !CSS.matcher(nz(item.getCssClass())).matches()) {
            return "CSS classes may only contain letters, digits, spaces and _ : . / -";
        }
        if (item.getParentId() != null) {
            LinkItem parent = item(item.getParentId());
            if (parent == null || parent.getParentId() != null || !parent.getListCode().equals(item.getListCode())
                    || parent.getLinkId().equals(item.getLinkId())) {
                return "A sub-entry must belong to a top-level entry of the same list.";
            }
        }
        LinkItem before = item(item.getLinkId());
        String oldValue = before == null ? null : describe(before);
        item.setLabel(label);
        item.setUrl(url);
        item.setIconClass(emptyToNull(item.getIconClass()));
        item.setCssClass(emptyToNull(item.getCssClass()));
        item.setGroupLabel(emptyToNull(item.getGroupLabel()));
        item.setAreaCode(AREA);
        if (before == null) {
            item.setLinkId(null);
            item.setSortOrder(nextSortOrder(item.getListCode(), item.getParentId()));
        } else {
            item.setSortOrder(before.getSortOrder());
        }
        item.setUpdatedBy(ControllerUtility.getPrincipalName());
        item.setUpdatedAt(new Date());
        LinkItem saved = linkItemRepository.save(item);
        contentChangeLogService.record(AREA, "link_item", String.valueOf(saved.getLinkId()), before == null ? "create" : "update",
                oldValue, describe(saved));
        cache = null;
        return null;
    }

    @Override
    public void delete(Long linkId) {
        LinkItem item = item(linkId);
        if (item == null) {
            return;
        }
        for (LinkItem child : linkItemRepository.findByParentId(linkId)) {
            linkItemRepository.delete(child);
            contentChangeLogService.record(AREA, "link_item", String.valueOf(child.getLinkId()), "delete", describe(child), null);
        }
        linkItemRepository.delete(item);
        contentChangeLogService.record(AREA, "link_item", String.valueOf(linkId), "delete", describe(item), null);
        cache = null;
    }

    @Override
    public void move(Long linkId, int direction) {
        LinkItem item = item(linkId);
        if (item == null) {
            return;
        }
        List<LinkItem> siblings = new ArrayList<>();
        for (LinkItem other : linkItemRepository.findByListCodeOrderBySortOrderAscLinkIdAsc(item.getListCode())) {
            if (Objects.equals(other.getParentId(), item.getParentId())) {
                siblings.add(other);
            }
        }
        int index = -1;
        for (int i = 0; i < siblings.size(); i++) {
            if (siblings.get(i).getLinkId().equals(linkId)) index = i;
        }
        int target = index + (direction < 0 ? -1 : 1);
        if (index < 0 || target < 0 || target >= siblings.size()) {
            return;
        }
        Collections.swap(siblings, index, target);
        for (int i = 0; i < siblings.size(); i++) { // renumber 10, 20, 30 ... so equal sort orders cannot block a move
            LinkItem sibling = siblings.get(i);
            if (sibling.getSortOrder() != (i + 1) * 10) {
                sibling.setSortOrder((i + 1) * 10);
                linkItemRepository.save(sibling);
            }
        }
        contentChangeLogService.record(AREA, "link_item", String.valueOf(linkId), "update", null,
                "moved " + (direction < 0 ? "up" : "down"));
        cache = null;
    }

    @Override
    public void setEnabled(String listCode, boolean enabled) {
        siteSettingService.setSwitch("links." + listCode + ".enabled", enabled ? "yes" : "no", AREA,
                "Show '" + LISTS.get(listCode) + "' from the database");
    }

    @Override
    public List<LinkItem> menuPath(String url) {
        Map<String, List<LinkItem>> lists = cache;
        if (lists == null) {
            lists = load();
            cache = lists;
        }
        for (LinkItem top : lists.getOrDefault("main_menu", Collections.emptyList())) {
            if (url.equals(top.getUrl())) {
                return Collections.singletonList(top);
            }
            for (LinkItem child : top.getChildren()) {
                if (url.equals(child.getUrl())) {
                    return Arrays.asList(top, child);
                }
            }
        }
        return null;
    }

    private int nextSortOrder(String listCode, Long parentId) {
        int max = 0;
        for (LinkItem other : linkItemRepository.findByListCodeOrderBySortOrderAscLinkIdAsc(listCode)) {
            if (Objects.equals(other.getParentId(), parentId)) {
                max = Math.max(max, other.getSortOrder());
            }
        }
        return max + 10;
    }

    private static String describe(LinkItem item) {
        return item.getLabel() + " -> " + item.getUrl() + (item.isVisible() ? "" : " (hidden)")
                + (item.getGroupLabel() == null ? "" : " [" + item.getGroupLabel() + "]");
    }

    private static String nz(String s) {
        return s == null ? "" : s;
    }

    private static String emptyToNull(String s) {
        return s == null || s.trim().isEmpty() ? null : s.trim();
    }
}
