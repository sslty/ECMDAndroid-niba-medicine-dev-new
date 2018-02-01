package com.nibatech.ecmd.fragment.guide.details;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.nibatech.ecmd.activity.guide.introduce.GuideIntroduceDoctorActivity;
import com.nibatech.ecmd.common.broadcast.BroadCast;
import com.nibatech.ecmd.common.broadcast.BroadCastCallBack;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.fragment.guide.base.BaseGuideFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * 患者端   guide-挂单区／进行中-病例详情-子类
 */
public class PatientCaseDetailsFragment extends GuideDetailsFragment {
    private BroadCast broadCast;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        registerBroadCast();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unRegisterBroadCast();
    }

    private void registerBroadCast() {
        broadCast = new BroadCast(getActivity(), BroadCast.DESTROY_GUIDE_DETAILS,
                new BroadCastCallBack() {
                    @Override
                    public void onDestroy(String action) {
                        if (action.equals(BroadCast.DESTROY_GUIDE_DETAILS)) {
                            getActivity().finish();
                        }
                    }
                });
    }

    private void unRegisterBroadCast() {
        if (broadCast != null) {
            broadCast.unRegister();
        }
    }

    protected void getListData() {
        //医生抢单列表
        list = new ArrayList<>();
        proposals = guidePatientCaseDetailsBean.getProposals();

        for (int i = 0; i < proposals.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            //头像
            map.put(DataKey.KEY_AVATAR, proposals.get(i).getAvatarUrl());
            //诊断信息
            map.put(DataKey.KEY_CONTENT, proposals.get(i).getExpectation());
            //价格
            map.put(DataKey.KEY_MAX_PRICE, proposals.get(i).getExpense());
            //Url
            map.put(DataKey.KEY_URL, proposals.get(i).getSelfUrl());
            list.add(map);
        }
    }

    protected void setViewListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (getIntentIntEntrance()) {
                    case BaseGuideFragment.GUIDE_PATIENT_ORDER_MINE:
                        startActivityBindData(GuideIntroduceDoctorActivity.class,
                                (String) ((Map<String, Object>) parent.getAdapter().getItem(position)).get(DataKey.KEY_URL));
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
