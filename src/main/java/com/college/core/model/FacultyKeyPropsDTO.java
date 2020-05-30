package com.college.core.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FacultyKeyPropsDTO {
    private Long keyPropertyId;
    private String keyPropertyName;
    private FacultyDTO faculty;
    private List<FacultyKeyPropValuesDTO> keyPropVals = new ArrayList<>();

    public Long getKeyPropertyId() {
        return keyPropertyId;
    }

    public void setKeyPropertyId(Long keyPropertyId) {
        this.keyPropertyId = keyPropertyId;
    }

    public String getKeyPropertyName() {
        return keyPropertyName;
    }

    public void setKeyPropertyName(String keyPropertyName) {
        this.keyPropertyName = keyPropertyName;
    }

    public void setKeyPropVals(List<FacultyKeyPropValuesDTO> keyPropVals) {
        this.keyPropVals = keyPropVals;
    }

    public List<FacultyKeyPropValuesDTO> getKeyPropVals() {
        return keyPropVals;
    }

    public void setFaculty(FacultyDTO faculty) {
        this.faculty = faculty;
    }

    public FacultyDTO getFaculty() {
        return faculty;
    }
}
