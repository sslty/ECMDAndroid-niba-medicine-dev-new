package com.nibatech.ecmd.fragment.guide.details;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.bean.common.ImageNameBean;
import com.nibatech.ecmd.common.bean.guide.GuidePatientCaseDetailsBean;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.fragment.base.BaseFragment;
import com.nibatech.ecmd.fragment.guide.base.BaseGuideFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.AutoGridImageView;
import com.nibatech.ecmd.view.HeadView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 患者端/游客端   guide-挂单区／进行中-病例详情-父类
 */
public class GuideDetailsFragment extends BaseFragment {
    protected ListView listView;
    private TextView mTxtDescription, mTxtSymptom;
    private TextView mTxtJoinNumber, mTxtCountTime;
    protected GuidePatientCaseDetailsBean guidePatientCaseDetailsBean;
    protected List<GuidePatientCaseDetailsBean.Proposal> proposals;
    protected List<Map<String, Object>> list;
    private View headerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide_details, container, false);

        initView(view);
        getHostUrlData();
        setViewListener();

        return view;
    }

    @SuppressLint("InflateParams")
    private void initView(View view) {
        //医生详细列表
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        headerView = layoutInflater.inflate(R.layout.activity_details_list_header, null);
        listView = (ListView) view.findViewById(R.id.id_list_view);
        listView.addHeaderView(headerView, null, false);
        //病情描述
        mTxtDescription = (TextView) headerView.findViewById(R.id.id_txt_description);
        //病理特征
        mTxtSymptom = (TextView) headerView.findViewById(R.id.id_txt_symptom);
        //医生参与数量
        mTxtJoinNumber = (TextView) headerView.findViewById(R.id.id_txt_doctor_join);
        //倒计时
        mTxtCountTime = (TextView) headerView.findViewById(R.id.id_txt_updated_time);
    }

    //向服务器请求数据
    private void getHostUrlData() {
        CommonRequest.getUrlData(getIntentSelfUrl(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getHostUrlDataSuccess(success.toString());
            }
        });
    }

    protected void getHostUrlDataSuccess(String json) {
        guidePatientCaseDetailsBean = new Gson().fromJson(json, GuidePatientCaseDetailsBean.class);
        setViewData();
    }

    private void setViewData() {
        //病情简述
        mTxtDescription.setText(UIUtils.getNotNullString(guidePatientCaseDetailsBean.getDescription()));
        //病理特征
        mTxtSymptom.setText(UIUtils.getNotNullString(guidePatientCaseDetailsBean.getSymptom()));
        //倒计时
        String mStrUpdateTime = UIUtils.timeISO8601RemoveT(guidePatientCaseDetailsBean.getUpdatedTime());
        String mShowTime = UIUtils.timeCountDownIn24(mStrUpdateTime);
        String mStrCountDown = String.format(getString(R.string.countdown), mShowTime);
        mTxtCountTime.setText(mStrCountDown);
        //医生参与数量
        String mStrDoctorJoin = String.format(getString(R.string.already_involved),
                String.valueOf(guidePatientCaseDetailsBean.getProposals().size()));
        mTxtJoinNumber.setText(mStrDoctorJoin);
        //照片列表
        if (guidePatientCaseDetailsBean.getImages().size() != 0) {
            createImageView();
        }

        //医生抢单列表
        getListData();
        //绑定适配器
        MyAdapter myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
    }

    public void createImageView() {
        AutoGridImageView autoGridImageView = (AutoGridImageView) headerView.findViewById(R.id.auto_image_container);
        autoGridImageView.setVisibility(View.VISIBLE);
        ArrayList<String> imageList = new ArrayList<>();
        for (ImageNameBean imageBean : guidePatientCaseDetailsBean.getImages()) {
            String imageUrl = imageBean.getImageUrl();
            imageList.add(imageUrl);
        }

        autoGridImageView.addImages(imageList);
    }

    protected void getListData() {
        //父类，子类实现
    }

    protected void setViewListener() {
        //父类，子类实现
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(getActivity(), R.layout.list_detail_case_guide_order, null);
            //头像
            HeadView headView = (HeadView) view.findViewById(R.id.id_image_avatar);
            headView.setHeadPhotoAndGender((String) list.get(position).get(DataKey.KEY_AVATAR), null);
            //箭头
            TextView tvArrow = (TextView) view.findViewById(R.id.tv_can_click_arrow);
            //费用
            TextView mTxtPrice = (TextView) view.findViewById(R.id.id_txt_price);
            //医生诊断说明
            TextView mTxtExpectation = (TextView) view.findViewById(R.id.id_txt_expectation);

            if (proposals.size() > 0) {
                String mStrExpectation = (String) list.get(position).get(DataKey.KEY_CONTENT);
                mTxtExpectation.setText(mStrExpectation);
                String mStrPrice = String.valueOf(list.get(position).get(DataKey.KEY_MAX_PRICE));
                mTxtPrice.setText(String.format(getString(R.string.price_every_time), mStrPrice));
            }

            //患者端，我的挂单中，显示医生抢单列表; 在他人的挂单中，进行中的，不显示
            if (BaseGuideFragment.GUIDE_PATIENT_ORDER_OTHERS == getIntentIntEntrance() ||
                    BaseGuideFragment.GUIDE_PATIENT_ONGOING == getIntentIntEntrance()) {
                mTxtPrice.setVisibility(View.GONE);
                tvArrow.setVisibility(View.GONE);
            }

            return view;
        }
    }
}
