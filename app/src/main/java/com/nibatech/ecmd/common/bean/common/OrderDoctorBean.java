package com.nibatech.ecmd.common.bean.common;

import com.google.gson.annotations.SerializedName;



public class OrderDoctorBean {
    private String gender;
    @SerializedName("fullname")
    private String fullName;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("doctor_type")
    private String doctorType;

    public String getGender() {
        return gender;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getDoctorType() {
        return doctorType;
    }
}
