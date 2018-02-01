package com.nibatech.ecmd.common.bean.mine;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CertifyBean {
    @SerializedName("ext_info")
    private List<ExtInfo> extInfo;

    public List<ExtInfo> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(List<ExtInfo> extInfo) {
        this.extInfo = extInfo;
    }

    public static class ExtInfo {
        /**
         * image_url : null
         * desc : 医院
         */

        @SerializedName("image_url")
        private String imageUrl;
        private String desc;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
