package com.college.core.entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table( name= "FACULTY_KEY_PROPS")
public class FacultyKeyProps {
    private  Long keyPropertyId;
    private String keyPropertyName;
    private Faculty faculty;
    private List<FacultyKeyPropValues> keyPropVals = new ArrayList<>() ;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    public Long getKeyPropertyId() {return keyPropertyId;}
    public void setKeyPropertyId(Long keyPropertyId) {this.keyPropertyId = keyPropertyId;}


    public String getKeyPropertyName() {return keyPropertyName; }

    public void setKeyPropertyName(String keyPropertyName) { this.keyPropertyName = keyPropertyName; }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @ManyToOne(targetEntity = Faculty.class)
    @JoinColumn(name = "facultyId")
    public Faculty getFaculty() {
        return faculty;
    }

    @OneToMany(mappedBy = "facultyKeyProps", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<FacultyKeyPropValues> getKeyPropVals() {
        return keyPropVals;
    }

    public void setKeyPropVals(List<FacultyKeyPropValues> keyPropVals) {
        this.keyPropVals = keyPropVals;
    }
}