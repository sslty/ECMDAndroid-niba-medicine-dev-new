package com.nibatech.ecmd.fragment.guide.base;


import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.guide.details.DoctorCaseDetailsActivity;
import com.nibatech.ecmd.common.broadcast.BroadCast;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.fragment.base.RecycleListViewFragment;
import com.nibatech.ecmd.fragment.floatactionbutton.guide.PatientSubmitOrderFragment;
import com.nibatech.ecmd.view.BaseCustomRecycleListView;
import com.nibatech.ecmd.view.UniformCustomListView;

import java.util.List;
import java.util.Map;


/**
 * 医生端／患者端   中医指导-挂单区／求高手／进行中-父类
 */
public class BaseGuideFragment extends RecycleListViewFragment implements
        RecycleListViewFragment.ShowRecycleListViewDataListener {
    public static final int GUIDE_PATIENT_ORDER_MINE = 1;//患者-我的挂单区
    public static final int GUIDE_PATIENT_ORDER_OTHERS = 2;//患者-其他人的挂单区
    public static final int GUIDE_PATIENT_ONGOING = 3;//患者进行中
    public static final int GUIDE_DOCTOR_ORDER = 4;//医生挂单区
    public static final int GUIDE_DOCTOR_DIFFICULT = 5;//医生求高手
    public static final int GUIDE_DOCTOR_ONGOING = 6;//医生进行中
    public static final int GUIDE_TOURIST_ORDER = 7;//游客挂单区
    public static final int GUIDE_TOURIST_ONGOING = 8;//游客进行中
    public static final String STRING_AGAIN_ORDER = "再挂一次";
    public static final String STRING_PRICE_RANGE = "价格区间: %1$s-%2$s元";
    public static final String STRING_DOCTOR_JOINED = "%s 位医生已经参与";
    public static final String STRING_COUNT_TIME = "倒计时: %s";
    public static final String STRING_DOCTOR_NAME = "%s 医生";
    public static final String STRING_TREATMENT_DAYS = "中医指导第 %s天";
    public static final String STRING_DIRECTION_ORDER = "%s  定向挂单";
    public static final String STRING_PUBLISH_DAYS = "已发布： %s天";
    private String strNextUrl;
    protected BroadCast broadCast;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PatientSubmitOrderFragment.ACTIVITY_RESULT_SUBMIT_ORDER//患者挂单成功
                    || requestCode == DoctorCaseDetailsActivity.ACTIVITY_RESULT_TAKE_ORDER) {//医生接单成功
                //重新刷新界面
                getHttpData();
            }
        }
    }

    protected void initView(View view) {
        initRecycleListView((UniformCustomListView) view.findViewById(R.id.uniform_recycle_view), this);
    }

    protected void unRegisterBroadCast() {
        if (broadCast != null) {
            broadCast.unRegister();
        }
    }

    @Override
    protected void getHttpData() {
        //父类，子类实现
    }

    protected void onButtonClick() {
        //父类，子类实现
    }

    protected void onListItemClick(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        //父类，子类实现
    }

    @Override
    public void setNextUrl(String url) {
        this.strNextUrl = url;
    }

    @Override
    public String getNextUrl() {
        return strNextUrl;
    }

    @Override
    public void clickViewItem(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        onListItemClick(viewHolder, adapterList, position);
    }

    @Override
    public void showViewItem(final BaseCustomRecycleListView.ViewHolder viewHolder, final List<Map<String, Object>> adapterList, final int position) {
        viewHolder.getListItemView(R.id.list_item_view)
                .setHeadViewImageAndGender((String) adapterList.get(position).get(DataKey.KEY_AVATAR),
                        (String) adapterList.get(position).get(DataKey.KEY_GENDER))
                .setHeadPartTopText((String) adapterList.get(position).get(DataKey.KEY_STATE))
                .setContentPartTopLeftText((String) adapterList.get(position).get(DataKey.KEY_CREATE_TIME))
                .setContentPartTopRightText((String) adapterList.get(position).get(DataKey.KEY_UPDATE_TIME))
                .setContentPartMiddleLeftText((String) adapterList.get(position).get(DataKey.KEY_CONTENT))
                .setContentPartBottomLeftText((String) adapterList.get(position).get(DataKey.KEY_DOCTOR_COUNT))
                .setContentPartBottomRightText((String) adapterList.get(position).get(DataKey.KEY_MAX_PRICE))
                .setHeadPartBottomText((String) adapterList.get(position).get(DataKey.KEY_NAME))
                .setContentPartTopRightBackgroundResource(null)
                .setContentPartTopRightOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onListItemClick(viewHolder, adapterList, position);
                    }
                });

        //患者端，如果挂单超过24小时，需要再挂一次
        String strCountDown = (String) adapterList.get(position).get(DataKey.KEY_UPDATE_TIME);
        if (strCountDown != null && strCountDown.compareTo(STRING_AGAIN_ORDER) == 0) {//再挂一次
            viewHolder.getListItemView(R.id.list_item_view).setContentPartTopRightBackgroundResource(R.drawable.shape_button_circle_nomal)//绿色button
                    .setContentPartTopRightOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String strAgain = (String) adapterList.get(position).get(DataKey.KEY_UPDATE_TIME);
                            if (strAgain.compareTo(STRING_AGAIN_ORDER) == 0) {//再挂一次
                                onButtonClick();
                            }
                        }
                    });
        }
    }

    @Override
    public List<Map<String, Object>> getDataFromJson(String json) {
        //父类，子类实现
        return null;
    }
}
