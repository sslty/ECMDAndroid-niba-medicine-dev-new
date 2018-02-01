package com.nibatech.ecmd.common.bean.prize;


public class PrizeGuessPermissionBean {
    /**
     * message : 上轮竞猜结果错误，无法继续，请再接再厉！
     * allow : false
     */

    private String message;
    private boolean allow;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }
}
