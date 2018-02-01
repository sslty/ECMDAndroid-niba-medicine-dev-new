package com.nibatech.ecmd.fragment.register.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nibatech.ecmd.activity.homepage.DoctorHomePageActivity;


/**
 * 医生端   注册-新建资料
 */
public class CreateDoctorFragment extends ProfileFragment {

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
        gotoNextActivity();
    }

    //保存资料
    protected void onClickRightButton() {
        if (attemptCheckError()) {
            requestSaveProfile();
        }
    }

    //进入首页
    protected void gotoNextActivity() {
        //进入首页
        startActivity(new Intent(getActivity(), DoctorHomePageActivity.class));
        //关闭当前页面
        getActivity().finish();
    }
}