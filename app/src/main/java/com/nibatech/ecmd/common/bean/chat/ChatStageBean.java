package com.nibatech.ecmd.common.bean.chat;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatStageBean {
    /**
     * end_time : null
     * start_time : 2016-10-17T10:17:51
     * urls : [{"finished":false,"name":"补充资料33","time":"2016-10-17T17:24:14","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/46/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/46"},{"finished":false,"name":"补充资料32","time":"2016-10-17T17:23:31","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/45/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/45"},{"finished":false,"name":"补充资料31","time":"2016-10-17T17:23:27","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/44/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/44"},{"finished":false,"name":"补充资料30","time":"2016-10-17T17:22:32","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/43/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/43"},{"finished":false,"name":"补充资料29","time":"2016-10-17T17:12:11","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/42/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/42"},{"finished":false,"name":"补充资料28","time":"2016-10-17T17:12:10","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/41/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/41"},{"finished":false,"name":"补充资料27","time":"2016-10-17T17:09:57","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/40/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/40"},{"finished":false,"name":"补充资料26","time":"2016-10-17T17:08:47","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/39/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/39"},{"finished":false,"name":"补充资料25","time":"2016-10-17T17:06:21","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/38/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/38"},{"finished":false,"name":"补充资料24","time":"2016-10-17T17:04:06","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/37/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/37"},{"finished":false,"name":"补充资料23","time":"2016-10-17T17:03:16","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/36/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/36"},{"finished":false,"name":"补充资料22","time":"2016-10-17T17:00:20","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/35/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/35"},{"finished":false,"name":"补充资料21","time":"2016-10-17T16:59:27","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/34/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/34"},{"finished":false,"name":"补充资料20","time":"2016-10-17T16:57:37","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/33/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/33"},{"finished":false,"name":"补充资料19","time":"2016-10-17T16:57:35","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/32/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/32"},{"finished":false,"name":"补充资料18","time":"2016-10-17T16:57:20","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/31/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/31"},{"finished":false,"name":"补充资料17","time":"2016-10-17T16:57:16","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/30/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/30"},{"finished":false,"name":"补充资料16","time":"2016-10-17T16:55:21","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/29/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/29"},{"finished":false,"name":"补充资料15","time":"2016-10-17T16:53:21","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/28/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/28"},{"finished":false,"name":"补充资料14","time":"2016-10-17T16:52:56","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/27/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/27"},{"finished":false,"name":"补充资料13","time":"2016-10-17T16:52:54","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/26/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/26"},{"finished":false,"name":"补充资料12","time":"2016-10-17T16:52:19","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/25/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/25"},{"finished":false,"name":"补充资料11","time":"2016-10-17T16:52:17","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/24/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/24"},{"finished":false,"name":"补充资料10","time":"2016-10-17T16:51:25","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/23/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/23"},{"finished":false,"name":"补充资料9","time":"2016-10-17T16:41:53","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/22/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/22"},{"finished":false,"name":"补充资料8","time":"2016-10-17T16:41:50","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/21/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/21"},{"finished":false,"name":"补充资料7","time":"2016-10-17T16:40:32","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/20/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/20"},{"finished":false,"name":"补充资料6","time":"2016-10-17T16:30:56","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/19/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/19"},{"finished":false,"name":"补充资料5","time":"2016-10-17T16:27:46","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/18/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/18"},{"finished":false,"name":"补充资料4","time":"2016-10-17T16:25:43","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/17/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/17"},{"finished":false,"name":"补充资料3","time":"2016-10-17T16:25:41","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/16/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/16"},{"finished":false,"name":"补充资料2","time":"2016-10-17T16:25:18","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/15/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/15"},{"finished":false,"name":"补充资料1","time":"2016-10-17T16:25:16","type":"material","upload_url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/14/upload_images","url":"http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/14"}]
     */

    private List<Stage> stages;

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    public static class Stage {
        @SerializedName("end_time")
        private Object endTime;
        @SerializedName("start_time")
        private String startTime;
        /**
         * finished : false
         * name : 补充资料33
         * time : 2016-10-17T17:24:14
         * type : material
         * upload_url : http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/46/upload_images
         * url : http://192.168.31.123:5000/api/online_treatments/17/stages/17/materials/46
         */

        private List<Url> urls;

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public List<Url> getUrls() {
            return urls;
        }

        public void setUrls(List<Url> urls) {
            this.urls = urls;
        }

        public static class Url {
            private boolean finished;
            private String name;
            private String time;
            private String type;
            @SerializedName("upload_url")
            private String uploadUrl;
            private String url;

            public boolean isFinished() {
                return finished;
            }

            public void setFinished(boolean finished) {
                this.finished = finished;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUploadUrl() {
                return uploadUrl;
            }

            public void setUploadUrl(String uploadUrl) {
                this.uploadUrl = uploadUrl;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
