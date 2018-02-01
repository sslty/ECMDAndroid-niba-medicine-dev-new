package com.nibatech.ecmd.common.bean.mine;

import com.google.gson.annotations.SerializedName;

public class MineFollowerBean {

    /**
     * patient_followers_url : http://60.205.141.66:5000/api/patient_followers/
     * doctor_followers_url : http://60.205.141.66:5000/api/doctor_relation/followers
     */

    @SerializedName("patient_followers_url")
    private String patientFollowersUrl;
    @SerializedName("doctor_followers_url")
    private String doctorFollowersUrl;

    public String getPatientFollowersUrl() {
        return patientFollowersUrl;
    }

    public void setPatientFollowersUrl(String patientFollowersUrl) {
        this.patientFollowersUrl = patientFollowersUrl;
    }

    public String getDoctorFollowersUrl() {
        return doctorFollowersUrl;
    }

    public void setDoctorFollowersUrl(String doctorFollowersUrl) {
        this.doctorFollowersUrl = doctorFollowersUrl;
    }
}
