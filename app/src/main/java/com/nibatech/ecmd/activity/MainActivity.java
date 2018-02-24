package com.nibatech.ecmd.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.flyco.roundview.RoundTextView;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.activity.homepage.DoctorHomePageActivity;
import com.nibatech.ecmd.activity.homepage.PatientHomePageActivity;
import com.nibatech.ecmd.activity.homepage.TouristHomePageActivity;
import com.nibatech.ecmd.activity.register.password.ForgetPasswordActivity;
import com.nibatech.ecmd.activity.register.register.IdentityActivity;
import com.nibatech.ecmd.application.ECMDApplication;
import com.nibatech.ecmd.common.bean.login.LoginPhoneBean;
import com.nibatech.ecmd.common.preferences.LoginSharedPreferences;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.common.update.UpdateManager;
import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.utils.MyAsycTask;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.utils.Validate;

import org.json.JSONObject;


public class MainActivity extends SlidingTabActivity implements View.OnClickListener {
    private AutoCompleteTextView mEditTxtPhone;
    private EditText mEditTxtPassword;
    private View mProgressView;
    private Button mBtnForgot;
    private Button mBtnRegister;
    private RoundTextView rtvLogin;
    private RoundTextView rtvTourist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBackOnToolbar();
        // 注意此处不能传入getApplicationContext();会报错，因为只有是一个Activity才可以添加窗体
        UpdateManager mUpdateManger = new UpdateManager(MainActivity.this);
        if (mUpdateManger.checkUpdateInfo()) {
            mUpdateManger.needToUpdate();
        } else {
            new MyAsycTask() {

                private LoginPhoneBean loginBean;

                @Override
                public void preTask() {
                    setContentView(R.layout.activity_splash);
                }

                @Override
                public void afterTask() {
                    if (loginBean != null) {
                        loginOnBackground(loginBean);
                    } else {
                        //设置界面
                        setContentView(R.layout.activity_main);
                        //初始化所有控件
                        initView();
                        //设置控件事件
                        initViewListener();
                        //设置控件数据
                        setViewData();
                    }
                }

                @Override
                public void doInBack() {
                    initData();//初始化信息
                    loginBean = LoginSharedPreferences.get(MainActivity.this);
                }
            }.execute();
        }
    }

    private void initView() {
        //登录等待控件
        mProgressView = findViewById(R.id.id_progress_bar_login);
        //手机输入框
        mEditTxtPhone = (AutoCompleteTextView) findViewById(R.id.id_et_input_phone);
        //密码输入框
        mEditTxtPassword = (EditText) findViewById(R.id.id_et_input_password);
        //注册
        mBtnRegister = (Button) findViewById(R.id.id_btn_register);
        //忘记密码
        mBtnForgot = (Button) findViewById(R.id.id_txt_forget_pwd);
        //登录
        rtvLogin = (RoundTextView) findViewById(R.id.rtv_login);
        //随便逛逛
        rtvTourist = (RoundTextView) findViewById(R.id.rtv_tourist);
        //输入法隐藏
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void initViewListener() {
        //注册
        mBtnRegister.setOnClickListener(this);
        //忘记密码
        mBtnForgot.setOnClickListener(this);
        //登录
        rtvLogin.setOnClickListener(this);
        //随便逛逛
        rtvTourist.setOnClickListener(this);
    }

    private void setViewData() {
        String strPhone = getIntent().getStringExtra(ExtraPass.PHONE);
        if (strPhone != null) {
            mEditTxtPhone.setText(strPhone);
            mEditTxtPassword.setFocusable(true);
            mEditTxtPassword.setFocusableInTouchMode(true);
            mEditTxtPassword.requestFocus();
        }
    }

    //尝试登录
    private boolean isLoginValid() {
        // Reset errors.
        mEditTxtPhone.setError(null);
        mEditTxtPassword.setError(null);
        // Store values at the time of the login attempt.
        String phone = mEditTxtPhone.getText().toString();
        String password = mEditTxtPassword.getText().toString();
        boolean ok = true;
        View focusView = null;
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !Validate.isPasswordValid(password)) {
            mEditTxtPassword.setError(getString(R.string.error_invalid_password));
            focusView = mEditTxtPassword;
            ok = false;
        }
        // Check for a valid phone address.
        if (TextUtils.isEmpty(phone)) {
            mEditTxtPhone.setError(getString(R.string.error_field_required));
            focusView = mEditTxtPhone;
            ok = false;
        } else if (!Validate.isMobileNOValid(phone)) {
            mEditTxtPhone.setError(getString(R.string.error_invalid_phone_cd));
            focusView = mEditTxtPhone;
            ok = false;
        }
        if (!ok) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }

        return ok;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        int id = view.getId();
        switch (id) {
            case R.id.id_txt_forget_pwd://忘记密码
                mEditTxtPassword.getText().clear();
                intent = new Intent(MainActivity.this, ForgetPasswordActivity.class);
                break;
            case R.id.rtv_login://登录
//                if (isLoginValid()) {
//                    login(mEditTxtPhone.getText().toString(), mEditTxtPassword.getText().toString());
//                }
                login("111", "111");
                break;
            case R.id.id_btn_register://注册
                intent = new Intent(MainActivity.this, IdentityActivity.class);
                break;
            case R.id.rtv_tourist://随便逛逛
                intent = new Intent(MainActivity.this, TouristHomePageActivity.class);
                finish();
                break;
            default:
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }

    private void initData() {
        //清空所有activity
        ECMDApplication.ExitApp.getInstance().exitAllActivity();
        ECMDApplication.ExitApp.getInstance().addOneActivity(MainActivity.this);
    }

    private void login(final String phone, final String password) {
//        showProgress(true);
//        CommonRequest.login(phone, password, new VolleyCallback() {
//            @Override
//            public void onSuccessResponse(JSONObject success) {
//                showProgress(false);
//                //登录
//                UIUtils.login(getApplicationContext(), success.toString(), phone, password);
//                //homepage
//                startHomePage();
//            }
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                showProgress(false);
//            }
//        });

        UIUtils.login(getApplicationContext(), null, phone, password);
        //homepage
        startHomePage();
    }

    //进入首页
    private void startHomePage() {
        Intent intent = new Intent();
//        switch (BaseVolleyRequest.getIdentity()) {
//            case BaseVolleyRequest.IDENTITY_DOCTOR:
//                intent.setClass(getApplicationContext(), DoctorHomePageActivity.class);
//                break;
//            case BaseVolleyRequest.IDENTITY_PATIENT:
//                intent.setClass(getApplicationContext(), PatientHomePageActivity.class);
//                break;
//            case BaseVolleyRequest.IDENTITY_TOURIST:
//                intent.setClass(getApplicationContext(), TouristHomePageActivity.class);
//                break;
//        }
        intent.setClass(getApplicationContext(), DoctorHomePageActivity.class);
        startActivity(intent);
        finish();
    }

    private void loginOnBackground(final LoginPhoneBean loginBean) {
        CommonRequest.login(loginBean.getPhone(), loginBean.getPassword(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                //登录
                UIUtils.login(getApplicationContext(), success.toString(), loginBean.getPhone(),
                        loginBean.getPassword());
                //homepage
                startHomePage();
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                UIUtils.connectToHostShowFail(getApplicationContext());
                finish();

                //清除用户配置信息
                LoginSharedPreferences.remove(getApplicationContext());
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}