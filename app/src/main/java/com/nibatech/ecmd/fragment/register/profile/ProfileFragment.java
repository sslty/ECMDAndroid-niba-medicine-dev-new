package com.nibatech.ecmd.fragment.register.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.bean.common.CityBean;
import com.nibatech.ecmd.common.bean.common.ProjectBean;
import com.nibatech.ecmd.common.bean.common.SpecialismBean;
import com.nibatech.ecmd.common.bean.login.UserBean;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.fragment.base.BaseFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.BottomButton;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.nibatech.ecmd.R.id.tv_code_value;


/**
 * 医生端/患者端   补充资料-父类
 */
public class ProfileFragment extends BaseFragment {
    private LinearLayout llTop;
    protected RadioButton rbMan, rbFemale;
    private LinearLayout llName, llGender, llAge;
    private LinearLayout llProjectJoin;
    private LinearLayout llSpecialism, llCity, llHospital, llProject;
    private Spinner spinnerCity, spinnerSpecialism, spinnerProject;
    protected EditText etName, etAge, etHospital;
    private TextView tvProjectName, tvCodeValue;
    protected BottomButton bottomButton;
    private CityBean cityBean;
    private SpecialismBean specialismBean;
    private ProjectBean projectBean;
    protected int itemCity, itemSpecialism;
    protected String itemProject;
    protected String strSelfUrl, strProjectName, strCodeValue, strJson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_proflle, container, false);

        getIntentData();
        initView(view);
        setViewListener();
        showView();
        getHttpData();

        return view;
    }

    private void getIntentData() {
        //url
        strSelfUrl = getIntentSelfUrl();
        //项目名称
        strProjectName = getActivity().getIntent().getStringExtra(ExtraPass.NAME);
        //识别码的值
        strCodeValue = getActivity().getIntent().getStringExtra(ExtraPass.CODE);
        //
        strJson = getActivity().getIntent().getStringExtra(ExtraPass.JSON);
    }

    private void initView(View view) {
        //界面顶部
        llTop = (LinearLayout) view.findViewById(R.id.ll_top);
        //识别码部分
        tvCodeValue = (TextView) view.findViewById(tv_code_value);
        //参与项目
        llProjectJoin = (LinearLayout) view.findViewById(R.id.ll_project_join);
        //参与项目名称
        tvProjectName = (TextView) view.findViewById(R.id.tv_project_name);
        //姓名标签
        llName = (LinearLayout) view.findViewById(R.id.ll_name);
        //姓名
        etName = (EditText) view.findViewById(R.id.et_name);
        //性别标签
        llGender = (LinearLayout) view.findViewById(R.id.ll_gender);
        //男或者女
        rbMan = (RadioButton) view.findViewById(R.id.rb_man);
        rbFemale = (RadioButton) view.findViewById(R.id.rb_female);
        //年龄标签
        llAge = (LinearLayout) view.findViewById(R.id.ll_age);
        //年龄
        etAge = (EditText) view.findViewById(R.id.et_age);
        //专业标签
        llSpecialism = (LinearLayout) view.findViewById(R.id.ll_specialisms);
        //专业
        spinnerSpecialism = (Spinner) view.findViewById(R.id.spinner_specialisms);
        //城市标签
        llCity = (LinearLayout) view.findViewById(R.id.ll_city);
        //城市
        spinnerCity = (Spinner) view.findViewById(R.id.spinner_city);
        //医院所在地址
        llHospital = (LinearLayout) view.findViewById(R.id.ll_hospital);
        etHospital = (EditText) view.findViewById(R.id.et_hospital);
        //项目标签
        llProject = (LinearLayout) view.findViewById(R.id.ll_project);
        spinnerProject = (Spinner) view.findViewById(R.id.spinner_project);
        //返回和保存
        bottomButton = (BottomButton) view.findViewById(R.id.bottom_button);
    }

    private void setViewListener() {
        //底部button，左边，右边的点击监听事件
        bottomButton.setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLeftButton();
            }
        });
        bottomButton.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRightButton();
            }
        });
        //年龄输入限制
        UIUtils.setInputMaxLengthListener(getActivity(), etAge, getString(R.string.error_invalid_age), 2);
    }

    protected void showView() {
        //父类为空，子类实现
    }

    protected void onClickLeftButton() {
        //父类为空，子类实现
    }

    protected void onClickRightButton() {
        //父类为空，子类实现
    }

    protected void gotoNextActivity() {
        //父类为空，子类实现
    }

    private void getHttpData() {
        //城市列表
        if (llCity.getVisibility() == View.VISIBLE) {
            CommonRequest.getCity(new VolleyCallback() {
                @Override
                public void onSuccessResponse(JSONObject success) {
                    getCityListSuccess(success.toString());
                }
            });
        }

        //专业列表
        if (llSpecialism.getVisibility() == View.VISIBLE) {
            CommonRequest.getSpecialism(new VolleyCallback() {
                @Override
                public void onSuccessResponse(JSONObject success) {
                    getSpecialismListSuccess(success.toString());
                }
            });
        }

        //项目列表
        if (llProject.getVisibility() == View.VISIBLE) {
            CommonRequest.getProject(new VolleyCallback() {
                @Override
                public void onSuccessResponse(JSONObject success) {
                    getProjectListSuccess(success.toString());
                }
            });
        }

    }

    private void getCityListSuccess(String success) {
        //建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.spinner_specialism, getCityListData(success));

        //绑定Adapter到控件
        spinnerCity.setAdapter(adapter);
        //添加事件Spinner事件监听
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemCity = cityBean.getCityList().get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private List<String> getCityListData(String cities) {
        cityBean = new Gson().fromJson(cities, CityBean.class);
        List<String> list = new ArrayList<>();

        //获取城市名称
        for (int i = 0; i < cityBean.getCityList().size(); i++) {
            String str = cityBean.getCityList().get(i).getName();
            list.add(i, str);
        }

        return list;
    }

    private void getSpecialismListSuccess(String success) {
        //建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.spinner_specialism, getSpecialismListData(success));

        //绑定Adapter到控件
        spinnerSpecialism.setAdapter(adapter);
        //添加事件Spinner事件监听
        spinnerSpecialism.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemSpecialism = specialismBean.getSpecialismList().get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private List<String> getSpecialismListData(String specialisms) {
        //把专业列表存入全局变量
        specialismBean = new Gson().fromJson(specialisms, SpecialismBean.class);
        List<String> list = new ArrayList<>();

        //获取专业名称
        for (int i = 0; i < specialismBean.getSpecialismList().size(); i++) {
            String str = specialismBean.getSpecialismList().get(i).getName();
            list.add(i, str);
        }

        return list;
    }

    private void getProjectListSuccess(String success) {
        //建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.spinner_specialism, getProjectListData(success));
        //绑定Adapter到控件
        spinnerProject.setAdapter(adapter);
        //添加事件Spinner事件监听
        spinnerProject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemProject = projectBean.getProjects().get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private List<String> getProjectListData(String projects) {
        //把专业列表存入全局变量
        projectBean = new Gson().fromJson(projects, ProjectBean.class);
        List<String> list = new ArrayList<>();

        //获取项目名称
        for (int i = 0; i < projectBean.getProjects().size(); i++) {
            String str = projectBean.getProjects().get(i);
            list.add(i, str);
        }

        return list;
    }


    //顶部信息：出现在补充患者资料界面，界面内会显示参加项目名称和识别码
    protected void setIdentityCodeVisibleInDoctor(boolean visible) {
        if (visible) {
            llTop.setVisibility(View.VISIBLE);

            //如果有识别码，直接显示，如果没有，显示没有识别码
            if (strCodeValue != null) {
                tvCodeValue.setText(strCodeValue);
            } else {
                tvCodeValue.setText("");
            }

            //如果有项目名称，显示，否则，隐藏
            if (strProjectName != null) {
                llProjectJoin.setVisibility(View.VISIBLE);
                tvProjectName.setText(strProjectName);
            }
        } else {
            llTop.setVisibility(View.GONE);
        }
    }

    //患者不显示项目名称
    protected void setIdentityCodeVisibleInPatient(boolean visible) {
        if (visible) {
            llTop.setVisibility(View.VISIBLE);

            //如果有识别码，直接显示，如果没有，显示没有识别码
            if (strCodeValue != null) {
                tvCodeValue.setText(strCodeValue);
            } else {
                tvCodeValue.setText("没有识别码");
            }

            //患者不显示项目名称
            llProjectJoin.setVisibility(View.GONE);
        } else {
            llTop.setVisibility(View.GONE);
        }
    }

    //显示姓名标签和控件
    protected void setNameVisible(boolean visible) {
        if (visible) {
            llName.setVisibility(View.VISIBLE);
        } else {
            llName.setVisibility(View.GONE);
        }
    }

    //显示性别标签和控件
    protected void setGenderVisible(boolean visible) {
        if (visible) {
            llGender.setVisibility(View.VISIBLE);
        } else {
            llGender.setVisibility(View.GONE);
        }
    }

    //显示年龄标签和控件
    protected void setAgeVisible(boolean visible) {
        if (visible) {
            llAge.setVisibility(View.VISIBLE);
        } else {
            llAge.setVisibility(View.GONE);
        }
    }

    //显示专业标签和控件
    protected void setSpecialismVisible(boolean visible) {
        if (visible) {
            llSpecialism.setVisibility(View.VISIBLE);
        } else {
            llSpecialism.setVisibility(View.GONE);
        }
    }

    //显示城市标签和控件
    protected void setCityVisible(boolean visible) {
        if (visible) {
            llCity.setVisibility(View.VISIBLE);
        } else {
            llCity.setVisibility(View.GONE);
        }
    }

    //显示项目标签和控件
    protected void setProjectVisible(boolean visible) {
        if (visible) {
            llProject.setVisibility(View.VISIBLE);
        } else {
            llProject.setVisibility(View.GONE);
        }
    }

    //显示医院标签和控件
    protected void setHospitalVisible(boolean visible) {
        if (visible) {
            llHospital.setVisibility(View.VISIBLE);
        } else {
            llHospital.setVisibility(View.GONE);
        }
    }

    //底部按钮：设置左边按钮和右边按钮的值
    protected void setBottomButtonVisible(String leftText, String rightText) {
        bottomButton.setLeftText(leftText);
        bottomButton.setRightText(rightText);
        bottomButton.setVisibility(View.VISIBLE);
    }

    //验证界面所有内容是否填写完整
    protected boolean attemptCheckError() {
        boolean ok = false;

        if (llName.getVisibility() == View.VISIBLE && etName.getText().toString().isEmpty()) {
            etName.setError(getString(R.string.error_field_required));//名字不能为空
        } else if (llAge.getVisibility() == View.VISIBLE && etAge.getText().toString().isEmpty()) {
            etAge.setError(getString(R.string.error_field_required));//年龄不能为空
        } else if (llHospital.getVisibility() == View.VISIBLE && etHospital.getText().toString().isEmpty()) {
            etHospital.setError(getString(R.string.error_invalid_age));//医院不能为空
        } else {
            ok = true;
        }

        return ok;
    }

    //向服务器请求保存资料
    protected void requestSaveProfile() {
        CommonRequest.putSaveUserProfile(BaseVolleyRequest.getLogin().getUser().getSelfUrl(),
                etName.getText().toString(),
                rbMan.isChecked(),
                etAge.getText().toString(),
                itemSpecialism, itemCity,
                etHospital.getText().toString(),
                new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject response) {
                        putSaveProfileSuccess(response.toString());
                    }
                }
        );
    }

    private void putSaveProfileSuccess(String json) {
        Toast.makeText(getActivity(), "保存成功!", Toast.LENGTH_SHORT).show();
        //保存资料
        saveProfile(json);
        //进入下一页面，子类去实现
        gotoNextActivity();
    }

    //保存资料
    protected void saveProfile(String json) {
        //有资料后就会有cd号，需要把新的用户信息存入数据库
        UserBean user = new Gson().fromJson(json, UserBean.class);
        //存入变量
        BaseVolleyRequest.setUser(user);
        //腾讯登录
        UIUtils.loginTM(user.getCdNumber(), user.getTlsSig());
    }

}