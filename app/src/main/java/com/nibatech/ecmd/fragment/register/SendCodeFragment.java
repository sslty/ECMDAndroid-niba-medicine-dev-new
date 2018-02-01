package com.nibatech.ecmd.fragment.register;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.bean.common.VerifyCodeBean;
import com.nibatech.ecmd.common.helper.CountDownButtonHelper;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.utils.Validate;

import org.json.JSONObject;


/**
 * 医生端/患者端   父类-发送验证码
 */
public class SendCodeFragment extends Fragment implements View.OnClickListener {
    protected EditText editPhone, editCode;
    protected Button btnSend, btnNext;
    protected String strCodeValue;
    private CountDownButtonHelper timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_send_code, container, false);

        getAllController(view);
        setControllerListener();
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send://发送验证码
                sendVerificationCode();
                break;
            case R.id.btn_next://下一步
                if (attemptNext()) {
                    onClickNext();
                }
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        //进入下一个界面，清空当前状态
        editPhone.setText("");
        editCode.setText("");
        //计时器恢复
        if (timer != null) {
            timer.cancel();
            btnSend.setClickable(true);
            btnSend.setBackgroundResource(R.drawable.shape_button_circle_nomal);
        }
    }

    private void getAllController(View view) {
        //手机号码
        editPhone = (EditText) view.findViewById(R.id.et_phone);
        //验证码
        editCode = (EditText) view.findViewById(R.id.et_receive_sms);
        //发送
        btnSend = (Button) view.findViewById(R.id.btn_send);
        //下一步
        btnNext = (Button) view.findViewById(R.id.btn_next);
    }

    private void setControllerListener() {
        btnSend.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    //向服务器发送验证码
    private void sendVerificationCode() {
        //sendSMS();
        if (!Validate.isMobileNOValid(editPhone.getText().toString())) {
            editPhone.setError(getString(R.string.error_invalid_phone));
        } else {
            btnSend.setClickable(false);
            btnSend.setBackgroundResource(R.drawable.shape_button_circle_right_grey);
            timer = new CountDownButtonHelper(btnSend,
                    getString(R.string.send_code), "倒计时: ", 60, 1);
            timer.setOnFinishListener(new CountDownButtonHelper.OnFinishListener() {
                @Override
                public void finish() {
                    btnSend.setClickable(true);
                    btnSend.setBackgroundResource(R.drawable.shape_button_circle_right_green);
                }
            });
            timer.start();
            //服务器请求发送验证码
            requestGetVerificationCode();
        }
    }

    private void sendSMS() {
        String strPhone = editPhone.getText().toString();
        String strCode = "123456";//无发短信功能,暂时使用固定值
        editCode.setText(strCode);

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(
                strPhone,//目的电话号码
                null, //短信中心电话号码为null时,使用系统默认
                "123",//短信内容
                null, //发送状态
                null);//对方接受状态

        Toast.makeText(getActivity(),
                "发送完成", Toast.LENGTH_SHORT).show();
    }

    private void requestGetVerificationCode() {
        CommonRequest.sendCode(editPhone.getText().toString(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject response) {
                getVerificationCodeSuccess(response);//无短信功能,暂时从服务器上取code
            }
        });
    }

    //验证码获取成功
    private void getVerificationCodeSuccess(JSONObject response) {
        Gson gson = new Gson();
        VerifyCodeBean verifyCode = gson.fromJson(response.toString(), VerifyCodeBean.class);
        editCode.setText(verifyCode.getCode());
        strCodeValue = verifyCode.getCode();
    }

    //下一步验证
    private boolean attemptNext() {
        boolean ok = false;

        if (editPhone.getText().toString().isEmpty()) {
            editPhone.setError(getString(R.string.error_field_required));
        } else if (editCode.getText().toString().isEmpty()) {
            editCode.setError(getString(R.string.error_field_required));
        } else {
            ok = true;
        }

        return ok;
    }

    protected void onClickNext() {
        //父类为空，直接调用子类的方法
    }
}
