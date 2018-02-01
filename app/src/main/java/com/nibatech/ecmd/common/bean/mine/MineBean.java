package com.nibatech.ecmd.common.bean.mine;

import com.google.gson.annotations.SerializedName;

public class MineBean {
    /**
     * ext_info_urls : {"submit_url":"http://139.217.8.207:5000/api/doctor_ext_info/submit","get_ext_info":"http://139.217.8.207:5000/api/doctor_ext_info/get","upload_image_url":"http://139.217.8.207:5000/api/doctor_ext_info/upload_image"}
     * followers : {"count":7,"self_url":"http://139.217.8.207:5000/api/doctor_mine/followers/"}
     * followed : {"count":3,"self_url":"http://139.217.8.207:5000/api/doctor_relation/followed"}
     */

    @SerializedName("ext_info_urls")
    private ExtInfoUrlsBean extInfoUrls;
    private FollowersBean followers;
    private FollowedBean followed;

    public ExtInfoUrlsBean getExtInfoUrls() {
        return extInfoUrls;
    }

    public void setExtInfoUrls(ExtInfoUrlsBean extInfoUrls) {
        this.extInfoUrls = extInfoUrls;
    }

    public FollowersBean getFollowers() {
        return followers;
    }

    public void setFollowers(FollowersBean followers) {
        this.followers = followers;
    }

    public FollowedBean getFollowed() {
        return followed;
    }

    public void setFollowed(FollowedBean followed) {
        this.followed = followed;
    }

    public static class ExtInfoUrlsBean {
        /**
         * submit_url : http://139.217.8.207:5000/api/doctor_ext_info/submit
         * get_ext_info : http://139.217.8.207:5000/api/doctor_ext_info/get
         * upload_image_url : http://139.217.8.207:5000/api/doctor_ext_info/upload_image
         */

        @SerializedName("submit_url")
        private String submitUrl;
        @SerializedName("get_ext_info")
        private String getExtInfo;
        @SerializedName("upload_image_url")
        private String uploadImageUrl;

        public String getSubmitUrl() {
            return submitUrl;
        }

        public void setSubmitUrl(String submitUrl) {
            this.submitUrl = submitUrl;
        }

        public String getGetExtInfo() {
            return getExtInfo;
        }

        public void setGetExtInfo(String getExtInfo) {
            this.getExtInfo = getExtInfo;
        }

        public String getUploadImageUrl() {
            return uploadImageUrl;
        }

        public void setUploadImageUrl(String uploadImageUrl) {
            this.uploadImageUrl = uploadImageUrl;
        }
    }

    public static class FollowersBean {
        /**
         * count : 7
         * self_url : http://139.217.8.207:5000/api/doctor_mine/followers/
         */

        private int count;
        @SerializedName("self_url")
        private String selfUrl;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getSelfUrl() {
            return selfUrl;
        }

        public void setSelfUrl(String selfUrl) {
            this.selfUrl = selfUrl;
        }
    }

    public static class FollowedBean {
        /**
         * count : 3
         * self_url : http://139.217.8.207:5000/api/doctor_relation/followed
         */

        private int count;
        @SerializedName("self_url")
        private String selfUrl;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getSelfUrl() {
            return selfUrl;
        }

        public void setSelfUrl(String selfUrl) {
            this.selfUrl = selfUrl;
        }
    }
}
