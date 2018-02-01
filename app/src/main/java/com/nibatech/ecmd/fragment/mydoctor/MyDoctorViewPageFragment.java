package com.nibatech.ecmd.fragment.mydoctor;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.nibatech.ecmd.common.mydoctor.MyDoctorUrlBean;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.fragment.base.BaseFragment;
import com.nibatech.ecmd.utils.UIUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 患者端   我的医生-关注的
 */
public class MyDoctorViewPageFragment extends BaseFragment {

    private String[] mTopTitles = new String[]{
            "联系的", "关注的"
    };
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragments.add(new DoctorChatListFragment());//联系的
        fragments.add(new FollowedDoctorFragment());//关注的
    }

    public void setStrSelfUrl(String url) {
        CommonRequest.getUrlData(url, new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getHostUrlDataSuccess(success.toString());
            }
        });
    }

    private void getHostUrlDataSuccess(String json) {
        if (fragments != null && fragments.size() > 0) {
            MyDoctorUrlBean myDoctorUrlBean = UIUtils.fromJson(json, MyDoctorUrlBean.class);
            //self-urls
            List<String> selfUrls = new ArrayList<>();
            selfUrls.add(myDoctorUrlBean.getContacted());
            selfUrls.add(myDoctorUrlBean.getFollowed());
            //view-page
            addPageFragmentBindData(fragments, mTopTitles, selfUrls);
        }
    }
}
