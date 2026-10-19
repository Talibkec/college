package com.college.service;

import com.college.core.entity.ContentArea;
import com.college.core.entity.ContentAreaRole;
import com.college.core.entity.DepartmentSite;
import com.college.core.entity.Faculty;
import com.college.core.model.AreaAccess;
import com.college.repository.ContentAreaRepository;
import com.college.repository.ContentAreaRoleRepository;
import com.college.repository.DepartmentSiteRepository;
import com.college.repository.FacultyRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContentPermissionServiceImpl implements ContentPermissionService {
    /** Admin may edit every area, including "system"; this is not a row of the matrix and cannot be removed. */
    static final String ADMIN = "Admin";
    static final String HOD = "HOD";
    static final String FACULTY = "Faculty";

    private final ContentAreaRepository contentAreaRepository;
    private final ContentAreaRoleRepository contentAreaRoleRepository;
    private final DepartmentSiteRepository departmentSiteRepository;
    private final FacultyRepository facultyRepository;

    public ContentPermissionServiceImpl(ContentAreaRepository contentAreaRepository,
                                        ContentAreaRoleRepository contentAreaRoleRepository,
                                        DepartmentSiteRepository departmentSiteRepository,
                                        FacultyRepository facultyRepository) {
        this.contentAreaRepository = contentAreaRepository;
        this.contentAreaRoleRepository = contentAreaRoleRepository;
        this.departmentSiteRepository = departmentSiteRepository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public List<AreaAccess> editableAreas(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            return Collections.emptyList();
        }
        Set<String> roles = new HashSet<>();
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            roles.add(authority.getAuthority());
        }
        List<ContentArea> areas = contentAreaRepository.findAllByOrderBySortOrderAsc();
        List<AreaAccess> result = new ArrayList<>();
        if (roles.contains(ADMIN)) {
            for (ContentArea area : areas) {
                result.add(new AreaAccess(area, ContentAreaRole.EDIT, ContentAreaRole.SCOPE_ALL));
            }
            return result;
        }

        Map<String, ContentArea> areasByCode = new HashMap<>();
        for (ContentArea area : areas) {
            areasByCode.put(area.getAreaCode(), area);
        }
        Map<String, Set<String>> ownSitesByRole = new HashMap<>();
        Map<String, AreaAccess> best = new HashMap<>();
        for (ContentAreaRole cell : contentAreaRoleRepository.findByRoleNameIn(roles)) {
            ContentArea area = areasByCode.get(cell.getAreaCode());
            if (area == null) {
                continue;
            }
            if (ContentAreaRole.SCOPE_OWN_DEPARTMENT.equals(cell.getScope())) {
                Set<String> ownSites = ownSitesByRole.get(cell.getRoleName());
                if (ownSites == null) {
                    ownSites = ownSites(authentication.getName(), cell.getRoleName());
                    ownSitesByRole.put(cell.getRoleName(), ownSites);
                }
                if (area.getSiteCode() == null || !ownSites.contains(area.getSiteCode())) {
                    continue;
                }
            }
            AreaAccess access = new AreaAccess(area, cell.getAccess(), cell.getScope());
            best.put(area.getAreaCode(), AreaAccess.best(best.get(area.getAreaCode()), access));
        }
        for (ContentArea area : areas) {
            if (best.containsKey(area.getAreaCode())) {
                result.add(best.get(area.getAreaCode()));
            }
        }
        return result;
    }

    @Override
    public boolean can(Authentication authentication, String areaCode, String access) {
        for (AreaAccess areaAccess : editableAreas(authentication)) {
            if (areaAccess.getArea().getAreaCode().equals(areaCode)) {
                return areaAccess.isEdit() || ContentAreaRole.ADD_ONLY.equals(access);
            }
        }
        return false;
    }

    /**
     * Department sites a role's "own department" scope covers: HOD → the sites whose {@code hod_username} is the user
     * (an HOD in charge of several sites gets all of them); Faculty → the sites of the user's faculty department.
     */
    private Set<String> ownSites(String username, String roleName) {
        List<DepartmentSite> sites = Collections.emptyList();
        if (HOD.equals(roleName)) {
            sites = departmentSiteRepository.findByHodUsername(username);
        } else if (FACULTY.equals(roleName)) {
            Faculty faculty = facultyRepository.findFacltyByUserName(username);
            if (faculty != null && faculty.getDepartmentId() != null) {
                sites = departmentSiteRepository.findByDeptId(faculty.getDepartmentId());
            }
        }
        Set<String> codes = new HashSet<>();
        for (DepartmentSite site : sites) {
            codes.add(site.getSiteCode());
        }
        return codes;
    }
}
