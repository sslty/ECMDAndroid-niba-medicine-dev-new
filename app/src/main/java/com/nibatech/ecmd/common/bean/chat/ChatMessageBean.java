package com.nibatech.ecmd.common.bean.chat;

public class ChatMessageBean {
    private String time;
    private String content;


    private Type type;
    private String selfUrl;

    public String getSelfUrl() {
        return selfUrl;
    }

    public void setSelfUrl(String selfUrl) {
        this.selfUrl = selfUrl;
    }

    public enum Type {
        MESSAGE_SEND_GUIDE,
        MESSAGE_SUPPLY,
        MESSAGE_END_GUIDE,
        MESSAGE_AGAIN,
        MESSAGE_NORMAL
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
