package com.college.core.model;

import com.college.core.entity.ContentArea;
import com.college.core.entity.ContentAreaRole;

/** What the logged-in user may do in one content area (the best of all their roles). */
public class AreaAccess {
    private final ContentArea area;
    private final String access;
    private final String scope;

    public AreaAccess(ContentArea area, String access, String scope) {
        this.area = area;
        this.access = access;
        this.scope = scope;
    }

    public ContentArea getArea() {
        return area;
    }

    /** {@link ContentAreaRole#EDIT} or {@link ContentAreaRole#ADD_ONLY}. */
    public String getAccess() {
        return access;
    }

    public String getScope() {
        return scope;
    }

    public boolean isEdit() {
        return ContentAreaRole.EDIT.equals(access);
    }

    /** E before A; with equal access, the wider scope ("all") wins. */
    boolean isBetterThan(AreaAccess other) {
        if (isEdit() != other.isEdit()) {
            return isEdit();
        }
        return ContentAreaRole.SCOPE_ALL.equals(scope) && !ContentAreaRole.SCOPE_ALL.equals(other.scope);
    }

    public static AreaAccess best(AreaAccess a, AreaAccess b) {
        if (a == null) return b;
        return b.isBetterThan(a) ? b : a;
    }
}
