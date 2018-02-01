package com.nibatech.ecmd.common.mydoctor;


public class MyDoctorUrlBean {
    /**
     * followed : http://139.217.8.207:5000/api/patient_followed/
     * contacted : http://139.217.8.207:5000/api/offline_doctors/
     */

    private String followed;
    private String contacted;

    public String getFollowed() {
        return followed;
    }

    public void setFollowed(String followed) {
        this.followed = followed;
    }

    public String getContacted() {
        return contacted;
    }

    public void setContacted(String contacted) {
        this.contacted = contacted;
    }
}
