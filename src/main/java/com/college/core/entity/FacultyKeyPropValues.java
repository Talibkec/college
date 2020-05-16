package com.college.core.entity;

import javax.persistence.*;

@Entity(name = "FACULTY_KEYPROP_VALUES")
public class FacultyKeyPropValues {
    private  Long keyPropValuesId;
    private String keyPropVal;
    private FacultyKeyProps facultyKeyProps;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    public Long getKeyPropValuesId() {return keyPropValuesId;}
    public void setKeyPropValuesId(Long keyPropValuesId) {this.keyPropValuesId = keyPropValuesId;}


    @ManyToOne
    @JoinColumn(name = "keyPropertyId")
    public FacultyKeyProps getFacultyKeyProps() {
        return facultyKeyProps;
    }

    public void setFacultyKeyProps(FacultyKeyProps facultyKeyProps) {
        this.facultyKeyProps = facultyKeyProps;
    }

    public void setKeyPropVal(String keyPropVal) {
        this.keyPropVal = keyPropVal;
    }

    public String getKeyPropVal() {
        return keyPropVal;
    }
}
