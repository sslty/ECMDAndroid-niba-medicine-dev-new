package com.nibatech.ecmd.common.bean.common;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CaseStageBean {
    /**
     * stages : [{"created_time":"2016-12-01T18:12:15","report_url":"http://139.217.8.207:5000/static/app_files/project_case/21/stages/1.html","name":"fasdfdsfsdfdf"},{"created_time":"2016-12-01T18:12:41","report_url":"http://139.217.8.207:5000/static/app_files/project_case/21/stages/2.html","name":"http://www.ni"}]
     * pages : {"prev_url":null,"pages":1,"next_url":null,"per_page":30,"last_url":"http://139.217.8.207:5000/api/medicine_cases/21/stages/?per_page=30&page=1","total":2,"page":1,"first_url":"http://139.217.8.207:5000/api/medicine_cases/21/stages/?per_page=30&page=1"}
     */

    private PagesBean pages;
    private List<Stage> stages;

    public PagesBean getPages() {
        return pages;
    }

    public void setPages(PagesBean pages) {
        this.pages = pages;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    public static class Stage {
        /**
         * created_time : 2016-12-01T18:12:15
         * report_url : http://139.217.8.207:5000/static/app_files/project_case/21/stages/1.html
         * name : fasdfdsfsdfdf
         */

        @SerializedName("created_time")
        private String createdTime;
        @SerializedName("report_url")
        private String reportUrl;
        private String name;

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public String getReportUrl() {
            return reportUrl;
        }

        public void setReportUrl(String reportUrl) {
            this.reportUrl = reportUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
