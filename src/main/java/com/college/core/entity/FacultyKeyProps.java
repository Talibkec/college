package com.college.core.entity;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name= "FACULTY_KEY_PROPS")
public class FacultyKeyProps {
    private  Long keyPropertyId;
    private String keyPropertyName;
    private Faculty faculty;
    private Set<FacultyKeyPropValues> keyPropVals = new HashSet<>() ;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    public Long getKeyPropertyId() {return keyPropertyId;}
    public void setKeyPropertyId(Long keyPropertyId) {this.keyPropertyId = keyPropertyId;}


    public String getKeyPropertyName() {return keyPropertyName; }

    public void setKeyPropertyName(String keyPropertyName) { this.keyPropertyName = keyPropertyName; }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facultyId")
    public Faculty getFaculty() {
        return faculty;
    }

    @OneToMany(mappedBy = "facultyKeyProps", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<FacultyKeyPropValues> getKeyPropVals() {
        return keyPropVals;
    }

    public void setKeyPropVals(Set<FacultyKeyPropValues> keyPropVals) {
        this.keyPropVals = keyPropVals;
    }
}
