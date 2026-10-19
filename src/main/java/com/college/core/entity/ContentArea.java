package com.college.core.entity;

import jakarta.persistence.*;

/** A part of the website that can be edited by certain roles (docs/DYNAMIC_CONTENT_FINDINGS.md §1.3.3). */
@Entity
@Table(name = "content_area")
public class ContentArea {
    private String areaCode;
    private String label;
    private String description;
    private String siteCode;
    private int sortOrder;

    @Id
    @Column(name = "area_code")
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    @Column(name = "label")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /** Department mini-site of a {@code department.<site>} area, else null. */
    @Column(name = "site_code")
    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    @Column(name = "sort_order")
    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }
}
