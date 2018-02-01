package com.nibatech.ecmd.common.realm;


import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;


public class BaseRealm {

    public BaseRealm() {
    }

    //当前的数据库对象
    protected static Realm getRealm() {
        return Realm.getDefaultInstance();
    }

    public static void createUserRealm(Context context, String phone) {
        //每一位用户都有自己手机命名的数据库
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(phone + ".realm")
                .build();

        //保存配置信息
        Realm.setDefaultConfiguration(config);
    }

    //保存
    protected static void save(RealmObject realmObject) {
        getRealm().beginTransaction();
        getRealm().copyToRealm(realmObject);
        getRealm().commitTransaction();
    }

    //查找所有
    protected static <T extends RealmObject> RealmResults<T> findAll(Class<T> realmObjectClass) {
        return getRealm().where(realmObjectClass).findAll();
    }

    //过滤查找第一个
    protected static <T extends RealmObject> T findFirst(String FiledName,
                                                         String FieldValue,
                                                         Class<T> realmObjectClass) {
        return getRealm().where(realmObjectClass).equalTo(FiledName, FieldValue).findFirst();
    }

    //查找第一个
    public <T extends RealmObject> T findFirst(Class<T> realmObjectClass) {
        return getRealm().where(realmObjectClass).findFirst();
    }

    //过滤查找所有
    protected static <T extends RealmObject> RealmResults<T> query(String FiledName,
                                                                   String FieldValue,
                                                                   Class<T> realmObjectClass) {
        return getRealm().where(realmObjectClass).equalTo(FiledName, FieldValue).findAll();
    }

    //排序
    protected static <T extends RealmObject> RealmResults<T> sort(String FieldName, Class<T> realmObjectClass) {
        RealmResults<T> results = findAll(realmObjectClass);
        results.sort(FieldName, Sort.DESCENDING);
        return results;
    }

    //删除所有数据
    protected static <T extends RealmObject> void clear(Class<T> realmObjectClass) {
        getRealm().beginTransaction();
        findAll(realmObjectClass).deleteAllFromRealm();
        getRealm().commitTransaction();
    }

    //删除第一条数据
    protected static void deleteFirstItem(final RealmResults<?> result) {
        getRealm().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteFirstFromRealm();
            }
        });
    }

    //删除第一条匹配的数据


}
