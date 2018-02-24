package com.nibatech.ecmd.activity.guide;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.nibatech.ecmd.activity.base.BaseActivity;
import com.nibatech.ecmd.common.bean.guide.GuideDoctorUrlBean;
import com.nibatech.ecmd.fragment.guide.DoctorDifficultFragment;
import com.nibatech.ecmd.fragment.guide.DoctorOngoingFragment;
import com.nibatech.ecmd.fragment.guide.DoctorOrderFragment;
import com.nibatech.ecmd.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 医生端   guide-挂单区／求高手／进行中
 */
public class DoctorGuideActivity extends BaseActivity {

    private String[] mTopTitles = new String[]{
            "挂单区", "求高手", "进行中"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("中医指导");

//        getHostUrlData();

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new DoctorOrderFragment());
        fragments.add(new DoctorDifficultFragment());
        fragments.add(new DoctorOngoingFragment());
        List<String> selfUrls = new ArrayList<>();
        selfUrls.add("");
        selfUrls.add("");
        selfUrls.add("");
        //view-page
        addPageFragmentBindData(fragments, mTopTitles, selfUrls);
    }

    protected void getHostUrlDataSuccess(String json) {
        GuideDoctorUrlBean guideDoctorUrlBean = UIUtils.fromJson(json, GuideDoctorUrlBean.class);
        if (guideDoctorUrlBean != null) {
            ArrayList<Fragment> fragments = new ArrayList<>();
            fragments.add(new DoctorOrderFragment());
            fragments.add(new DoctorDifficultFragment());
            fragments.add(new DoctorOngoingFragment());
            //self-urls
            List<String> selfUrls = new ArrayList<>();
            selfUrls.add(guideDoctorUrlBean.getNormalOltUrl());//挂单区
            selfUrls.add(guideDoctorUrlBean.getDifficultOltUrl());//求高手
            selfUrls.add(guideDoctorUrlBean.getTreatingOltUrl());//进行中
            //view-page
            addPageFragmentBindData(fragments, mTopTitles, selfUrls);
        }
    }

}
