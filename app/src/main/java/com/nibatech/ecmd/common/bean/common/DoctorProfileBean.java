package com.nibatech.ecmd.common.bean.common;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class DoctorProfileBean extends RealmObject {
    /**
     * homepage_url : http://139.217.8.207:5000/api/doctor_homepage/35
     * avatar_url : null
     * fullname : 哦们们
     * excellent : false
     * specialism : 全科
     * gender : 男
     * age : 30
     * verified : true
     * cd_number : D111000035
     * doctor_type : 医院
     */

    @SerializedName("homepage_url")
    private String homepageUrl;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("fullname")
    private String fullName;
    private boolean excellent;
    private String specialism;
    private String gender;
    private int age;
    private boolean verified;
    @SerializedName("cd_number")
    private String cdNumber;
    @SerializedName("doctor_type")
    private String doctorType;

    public String getHomepageUrl() {
        return homepageUrl;
    }

    public void setHomepageUrl(String homepageUrl) {
        this.homepageUrl = homepageUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isExcellent() {
        return excellent;
    }

    public void setExcellent(boolean excellent) {
        this.excellent = excellent;
    }

    public String getSpecialism() {
        return specialism;
    }

    public void setSpecialism(String specialism) {
        this.specialism = specialism;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getCdNumber() {
        return cdNumber;
    }

    public void setCdNumber(String cdNumber) {
        this.cdNumber = cdNumber;
    }

    public String getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(String doctorType) {
        this.doctorType = doctorType;
    }
}
