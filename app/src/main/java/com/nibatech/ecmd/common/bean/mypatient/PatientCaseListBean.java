package com.nibatech.ecmd.common.bean.mypatient;

import com.google.gson.annotations.SerializedName;

import java.util.List;



public class PatientCaseListBean {
    /**
     * created_time : 2016-09-18T11:05:54
     * report_url : http://60.205.141.66:5000/ecmd_admin/static/project_case/62/stages/3.html
     * name : 基本病情
     */

    public List<Stages> stages;

    public List<Stages> getStages() {
        return stages;
    }

    public static class Stages {
        @SerializedName("created_time")
        private String createdTime;
        @SerializedName("report_url")
        private String reportUrl;
        private String name;

        public String getCreatedTime() {
            return createdTime;
        }

        public String getReportUrl() {
            return reportUrl;
        }

        public String getName() {
            return name;
        }
    }
}
