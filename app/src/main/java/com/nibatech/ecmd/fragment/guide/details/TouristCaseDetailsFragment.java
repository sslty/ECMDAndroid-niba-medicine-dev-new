package com.nibatech.ecmd.fragment.guide.details;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 游客端   guide-挂单区／进行中-病例详情-子类
 */
public class TouristCaseDetailsFragment extends GuideDetailsFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    //游客端，不显示医生抢单列表
    protected void getListData() {
        list = new ArrayList<>();
    }
}
