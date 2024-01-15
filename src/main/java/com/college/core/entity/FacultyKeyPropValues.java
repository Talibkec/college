package com.college.core.entity;

import jakarta.persistence.*;

@Entity(name = "FACULTY_KEYPROP_VALUES")
public class FacultyKeyPropValues {
    private  Long keyPropValuesId;
    private String keyPropVal;
    private FacultyKeyProps facultyKeyProps;
    private Integer keyValueOrder;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    public Long getKeyPropValuesId() {return keyPropValuesId;}
    public void setKeyPropValuesId(Long keyPropValuesId) {this.keyPropValuesId = keyPropValuesId;}


    @ManyToOne(fetch = FetchType.EAGER)
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

    @Column(name="key_value_order")
    public Integer getKeyValueOrder() { return keyValueOrder; }
    
    public void setKeyValueOrder(Integer keyValueOrder) { this.keyValueOrder = keyValueOrder; }

}
