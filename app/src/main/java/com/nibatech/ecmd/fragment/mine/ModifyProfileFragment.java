package com.nibatech.ecmd.fragment.mine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.mine.ModifyProfileActivity;
import com.nibatech.ecmd.common.bean.login.UserBean;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.ExtraPass;

import org.json.JSONObject;


/**
 * 医生端/患者端   我的-个人资料-修改资料
 */
public class ModifyProfileFragment extends Fragment {
    private String strContent, strKey;
    private EditText etContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modify_profile, container, false);
        getIntentData();
        getAllController(view);
        setControllerListener();
        setControllerData();

        return view;
    }


    private void getIntentData() {
        strContent = getActivity().getIntent().getStringExtra(ExtraPass.EXTRA_INFORMATION);
        strKey = getActivity().getIntent().getStringExtra(ExtraPass.KEY);
    }

    private void getAllController(View view) {
        etContent = (EditText) view.findViewById(R.id.et_content);
    }

    private void setControllerData() {
        //如果是年龄，需要设置输入法可选择状态
        if (strKey.compareTo(BaseVolleyRequest.JSON_KEY_AGE) == 0) {
            etContent.setInputType(InputType.TYPE_CLASS_NUMBER);
        }

        etContent.setFocusable(true);
        etContent.setText(strContent != null ? strContent : "");
        etContent.setSelection(etContent.getText().length());
    }

    //保存个人用户信息
    private void setControllerListener() {
        ImageView imageView = ((ModifyProfileActivity) getActivity()).findSaveButton();
        imageView.setVisibility(View.VISIBLE);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (attemptSave()) {
                    requestSaveProfile();
                }
            }
        });
    }

    private boolean attemptSave() {
        boolean ok = false;
        String strInput = etContent.getText().toString();

        if (strInput.compareTo(strContent) == 0) {//无修改，直接退出
            getActivity().finish();
        } else if (strInput.isEmpty()) {//不能为空
            etContent.setError(getString(R.string.error_field_required));
        } else if (strKey.compareTo(BaseVolleyRequest.JSON_KEY_FULL_NAME) == 0) {
            if (strInput.length() > 8) {
                etContent.setError("姓名不能超过8个字");
            } else {
                ok = true;
            }
        } else if (strKey.compareTo(BaseVolleyRequest.JSON_KEY_GENDER) == 0) {
            if (strInput.compareTo("男") != 0 && strInput.compareTo("女") != 0) {
                etContent.setError("性别错误");
            } else {
                ok = true;
            }
        } else if (strKey.compareTo(BaseVolleyRequest.JSON_KEY_AGE) == 0) {
            if (strInput.length() > 2) {
                etContent.setError(getString(R.string.error_invalid_age));
            } else {
                ok = true;
            }
        } else {
            ok = true;
        }

        return ok;
    }

    private void requestSaveProfile() {
        final UserBean userBean = BaseVolleyRequest.getLogin().getUser();

        String strName = userBean.getFullName();
        String strGender = userBean.getGender();
        String strAge = String.valueOf(userBean.getAge());
        if (strKey.compareTo(BaseVolleyRequest.JSON_KEY_FULL_NAME) == 0) {//修改姓名
            strName = etContent.getText().toString();
        } else if (strKey.compareTo(BaseVolleyRequest.JSON_KEY_GENDER) == 0) {//修改性别
            strGender = etContent.getText().toString();
        } else if (strKey.compareTo(BaseVolleyRequest.JSON_KEY_AGE) == 0) {//修改年龄
            strAge = etContent.getText().toString();
        }

        CommonRequest.putSaveUserProfile(userBean.getSelfUrl(),
                strName,
                strGender.compareTo("男") == 0,
                strAge,
                null, null, null, new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject success) {
                        Toast.makeText(getActivity(), "资料修改成功", Toast.LENGTH_SHORT).show();
                        UserBean user = new Gson().fromJson(success.toString(), UserBean.class);
                        BaseVolleyRequest.setUser(user);
                        getActivity().finish();
                    }
                });
    }

}
