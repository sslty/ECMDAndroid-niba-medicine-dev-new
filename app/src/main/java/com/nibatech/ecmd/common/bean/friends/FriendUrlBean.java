package com.nibatech.ecmd.common.bean.friends;

import com.google.gson.annotations.SerializedName;

public class FriendUrlBean {


    /**
     * chat_list_url : http://139.217.8.207:5000/api/doctor_relation/chats/
     * contact_url : http://139.217.8.207:5000/api/doctor_relation/contacts/
     * get_chat_url : http://139.217.8.207:5000/api/doctor_relation/get_chat_by_cd_number
     */

    @SerializedName("chat_list_url")
    private String chatListUrl;
    @SerializedName("contact_url")
    private String contactUrl;
    @SerializedName("get_chat_url")
    private String getChatUrl;

    public String getChatListUrl() {
        return chatListUrl;
    }

    public void setChatListUrl(String chatListUrl) {
        this.chatListUrl = chatListUrl;
    }

    public String getContactUrl() {
        return contactUrl;
    }

    public void setContactUrl(String contactUrl) {
        this.contactUrl = contactUrl;
    }

    public String getGetChatUrl() {
        return getChatUrl;
    }

    public void setGetChatUrl(String getChatUrl) {
        this.getChatUrl = getChatUrl;
    }
}
