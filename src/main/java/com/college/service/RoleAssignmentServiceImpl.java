package com.college.service;

import com.college.core.entity.ContentArea;
import com.college.core.entity.ContentAreaRole;
import com.college.core.entity.DepartmentSite;
import com.college.core.entity.Faculty;
import com.college.core.entity.Role;
import com.college.core.entity.User;
import com.college.repository.ContentAreaRepository;
import com.college.repository.ContentAreaRoleRepository;
import com.college.repository.DepartmentSiteRepository;
import com.college.repository.FacultyRepository;
import com.college.repository.RoleRepository;
import com.college.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleAssignmentServiceImpl implements RoleAssignmentService {
    static final String HOD = "HOD";
    /** Roles with their own screens and rules; never added or removed by the website-role checkboxes (HOD has its own part). */
    static final Set<String> EXISTING_ROLES = new HashSet<>(Arrays.asList("Admin", "Faculty", "HOD", "SM", "SK"));

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ContentAreaRepository contentAreaRepository;
    private final ContentAreaRoleRepository contentAreaRoleRepository;
    private final ContentChangeLogService contentChangeLogService;
    private final DepartmentSiteRepository departmentSiteRepository;
    private final FacultyRepository facultyRepository;

    public RoleAssignmentServiceImpl(RoleRepository roleRepository, UserRepository userRepository,
                                     ContentAreaRepository contentAreaRepository,
                                     ContentAreaRoleRepository contentAreaRoleRepository,
                                     ContentChangeLogService contentChangeLogService,
                                     DepartmentSiteRepository departmentSiteRepository,
                                     FacultyRepository facultyRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.contentAreaRepository = contentAreaRepository;
        this.contentAreaRoleRepository = contentAreaRoleRepository;
        this.contentChangeLogService = contentChangeLogService;
        this.departmentSiteRepository = departmentSiteRepository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public List<Role> assignableRoles() {
        List<Role> roles = new ArrayList<>();
        for (Role role : roleRepository.findAll()) {
            if (!EXISTING_ROLES.contains(role.getName())) {
                roles.add(role);
            }
        }
        roles.sort(Comparator.comparing(Role::getId));
        return roles;
    }

    @Override
    public List<String> matrixRoles() {
        List<String> names = new ArrayList<>();
        for (Role role : assignableRoles()) {
            names.add(role.getName());
        }
        names.add("HOD");
        names.add("Faculty");
        return names;
    }

    @Override
    public List<User> users(String query) {
        String q = query == null ? "" : query.trim().toLowerCase();
        List<User> users = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            if (q.isEmpty() || (user.getUsername() != null && user.getUsername().toLowerCase().contains(q))) {
                users.add(user);
            }
        }
        users.sort(Comparator.comparing(u -> u.getUsername() == null ? "" : u.getUsername().toLowerCase()));
        return users;
    }

    @Override
    public User user(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void assignRoles(Long userId, Collection<String> roleNames) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("No user " + userId));
        Set<Role> roles = user.getRoles() == null ? new HashSet<>() : user.getRoles();
        TreeSet<String> before = new TreeSet<>();
        Iterator<Role> it = roles.iterator();
        while (it.hasNext()) {
            Role role = it.next();
            if (!EXISTING_ROLES.contains(role.getName())) {
                before.add(role.getName());
                it.remove();
            }
        }
        TreeSet<String> after = new TreeSet<>();
        for (Role role : assignableRoles()) {
            if (roleNames != null && roleNames.contains(role.getName())) {
                roles.add(role);
                after.add(role.getName());
            }
        }
        if (before.equals(after)) {
            return;
        }
        user.setRoles(roles);
        userRepository.save(user);
        contentChangeLogService.record("system", "user_role", user.getUsername(), "update",
                String.join(",", before), String.join(",", after));
    }

    @Override
    public List<DepartmentSite> departmentSites() {
        return departmentSiteRepository.findAllByOrderBySortOrderAsc();
    }

    @Override
    public Long facultyDepartmentId(String username) {
        Faculty faculty = username == null ? null : facultyRepository.findFacltyByUserName(username);
        return faculty == null ? null : faculty.getDepartmentId();
    }

    @Override
    public void assignHod(Long userId, boolean hod, Collection<String> siteCodes) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("No user " + userId));
        String username = user.getUsername();
        Set<String> wanted = siteCodes == null ? Collections.emptySet() : new HashSet<>(siteCodes);
        boolean holdsHod = hod || !wanted.isEmpty();
        for (DepartmentSite site : departmentSites()) {
            String before = site.getHodUsername();
            boolean mine = username.equals(before);
            boolean pick = holdsHod && wanted.contains(site.getSiteCode());
            if (pick == mine) {
                continue;
            }
            site.setHodUsername(pick ? username : null);
            departmentSiteRepository.save(site);
            contentChangeLogService.record("system", "department_site_hod", site.getSiteCode(), "update",
                    before, site.getHodUsername());
        }

        Set<Role> roles = user.getRoles() == null ? new HashSet<>() : user.getRoles();
        boolean held = roles.stream().anyMatch(role -> HOD.equals(role.getName()));
        if (held == holdsHod) {
            return;
        }
        if (holdsHod) {
            roles.add(roleRepository.getHodRoleByName(HOD));
        } else {
            roles.removeIf(role -> HOD.equals(role.getName()));
        }
        user.setRoles(roles);
        userRepository.save(user);
        contentChangeLogService.record("system", "user_role", username, "update",
                held ? HOD : "", holdsHod ? HOD : "");
    }

    @Override
    public List<ContentArea> areas() {
        return contentAreaRepository.findAllByOrderBySortOrderAsc();
    }

    @Override
    public Map<String, ContentAreaRole> matrix() {
        Map<String, ContentAreaRole> matrix = new HashMap<>();
        for (ContentAreaRole cell : contentAreaRoleRepository.findAll()) {
            matrix.put(cell.getAreaCode() + "|" + cell.getRoleName(), cell);
        }
        return matrix;
    }

    @Override
    public void updateMatrix(Map<String, String> cells) {
        Map<String, ContentArea> areas = new HashMap<>();
        for (ContentArea area : areas()) {
            areas.put(area.getAreaCode(), area);
        }
        List<String> roles = matrixRoles();
        Map<String, ContentAreaRole> matrix = matrix();
        for (Map.Entry<String, String> cell : cells.entrySet()) {
            String[] key = cell.getKey().split("\\|", 2);
            ContentArea area = key.length == 2 ? areas.get(key[0]) : null;
            if (area == null || "system".equals(area.getAreaCode()) || !roles.contains(key[1])) {
                continue; // unknown cell, or the Admin-only area
            }
            String access = cell.getValue() == null ? "" : cell.getValue().trim();
            if (!access.isEmpty() && !ContentAreaRole.EDIT.equals(access) && !ContentAreaRole.ADD_ONLY.equals(access)) {
                continue;
            }
            ContentAreaRole existing = matrix.get(cell.getKey());
            String before = existing == null ? "" : existing.getAccess() + " " + existing.getScope();
            if (access.isEmpty()) {
                if (existing != null) {
                    contentAreaRoleRepository.delete(existing);
                    contentChangeLogService.record("system", "content_area_role", cell.getKey(), "delete", before, null);
                }
                continue;
            }
            if (existing != null && access.equals(existing.getAccess())) {
                continue;
            }
            ContentAreaRole changed = existing;
            if (changed == null) {
                changed = new ContentAreaRole();
                changed.setAreaCode(area.getAreaCode());
                changed.setRoleName(key[1]);
                changed.setScope(defaultScope(area, key[1]));
            }
            changed.setAccess(access);
            contentAreaRoleRepository.save(changed);
            contentChangeLogService.record("system", "content_area_role", cell.getKey(), existing == null ? "create" : "update",
                    existing == null ? null : before, changed.getAccess() + " " + changed.getScope());
        }
    }

    /** HOD and Faculty only get their own department on department areas; everything else applies college-wide. */
    private static String defaultScope(ContentArea area, String roleName) {
        if (area.getSiteCode() != null && ("HOD".equals(roleName) || "Faculty".equals(roleName))) {
            return ContentAreaRole.SCOPE_OWN_DEPARTMENT;
        }
        return ContentAreaRole.SCOPE_ALL;
    }
}
