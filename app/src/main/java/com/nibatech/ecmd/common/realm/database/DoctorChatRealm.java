package com.nibatech.ecmd.common.realm.database;


import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;
import com.nibatech.ecmd.common.realm.BaseRealm;
import com.nibatech.ecmd.common.realm.object.DoctorChatObject;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class DoctorChatRealm extends BaseRealm {

    public static RealmResults<DoctorChatObject> get() {
        return getRealm().where(DoctorChatObject.class).findAll();
    }

    public static void add(DoctorChatObject DoctorChatObject) {
        getRealm().beginTransaction();
        getRealm().copyToRealm(DoctorChatObject);
        getRealm().commitTransaction();
    }

    public static void remove() {
        // obtain the results of a query
        final RealmResults<DoctorChatObject> results = getRealm().where(DoctorChatObject.class).findAll();
        // All changes to data must happen in a transaction
        getRealm().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Delete all matches
                results.deleteAllFromRealm();
            }
        });
    }

    public static void deleteLastItem() {
        final RealmResults<DoctorChatObject> results = getRealm().where(DoctorChatObject.class).findAll();
        getRealm().beginTransaction();
        results.deleteLastFromRealm();
        getRealm().commitTransaction();
    }

    public static RealmResults<DoctorChatObject> sort(String key) {
        RealmResults<DoctorChatObject> result = getRealm().where(DoctorChatObject.class).findAll();
        result = result.sort(key, Sort.DESCENDING);

        return result;
    }

    public static RealmResults<DoctorChatObject> search(String key, String value) {
        return getRealm().where(DoctorChatObject.class).equalTo(key, value).findAll();
    }

    public static void setItemUnReadNumber(final String key, final String value, final String updatedTime,
                                           final int unReadNumber) {
        getRealm().beginTransaction();
        DoctorChatObject chatObject = getRealm().where(DoctorChatObject.class).
                equalTo(key, value).findFirst();
        chatObject.setUpdatedTime(updatedTime);
        //在此item中，list列表应该是全部已读
        if (chatObject.isReadLock()) {
            chatObject.setUnReadNumber(0);
        } else {
            chatObject.setUnReadNumber(unReadNumber);
        }
        getRealm().commitTransaction();
    }

    public static void setItemHasReadLocked(String key, String value) {
        getRealm().beginTransaction();
        DoctorChatObject chatObject = getRealm().where(DoctorChatObject.class).
                equalTo(key, value).findFirst();
        chatObject.setReadLock(true);
        chatObject.setUnReadNumber(0);
        getRealm().commitTransaction();
    }

    public static void setItemUnLockRead() {
        getRealm().beginTransaction();
        DoctorChatObject chatObject = getRealm().where(DoctorChatObject.class).
                equalTo("readLock", true).findFirst();
        if (chatObject != null) {
            chatObject.setReadLock(false);
        }
        getRealm().commitTransaction();
    }

    public static void insert(DoctorChatObject object, String key) {
        DoctorChatObject result = getRealm().where(DoctorChatObject.class).
                equalTo(key, object.getCdNumber()).findFirst();
        if (result == null) {
            add(object);
        } else {
            getRealm().beginTransaction();
            setDoctorProfile(result.getDoctor(), object.getDoctor());
            result.setUnReadNumber(object.getUnReadNumber());
            getRealm().commitTransaction();
        }
    }

    //realm不支持直接set class方式，只能set value
    private static void setDoctorProfile(DoctorProfileBean old, DoctorProfileBean update) {
        old.setCdNumber(update.getCdNumber());
        old.setAge(update.getAge());
        old.setAvatarUrl(update.getAvatarUrl());
        old.setDoctorType(update.getDoctorType());
        old.setExcellent(update.isExcellent());
        old.setFullName(update.getFullName());
        old.setGender(update.getGender());
        old.setSpecialism(update.getSpecialism());
        old.setVerified(update.isVerified());
    }
}
