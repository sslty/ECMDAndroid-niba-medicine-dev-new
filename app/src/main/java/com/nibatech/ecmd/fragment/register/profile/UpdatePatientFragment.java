package com.nibatech.ecmd.fragment.register.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nibatech.ecmd.fragment.register.profile.ProfileFragment;


/**
 * 患者端   患者补充资料（患者没有个人资料，会限制很多行为）
 */
public class UpdatePatientFragment extends ProfileFragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected void showView() {
        //姓名
        setNameVisible(true);
        //性别
        setGenderVisible(true);
        //年龄
        setAgeVisible(true);
        //城市
        setCityVisible(true);
        //底部按钮
        setBottomButtonVisible("跳过", "保存");
    }

    //跳过
    protected void onClickLeftButton() {
        getActivity().finish();
    }

    //保存资料
    protected void onClickRightButton() {
        if (attemptCheckError()) {
            requestSaveProfile();
        }
    }

    //关闭当前页面
    protected void gotoNextActivity() {
        getActivity().finish();
    }
}