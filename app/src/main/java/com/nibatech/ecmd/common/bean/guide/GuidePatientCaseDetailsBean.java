package com.nibatech.ecmd.common.bean.guide;

import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.ImageNameBean;

import java.util.List;

public class GuidePatientCaseDetailsBean {
    /**
     * created_time : 2016-10-08T15:12:28
     * images : [{"image_url":"http://139.217.8.207:5000/static/app_files/online_treatment/1/data/1.1475910747.887947.jpg","name":null},{"image_url":"http://139.217.8.207:5000/static/app_files/online_treatment/1/data/1.1475910747.888410.jpg","name":null},{"image_url":"http://139.217.8.207:5000/static/app_files/online_treatment/1/data/1.1475910747.888657.jpg","name":null}]
     * proposals : [{"self_url":"http://139.217.8.207:5000/api/patient_olt_proposals/2","avatar_url":null,"expectation":"看不好了","expense":10000}]
     * description : 我有病
     * symptom : 脸疼
     */

    @SerializedName("created_time")
    private String createdTime;
    @SerializedName("updated_time")
    private String updatedTime;
    private String description;
    private String symptom;
    /**
     * image_url : http://139.217.8.207:5000/static/app_files/online_treatment/1/data/1.1475910747.887947.jpg
     * name : null
     */

    private List<ImageNameBean> images;
    /**
     * self_url : http://139.217.8.207:5000/api/patient_olt_proposals/2
     * avatar_url : null
     * expectation : 看不好了
     * expense : 10000
     */

    private List<Proposal> proposals;

    public String getCreatedTime() {
        return createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public String getDescription() {
        return description;
    }

    public String getSymptom() {
        return symptom;
    }

    public List<ImageNameBean> getImages() {
        return images;
    }

    public List<Proposal> getProposals() {
        return proposals;
    }

    public static class Proposal {
        @SerializedName("self_url")
        private String selfUrl;
        @SerializedName("avatar_url")
        private String avatarUrl;
        private String expectation;
        private int expense;

        public String getSelfUrl() {
            return selfUrl;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public String getExpectation() {
            return expectation;
        }

        public int getExpense() {
            return expense;
        }
    }
}
