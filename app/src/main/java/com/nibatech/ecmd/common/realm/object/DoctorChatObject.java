package com.nibatech.ecmd.common.realm.object;


import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;

import io.realm.RealmObject;

public class DoctorChatObject extends RealmObject {
    private DoctorProfileBean doctor;
    private String charUrl;
    private int unReadNumber;
    private String updatedTime;
    private boolean stick;
    private boolean readLock;
    private String cdNumber;
    private int chatId;

    public DoctorProfileBean getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorProfileBean doctor) {
        this.doctor = doctor;
    }

    public String getCharUrl() {
        return charUrl;
    }

    public void setCharUrl(String charUrl) {
        this.charUrl = charUrl;
    }

    public int getUnReadNumber() {
        return unReadNumber;
    }

    public void setUnReadNumber(int unReadNumber) {
        this.unReadNumber = unReadNumber;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public boolean isStick() {
        return stick;
    }

    public void setStick(boolean stick) {
        this.stick = stick;
    }

    public boolean isReadLock() {
        return readLock;
    }

    public void setReadLock(boolean readLock) {
        this.readLock = readLock;
    }

    public String getCdNumber() {
        return cdNumber;
    }

    public void setCdNumber(String cdNumber) {
        this.cdNumber = cdNumber;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }
}
