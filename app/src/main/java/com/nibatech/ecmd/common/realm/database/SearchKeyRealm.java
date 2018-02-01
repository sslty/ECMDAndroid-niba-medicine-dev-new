package com.nibatech.ecmd.common.realm.database;

import com.nibatech.ecmd.common.realm.BaseRealm;
import com.nibatech.ecmd.common.realm.object.SearchKeyObject;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * 医生端／患者端／游客端   搜索界面-保存搜索关键字
 */

public class SearchKeyRealm extends BaseRealm {
    public static final int MAX_ITEM = 10;

    public static RealmResults<SearchKeyObject> get() {
        return getRealm().where(SearchKeyObject.class).findAll();
    }

    public static void add(SearchKeyObject searchKeyObject) {
        final RealmResults<SearchKeyObject> result = query("keyword",
                searchKeyObject.getKeyword(),
                SearchKeyObject.class);
        if (result.size() != 0) {
            deleteFirstItem(result);
        }

        save(searchKeyObject);
        final RealmResults<SearchKeyObject> results = get();
        if (results.size() > MAX_ITEM) {
            getRealm().executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    result.deleteFirstFromRealm();
                }
            });
        }
    }
}
