package com.nibatech.ecmd.common.bean.chat;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatFansDoctorListBean {

    /**
     * self_url : http://139.217.8.207:5000/api/offline_doctors/8/chat
     * gender : 男
     * cd_number : D011000004
     * avatar_url : http://139.217.8.207:5000/static/app_files/avatars/14/14.1477648760.871737.webp
     * specialism : 全科
     * fullname : 小发吧
     * doctor_type : 医院
     */

    @SerializedName("offline_doctors")
    private List<OfflineDoctor> offlineDoctors;

    public List<OfflineDoctor> getOfflineDoctors() {
        return offlineDoctors;
    }

    public void setOfflineDoctors(List<OfflineDoctor> offlineDoctors) {
        this.offlineDoctors = offlineDoctors;
    }

    public static class OfflineDoctor {
        @SerializedName("self_url")
        private String selfUrl;
        private String gender;
        @SerializedName("cd_number")
        private String cdNumber;
        @SerializedName("avatar_url")
        private String avatarUrl;
        private String specialism;
        @SerializedName("fullname")
        private String fullName;
        @SerializedName("doctor_type")
        private String doctorType;

        public String getSelfUrl() {
            return selfUrl;
        }

        public void setSelfUrl(String selfUrl) {
            this.selfUrl = selfUrl;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getCdNumber() {
            return cdNumber;
        }

        public void setCdNumber(String cdNumber) {
            this.cdNumber = cdNumber;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getSpecialism() {
            return specialism;
        }

        public void setSpecialism(String specialism) {
            this.specialism = specialism;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getDoctorType() {
            return doctorType;
        }

        public void setDoctorType(String doctorType) {
            this.doctorType = doctorType;
        }
    }
}
