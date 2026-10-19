package com.college.service;

import com.college.core.entity.ContentArea;
import com.college.core.entity.ContentAreaRole;
import com.college.core.entity.Role;
import com.college.core.entity.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Admin screens {@code /auth/roles}: giving users the new website roles and editing the permission matrix.
 * The existing roles (Admin, Faculty, HOD and the store roles SM/SK) are never changed here; they keep their own
 * screens (registration, HOD in-charge).
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

    List<ContentArea> areas();

    /** The matrix, keyed {@code "<area>|<role>"}. */
    Map<String, ContentAreaRole> matrix();

    /**
     * Applies the posted cells ({@code "<area>|<role>"} → {@code ""}, {@code "A"} or {@code "E"}). Cells that are not
     * posted stay as they are.
     */
    void updateMatrix(Map<String, String> cells);
}
