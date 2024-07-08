package com.college.core.model;

public class DepartmentDTO {
    private Long departmentId;
    private String departmentName;
    private String departmentHod;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentHod() {
        return departmentHod;
    }

    public void setDepartmentHod(String departmentHod) {
        this.departmentHod = departmentHod;
    }


}
