package com.college.core.entity;

import jakarta.persistence.*;

/**
 * One department mini-site under {@code /department/<siteCode>/}. Only the columns the permission check needs are
 * mapped so far (HOD and Faculty department scope); the rest follows with the departments step.
 */
@Entity
@Table(name = "department_site")
public class DepartmentSite {
    private String siteCode;
    private Long deptId;
    private String hodUsername;

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
}
