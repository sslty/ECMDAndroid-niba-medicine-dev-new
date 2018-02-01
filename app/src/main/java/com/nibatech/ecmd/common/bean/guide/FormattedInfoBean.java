package com.nibatech.ecmd.common.bean.guide;

import com.google.gson.annotations.SerializedName;



public class FormattedInfoBean {
    /**
     * max_expense : 0
     * min_expense : 0
     * participated : null
     * doctor_count : 0
     * stage_count : 0
     */
    @SerializedName("max_expense")
    private int maxExpense;
    @SerializedName("min_expense")
    private int minExpense;
    private Boolean participated;
    @SerializedName("doctor_count")
    private int doctorCount;
    @SerializedName("stage_count")
    private int stageCount;

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

    public Boolean isParticipated() {
        return participated;
    }

    public void setParticipated(Boolean participated) {
        this.participated = participated;
    }

    public int getDoctorCount() {
        return doctorCount;
    }

    public void setDoctorCount(int doctorCount) {
        this.doctorCount = doctorCount;
    }

    public int getStageCount() {
        return stageCount;
    }

    public void setStageCount(int stageCount) {
        this.stageCount = stageCount;
    }
}
