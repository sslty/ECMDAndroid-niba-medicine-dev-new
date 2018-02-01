package com.nibatech.ecmd.common.bean.mydoctor;


import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;
import com.nibatech.ecmd.common.bean.personal.RelationsBean;

import java.util.List;

public class FollowedDoctorListBean {

    private List<DoctorsBean> doctors;

    public List<DoctorsBean> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DoctorsBean> doctors) {
        this.doctors = doctors;
    }

    public static class DoctorsBean {
        /**
         * doctor_profile : {"homepage_url":"http://139.217.8.207:5000/api/doctor_homepage/38","avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/152/152.1481190808.841979.jpg","fullname":"你地几时","excellent":false,"specialism":"全科","gender":"女","age":11,"verified":false,"cd_number":"D111000038","doctor_type":null}
         * relations : {"unfollow_url":"http://139.217.8.207:5000/api/patient_followed/unfollow/38","follow_url":"http://139.217.8.207:5000/api/patient_followed/follow/38","is_following":true,"is_followed_by":false}
         */

        @SerializedName("doctor_profile")
        private DoctorProfileBean doctorProfile;
        private RelationsBean relations;

        public DoctorProfileBean getDoctorProfile() {
            return doctorProfile;
        }

        public void setDoctorProfile(DoctorProfileBean doctorProfile) {
            this.doctorProfile = doctorProfile;
        }

        public RelationsBean getRelations() {
            return relations;
        }

        public void setRelations(RelationsBean relations) {
            this.relations = relations;
        }
    }
}
