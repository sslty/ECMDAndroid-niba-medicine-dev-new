package com.nibatech.ecmd.common.bean.code;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CodeUsedBean {

    /**
     * code : 751147
     * edited : false
     * name : 静脉曲张方剂
     * project_case : {"self_url":"http://192.168.31.235:5000/api/project_cases/11"}
     * status : 已发放
     * updated_time : 2016-09-09 09:46:18
     */

    @SerializedName("project_codes")
    private List<ProjectCodes> projectCodes;

    public List<ProjectCodes> getProjectCodes() {
        return projectCodes;
    }

    public static class ProjectCodes {
        private String code;
        private boolean edited;
        private String name;
        /**
         * self_url : http://192.168.31.235:5000/api/project_cases/11
         */

        @SerializedName("project_case")
        private ProjectCaseBean projectCase;
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

        public ProjectCaseBean getProjectCase() {
            return projectCase;
        }

        public String getStatus() {
            return status;
        }

        public String getUpdatedTime() {
            return updatedTime;
        }

        public static class ProjectCaseBean {
            @SerializedName("self_url")
            private String selfUrl;

            public String getSelfUrl() {
                return selfUrl;
            }
        }
    }
}
