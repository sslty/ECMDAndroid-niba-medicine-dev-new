package com.nibatech.ecmd.common.bean.mine;

import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;

import java.util.List;

public class MineFollowedBean {

    private List<Followed> followed;

    public List<Followed> getFollowed() {
        return followed;
    }

    public void setFollowed(List<Followed> followed) {
        this.followed = followed;
    }

    public static class Followed {
        /**
         * doctor_profile : {"avatar_url":null,"fullname":"hhhggg","excellent":false,"specialism":"全科","gender":"女","age":55,"verified":true,"cd_number":"D011000006","doctor_type":"医院"}
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
