package com.college.service;

import com.college.core.entity.SiteSetting;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/** Texts of the website that editors can change (table {@code site_setting}). */
@Service
public interface SiteSettingService {

    /** The value, or null if the setting has none (or the table does not exist): the page then shows its own text. */
    String value(String key);

    /** The settings the user may edit, sorted by area and key. */
    List<SiteSetting> editableSettings(Authentication authentication);

    /** Area codes that have settings (for the links on /manage). */
    List<String> areasWithSettings();

    /**
     * Saves the posted values ({@code key → value}; null = use the page's own text). Keys the user may not edit are
     * ignored. Returns the validation errors per key; nothing is saved if there is any.
     */
    Map<String, String> save(Authentication authentication, Map<String, String> values);

    /**
     * Creates or changes an on/off setting (value type "switch", not shown in /manage/settings). The caller checks the
     * permission.
     */
    void setSwitch(String key, String value, String areaCode, String description);
}
