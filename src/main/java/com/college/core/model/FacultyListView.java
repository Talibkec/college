package com.college.core.model;

import java.util.List;

/**
 * View data of facultylist.jsp (formerly computed in a JSP scriptlet): the department name, taken from the first
 * faculty member of the list. Like the scriptlet, it fails for an empty list or an unknown department while the
 * page renders (e.g. a department without faculty still gives the same truncated page as before).
 */
public class FacultyListView {

    private static final String[] BRANCH_NAMES = {"Computer Sc. & Engineering", " Civil Engineering",
            "Mechanical Engineering", " Electronics & Electrical Engineering", "Applied Sc. & Humanities",
            "Electronics Engineering (VLSI Design and Technology)", "Food Processing"};

    private final List<FacultyDTO> facultyList;

    public FacultyListView(List<FacultyDTO> facultyList) {
        this.facultyList = facultyList;
    }

    public String getBranchName() {
        int depIndex = Math.toIntExact(facultyList.get(0).getDepartmentId() - 1L);
        return BRANCH_NAMES[depIndex];
    }
}
