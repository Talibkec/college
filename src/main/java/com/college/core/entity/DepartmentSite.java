package com.college.core.entity;

import jakarta.persistence.*;

/**
 * One department mini-site under {@code /department/<siteCode>/}. Only the columns the permission check and the HOD
 * assignment on {@code /auth/roles} need are mapped so far; the rest follows with the departments step.
 */
@Entity
@Table(name = "department_site")
public class DepartmentSite {
    private String siteCode;
    private Long deptId;
    private String hodUsername;
    private String fullName;
    private String shortName;
    private String menuGroup;
    private int sortOrder;

    @Id
    @Column(name = "site_code")
    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    @Column(name = "dept_id")
    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Column(name = "hod_username")
    public String getHodUsername() {
        return hodUsername;
    }

    public void setHodUsername(String hodUsername) {
        this.hodUsername = hodUsername;
    }

    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "short_name")
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Column(name = "menu_group")
    public String getMenuGroup() {
        return menuGroup;
    }

    public void setMenuGroup(String menuGroup) {
        this.menuGroup = menuGroup;
    }

    @Column(name = "sort_order")
    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }
}
