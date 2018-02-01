package com.nibatech.ecmd.common.bean.code;

import com.google.gson.annotations.SerializedName;

public class CodeUrlBean {

    /**
     * update_profile_url : http://139.217.8.207:5000/api/project_cases/16/profile_by_doctor
     */

    @SerializedName("update_profile_url")
    private String updateProfileUrl;

    public String getUpdateProfileUrl() {
        return updateProfileUrl;
    }

    public void setUpdateProfileUrl(String updateProfileUrl) {
        this.updateProfileUrl = updateProfileUrl;
    }
}
