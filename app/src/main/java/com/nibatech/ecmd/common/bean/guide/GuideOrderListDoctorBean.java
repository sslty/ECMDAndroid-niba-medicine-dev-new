package com.nibatech.ecmd.common.bean.guide;

import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.FormattedInfoBean;
import com.nibatech.ecmd.common.bean.common.OrderDoctorBean;
import com.nibatech.ecmd.common.bean.common.PagesBean;

import java.util.List;

public class GuideOrderListDoctorBean {


    /**
     * first_url : http://192.168.31.123:5000/api/doctor_online_treatments/?per_page=30&page=1
     * last_url : http://192.168.31.123:5000/api/doctor_online_treatments/?per_page=30&page=1
     * next_url : null
     * page : 1
     * pages : 1
     * per_page : 30
     * prev_url : null
     * total : 2
     */

    private PagesBean pages;
    /**
     * created_time : 2016-10-08T17:17:36
     * description : 我有病
     * doctor : null
     * formatted_info : {"doctor_count":0,"max_expense":0,"min_expense":0,"participated":false,"stage_count":0}
     * patient : {"age":32,"avatar_url":null,"cd_number":"P161000004","gender":"男"}
     * self_url : http://192.168.31.123:5000/api/doctor_online_treatments/15
     * symptom : 不好治
     * updated_time : 2016-10-08T17:17:36
     */

    @SerializedName("online_treatments")
    private List<OnlineTreatment> onlineTreatments;

    public PagesBean getPages() {
        return pages;
    }

    public void setPages(PagesBean pages) {
        this.pages = pages;
    }

    public List<OnlineTreatment> getOnlineTreatments() {
        return onlineTreatments;
    }

    public void setOnlineTreatments(List<OnlineTreatment> onlineTreatments) {
        this.onlineTreatments = onlineTreatments;
    }


    public static class OnlineTreatment {
        @SerializedName("created_time")
        private String createdTime;
        private String description;
        private OrderDoctorBean doctor;
        /**
         * doctor_count : 0
         * max_expense : 0
         * min_expense : 0
         * participated : false
         * stage_count : 0
         */

        @SerializedName("formatted_info")
        private FormattedInfoBean formattedInfo;
        /**
         * age : 32
         * avatar_url : null
         * cd_number : P161000004
         * gender : 男
         */

        private Patient patient;
        @SerializedName("self_url")
        private String selfUrl;
        private String symptom;
        @SerializedName("updated_time")
        private String updatedTime;

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public OrderDoctorBean getDoctor() {
            return doctor;
        }

        public void setDoctor(OrderDoctorBean doctor) {
            this.doctor = doctor;
        }

        public FormattedInfoBean getFormattedInfo() {
            return formattedInfo;
        }

        public void setFormattedInfo(FormattedInfoBean formattedInfo) {
            this.formattedInfo = formattedInfo;
        }

        public Patient getPatient() {
            return patient;
        }

        public void setPatient(Patient patient) {
            this.patient = patient;
        }

        public String getSelfUrl() {
            return selfUrl;
        }

        public void setSelfUrl(String selfUrl) {
            this.selfUrl = selfUrl;
        }

        public String getSymptom() {
            return symptom;
        }

        public void setSymptom(String symptom) {
            this.symptom = symptom;
        }

        public String getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(String updatedTime) {
            this.updatedTime = updatedTime;
        }

        public static class Patient {
            private int age;
            @SerializedName("avatar_url")
            private String avatarUrl;
            @SerializedName("cd_number")
            private String cdNumber;
            private String gender;

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public String getCdNumber() {
                return cdNumber;
            }

            public void setCdNumber(String cdNumber) {
                this.cdNumber = cdNumber;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }
        }
    }
}
