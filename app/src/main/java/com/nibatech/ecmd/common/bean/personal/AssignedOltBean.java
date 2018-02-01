package com.nibatech.ecmd.common.bean.personal;

import com.google.gson.annotations.SerializedName;



public class AssignedOltBean {
    /**
     * treating_status : http://139.217.8.207:5000/api/patient_online_treatments/treating_status
     * create_assigned_olt_url : http://139.217.8.207:5000/api/patient_online_treatments/assigned/1
     */

    @SerializedName("treating_status")
    private String treatingStatus;
    @SerializedName("create_assigned_olt_url")
    private String createAssignedOltUrl;

    public String getTreatingStatus() {
        return treatingStatus;
    }

    public void setTreatingStatus(String treatingStatus) {
        this.treatingStatus = treatingStatus;
    }

    public String getCreateAssignedOltUrl() {
        return createAssignedOltUrl;
    }

    public void setCreateAssignedOltUrl(String createAssignedOltUrl) {
        this.createAssignedOltUrl = createAssignedOltUrl;
    }
}
