package com.college.service;

import com.college.core.controller.ControllerUtility;
import com.college.core.entity.SiteSetting;
import com.college.core.model.AreaAccess;
import com.college.repository.SiteSettingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

@Service
public class SiteSettingServiceImpl implements SiteSettingService {
    private static final Logger logger = LoggerFactory.getLogger(SiteSettingServiceImpl.class);
    private static final Pattern PHONE = Pattern.compile("[0-9+()\\-/ ]{3,30}");
    private static final Pattern EMAIL = Pattern.compile("[^@\\s<>\"']+@[^@\\s<>\"']+\\.[^@\\s<>\"']+");
    private static final Pattern URL = Pattern.compile("https?://[^\\s<>\"']+");
    private static final Pattern NUMBER = Pattern.compile("-?[0-9]+([.,][0-9]+)?");
    private static final int MAX_LENGTH = 1000;
    static final String SWITCH = "switch";

    private final SiteSettingRepository siteSettingRepository;
    private final ContentPermissionService contentPermissionService;
    private final ContentChangeLogService contentChangeLogService;

    /** Values of all settings; loaded on first use, replaced after every save (changes made by SQL need a restart). */
    private volatile Map<String, String> cache;

    public SiteSettingServiceImpl(SiteSettingRepository siteSettingRepository,
                                  ContentPermissionService contentPermissionService,
                                  ContentChangeLogService contentChangeLogService) {
        this.siteSettingRepository = siteSettingRepository;
        this.contentPermissionService = contentPermissionService;
        this.contentChangeLogService = contentChangeLogService;
    }

    @Override
    public String value(String key) {
        Map<String, String> values = cache;
        if (values == null) {
            values = load();
            cache = values;
        }
        return values.get(key);
    }

    private Map<String, String> load() {
        Map<String, String> values = new HashMap<>();
        try {
            for (SiteSetting setting : siteSettingRepository.findAll()) {
                if (setting.getSettingValue() != null) {
                    values.put(setting.getSettingKey(), setting.getSettingValue());
                }
            }
        } catch (DataAccessException e) {
            // table not created yet (docs/sql/dynamic not applied): every page shows its built-in texts
            logger.warn("site_setting not readable, pages show their built-in texts: {}", e.getMostSpecificCause().getMessage());
        }
        return Collections.unmodifiableMap(values);
    }

    @Override
    public List<SiteSetting> editableSettings(Authentication authentication) {
        Set<String> areas = editableAreaCodes(authentication);
        if (areas.isEmpty()) {
            return Collections.emptyList();
        }
        List<SiteSetting> settings = new ArrayList<>();
        for (SiteSetting setting : siteSettingRepository.findByAreaCodeInOrderBySettingKeyAsc(areas)) {
            if (!SWITCH.equals(setting.getValueType())) { // switches have their own buttons (e.g. /manage/links)
                settings.add(setting);
            }
        }
        settings.sort(Comparator.comparing(SiteSetting::getAreaCode).thenComparing(SiteSetting::getSettingKey));
        return settings;
    }

    @Override
    public List<String> areasWithSettings() {
        Set<String> areas = new TreeSet<>();
        for (SiteSetting setting : siteSettingRepository.findAll()) {
            if (!SWITCH.equals(setting.getValueType())) {
                areas.add(setting.getAreaCode());
            }
        }
        return new ArrayList<>(areas);
    }

    @Override
    public Map<String, String> save(Authentication authentication, Map<String, String> values) {
        Map<String, String> errors = new LinkedHashMap<>();
        List<SiteSetting> changed = new ArrayList<>();
        Map<String, String> before = new HashMap<>();
        for (SiteSetting setting : editableSettings(authentication)) {
            if (!values.containsKey(setting.getSettingKey())) {
                continue;
            }
            String value = values.get(setting.getSettingKey());
            if (value != null) {
                value = value.trim();
                String error = validate(setting.getValueType(), value);
                if (error != null) {
                    errors.put(setting.getSettingKey(), error);
                    continue;
                }
            }
            if (!Objects.equals(value, setting.getSettingValue())) {
                before.put(setting.getSettingKey(), setting.getSettingValue());
                setting.setSettingValue(value);
                changed.add(setting);
            }
        }
        if (!errors.isEmpty()) {
            return errors;
        }
        for (SiteSetting setting : changed) {
            setting.setUpdatedBy(ControllerUtility.getPrincipalName());
            setting.setUpdatedAt(new Date());
            siteSettingRepository.save(setting);
            contentChangeLogService.record(setting.getAreaCode(), "site_setting", setting.getSettingKey(), "update",
                    before.get(setting.getSettingKey()), setting.getSettingValue());
        }
        if (!changed.isEmpty()) {
            cache = null;
        }
        return errors;
    }

    @Override
    public void setSwitch(String key, String value, String areaCode, String description) {
        SiteSetting setting = siteSettingRepository.findById(key).orElse(null);
        String before = setting == null ? null : setting.getSettingValue();
        if (setting == null) {
            setting = new SiteSetting();
            setting.setSettingKey(key);
            setting.setValueType(SWITCH);
            setting.setAreaCode(areaCode);
            setting.setDescription(description);
        } else if (Objects.equals(before, value)) {
            return;
        }
        setting.setSettingValue(value);
        setting.setUpdatedBy(ControllerUtility.getPrincipalName());
        setting.setUpdatedAt(new Date());
        siteSettingRepository.save(setting);
        contentChangeLogService.record(areaCode, "site_setting", key, before == null ? "create" : "update", before, value);
        cache = null;
    }

    private Set<String> editableAreaCodes(Authentication authentication) {
        Set<String> areas = new HashSet<>();
        for (AreaAccess access : contentPermissionService.editableAreas(authentication)) {
            if (access.isEdit()) {
                areas.add(access.getArea().getAreaCode());
            }
        }
        return areas;
    }

    /** Empty texts are allowed (the page then shows nothing); typed values must match their type. */
    static String validate(String type, String value) {
        if (value.length() > MAX_LENGTH) {
            return "At most " + MAX_LENGTH + " characters.";
        }
        if (value.isEmpty() || type == null || "text".equals(type)) {
            return null;
        }
        switch (type) {
            case "phone": return PHONE.matcher(value).matches() ? null : "Only digits, spaces and + - ( ) / are allowed.";
            case "email": return EMAIL.matcher(value).matches() ? null : "Not a valid e-mail address.";
            case "url": return URL.matcher(value).matches() ? null : "Must start with http:// or https://.";
            case "number": return NUMBER.matcher(value).matches() ? null : "Not a number.";
            default: return null;
        }
    }
}
