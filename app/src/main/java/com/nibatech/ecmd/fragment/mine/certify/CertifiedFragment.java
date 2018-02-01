package com.nibatech.ecmd.fragment.mine.certify;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.mine.certify.UnCertifyActivity;
import com.nibatech.ecmd.common.bean.mine.CertifyBean;
import com.nibatech.ecmd.common.bean.mine.MineBean;
import com.nibatech.ecmd.common.dialog.AlertDialogBuilder;
import com.nibatech.ecmd.common.dialog.AlertDialogListener;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.fragment.base.BaseFragment;
import com.nibatech.ecmd.fragment.base.NormalViewFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.AutoGridImageView;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 医生端   我的-个人资料-医师证明-已认证
 */
public class CertifiedFragment extends BaseFragment implements NormalViewFragment.ShowNormalViewDataListener {
    private AutoGridImageView autoImgViewPhysician, autoImgViewTitle, autoImgViewWork, autoGridImageView;
    private LinearLayout llMainView, llTitle, llWork, llOthers;
    private TextView tvClass, tvName;
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_certified, container, false);
        initView(view);
        setViewListener();
        getHostUrlData();
        return view;
    }

    @Override
    public void initView(View view) {
        //认证类型
        tvClass = (TextView) view.findViewById(R.id.tv_class);
        //姓名
        tvName = (TextView) view.findViewById(R.id.tv_name);

        autoImgViewPhysician = (AutoGridImageView) view.findViewById(R.id.auto_grid_image_container_physician);
        autoImgViewTitle = (AutoGridImageView) view.findViewById(R.id.auto_grid_image_container_title);
        autoImgViewWork = (AutoGridImageView) view.findViewById(R.id.auto_grid_image_container_work);
        autoGridImageView = (AutoGridImageView) view.findViewById(R.id.auto_grid_image_container);

        //主界面
        llMainView = (LinearLayout) view.findViewById(R.id.ll_main_view);
        //职称证
        llTitle = (LinearLayout) view.findViewById(R.id.ll_title);
        //工作证／胸牌
        llWork = (LinearLayout) view.findViewById(R.id.ll_work);
        //其他证件
        llOthers = (LinearLayout) view.findViewById(R.id.ll_others);
        //重新认证
        button = (Button) view.findViewById(R.id.btn_re_certify);
    }

    @Override
    public void setViewListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAlertDialogShow();
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
        llMainView.setVisibility(View.VISIBLE);
        CertifyBean certifyBean = (CertifyBean) object;
        ArrayList<String> othersImageUrl = new ArrayList<>();

        for (int i = 0; i < certifyBean.getExtInfo().size(); i++) {
            String desc = certifyBean.getExtInfo().get(i).getDesc();
            String imageUrl = certifyBean.getExtInfo().get(i).getImageUrl();

            if (i == 0) {//认证类型： 医馆医生
                tvClass.setText(String.format("认证类型：%s医生", desc));
            } else if (i == 1) {//名字
                tvName.setText(String.format("真实姓名：%s", desc));
            } else {
                switch (desc) {
                    case UnCertifyFragment.STRING_PHYSICIAN: //执业医师证
                        autoImgViewPhysician.addImage(imageUrl);
                        break;
                    case UnCertifyFragment.STRING_PROFESSIONAL: //职称证
                        autoImgViewTitle.addImage(imageUrl);
                        llTitle.setVisibility(View.VISIBLE);
                        break;
                    case UnCertifyFragment.STRING_CHEST_CARD: //工作证/胸牌
                        autoImgViewWork.addImage(imageUrl);
                        llWork.setVisibility(View.VISIBLE);
                        break;
                    default:
                        othersImageUrl.add(imageUrl);
                        break;
                }
            }
        }

        if (othersImageUrl.size() != 0) {//其他工作证件
            llOthers.setVisibility(View.VISIBLE);
            autoGridImageView.addImages(othersImageUrl);
        }
    }

    private void onAlertDialogShow() {
        AlertDialogBuilder.onCreate(getActivity(), "提示", "您是否需要重新认证您的医生证件信息?", true,
                new AlertDialogListener() {
                    @Override
                    public void selectPositive() {
                        startActivityBindData(UnCertifyActivity.class, getIntentSelfUrl());
                        getActivity().finish();
                    }
                });
    }
}
