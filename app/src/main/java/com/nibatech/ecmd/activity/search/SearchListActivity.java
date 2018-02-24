package com.nibatech.ecmd.activity.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.nibatech.ecmd.activity.base.BaseActivity;
import com.nibatech.ecmd.common.bean.common.SearchBean;
import com.nibatech.ecmd.common.realm.database.SearchKeyRealm;
import com.nibatech.ecmd.common.realm.object.SearchKeyObject;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.fragment.search.SearchEncyclopediaFragment;
import com.nibatech.ecmd.fragment.search.SearchMienFragment;
import com.nibatech.ecmd.fragment.search.SearchMomentFragment;
import com.nibatech.ecmd.fragment.search.SearchNewsFragment;
import com.nibatech.ecmd.fragment.search.SearchSeaFragment;
import com.nibatech.ecmd.fragment.search.guide.DoctorSearchGuideFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.BaseView;
import com.nibatech.ecmd.view.CustomSearchView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;


public class SearchListActivity extends BaseActivity implements CustomSearchView.MySearchViewListener {
    public static final String guide = "中医指导";
    public static final String mien = "风采";
    public static final String moment = "中医圈";
    public static final String news = "平台动态";
    public static final String encyclopedia = "百科";
    public static final String sea = "方海寻珠";
    private DoctorSearchGuideFragment doctorSearchGuideFragment;
    private SearchMienFragment searchMienFragment;
    private SearchMomentFragment searchMomentFragment;
    private SearchNewsFragment searchNewsFragment;
    private SearchEncyclopediaFragment searchEncyclopediaFragment;
    private SearchSeaFragment searchSeaFragment;
    private String strSearchKey;

    ArrayList<Fragment> fragments;
    private String[] mTopTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarVisible(false);
        setSearchViewVisible(true);
        saveKeywordToView();
        getHttpData();

    }

    private void getHttpData() {
//        CommonRequest.getUrlData(getIntentSelfUrl(), new VolleyCallback() {
//            @Override
//            public void onSuccessResponse(JSONObject success) {
//                getHttpDataSuccess(success.toString());
//            }
//        });
        getHttpDataSuccess("");
    }

    private void getHttpDataSuccess(String json) {
//        SearchBean searchBean = UIUtils.fromJson(json, SearchBean.class);
//        if (searchBean != null) {
//            setViewData(searchBean);
//        }
        SearchBean searchBean = new SearchBean();
        searchBean.setCmsArticleDynamicQueryUrl("");
        searchBean.setCmsEncyclopaedicQueryUrl("");
        searchBean.setDoctorArticleQueryUrl("");
        searchBean.setExcellentQueryUrl("");
        searchBean.setMedicineLegacyUrl("");
        setViewData(searchBean);
    }

    private void setViewData(SearchBean searchBean) {
        mTopTitles = new String[6];
        fragments = new ArrayList<>();
        List<String> selfUrls = new ArrayList<>();
        int number = 0;


        if (searchBean.getOltQueryUrl() != null) {
            mTopTitles[number++] = guide;
            doctorSearchGuideFragment = new DoctorSearchGuideFragment();
            fragments.add(doctorSearchGuideFragment);
            selfUrls.add(searchBean.getOltQueryUrl());
        }
        if (searchBean.getCmsArticleDynamicQueryUrl() != null) {
            mTopTitles[number++] = mien;
            searchMienFragment = new SearchMienFragment();
            fragments.add(searchMienFragment);
            selfUrls.add(searchBean.getCmsArticleDynamicQueryUrl());
        }
        if (searchBean.getDoctorArticleQueryUrl() != null) {
            mTopTitles[number++] = moment;
            searchMomentFragment = new SearchMomentFragment();
            fragments.add(searchMomentFragment);
            selfUrls.add(searchBean.getDoctorArticleQueryUrl());
        }
        if (searchBean.getPlatDynamicQueryUrl() != null) {
            mTopTitles[number++] = news;
            searchNewsFragment = new SearchNewsFragment();
            fragments.add(searchNewsFragment);
            selfUrls.add(searchBean.getPlatDynamicQueryUrl());
        }
        if (searchBean.getCmsEncyclopaedicQueryUrl() != null) {
            mTopTitles[number++] = encyclopedia;
            searchEncyclopediaFragment = new SearchEncyclopediaFragment();
            fragments.add(searchEncyclopediaFragment);
            selfUrls.add(searchBean.getCmsEncyclopaedicQueryUrl());
        }
        if (searchBean.getMedicineLegacyUrl() != null) {
            mTopTitles[number] = sea;
            searchSeaFragment = new SearchSeaFragment();
            fragments.add(searchSeaFragment);
            selfUrls.add(searchBean.getMedicineLegacyUrl());
        }

        addPageFragmentBindNewData(fragments, mTopTitles, selfUrls);
        getSearchView().setTagFlowLayoutVisible(true);
    }


    @Override
    public void onSearch(String search) {
        getSearchView().addData(search);
        queryKeyword(search);
        onTabSelect(getCurrentTopTab());
    }

    private void queryKeyword(String search) {
        strSearchKey = "?query=" + search;
        saveKeywordToRealm(search);
    }

    private void saveKeywordToRealm(String key) {
        SearchKeyObject searchKeyObject = new SearchKeyObject();
        searchKeyObject.setKeyword(key);
        SearchKeyRealm.add(searchKeyObject);
    }

    private void saveKeywordToView() {
        CustomSearchView mySearchView = getSearchView();
        mySearchView.setSearchViewListener(this);
        RealmResults<SearchKeyObject> results = SearchKeyRealm.get();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            list.add(results.get(i).getKeyword());
        }

        mySearchView.setTagViewData(list);
    }

    @Override
    public void onTabSelect(int position) {
        getHttpData(position);
    }

    private void getHttpData(int position) {
        switch (mTopTitles[position]) {
            case guide:
                doctorSearchGuideFragment.getHttpData(strSearchKey);
                break;
            case mien:
                searchMienFragment.getHttpData(strSearchKey);
                searchMienFragment.setVisibility(BaseView.Visibility.LOADING);
                break;
            case moment:
                searchMomentFragment.getHttpData(strSearchKey);
                break;
            case news:
                searchNewsFragment.getHttpData(strSearchKey);
                searchNewsFragment.setVisibility(BaseView.Visibility.ERROR);
                break;
            case encyclopedia:
                searchEncyclopediaFragment.getHttpData(strSearchKey);
                break;
            case sea:
                searchSeaFragment.getHttpData(strSearchKey);
                break;
        }
    }


}
