package com.nibatech.ecmd.common.bean.register;

import com.google.gson.annotations.SerializedName;

public class RegisterAllowExamBean {
    /**
     * allow_answer : true
     */

    @SerializedName("allow_answer")
    private boolean allowAnswer;

    public boolean isAllowAnswer() {
        return allowAnswer;
    }

    public void setAllowAnswer(boolean allowAnswer) {
        this.allowAnswer = allowAnswer;
    }
}
