package com.nibatech.ecmd.common.bean.prize;

import com.google.gson.annotations.SerializedName;

public class PrizeRoundBean {
    /**
     * self_url : http://139.217.8.207:5000/api/case_game_rounds/5
     * end_date : 2016-10-25
     * name : 第2轮
     * doctor_right : 0
     * processing : true
     * start_date : 2016-10-25
     * doctor_total : 0
     */

    @SerializedName("self_url")
    private String selfUrl;
    @SerializedName("end_date")
    private String endDate;
    private String name;
    @SerializedName("doctor_right")
    private int doctorRight;
    private boolean processing;
    @SerializedName("start_date")
    private String startDate;
    @SerializedName("doctor_total")
    private int doctorTotal;

    public String getSelfUrl() {
        return selfUrl;
    }

    public void setSelfUrl(String selfUrl) {
        this.selfUrl = selfUrl;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDoctorRight() {
        return doctorRight;
    }

    public void setDoctorRight(int doctorRight) {
        this.doctorRight = doctorRight;
    }

    public boolean isProcessing() {
        return processing;
    }

    public void setProcessing(boolean processing) {
        this.processing = processing;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getDoctorTotal() {
        return doctorTotal;
    }

    public void setDoctorTotal(int doctorTotal) {
        this.doctorTotal = doctorTotal;
    }
}
