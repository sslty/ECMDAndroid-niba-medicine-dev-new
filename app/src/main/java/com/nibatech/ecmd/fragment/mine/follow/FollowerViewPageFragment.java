package com.nibatech.ecmd.fragment.mine.follow;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.nibatech.ecmd.common.bean.mine.MineFollowerBean;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.fragment.base.BaseFragment;
import com.nibatech.ecmd.utils.UIUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * 医生端   我的-关注我的医生／患者
 */
public class FollowerViewPageFragment extends BaseFragment {
    private String[] mTopTitles = new String[]{
            "医生", "患者"
    };
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragments.add(new FollowerDoctorListFragment());
        fragments.add(new FollowerPatientListFragment());

        getHostUrlData();
    }

    private void getHostUrlData() {
        CommonRequest.getUrlData(getIntentSelfUrl(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getHostUrlDataSuccess(success.toString());
            }
        });
    }

    private void getHostUrlDataSuccess(String json) {
        if (fragments != null && fragments.size() > 0) {
            MineFollowerBean mineFollowedBean = UIUtils.fromJson(json, MineFollowerBean.class);
            //urls
            List<String> urls = new ArrayList<>();
            urls.add(mineFollowedBean.getDoctorFollowersUrl());//关注我的医生
            urls.add(mineFollowedBean.getPatientFollowersUrl());//关注我的患者

            addPageFragmentBindData(fragments, mTopTitles, urls);
        }
    }

}
