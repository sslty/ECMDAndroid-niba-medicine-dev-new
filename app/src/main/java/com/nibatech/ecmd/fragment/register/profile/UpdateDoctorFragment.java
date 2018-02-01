package com.nibatech.ecmd.fragment.register.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;


/**
 * 医生端   医生补充资料（医生没有个人资料，会限制很多行为）
 */
public class UpdateDoctorFragment extends ProfileFragment {

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
        //专业
        setSpecialismVisible(true);
        //城市
        setCityVisible(true);
        //医院
        setHospitalVisible(true);
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

    ////关闭当前页面
    protected void gotoNextActivity() {
        getActivity().finish();
    }
}