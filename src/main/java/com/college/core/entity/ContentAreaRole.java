package com.college.core.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/** One cell of the permission matrix: what a role may do in a content area. */
@Entity
@Table(name = "content_area_role")
@IdClass(ContentAreaRole.Key.class)
public class ContentAreaRole {
    public static final String EDIT = "E";
    public static final String ADD_ONLY = "A";
    public static final String SCOPE_ALL = "all";
    public static final String SCOPE_OWN_DEPARTMENT = "own_department";
    public static final String SCOPE_OWN_RECORD = "own_record";

    private String areaCode;
    private String roleName;
    private String access;
    private String scope;

    @Id
    @Column(name = "area_code")
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /** {@code test_role.name}, i.e. the Spring authority. */
    @Id
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /** {@link #EDIT} or {@link #ADD_ONLY}. */
    @Column(name = "access")
    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    @Column(name = "scope")
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public static class Key implements Serializable {
        private String areaCode;
        private String roleName;

        public Key() {
        }

        public Key(String areaCode, String roleName) {
            this.areaCode = areaCode;
            this.roleName = roleName;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;
            Key key = (Key) o;
            return Objects.equals(areaCode, key.areaCode) && Objects.equals(roleName, key.roleName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(areaCode, roleName);
        }
    }
}
