package com.college.service;

import com.college.core.entity.ContentArea;
import com.college.core.entity.ContentAreaRole;
import com.college.core.entity.DepartmentSite;
import com.college.core.entity.Role;
import com.college.core.entity.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Admin screens {@code /auth/roles}: giving users the new website roles and editing the permission matrix.
 * Admin, Faculty and the store roles SM/SK are never changed here; they keep their own screens (registration). HOD is
 * given together with the department site(s) the user heads ({@code department_site.hod_username}); the old
 * {@code /auth/hodincharge} screen keeps working.
 */
@Service
public interface RoleAssignmentService {

    /** Roles managed on {@code /auth/roles}: every role except Admin, Faculty, HOD, SM and SK. */
    List<Role> assignableRoles();

    /** Roles shown as columns of the permission matrix: the assignable roles plus HOD and Faculty. */
    List<String> matrixRoles();

    /** All users (sorted by user name) whose name contains {@code query}; all users if it is empty. */
    List<User> users(String query);

    User user(Long userId);

    /** Sets the user's assignable roles to exactly {@code roleNames}; other roles of the user are kept. */
    void assignRoles(Long userId, Collection<String> roleNames);

    /** The department sites (sorted like the menu) with their current HOD account. */
    List<DepartmentSite> departmentSites();

    /** The faculty department of the user, or {@code null} (an HOD always covers the sites of that department). */
    Long facultyDepartmentId(String username);

    /**
     * Makes the user HOD of exactly {@code siteCodes} (the previous HOD of those sites is replaced) and gives or takes
     * the HOD role: the user keeps or gets it when {@code hod} is set or at least one site is given.
     */
    void assignHod(Long userId, boolean hod, Collection<String> siteCodes);

    List<ContentArea> areas();

    /** The matrix, keyed {@code "<area>|<role>"}. */
    Map<String, ContentAreaRole> matrix();

    /**
     * Applies the posted cells ({@code "<area>|<role>"} → {@code ""}, {@code "A"} or {@code "E"}). Cells that are not
     * posted stay as they are.
     */
    void updateMatrix(Map<String, String> cells);
}
