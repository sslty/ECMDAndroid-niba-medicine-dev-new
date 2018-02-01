package com.nibatech.ecmd.common.bean.friends;

import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;

public class FriendDoctorProfileBean {
    /**
     * doctor_2_profile : {"avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/7/7.1477972506.342093.jpg","fullname":"小医生","excellent":false,"specialism":"儿科","gender":"女","age":30,"verified":true,"cd_number":"D161000001","doctor_type":"医院"}
     * conversion_url : http://139.217.8.207:5000/api/doctor_chats/1/conversions/
     * doctor_1_profile : {"avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/14/14.1478080749.226353.jpg","fullname":"小发吧谷歌","excellent":false,"specialism":"全科","gender":"男","age":69,"verified":true,"cd_number":"D011000004","doctor_type":"医院"}
     */

    @SerializedName("doctor_2_profile")
    private DoctorProfileBean doctorProfileTwo;
    @SerializedName("conversion_url")
    private String conversionUrl;
    @SerializedName("doctor_1_profile")
    private DoctorProfileBean doctorProfileOne;

    public DoctorProfileBean getDoctorProfileTwo() {
        return doctorProfileTwo;
    }

    public void setDoctorProfileTwo(DoctorProfileBean doctorProfileTwo) {
        this.doctorProfileTwo = doctorProfileTwo;
    }

    public String getConversionUrl() {
        return conversionUrl;
    }

    public void setConversionUrl(String conversionUrl) {
        this.conversionUrl = conversionUrl;
    }

    public DoctorProfileBean getDoctorProfileOne() {
        return doctorProfileOne;
    }

    public void setDoctorProfileOne(DoctorProfileBean doctorProfileOne) {
        this.doctorProfileOne = doctorProfileOne;
    }
}
