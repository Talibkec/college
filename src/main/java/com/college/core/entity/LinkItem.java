package com.college.core.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** One entry of a menu or link list (table {@code link_item}, treatment T3). */
@Entity
@Table(name = "link_item")
public class LinkItem {
    private Long linkId;
    private String listCode;
    private String areaCode;
    private Long parentId;
    private String groupLabel;
    private String label;
    private String url;
    private boolean openNewTab;
    private String iconClass;
    private String cssClass;
    private String visibility = "all";
    private int sortOrder;
    private boolean visible = true;
    private String updatedBy;
    private Date updatedAt;
    private List<LinkItem> children = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "link_id")
    public Long getLinkId() {
        return linkId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    @Column(name = "list_code")
    public String getListCode() {
        return listCode;
    }

    public void setListCode(String listCode) {
        this.listCode = listCode;
    }

    @Column(name = "area_code")
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    @Column(name = "parent_id")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /** Heading of a column in a submenu, e.g. "Under Graduates" (departments menu). */
    @Column(name = "group_label")
    public String getGroupLabel() {
        return groupLabel;
    }

    public void setGroupLabel(String groupLabel) {
        this.groupLabel = groupLabel;
    }

    @Column(name = "label")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "open_new_tab")
    public boolean isOpenNewTab() {
        return openNewTab;
    }

    public void setOpenNewTab(boolean openNewTab) {
        this.openNewTab = openNewTab;
    }

    @Column(name = "icon_class")
    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    @Column(name = "css_class")
    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    @Column(name = "visibility")
    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @Column(name = "sort_order")
    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Column(name = "visible")
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Column(name = "updated_by")
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /** Visible sub-entries (filled by LinkService for rendering, not a mapped relation). */
    @Transient
    public List<LinkItem> getChildren() {
        return children;
    }

    public void setChildren(List<LinkItem> children) {
        this.children = children;
    }

    /** True if the sub-entries are arranged in columns with headings (group labels). */
    @Transient
    public boolean isGrouped() {
        for (LinkItem child : children) {
            if (child.getGroupLabel() != null && !child.getGroupLabel().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /** Sub-entries per group label, in order of appearance. */
    @Transient
    public List<Group> getGroups() {
        Map<String, Group> groups = new LinkedHashMap<>();
        for (LinkItem child : children) {
            String label = child.getGroupLabel() == null ? "" : child.getGroupLabel();
            groups.computeIfAbsent(label, Group::new).items.add(child);
        }
        return new ArrayList<>(groups.values());
    }

    public static class Group {
        private final String label;
        private final List<LinkItem> items = new ArrayList<>();

        Group(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public List<LinkItem> getItems() {
            return items;
        }
    }
}
