package com.nibatech.ecmd.common.bean.comment;

import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.PagesBean;

import java.util.List;


public class CommentListBean {

    /**
     * pages : {"prev_url":null,"pages":1,"next_url":null,"per_page":30,"last_url":"http://60.205.141.66:5000/api/cms_articles/5/comment_list?per_page=30&page=1","total":1,"page":1,"first_url":"http://60.205.141.66:5000/api/cms_articles/5/comment_list?per_page=30&page=1"}
     * comments : [{"comment":"你好呀","user":{"gender":"女","avatar_url":"http://60.205.141.66:5000/static/app_files/avatars/13/13.1480059134.520941.jpg","name":"小医生"},"time":"2016-11-29 15:30:53"}]
     */

    private PagesBean pages;
    private List<Comment> comments;

    public PagesBean getPages() {
        return pages;
    }

    public void setPages(PagesBean pages) {
        this.pages = pages;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public static class Comment {
        /**
         * comment : 你好呀
         * user : {"gender":"女","avatar_url":"http://60.205.141.66:5000/static/app_files/avatars/13/13.1480059134.520941.jpg","name":"小医生"}
         * time : 2016-11-29 15:30:53
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
             * gender : 女
             * avatar_url : http://60.205.141.66:5000/static/app_files/avatars/13/13.1480059134.520941.jpg
             * name : 小医生
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
}
