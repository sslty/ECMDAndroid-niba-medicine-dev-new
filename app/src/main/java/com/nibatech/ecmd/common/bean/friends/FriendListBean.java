package com.nibatech.ecmd.common.bean.friends;

import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;

import java.util.List;

public class FriendListBean {

    private List<Friend> friends;

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public static class Friend {
        /**
         * create_chat_url : http://139.217.8.207:5000/api/doctor_relation/create_chat/1
         * doctor_profile : {"avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/7/7.1477972506.342093.jpg","fullname":"小医生","excellent":false,"specialism":"儿科","gender":"女","age":30,"verified":true,"cd_number":"D161000001","doctor_type":"医院"}
         */

        @SerializedName("create_chat_url")
        private String createChatUrl;
        @SerializedName("doctor_profile")
        private DoctorProfileBean doctorProfile;

        public String getCreateChatUrl() {
            return createChatUrl;
        }

        public void setCreateChatUrl(String createChatUrl) {
            this.createChatUrl = createChatUrl;
        }

        public DoctorProfileBean getDoctorProfile() {
            return doctorProfile;
        }

        public void setDoctorProfile(DoctorProfileBean doctorProfile) {
            this.doctorProfile = doctorProfile;
        }
    }
}
