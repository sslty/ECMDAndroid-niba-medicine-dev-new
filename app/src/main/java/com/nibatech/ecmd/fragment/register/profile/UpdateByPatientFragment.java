package com.nibatech.ecmd.fragment.register.profile;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;

import org.json.JSONObject;


/**
 * 患者端   识别码-输入识别码-补充自己的资料
 */
public class UpdateByPatientFragment extends ProfileFragment {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected void showView() {
        //识别码
        setIdentityCodeVisibleInPatient(true);
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
            requestUpdateProfileByPatient();
        }
    }

    private void requestUpdateProfileByPatient() {
        //由于后台接口问题，此处城市必须传值，所以填上默认值
        CommonRequest.putSaveUserProfile(strSelfUrl, etName.getText().toString(),
                rbMan.isChecked(), etAge.getText().toString(), null, 287, null, new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject response) {
                        putSaveUserProfileSuccess();
                    }
                });
    }

    private void putSaveUserProfileSuccess() {
        Toast.makeText(getActivity(), "资料补充成功", Toast.LENGTH_SHORT).show();
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }


}