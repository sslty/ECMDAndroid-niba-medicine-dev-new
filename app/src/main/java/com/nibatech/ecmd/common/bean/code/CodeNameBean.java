package com.nibatech.ecmd.common.bean.code;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CodeNameBean {

    /**
     * code : 800745
     * name : 静脉曲张方剂
     */

    @SerializedName("project_codes")
    private List<ProjectCodes> projectCodes;

    public List<ProjectCodes> getProjectCodes() {
        return projectCodes;
    }

    public static class ProjectCodes {
        private String code;
        private String name;

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }
}
