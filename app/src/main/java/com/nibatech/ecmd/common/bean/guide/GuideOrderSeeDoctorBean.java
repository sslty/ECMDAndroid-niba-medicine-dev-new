package com.nibatech.ecmd.common.bean.guide;

import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;

public class GuideOrderSeeDoctorBean {
    /**
     * free_first_time : false
     * viewpoint : 有一个月
     * expectation : 发图头发
     * accept_url : http://139.217.8.207:5000/api/patient_olt_proposals/120/accept
     * doctor_profile : {"avatar_url":null,"fullname":"给我送女","excellent":false,"specialism":"全科","gender":"男","age":11,"verified":false,"cd_number":"D111000060","doctor_type":null}
     * expense : 32
     */

    @SerializedName("free_first_time")
    private boolean freeFirstTime;
    @SerializedName("viewpoint")
    private String viewPoint;
    private String expectation;
    @SerializedName("accept_url")
    private String acceptUrl;
    @SerializedName("doctor_profile")
    private DoctorProfileBean doctorProfile;
    private int expense;

    public boolean isFreeFirstTime() {
        return freeFirstTime;
    }

    public void setFreeFirstTime(boolean freeFirstTime) {
        this.freeFirstTime = freeFirstTime;
    }

    public String getViewPoint() {
        return viewPoint;
    }

    public void setViewPoint(String viewPoint) {
        this.viewPoint = viewPoint;
    }

    public String getExpectation() {
        return expectation;
    }

    public void setExpectation(String expectation) {
        this.expectation = expectation;
    }

    public String getAcceptUrl() {
        return acceptUrl;
    }

    public void setAcceptUrl(String acceptUrl) {
        this.acceptUrl = acceptUrl;
    }

    public DoctorProfileBean getDoctorProfile() {
        return doctorProfile;
    }

    public void setDoctorProfile(DoctorProfileBean doctorProfile) {
        this.doctorProfile = doctorProfile;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }
}
