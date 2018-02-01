package com.nibatech.ecmd.fragment.guide;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.guide.PatientGuideActivity;
import com.nibatech.ecmd.activity.guide.details.PatientCaseDetailsActivity;
import com.nibatech.ecmd.common.bean.guide.FormattedInfoBean;
import com.nibatech.ecmd.common.bean.guide.OnlineTreatmentBean;
import com.nibatech.ecmd.common.broadcast.BroadCast;
import com.nibatech.ecmd.common.broadcast.BroadCastCallBack;
import com.nibatech.ecmd.common.dialog.AlertDialogBuilder;
import com.nibatech.ecmd.common.dialog.AlertDialogListener;
import com.nibatech.ecmd.common.request.GuideRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.fragment.guide.base.PatientGuideFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.BaseCustomRecycleListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 患者端   guide-挂单区
 */
public class PatientOrderFragment extends PatientGuideFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        registerBroadCast();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unRegisterBroadCast();
    }

    private void registerBroadCast() {
        broadCast = new BroadCast(getActivity(), BroadCast.REFRESH_GUIDE_PATIENT_ORDER,
                new BroadCastCallBack() {
                    @Override
                    public void onRefresh(String action) {
                        if (action.equals(BroadCast.REFRESH_GUIDE_PATIENT_ORDER)) {
                            getHttpData();
                        }
                    }
                });
    }

    protected void getHttpData() {
        setSuccessCount(0);
        //我的挂单
        requestUrlDataOfMine();
        //其他人的挂单
        requestUrlDataOfOthers();
    }

    protected List<Map<String, Object>> setListToData(List<OnlineTreatmentBean> treatments,
                                                      int type) {
        List<Map<String, Object>> list = new ArrayList<>();

        for (int i = 0; i < treatments.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            OnlineTreatmentBean onlineTreatment = treatments.get(i);
            FormattedInfoBean guideFormattedInfo = onlineTreatment.getFormattedInfo();

            //2016.8.10
            String mStrUpdatedTime = UIUtils.timeISO8601ConvertToNormal(onlineTreatment.getCreatedTime());
            //头像和姓名，只显示本人头像和姓名，其他全部是默认的头像，隐藏姓名
            String mStrAvatar = null;
            String mStrName = null;
            if (PatientGuideFragment.LIST_VIEW_MINE == type) {
                mStrAvatar = onlineTreatment.getPatient().getAvatarUrl();
                mStrName = onlineTreatment.getPatient().getFullName();
            }
            //性别
            String mStrGender = onlineTreatment.getPatient().getGender();
            //倒计时:23小时, 如果超过24小时，变成再挂一次
            String mShowTime = String.format(STRING_COUNT_TIME, UIUtils.timeCountDownIn24(
                    UIUtils.timeISO8601RemoveT(onlineTreatment.getUpdatedTime())));
            if (PatientGuideFragment.LIST_VIEW_MINE == type && mStrUpdatedTime.compareTo("已结束") == 0) {
                mShowTime = STRING_AGAIN_ORDER;//再挂一次
            }
            //烧心，反酸
            String mStrDescription = UIUtils.getNotNullString(onlineTreatment.getDescription());
            //15位医生已经参与
            String mStrDoctorCount = String.format(STRING_DOCTOR_JOINED,
                    String.valueOf(guideFormattedInfo.getDoctorCount()));
            //费用区间：5-10元
            String mStrPrice = String.format(STRING_PRICE_RANGE,
                    String.valueOf(guideFormattedInfo.getMinExpense()),
                    String.valueOf(guideFormattedInfo.getMaxExpense()));
            //URL
            String mStrUrl = onlineTreatment.getSelfUrl();
            //定向挂单 0-没有 1-有定向挂单
            int intOrderType = onlineTreatment.getOrderType();
            if (intOrderType == 1) {
                mStrUpdatedTime = String.format(STRING_DIRECTION_ORDER, mStrUpdatedTime);
            }

            map.put(DataKey.KEY_AVATAR, mStrAvatar);
            map.put(DataKey.KEY_GENDER, mStrGender);
            map.put(DataKey.KEY_CREATE_TIME, mStrUpdatedTime);
            map.put(DataKey.KEY_UPDATE_TIME, mShowTime);
            map.put(DataKey.KEY_CONTENT, mStrDescription);
            map.put(DataKey.KEY_DOCTOR_COUNT, mStrDoctorCount);
            map.put(DataKey.KEY_MAX_PRICE, mStrPrice);
            map.put(DataKey.KEY_NAME, mStrName);
            map.put(DataKey.KEY_URL, mStrUrl);
            map.put(DataKey.KEY_ENTRANCE, type);

            list.add(map);
        }

        return list;
    }

    protected void onButtonClick() {
        AlertDialogBuilder.onCreate(getActivity(),
                "提示",
                "您是否需要重新挂单?",
                "再次挂单",
                "取消挂单",
                new AlertDialogListener() {
                    @Override
                    public void selectPositive() {
                        requestAgainSubmitOrder();
                    }

                    @Override
                    public void selectNegative() {
                        requestCancelOrder();
                    }
                });
    }

    private void requestAgainSubmitOrder() {
        GuideRequest.putAgainOrder(getActivity(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                Toast.makeText(getActivity(), "再次挂单成功", Toast.LENGTH_SHORT).show();
                getHttpData();
            }
        });
    }

    private void requestCancelOrder() {
        GuideRequest.putCancelOrder(getActivity(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                Toast.makeText(getActivity(), "取消订单成功", Toast.LENGTH_SHORT).show();
                ((PatientGuideActivity) getActivity()).setFloatingButtonVisible(true);
                getHttpData();
            }
        });
    }


    @Override
    protected void onListItemClick(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        int entrance = (int) adapterList.get(position).get(DataKey.KEY_ENTRANCE);
        if (PatientGuideFragment.LIST_VIEW_MINE == entrance) {
            entrance = GUIDE_PATIENT_ORDER_MINE;//自己的详情，可以看的医生的价格区间
        } else {
            entrance = GUIDE_PATIENT_ORDER_OTHERS;
        }

        startActivityBindData(PatientCaseDetailsActivity.class,
                (String) adapterList.get(position).get(DataKey.KEY_URL),
                null, entrance);
    }
}
