package com.college.core.entity;
import javax.persistence.*;

@Entity
@Table( name= "PROPERTY")
public class Property {
    private  Long propertyId;
    private String propertyName;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    public Long getPropertyId() {return propertyId;}
    @ManyToMany(mappedBy = "facultyId")
    public void setPropertyId(Long propertyId) {this.propertyId = propertyId;}


    public String getPropertyName() {return propertyName; }

    public void setPropertyName(String propertyName) { this.propertyName = propertyName; }

}
