package com.college.core.entity;

import javax.persistence.*;


@Entity
@Table(name = "No_of_seat")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "courseId")
    @SequenceGenerator(name = "courseId", sequenceName = "courseId")
    private Long courseId;

    @Column(name = "branch_name", nullable = false)
    private String branchName;

    @Column(name = "Regular_Student", nullable = false)
    private Long regularSeat;

    @Column(name = "Lateral_Student", nullable = false)
    private Long lateralSeat;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Long getRegularSeat() {
        return regularSeat;
    }

    public void setRegularSeat(Long regularSeat) {
        this.regularSeat = regularSeat;
    }

    public Long getLateralSeat() {
        return lateralSeat;
    }

    public void setLateralSeat(Long lateralSeat) {
        this.lateralSeat = lateralSeat;
    }
}
