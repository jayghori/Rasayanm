package com.example.rasayanm.Model;

public class GradeSpinner {

    String grade;
    String recover;
    String fresh;
    String industrial;
    String analitycal;
    String pharmapass;
    String other;

    public GradeSpinner(){


    }
    public GradeSpinner(String grade, String recover, String fresh, String industrial, String analitycal, String pharmapass, String other) {
        this.grade = grade;
        this.recover = recover;
        this.fresh = fresh;
        this.industrial = industrial;
        this.analitycal = analitycal;
        this.pharmapass = pharmapass;
        this.other = other;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRecover() {
        return recover;
    }

    public void setRecover(String recover) {
        this.recover = recover;
    }

    public String getFresh() {
        return fresh;
    }

    public void setFresh(String fresh) {
        this.fresh = fresh;
    }

    public String getIndustrial() {
        return industrial;
    }

    public void setIndustrial(String industrial) {
        this.industrial = industrial;
    }

    public String getAnalitycal() {
        return analitycal;
    }

    public void setAnalitycal(String analitycal) {
        this.analitycal = analitycal;
    }

    public String getPharmapass() {
        return pharmapass;
    }

    public void setPharmapass(String pharmapass) {
        this.pharmapass = pharmapass;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
