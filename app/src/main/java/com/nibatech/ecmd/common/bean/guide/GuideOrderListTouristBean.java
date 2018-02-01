package com.nibatech.ecmd.common.bean.guide;

import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;
import com.nibatech.ecmd.common.bean.common.FormattedInfoBean;
import com.nibatech.ecmd.common.bean.common.PagesBean;

import java.util.List;

public class GuideOrderListTouristBean {

    /**
     * prev_url : null
     * pages : 1
     * next_url : null
     * per_page : 30
     * last_url : http://139.217.8.207:5000/api/patient_online_treatments/others_unaccepted/?per_page=30&page=1
     * total : 2
     * page : 1
     * first_url : http://139.217.8.207:5000/api/patient_online_treatments/others_unaccepted/?per_page=30&page=1
     */

    private PagesBean pages;
    /**
     * self_url : http://139.217.8.207:5000/api/patient_online_treatments/3
     * formatted_info : {"max_expense":0,"min_expense":0,"doctor_count":0,"stage_count":0}
     * description : 没有病有病
     * doctor : null
     * created_time : 2016-10-08T15:16:15
     * start_time : null
     * symptom : 肚子疼
     * updated_time : 2016-10-08T15:16:15
     */

    @SerializedName("online_treatments")
    private List<OnlineTreatment> onlineTreatments;

    public PagesBean getPages() {
        return pages;
    }

    public List<OnlineTreatment> getOnlineTreatments() {
        return onlineTreatments;
    }


    public static class OnlineTreatment {
        @SerializedName("self_url")
        private String selfUrl;
        /**
         * max_expense : 0
         * min_expense : 0
         * doctor_count : 0
         * stage_count : 0
         */

        @SerializedName("formatted_info")
        private FormattedInfoBean formattedInfoBean;
        private String description;
        private DoctorProfileBean doctor;
        @SerializedName("created_time")
        private String createdTime;
        @SerializedName("start_time")
        private String startTime;
        private String symptom;
        @SerializedName("updated_time")
        private String updatedTime;

        public String getSelfUrl() {
            return selfUrl;
        }

        public FormattedInfoBean getFormattedInfoBean() {
            return formattedInfoBean;
        }

        public String getDescription() {
            return description;
        }

        public DoctorProfileBean getDoctor() {
            return doctor;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public String getStartTime() {
            return startTime;
        }

        public String getSymptom() {
            return symptom;
        }

        public String getUpdatedTime() {
            return updatedTime;
        }
    }

}
