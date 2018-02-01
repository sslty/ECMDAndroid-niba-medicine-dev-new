package com.nibatech.ecmd.common.bean.common;

import com.google.gson.annotations.SerializedName;

public class EffectsBean {
    /**
     * remarkable_rate : 0
     * recover : 0
     * lost : 0
     * remarkable : 0
     * processing : 1
     * valid : 0
     * invalid : 0
     * delete : 0
     */

    @SerializedName("remarkable_rate")
    private int remarkableRate;
    private int recover;
    private int lost;
    private int remarkable;
    private int processing;
    private int valid;
    private int invalid;
    private int delete;

    public int getRemarkableRate() {
        return remarkableRate;
    }

    public void setRemarkableRate(int remarkableRate) {
        this.remarkableRate = remarkableRate;
    }

    public int getRecover() {
        return recover;
    }

    public void setRecover(int recover) {
        this.recover = recover;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getRemarkable() {
        return remarkable;
    }

    public void setRemarkable(int remarkable) {
        this.remarkable = remarkable;
    }

    public int getProcessing() {
        return processing;
    }

    public void setProcessing(int processing) {
        this.processing = processing;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public int getInvalid() {
        return invalid;
    }

    public void setInvalid(int invalid) {
        this.invalid = invalid;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }
}
