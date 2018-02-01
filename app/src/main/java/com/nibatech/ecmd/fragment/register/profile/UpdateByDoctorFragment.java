package com.nibatech.ecmd.fragment.register.profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.nibatech.ecmd.activity.homepage.PatientHomePageActivity;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;

import org.json.JSONObject;


/**
 * 医生端   医生-发放识别码-补充患者资料
 */
public class UpdateByDoctorFragment extends ProfileFragment {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected void showView() {
        //顶部信息
        setIdentityCodeVisibleInDoctor(true);
        //姓名
        setNameVisible(true);
        //性别
        setGenderVisible(true);
        //年龄
        setAgeVisible(true);
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
            requestUpdateProfileByDoctor();
        }
    }

    //进入首页
    protected void gotoNextActivity() {
        //进入首页
        startActivity(new Intent(getActivity(), PatientHomePageActivity.class));
        //关闭当前页面
        getActivity().finish();
    }

    //向服务器请求保存患者补充资料信息
    private void requestUpdateProfileByDoctor() {
        String strKey = "/profile_by_doctor";
        CommonRequest.putSaveUserProfile(strSelfUrl + strKey, etName.getText().toString(),
                rbMan.isChecked(), etAge.getText().toString(), null, null, null,
                new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject response) {
                        putSaveUserProfileSuccess();
                    }
                });
    }

    //请求补充患者信息资料成功
    private void putSaveUserProfileSuccess() {
        Toast.makeText(getActivity(), "资料补充成功", Toast.LENGTH_SHORT).show();
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }
}