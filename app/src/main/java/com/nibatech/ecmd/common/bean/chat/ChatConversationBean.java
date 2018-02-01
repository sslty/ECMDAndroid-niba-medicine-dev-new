package com.nibatech.ecmd.common.bean.chat;

import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.PagesBean;

import java.util.List;

public class ChatConversationBean {
    /**
     * prev_url : null
     * pages : 1
     * next_url : null
     * per_page : 100
     * last_url : http://139.217.8.207:5000/api/online_treatments/80/conversions/?per_page=100&page=1
     * total : 3
     * page : 1
     * first_url : http://139.217.8.207:5000/api/online_treatments/80/conversions/?per_page=100&page=1
     */

    private PagesBean pages;
    /**
     * created_time : 2016-10-24T14:21:24
     * message : {"content":"可口可乐了","name":"doctor","time":"2016-10-24T14:01:40","type":"MESSAGE_NORMAL"}
     * msg_type : message_
     * from_id : 14
     */

    private List<Conversation> conversations;

    public PagesBean getPages() {
        return pages;
    }

    public void setPages(PagesBean pages) {
        this.pages = pages;
    }

    public List<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
    }

    public static class Conversation {
        @SerializedName("created_time")
        private String createdTime;
        private String message;
        @SerializedName("msg_type")
        private String msgType;
        @SerializedName("from_id")
        private int fromId;

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMsgType() {
            return msgType;
        }

        public void setMsgType(String msgType) {
            this.msgType = msgType;
        }

        public int getFromId() {
            return fromId;
        }

        public void setFromId(int fromId) {
            this.fromId = fromId;
        }
    }
}
