package com.nibatech.ecmd.activity.guide;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.nibatech.ecmd.activity.base.BaseActivity;
import com.nibatech.ecmd.common.bean.guide.GuideTouristUrlBean;
import com.nibatech.ecmd.fragment.guide.TouristOngoingFragment;
import com.nibatech.ecmd.fragment.guide.TouristOrderFragment;
import com.nibatech.ecmd.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 游客端   guide-挂单区／进行中
 */
public class TouristGuideActivity extends BaseActivity {

    private String[] mTopTitles = new String[]{
            "挂单区", "进行中"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("中医指导");

        getHostUrlData();
    }

    @Override
    protected void getHostUrlDataSuccess(String json) {
        GuideTouristUrlBean guideTouristUrlBean = UIUtils.fromJson(json, GuideTouristUrlBean.class);
        if (guideTouristUrlBean != null) {
            ArrayList<Fragment> fragments = new ArrayList<>();
            fragments.add(new TouristOrderFragment());
            fragments.add(new TouristOngoingFragment());
            //self-urls
            List<String> selfUrls = new ArrayList<>();
            selfUrls.add(guideTouristUrlBean.getUnacceptedUrl());//挂单区
            selfUrls.add(guideTouristUrlBean.getTreatingUrl());//进行中
            //view-page
            addPageFragmentBindData(fragments, mTopTitles, selfUrls);
        }
    }

}
