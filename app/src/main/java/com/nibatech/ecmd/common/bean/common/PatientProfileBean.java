package com.nibatech.ecmd.common.bean.common;

import com.google.gson.annotations.SerializedName;


public class PatientProfileBean {
    /**
     * city : null
     * name : dsdsdA
     * gender : 男
     * age : 12
     * avatar_url : null
     * cd_number : null
     */

    private String city;
    private String name;
    private String gender;
    private int age;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("cd_number")
    private String cdNumber;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
