package com.nibatech.ecmd.common.bean.guide;


import com.google.gson.annotations.SerializedName;

public class GuideTouristUrlBean {
    /**
     * unaccepted_url : http://139.217.8.207:5000/api/tourist_online_treatments/unaccepted/
     * treating_url : http://139.217.8.207:5000/api/tourist_online_treatments/treating/
     */

    @SerializedName("unaccepted_url")
    private String unacceptedUrl;
    @SerializedName("treating_url")
    private String treatingUrl;

    public String getUnacceptedUrl() {
        return unacceptedUrl;
    }

    public void setUnacceptedUrl(String unacceptedUrl) {
        this.unacceptedUrl = unacceptedUrl;
    }

    public String getTreatingUrl() {
        return treatingUrl;
    }

    public void setTreatingUrl(String treatingUrl) {
        this.treatingUrl = treatingUrl;
    }
}
