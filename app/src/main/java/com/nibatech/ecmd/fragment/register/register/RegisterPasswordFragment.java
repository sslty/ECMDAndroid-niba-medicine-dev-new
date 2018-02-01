package com.nibatech.ecmd.fragment.register.register;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.nibatech.ecmd.activity.register.profile.CreateDoctorActivity;
import com.nibatech.ecmd.activity.register.profile.CreatePatientActivity;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.RegisterRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.fragment.register.InputPasswordFragment;
import com.nibatech.ecmd.utils.UIUtils;

import org.json.JSONObject;


/**
 * 医生端/患者端   注册-输入密码
 */
public class RegisterPasswordFragment extends InputPasswordFragment {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setControllerData();
    }

    private void setControllerData() {
        btnOK.setText("下一步");
    }

    protected void onClickOK() {
        if (attemptNext()) {
            requestPostRegister();
        }
    }

    private boolean attemptNext() {
        boolean ok = false;

        if (!cbAgree.isChecked()) {
            Toast.makeText(getActivity(), "您是否已经同意中医世家平台注册协议", Toast.LENGTH_SHORT).show();
        } else {
            ok = true;
        }

        return ok;
    }

    //向服务器请求注册
    private void requestPostRegister() {
        String strPhone = getActivity().getIntent().getStringExtra(ExtraPass.PHONE);
        String strCode = getActivity().getIntent().getStringExtra(ExtraPass.CODE);
        String strPassword = editSecPwd.getText().toString();

        RegisterRequest.register(strPhone, strPassword,
                getActivity().getIntent().getStringExtra(ExtraPass.ID),
                strCode, new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject success) {
                        postRegisterSuccess();
                    }
                });
    }

    //注册成功后，后台静默登录账号
    private void postRegisterSuccess() {
        CommonRequest.login(getActivity().getIntent().getStringExtra(ExtraPass.PHONE),
                editSecPwd.getText().toString(), new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject success) {
                        postLoginSuccess(success.toString());
                    }
                });
    }

    private void postLoginSuccess(String json) {
        UIUtils.login(getActivity(), json,
                getActivity().getIntent().getStringExtra(ExtraPass.PHONE),
                editSecPwd.getText().toString());
        gotoNextActivity();
    }

    //进入补充资料界面
    private void gotoNextActivity() {
        Intent intent = new Intent();
        String id = getActivity().getIntent().getStringExtra(ExtraPass.ID);
        Class cls;
        if (id.compareTo(RegisterRequest.JSON_VALUE_DOCTOR) == 0) {
            cls = CreateDoctorActivity.class;//医生
        } else {
            cls = CreatePatientActivity.class;//患者
        }

        Toast.makeText(getActivity(), "您好，您已成功注册“中医世家”会员，欢迎使用！", Toast.LENGTH_SHORT).show();
        intent.setClass(getActivity(), cls);
        intent.putExtra(ExtraPass.PHONE, getActivity().getIntent().getStringExtra(ExtraPass.PHONE));
        intent.putExtra(ExtraPass.PASSWORD, editSecPwd.getText().toString());
        intent.putExtra(ExtraPass.CODE, getActivity().getIntent().getStringExtra(ExtraPass.CODE));
        intent.putExtra(ExtraPass.ID, id);
        startActivity(intent);
    }
}
