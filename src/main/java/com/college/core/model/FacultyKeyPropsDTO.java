package com.college.core.model;

import java.util.HashSet;
import java.util.Set;

public class FacultyKeyPropsDTO {
    private Long keyPropertyId;
    private String keyPropertyName;
    private FacultyDTO facultyDTO;
    private Set<FacultyKeyPropValuesDTO> keyPropVals = new HashSet<>();

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

    public void setKeyPropVals(Set<FacultyKeyPropValuesDTO> keyPropVals) {
        this.keyPropVals = keyPropVals;
    }

    public Set<FacultyKeyPropValuesDTO> getKeyPropVals() {
        return keyPropVals;
    }
}
