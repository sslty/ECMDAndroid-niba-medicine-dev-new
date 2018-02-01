package com.nibatech.ecmd.common.bean.mypatient;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class FansPatientListBean {


    /**
     * code : 322224
     * created_time : 2016-09-29T14:25:34
     * patient_profile : {"age":null,"avatar_url":"http://192.168.31.123:5000/static/app_files/avatars/9/9.1477559928.669350.png","cd_number":"P061000002","city":"北京市","gender":"女","name":"小磬"}
     * self_url : http://192.168.31.123:5000/api/offline_patients/2/chat
     * update_profile_url : http://192.168.31.123:5000/api/offline_patients/2/profile_by_doctor
     * updated_time : 2016-09-29T14:29:13
     */

    @SerializedName("offline_patients")
    private List<OfflinePatient> offlinePatients;

    public List<OfflinePatient> getOfflinePatients() {
        return offlinePatients;
    }

    public void setOfflinePatients(List<OfflinePatient> offlinePatients) {
        this.offlinePatients = offlinePatients;
    }

    public static class OfflinePatient {
        private String code;
        @SerializedName("created_time")
        private String createdTime;
        /**
         * age : null
         * avatar_url : http://192.168.31.123:5000/static/app_files/avatars/9/9.1477559928.669350.png
         * cd_number : P061000002
         * city : 北京市
         * gender : 女
         * name : 小磬
         */

        @SerializedName("patient_profile")
        private PatientProfile patientProfile;
        @SerializedName("self_url")
        private String selfUrl;
        @SerializedName("update_profile_url")
        private String updateProfileUrl;
        @SerializedName("updated_time")
        private String updatedTime;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public PatientProfile getPatientProfile() {
            return patientProfile;
        }

        public void setPatientProfile(PatientProfile patientProfile) {
            this.patientProfile = patientProfile;
        }

        public String getSelfUrl() {
            return selfUrl;
        }

        public void setSelfUrl(String selfUrl) {
            this.selfUrl = selfUrl;
        }

        public String getUpdateProfileUrl() {
            return updateProfileUrl;
        }

        public void setUpdateProfileUrl(String updateProfileUrl) {
            this.updateProfileUrl = updateProfileUrl;
        }

        public String getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(String updatedTime) {
            this.updatedTime = updatedTime;
        }

        public static class PatientProfile {
            private int age;
            @SerializedName("avatar_url")
            private String avatarUrl;
            @SerializedName("cd_number")
            private String cdNumber;
            private String city;
            private String gender;
            private String name;

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

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
