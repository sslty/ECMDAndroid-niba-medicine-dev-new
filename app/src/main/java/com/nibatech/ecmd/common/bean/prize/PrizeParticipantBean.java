package com.nibatech.ecmd.common.bean.prize;

import com.google.gson.annotations.SerializedName;



public class PrizeParticipantBean {
    /**
     * answer : 无效
     * is_winner : true
     */

    private String answer;
    @SerializedName("is_winner")
    private boolean isWinner;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isIsWinner() {
        return isWinner;
    }

    public void setIsWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }
}
