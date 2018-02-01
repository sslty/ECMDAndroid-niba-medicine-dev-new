package com.nibatech.ecmd.fragment.register.password;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.nibatech.ecmd.activity.MainActivity;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.fragment.register.InputPasswordFragment;

import org.json.JSONObject;


/**
 * 医生端/患者端   重新设置密码
 */
public class ModifyPasswordFragment extends InputPasswordFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setControllerData();
    }

    private void setControllerData() {
        //隐藏"我同意"，"注册协议"
        llAgreeProtocol.setVisibility(View.INVISIBLE);
        //button设置为"确定"
        btnOK.setText("确定");
    }

    protected void onClickOK() {
        String strPhone = getActivity().getIntent().getStringExtra(ExtraPass.PHONE);
        String strCode = getActivity().getIntent().getStringExtra(ExtraPass.CODE);
        String strPassword = editSecPwd.getText().toString();

        CommonRequest.putModifyPassword(
                strPhone, strPassword, strCode,
                new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject response) {
                        putModifyPasswordSuccess();
                    }
                });
    }

    //密码修改成功
    private void putModifyPasswordSuccess() {
        Toast.makeText(getActivity(), "密码修改成功", Toast.LENGTH_SHORT).show();
        getActivity().finish();
        Intent intent = new Intent();
        intent.putExtra(ExtraPass.PHONE, getActivity().getIntent().getStringExtra(ExtraPass.PHONE));
        intent.setClass(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}