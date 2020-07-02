package com.college.core.model;

public class FacultyKeyPropValuesDTO {

    private Long keyPropValuesId;
    private FacultyKeyPropsDTO facultyKeyProps;
    private String keyPropVal;
    public Integer getKeyValueOrder() { return keyValueOrder; }
    public void setKeyValueOrder(Integer keyValueOrder) { this.keyValueOrder = keyValueOrder; }

    private  Integer keyValueOrder;
    public Long getKeyPropValuesId() {
        return keyPropValuesId;
    }

    public void setKeyPropValuesId(Long keyPropValuesId) {
        this.keyPropValuesId = keyPropValuesId;
    }

    public String getKeyPropVal() {
        return keyPropVal;
    }

    public void setKeyPropVal(String keyPropVal) {
        this.keyPropVal = keyPropVal;
    }

    public void setFacultyKeyProps(FacultyKeyPropsDTO facultyKeyProps) {
        this.facultyKeyProps = facultyKeyProps;
    }

    public FacultyKeyPropsDTO getFacultyKeyProps() {
        return facultyKeyProps;
    }
}
