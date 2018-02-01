package com.nibatech.ecmd.common.bean.chat;


public class ChatGuideResultBean {
    /**
     * name : 指导意见
     * url : http://139.217.8.207:5000/api/online_treatments/28/guide
     * finished : false
     * time : 2016-10-17T15:52:10
     * upload_url : null
     * type : guide
     */

    private ChatMaterialBean guide;

    public ChatMaterialBean getGuide() {
        return guide;
    }

    public void setGuide(ChatMaterialBean guide) {
        this.guide = guide;
    }
}
