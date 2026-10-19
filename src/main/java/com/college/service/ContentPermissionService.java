package com.college.service;

import com.college.core.model.AreaAccess;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

/** Who may edit which content area (docs/DYNAMIC_CONTENT_FINDINGS.md §1.3). */
@Service
public interface ContentPermissionService {

    /** The areas the user may edit, in the order of {@code content_area.sort_order}; empty for anonymous users. */
    List<AreaAccess> editableAreas(Authentication authentication);

    /** True if the user may edit ({@code "E"}) or at least add to ({@code "A"}) the area. */
    boolean can(Authentication authentication, String areaCode, String access);
}
