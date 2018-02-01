package com.nibatech.ecmd.common.bean.mypatient;

import com.google.gson.annotations.SerializedName;

public class CaseStageUrlBean {

    /**
     * stages_url : http://139.217.8.207:5000/api/project_cases/21/stages/
     * case_data_url : http://139.217.8.207:5000/api/project_cases/21/data/
     * chat_url : http://139.217.8.207:5000/api/project_cases/21/chat
     */

    private Stage stages;

    public Stage getStages() {
        return stages;
    }

    public void setStages(Stage stages) {
        this.stages = stages;
    }

    public static class Stage {
        @SerializedName("stages_url")
        private String stagesUrl;
        @SerializedName("case_data_url")
        private String caseDataUrl;
        @SerializedName("chat_url")
        private String chatUrl;

        public String getStagesUrl() {
            return stagesUrl;
        }

        public void setStagesUrl(String stagesUrl) {
            this.stagesUrl = stagesUrl;
        }

        public String getCaseDataUrl() {
            return caseDataUrl;
        }

        public void setCaseDataUrl(String caseDataUrl) {
            this.caseDataUrl = caseDataUrl;
        }

        public String getChatUrl() {
            return chatUrl;
        }

        public void setChatUrl(String chatUrl) {
            this.chatUrl = chatUrl;
        }
    }
}
