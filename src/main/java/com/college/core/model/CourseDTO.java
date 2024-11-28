package com.college.core.model;

import javax.persistence.Column;

public class CourseDTO {
    private Long courseId;
    private String branchName;
    private Long regularSeat;
    private Long lateralSeat;

    public  Long  getCourseId() {
        return  courseId;
    }

    public void setCourseId(Long courseId){this.courseId= courseId;}

    public String getBranchName(){
        return  branchName;
    }

    public void setBranchName(String branchName){
        this.branchName= branchName;
    }

    public Long getRegularSeat(){
        return regularSeat;
    }

    public void setRegularSeat(Long regularSeat){
        this.regularSeat=regularSeat;
    }

    public Long getLateralSeat(){
        return lateralSeat;
    }

    public void setLateralSeat(Long lateralSeat){
        this.lateralSeat=lateralSeat;
    }

}
