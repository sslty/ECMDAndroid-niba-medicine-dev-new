package com.nibatech.ecmd.common.bean.register;

import com.google.gson.annotations.SerializedName;

public class RegisterCheckCorrectBean {
    /**
     * pass_test : false
     */

    @SerializedName("pass_test")
    private boolean passTest;

    public boolean isPassTest() {
        return passTest;
    }

    public void setPassTest(boolean passTest) {
        this.passTest = passTest;
    }
}
