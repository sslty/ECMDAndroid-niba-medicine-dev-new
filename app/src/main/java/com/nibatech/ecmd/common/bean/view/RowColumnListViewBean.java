package com.nibatech.ecmd.common.bean.view;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RowColumnListViewBean {
    /**
     * indications : [{
     * "self_url":"http://60.205.141.66:5000/api/excellent/3/doctors/",
     * "project_name":"高血脂",
     * },
     * {
     * "doctors_url":"http://60.205.141.66:5000/api/excellent/1/doctors/",
     * "project_name":"高血压1型",
     * },
     * {
     * "doctors_url":"http://60.205.141.66:5000/api/excellent/4/doctors/",
     * "project_name":"冠心病",}]
     * type_name : 心血管科
     */

    private List<Projects> projects;

    public List<Projects> getProjects() {
        return projects;
    }

    public static class Projects {
        @SerializedName("type_name")
        private String typeName;
        /**
         * self_url : http://60.205.141.66:5000/api/excellent/3/doctors/
         * project_name : 高血脂
         */

        private List<Indication> indications;

        public String getTypeName() {
            return typeName;
        }

        public List<Indication> getIndications() {
            return indications;
        }

        public static class Indication {
            @SerializedName("self_url")
            private String selfUrl;
            @SerializedName("project_name")
            private String projectName;

            public String getSelfUrl() {
                return selfUrl;
            }

            public String getProjectName() {
                return projectName;
            }
        }
    }
}
