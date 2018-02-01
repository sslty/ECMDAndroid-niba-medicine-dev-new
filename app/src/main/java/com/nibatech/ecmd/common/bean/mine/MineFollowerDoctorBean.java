package com.nibatech.ecmd.common.bean.mine;

import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;

import java.util.List;

public class MineFollowerDoctorBean {

    private List<Follower> followers;

    public List<Follower> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Follower> followers) {
        this.followers = followers;
    }

    public static class Follower {
        /**
         * doctor_profile : {"avatar_url":"http://60.205.141.66:5000/static/app_files/avatars/20/20.1480305800.717324.jpeg","fullname":"OK","excellent":false,"specialism":"全科","gender":"男","age":32,"verified":false,"cd_number":"D111000011","doctor_type":"医院"}
         */

        @SerializedName("doctor_profile")
        private DoctorProfileBean doctorProfile;

        public DoctorProfileBean getDoctorProfile() {
            return doctorProfile;
        }

        public void setDoctorProfile(DoctorProfileBean doctorProfile) {
            this.doctorProfile = doctorProfile;
        }
    }
}
