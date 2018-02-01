package com.nibatech.ecmd.common.bean.common;

import com.google.gson.annotations.SerializedName;

public class DoctorBean {
    /**
     * age : 33
     * avatar_url : null
     * cd_number : D161000001
     * doctor_type : 医院
     * fullname : 智嘉石
     * gender : 男
     */

    private int age;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("cd_number")
    private String cdNumber;
    @SerializedName("fullname")
    private String fullName;
    private String gender;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCdNumber() {
        return cdNumber;
    }

    public void setCdNumber(String cdNumber) {
        this.cdNumber = cdNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
