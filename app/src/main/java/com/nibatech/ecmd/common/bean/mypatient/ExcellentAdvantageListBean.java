package com.nibatech.ecmd.common.bean.mypatient;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ExcellentAdvantageListBean {

    /**
     * self_url : http://139.217.8.207:5000/api/project_cases/21
     * code : 590307
     * name : 静脉曲张方剂
     * patient_profile : {"gender":"男","age":32,"avatar_url":null,"name":"hhhgqLisa","city":null}
     * updated_time : 2016-10-25T12:40:07
     * created_time : 2016-10-25T12:40:07
     */

    private List<Case> cases;

    public List<Case> getCases() {
        return cases;
    }

    public void setCases(List<Case> cases) {
        this.cases = cases;
    }

    public static class Case {
        @SerializedName("self_url")
        private String selfUrl;
        private String code;
        private String name;
        /**
         * gender : 男
         * age : 32
         * avatar_url : null
         * name : hhhgqLisa
         * city : null
         * cd_number:null
         */

        @SerializedName("patient_profile")
        private PatientProfile patientProfile;
        @SerializedName("updated_time")
        private String updatedTime;
        @SerializedName("created_time")
        private String createdTime;

        public String getSelfUrl() {
            return selfUrl;
        }

        public void setSelfUrl(String selfUrl) {
            this.selfUrl = selfUrl;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public PatientProfile getPatientProfile() {
            return patientProfile;
        }

        public void setPatientProfile(PatientProfile patientProfile) {
            this.patientProfile = patientProfile;
        }

        public String getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(String updatedTime) {
            this.updatedTime = updatedTime;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public static class PatientProfile {
            private String gender;
            private int age;
            @SerializedName("avatar_url")
            private String avatarUrl;
            private String name;
            private String city;
            @SerializedName("cd_number")
            private String cdNumber;

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCdNumber() {
                return cdNumber;
            }

            public void setCdNumber(String cdNumber) {
                this.cdNumber = cdNumber;
            }
        }
    }
}
