package com.nibatech.ecmd.common.bean.common;

import com.google.gson.annotations.SerializedName;

public class PatientBean {
    /**
     * gender : null
     * cd_number : null
     * avatar_url : null
     * age : 0
     * fullname : null
     */

    private String gender;
    @SerializedName("cd_number")
    private String cdNumber;
    @SerializedName("avatar_url")
    private String avatarUrl;
    private int age;
    @SerializedName("fullname")
    private String fullName;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCdNumber() {
        return cdNumber;
    }

    public void setCdNumber(String cdNumber) {
        this.cdNumber = cdNumber;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
