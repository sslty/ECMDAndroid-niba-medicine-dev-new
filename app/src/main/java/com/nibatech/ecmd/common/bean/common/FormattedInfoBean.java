package com.nibatech.ecmd.common.bean.common;

import com.google.gson.annotations.SerializedName;

public class FormattedInfoBean {
    @SerializedName("doctor_count")
    private int doctorCount;
    @SerializedName("max_expense")
    private int maxExpense;
    @SerializedName("min_expense")
    private int minExpense;
    private boolean participated;
    @SerializedName("stage_count")
    private int stageCount;

    public int getDoctorCount() {
        return doctorCount;
    }

    public void setDoctorCount(int doctorCount) {
        this.doctorCount = doctorCount;
    }

    public int getMaxExpense() {
        return maxExpense;
    }

    public void setMaxExpense(int maxExpense) {
        this.maxExpense = maxExpense;
    }

    public int getMinExpense() {
        return minExpense;
    }

    public void setMinExpense(int minExpense) {
        this.minExpense = minExpense;
    }

    public boolean isParticipated() {
        return participated;
    }

    public void setParticipated(boolean participated) {
        this.participated = participated;
    }

    public int getStageCount() {
        return stageCount;
    }

    public void setStageCount(int stageCount) {
        this.stageCount = stageCount;
    }
}
