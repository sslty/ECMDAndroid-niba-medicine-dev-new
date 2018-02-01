package com.nibatech.ecmd.fragment.register;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.register.ProtocolActivity;
import com.nibatech.ecmd.config.ExtraPass;


/**
 * 医生端/患者端   父类-输入密码
 */
public class InputPasswordFragment extends Fragment implements View.OnClickListener{
    protected EditText editFstPwd, editSecPwd;
    protected CheckBox cbAgree;
    protected TextView tvAgreeProtocol;
    protected LinearLayout llAgreeProtocol;
    protected Button btnOK;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_password, container, false);

        getAllController(view);
        setControllerListener();

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok://确认／下一步
                if (attemptOk()) {
                    onClickOK();
                }
                break;
            case R.id.tv_agree_protocol://注册协议
                gotoProtocol();
                break;
        }
    }

    //获得所有的控件对象
    private void getAllController(View view) {
        //输入密码
        editFstPwd = (EditText) view.findViewById(R.id.et_password_first);
        //再次输入密码
        editSecPwd = (EditText) view.findViewById(R.id.et_password_second);
        //同意
        cbAgree = (CheckBox) view.findViewById(R.id.cb_agree);
        //平台协议
        tvAgreeProtocol = (TextView) view.findViewById(R.id.tv_agree_protocol);
        //是否显示平台协议
        llAgreeProtocol = (LinearLayout) view.findViewById(R.id.ll_agree_protocol);
        //确认／下一步
        btnOK = (Button) view.findViewById(R.id.btn_ok);
    }

    //设置监听事件
    private void setControllerListener() {
        btnOK.setOnClickListener(this);
        tvAgreeProtocol.setOnClickListener(this);
    }

    private boolean attemptOk() {
        boolean ok = false;
        String strFst = editFstPwd.getText().toString();
        String strSec = editSecPwd.getText().toString();

        if (strFst.length() < 6) {
            editFstPwd.setError(getString(R.string.error_max_length));
        } else if (strSec.length() < 6) {
            editSecPwd.setError(getString(R.string.error_max_length));
        } else if (strFst.compareTo(strSec) != 0) {
            editSecPwd.setError(getString(R.string.error_passwords_match));
        } else {
            ok = true;
        }

        return ok;
    }

    protected void onClickOK() {
        //父类为空，直接调用子类的方法
    }

    //跳转注册协议
    private void gotoProtocol() {
        Intent intent = new Intent();
        intent.putExtra(ExtraPass.ID, getActivity().getIntent().getStringExtra(ExtraPass.ID));
        intent.setClass(getActivity(), ProtocolActivity.class);
        startActivity(intent);
    }
}
