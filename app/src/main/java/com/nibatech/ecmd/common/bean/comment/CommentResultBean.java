package com.nibatech.ecmd.common.bean.comment;

import com.google.gson.annotations.SerializedName;

public class CommentResultBean {

    /**
     * comment : 兔兔
     * user : {"gender":"男","avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/14/14.1478080749.226353.jpg","name":"小发吧谷歌"}
     * time : 2016-11-29 15:50:16
     */

    private String comment;
    private User user;
    private String time;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static class User {
        /**
         * gender : 男
         * avatar_url : http://139.217.8.207:5000/static/app_files/avatars/14/14.1478080749.226353.jpg
         * name : 小发吧谷歌
         */

        private String gender;
        @SerializedName("avatar_url")
        private String avatarUrl;
        private String name;

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
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
    }
}
