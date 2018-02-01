package com.nibatech.ecmd.common.bean.mypatient;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PatientRecordListBean {
    /**
     * created_time : 2016-09-15T00:46:45
     * description :
     * image_url : http://192.168.1.105:5000/static/app_files/project_case/28/data/28.1473871604.799206.jpg
     * self_url : http://192.168.1.105:5000/api/project_case_data/69
     * updated_time : 2016-09-15T00:46:45
     */

    @SerializedName("project_case_data_list")
    private List<ProjectCaseDataList> projectCaseDataList;

    public List<ProjectCaseDataList> getProjectCaseDataList() {
        return projectCaseDataList;
    }

    public static class ProjectCaseDataList {
        @SerializedName("created_time")
        private String createdTime;
        private String description;
        @SerializedName("image_url")
        private String imageUrl;
        @SerializedName("self_url")
        private String selfUrl;
        @SerializedName("updated_time")
        private String updatedTime;

        public String getDescription() {
            return description;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getSelfUrl() {
            return selfUrl;
        }


        public String getUpdatedTime() {
            return updatedTime;
        }
    }
}
