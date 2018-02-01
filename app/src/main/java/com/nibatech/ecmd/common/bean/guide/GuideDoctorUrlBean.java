package com.nibatech.ecmd.common.bean.guide;


import com.google.gson.annotations.SerializedName;

public class GuideDoctorUrlBean {
    /**
     * difficult_olt_url : http://139.217.8.207:5000/api/doctor_online_treatments/difficulties/
     * treating_olt_url : http://139.217.8.207:5000/api/doctor_online_treatments/treatings/
     * normal_olt_url : http://139.217.8.207:5000/api/doctor_online_treatments/
     */

    @SerializedName("difficult_olt_url")
    private String difficultOltUrl;
    @SerializedName("treating_olt_url")
    private String treatingOltUrl;
    @SerializedName("normal_olt_url")
    private String normalOltUrl;

    public String getDifficultOltUrl() {
        return difficultOltUrl;
    }

    public void setDifficultOltUrl(String difficultOltUrl) {
        this.difficultOltUrl = difficultOltUrl;
    }

    public String getTreatingOltUrl() {
        return treatingOltUrl;
    }

    public void setTreatingOltUrl(String treatingOltUrl) {
        this.treatingOltUrl = treatingOltUrl;
    }

    public String getNormalOltUrl() {
        return normalOltUrl;
    }

    public void setNormalOltUrl(String normalOltUrl) {
        this.normalOltUrl = normalOltUrl;
    }
}
