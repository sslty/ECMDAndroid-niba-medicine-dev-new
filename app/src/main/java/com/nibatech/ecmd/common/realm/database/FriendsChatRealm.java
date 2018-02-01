package com.nibatech.ecmd.common.realm.database;


import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;
import com.nibatech.ecmd.common.realm.BaseRealm;
import com.nibatech.ecmd.common.realm.object.FriendsChatObject;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;


/**
 * 医生端   首页-好友-聊天列表
 */
public class FriendsChatRealm extends BaseRealm {

    public static RealmResults<FriendsChatObject> get() {
        return getRealm().where(FriendsChatObject.class).findAll();
    }

    public static void add(FriendsChatObject friendsChatObject) {
        getRealm().beginTransaction();
        getRealm().copyToRealm(friendsChatObject);
        getRealm().commitTransaction();
    }

    public static void remove() {
        // obtain the results of a query
        final RealmResults<FriendsChatObject> results = getRealm().where(FriendsChatObject.class).findAll();
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
        final RealmResults<FriendsChatObject> results = getRealm().where(FriendsChatObject.class).findAll();
        getRealm().beginTransaction();
        results.deleteLastFromRealm();
        getRealm().commitTransaction();
    }

    public static RealmResults<FriendsChatObject> sort(String key) {
        RealmResults<FriendsChatObject> result = getRealm().where(FriendsChatObject.class).findAll();
        result = result.sort(key, Sort.DESCENDING);

        return result;
    }

    public static RealmResults<FriendsChatObject> search(String key, String value) {
        return getRealm().where(FriendsChatObject.class).equalTo(key, value).findAll();
    }

    public static void setItemUnReadNumber(final String key, final String value, final String updatedTime,
                                           final int unReadNumber) {
        getRealm().beginTransaction();
        FriendsChatObject chatObject = getRealm().where(FriendsChatObject.class).
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
        FriendsChatObject friendsChatObject = getRealm().where(FriendsChatObject.class).
                equalTo(key, value).findFirst();
        friendsChatObject.setReadLock(true);
        friendsChatObject.setUnReadNumber(0);
        getRealm().commitTransaction();
    }

    public static void setItemUnLockRead() {
        getRealm().beginTransaction();
        FriendsChatObject friendsChatObject = getRealm().where(FriendsChatObject.class).
                equalTo("readLock", true).findFirst();
        if (friendsChatObject != null) {
            friendsChatObject.setReadLock(false);
        }
        getRealm().commitTransaction();
    }

    public static void insert(FriendsChatObject object, String key) {
        FriendsChatObject result = getRealm().where(FriendsChatObject.class).
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
