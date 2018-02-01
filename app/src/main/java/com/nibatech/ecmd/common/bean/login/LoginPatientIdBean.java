package com.nibatech.ecmd.common.bean.login;

import io.realm.RealmObject;

public class LoginPatientIdBean extends RealmObject {
    /**
     * id : 1
     */
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
