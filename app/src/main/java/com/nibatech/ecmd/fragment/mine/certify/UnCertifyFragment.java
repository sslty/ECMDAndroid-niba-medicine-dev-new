package com.nibatech.ecmd.fragment.mine.certify;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.photo.PhotoViewActivity;
import com.nibatech.ecmd.common.bean.common.ImageArrayUrlBean;
import com.nibatech.ecmd.common.bean.mine.CertifyBean;
import com.nibatech.ecmd.common.bean.mine.MineBean;
import com.nibatech.ecmd.common.dialog.AlertDialogBuilder;
import com.nibatech.ecmd.common.dialog.AlertDialogListener;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.MineRequest;
import com.nibatech.ecmd.common.request.base.BaseOKHttpRequest;
import com.nibatech.ecmd.common.request.callback.OKHttpCallback;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.fragment.base.BaseFragment;
import com.nibatech.ecmd.fragment.base.NormalViewFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.AutoGridImageEdit;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 医生端   我的-个人资料-医师证明-未认证
 */
public class UnCertifyFragment extends BaseFragment implements NormalViewFragment.ShowNormalViewDataListener {
    public static final String CERTIFY_HOSPITAL = "医院";
    public static final String CERTIFY_OFFICE = "医馆";
    public static final String CERTIFY_POPULAR = "民间";
    public static final String STRING_PHYSICIAN = "执业医师证";
    public static final String STRING_PROFESSIONAL = "职称证";
    public static final String STRING_CHEST_CARD = "工作证/胸牌";
    public static final String STRING_OTHERS_IDENTITY = "其他";

    private AutoGridImageEdit autoImgEdPhysician, autoImgEdTitle, autoImgEdWork, autoGridImageEdit;
    private RadioButton rbHospital, rbOffice, rbPopular;
    private LinearLayout llTitle, llWork;
    private MineBean mineBean;
    private Button button;
    private String doctorId;
    private EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_un_certify, container, false);
        initView(view);
        setViewListener();
        mineBean = new Gson().fromJson(getIntentSelfUrl(), MineBean.class);
        return view;
    }

    @Override
    public void initView(View view) {
        //执业医师证
        autoImgEdPhysician = (AutoGridImageEdit) view.findViewById(R.id.auto_grid_image_container_physician);
        //职称证
        autoImgEdTitle = (AutoGridImageEdit) view.findViewById(R.id.auto_grid_image_container_title);
        //工作证
        autoImgEdWork = (AutoGridImageEdit) view.findViewById(R.id.auto_grid_image_container_work);
        //其他证明
        autoGridImageEdit = (AutoGridImageEdit) view.findViewById(R.id.auto_grid_image_container);
        //职称证布局
        llTitle = (LinearLayout) view.findViewById(R.id.ll_title);
        //工作证布局
        llWork = (LinearLayout) view.findViewById(R.id.ll_work);
        //提交认证
        button = (Button) view.findViewById(R.id.id_btn_submit);
        //姓名
        editText = (EditText) view.findViewById(R.id.et_input_name);
        //医院医生
        rbHospital = (RadioButton) view.findViewById(R.id.rb_hospital);
        //医馆医生
        rbOffice = (RadioButton) view.findViewById(R.id.rb_office);
        //民间医生
        rbPopular = (RadioButton) view.findViewById(R.id.rb_popular);
        //初始界面
        doctorId = CERTIFY_HOSPITAL;
        //初始焦点在医院医生上
        rbHospital.setChecked(true);
    }

    @Override
    public void setViewListener() {
        //提交认证
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (attemptSubmit()) {
                    requestPostCertify();
                }
            }
        });

        /**
         * 此处用3个radio-button替代radio-group，因为切换button前需要判断
         * 该界面是否有数据，如果有的话，弹出提示框，是否需要放弃？如果放弃的话，
         * 才会选中下一个button
         */
        //医院医生
        rbHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRadioButton(rbHospital);
            }
        });

        //医馆医生
        rbOffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRadioButton(rbOffice);
            }
        });

        //民间医生
        rbPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRadioButton(rbPopular);
            }
        });
    }

    @Override
    public void getHostUrlData() {
        MineBean mineBean = new Gson().fromJson(getIntentSelfUrl(), MineBean.class);
        CommonRequest.getUrlData(mineBean.getExtInfoUrls().getGetExtInfo(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getHostUrlDataSuccess(success.toString());
            }
        });
    }

    @Override
    public void getHostUrlDataSuccess(String json) {
        CertifyBean certifyBean = UIUtils.fromJson(json, CertifyBean.class);
        if (certifyBean != null) {
            setViewData(certifyBean);
        }
    }

    @Override
    public void setViewData(Object object) {
    }

    private boolean isFilledData() {
        boolean ok = false;

        if (autoImgEdPhysician.getImagesUrl().size() != 0 ||
                autoImgEdTitle.getImagesUrl().size() != 0 ||
                autoImgEdWork.getImagesUrl().size() != 0 ||
                autoGridImageEdit.getImagesUrl().size() != 0 ||
                !editText.getText().toString().isEmpty()) {
            ok = true;
        }

        return ok;
    }

    private void onAlertDialogShow(final RadioButton radioButton) {
        radioButton.setChecked(false);
        AlertDialogBuilder.onCreate(getActivity(), "警告", "界面已填数据，是否要放弃", true,
                new AlertDialogListener() {
                    @Override
                    public void selectPositive() {
                        editText.getText().clear();
                        autoImgEdPhysician.removeAllImages();
                        autoImgEdTitle.removeAllImages();
                        autoImgEdWork.removeAllImages();
                        autoGridImageEdit.removeAllImages();
                        onSelectRadioButton(radioButton);
                    }
                });
    }

    private boolean attemptSubmit() {
        boolean ok = false;

        if (editText.getText().toString().isEmpty()) {
            editText.setError(getString(R.string.error_field_required));
        } else if (autoImgEdPhysician.getImagesUrl().size() == 0) {
            Toast.makeText(getActivity(), "请上传执业医师证", Toast.LENGTH_SHORT).show();
        } else if (doctorId.equals(CERTIFY_HOSPITAL)) {
            if (autoImgEdTitle.getImagesUrl().size() == 0) {
                Toast.makeText(getActivity(), "请上传职称证", Toast.LENGTH_SHORT).show();
            } else if (autoImgEdWork.getImagesUrl().size() == 0) {
                Toast.makeText(getActivity(), "请上传工作证／胸牌", Toast.LENGTH_SHORT).show();
            } else {
                ok = true;
            }
        } else {
            ok = true;
        }

        return ok;
    }

    private void requestPostCertify() {
        togetherImagePathList();
        BaseOKHttpRequest.post(getActivity(), mineBean.getExtInfoUrls().getUploadImageUrl(),
                togetherImagePathList(), new OKHttpCallback() {
                    @Override
                    public void onSuccess(String success) {
                        getPostSuccess(success);
                    }
                });
    }

    private ArrayList<String> togetherImagePathList() {
        ArrayList<String> list = new ArrayList<>();
        //第一张照片为执业医师证明
        list.addAll(autoImgEdPhysician.getImagesUrl());
        //第二张照片为职称证
        list.addAll(autoImgEdTitle.getImagesUrl());
        //第三张照片为工作证／胸牌
        list.addAll(autoImgEdWork.getImagesUrl());
        //第四-十张照片为其他证件
        list.addAll(autoGridImageEdit.getImagesUrl());

        return list;
    }

    private void getPostSuccess(String json) {
        MineRequest.postCertify(mineBean.getExtInfoUrls().getSubmitUrl(),
                getJSONArrayList(json), new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject success) {
                        Toast.makeText(getActivity(), "提交成功，请等待数据专员处理", Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                    }
                });
    }

    private JSONArray getJSONArrayList(String json) {
        ImageArrayUrlBean imageArrayUrlBean = new Gson().fromJson(json, ImageArrayUrlBean.class);
        List<Map<String, Object>> list = new ArrayList<>();
        //医院
        list.add(setDescUrlToMap(doctorId, null));
        //姓名
        list.add(setDescUrlToMap(editText.getText().toString(), null));
        //证明照片
        for (int i = 0; i < imageArrayUrlBean.getImagesUrl().size(); i++) {
            String desc;
            if (i == 0) {//执业医师证
                desc = STRING_PHYSICIAN;
            } else if (i == 1) {//职称证
                if (doctorId.equals(CERTIFY_HOSPITAL)) {
                    desc = STRING_PROFESSIONAL;
                } else {//医馆医生不需要职称证
                    desc = STRING_OTHERS_IDENTITY;
                }
            } else if (i == 2) {//工作证/胸牌
                if (doctorId.equals(CERTIFY_HOSPITAL)) {
                    desc = STRING_CHEST_CARD;
                } else {//民间医生不需要工作证/胸牌
                    desc = STRING_OTHERS_IDENTITY;
                }
            } else {//其他
                desc = STRING_OTHERS_IDENTITY;
            }

            list.add(setDescUrlToMap(desc, imageArrayUrlBean.getImagesUrl().get(i)));
        }

        return new JSONArray(list);
    }

    private Map<String, Object> setDescUrlToMap(String desc, String url) {
        Map<String, Object> map = new HashMap<>();
        map.put("desc", desc);
        map.put("image_url", url);

        return map;
    }

    //职称证明和工作证，胸牌
    private void setTitleAndWorkShow(boolean visible) {
        if (visible) {
            llTitle.setVisibility(View.VISIBLE);
            llWork.setVisibility(View.VISIBLE);
        } else {
            llTitle.setVisibility(View.INVISIBLE);
            llWork.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            int viewId = data.getIntExtra(PhotoViewActivity.VIEW_ID, -1);
            switch (requestCode) {
                case PhotoViewActivity.REQUIRE_TYPE_CAMERA://相机
                case PhotoViewActivity.REQUIRE_TYPE_GALLERY://图库
                    ArrayList<String> paths = data.getStringArrayListExtra(PhotoViewActivity.RESULT_IMAGES);
                    addImages(viewId, paths);
                    break;
                case PhotoViewActivity.REQUIRE_TYPE_DELETE_IMAGE://删除照片
                    String imagePath = data.getStringExtra(PhotoViewActivity.RESULT_PATH);
                    removeImage(viewId, imagePath);
                    break;
            }
        }
    }

    private void addImages(int viewId, ArrayList<String> paths) {
        switch (viewId) {
            case R.id.auto_grid_image_container_physician:
                autoImgEdPhysician.addImages(paths);
                break;
            case R.id.auto_grid_image_container_title:
                autoImgEdTitle.addImages(paths);
                break;
            case R.id.auto_grid_image_container_work:
                autoImgEdWork.addImages(paths);
                break;
            case R.id.auto_grid_image_container:
                autoGridImageEdit.addImages(paths);
        }
    }

    private void removeImage(int viewId, String imagePath) {
        switch (viewId) {
            case R.id.auto_grid_image_container_physician:
                autoImgEdPhysician.removeImage(imagePath);
                break;
            case R.id.auto_grid_image_container_title:
                autoImgEdTitle.removeImage(imagePath);
                break;
            case R.id.auto_grid_image_container_work:
                autoImgEdWork.removeImage(imagePath);
                break;
            case R.id.auto_grid_image_container:
                autoGridImageEdit.removeImage(imagePath);
                break;
        }
    }

    private void onSelectRadioButton(RadioButton radioButton) {
        switch (radioButton.getId()) {
            case R.id.rb_hospital://医院医师
                doctorId = CERTIFY_HOSPITAL;
                setTitleAndWorkShow(true);
                break;
            case R.id.rb_office://医馆医生
                doctorId = CERTIFY_OFFICE;
                setTitleAndWorkShow(false);
                break;
            case R.id.rb_popular://民间医生
                doctorId = CERTIFY_POPULAR;
                setTitleAndWorkShow(false);
                break;
        }

        //选中某个button前，需要清除其他button的焦点
        clearRadioButtonGroupChecked();
        radioButton.setChecked(true);
    }

    private void onClickRadioButton(RadioButton radioButton) {
        if (radioButton.getId() != selectedRadioButtonId(doctorId)) {//判断是否点击的是已经选中的button
            if (isFilledData()) {//是否填了数据
                onAlertDialogShow(radioButton);
            } else {//选中button
                onSelectRadioButton(radioButton);
            }
        }
    }

    private void clearRadioButtonGroupChecked() {
        rbHospital.setChecked(false);
        rbOffice.setChecked(false);
        rbPopular.setChecked(false);
    }

    private int selectedRadioButtonId(String desc) {
        int id;
        switch (desc) {
            case CERTIFY_HOSPITAL:
                id = R.id.rb_hospital;
                break;
            case CERTIFY_OFFICE:
                id = R.id.rb_office;
                break;
            case CERTIFY_POPULAR:
                id = R.id.rb_popular;
                break;
            default:
                id = R.id.rb_hospital;
                break;
        }

        return id;
    }

}
