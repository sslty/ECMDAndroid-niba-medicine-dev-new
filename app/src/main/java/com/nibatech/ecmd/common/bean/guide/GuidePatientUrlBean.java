package com.nibatech.ecmd.common.bean.guide;


import com.google.gson.annotations.SerializedName;

public class GuidePatientUrlBean {

    /**
     * my_treating_url : http://139.217.8.207:5000/api/patient_online_treatments/my_treating
     * my_unaccepted_url : http://139.217.8.207:5000/api/patient_online_treatments/my_unaccepted
     * treating_status : http://139.217.8.207:5000/api/patient_online_treatments/treating_status
     * others_unaccepted_url : http://139.217.8.207:5000/api/patient_online_treatments/others_unaccepted/
     * others_treating_url : http://139.217.8.207:5000/api/patient_online_treatments/others_treating/
     * create_olt_url : http://139.217.8.207:5000/api/patient_online_treatments/
     */

    @SerializedName("my_treating_url")
    private String myTreatingUrl;
    @SerializedName("my_unaccepted_url")
    private String myUnacceptedUrl;
    @SerializedName("treating_status")
    private String treatingStatus;
    @SerializedName("others_unaccepted_url")
    private String othersUnacceptedUrl;
    @SerializedName("others_treating_url")
    private String othersTreatingUrl;
    @SerializedName("create_olt_url")
    private String createOltUrl;

    public String getMyTreatingUrl() {
        return myTreatingUrl;
    }

    public void setMyTreatingUrl(String myTreatingUrl) {
        this.myTreatingUrl = myTreatingUrl;
    }

    public String getMyUnacceptedUrl() {
        return myUnacceptedUrl;
    }

    public void setMyUnacceptedUrl(String myUnacceptedUrl) {
        this.myUnacceptedUrl = myUnacceptedUrl;
    }

    public String getTreatingStatus() {
        return treatingStatus;
    }

    public void setTreatingStatus(String treatingStatus) {
        this.treatingStatus = treatingStatus;
    }

    public String getOthersUnacceptedUrl() {
        return othersUnacceptedUrl;
    }

    public void setOthersUnacceptedUrl(String othersUnacceptedUrl) {
        this.othersUnacceptedUrl = othersUnacceptedUrl;
    }

    public String getOthersTreatingUrl() {
        return othersTreatingUrl;
    }

    public void setOthersTreatingUrl(String othersTreatingUrl) {
        this.othersTreatingUrl = othersTreatingUrl;
    }

    public String getCreateOltUrl() {
        return createOltUrl;
    }

    public void setCreateOltUrl(String createOltUrl) {
        this.createOltUrl = createOltUrl;
    }
}
