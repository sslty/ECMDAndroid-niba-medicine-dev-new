package com.nibatech.ecmd.common.bean.code;

import com.google.gson.annotations.SerializedName;



public class CodePatientBean {
    /**
     * code : 149831
     * edited : false
     * name : 粉丝患者
     * offline_patient : {"self_url":"http://192.168.31.235:5000/api/offline_patients/1"}
     * project_case : {"self_url":"http://192.168.31.235:5000/api/project_cases/9"}
     * status : 已注册
     * updated_time : 2016-09-09 18:40:42
     */

    private String code;
    private boolean edited;
    private String name;
    @SerializedName("update_profile_url")
    private String update_Profile_Url;
    /**
     * self_url : http://192.168.31.235:5000/api/offline_patients/1
     */

    @SerializedName("offline_patient")
    private OfflinePatient offlinePatient;
    /**
     * self_url : http://192.168.31.235:5000/api/project_cases/9
     */

    @SerializedName("project_case")
    private ProjectCase projectCase;
    private String status;
    @SerializedName("updated_time")
    private String updatedTime;

    public String getCode() {
        return code;
    }

    public boolean isEdited() {
        return edited;
    }

    public String getName() {
        return name;
    }

    public OfflinePatient getOfflinePatient() {
        return offlinePatient;
    }

    public ProjectCase getProjectCase() {
        return projectCase;
    }

    public String getStatus() {
        return status;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public String getUpdate_Profile_Url() {
        return update_Profile_Url;
    }

    public void setUpdate_Profile_Url(String update_Profile_Url) {
        this.update_Profile_Url = update_Profile_Url;
    }

    public static class OfflinePatient {
        @SerializedName("self_url")
        private String selfUrl;

        public String getSelfUrl() {
            return selfUrl;
        }
    }

    public static class ProjectCase {
        @SerializedName("self_url")
        private String selfUrl;

        public String getSelfUrl() {
            return selfUrl;
        }
    }
}
