package com.college.core.entity;

import jakarta.persistence.*;
import java.util.Date;

/** A text shown on the website, e.g. the phone number or the footer copyright (table {@code site_setting}). */
@Entity
@Table(name = "site_setting")
public class SiteSetting {
    private String settingKey;
    private String settingValue;
    private String valueType;
    private String areaCode;
    private String description;
    private String updatedBy;
    private Date updatedAt;

    @Id
    @Column(name = "setting_key")
    public String getSettingKey() {
        return settingKey;
    }

    public void setSettingKey(String settingKey) {
        this.settingKey = settingKey;
    }

    /** Null = the page shows its built-in text. */
    @Column(name = "setting_value")
    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    /** text, url, email, phone or number (validation in the edit screen). */
    @Column(name = "value_type")
    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    @Column(name = "area_code")
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
