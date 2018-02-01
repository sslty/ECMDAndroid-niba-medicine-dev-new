package com.nibatech.ecmd.fragment.guide;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.chat.ChatPatientCustomActivity;
import com.nibatech.ecmd.activity.guide.details.PatientCaseDetailsActivity;
import com.nibatech.ecmd.activity.guide.pay.PaymentActivity;
import com.nibatech.ecmd.common.bean.guide.FormattedInfoBean;
import com.nibatech.ecmd.common.bean.guide.OnlineTreatmentBean;
import com.nibatech.ecmd.common.broadcast.BroadCast;
import com.nibatech.ecmd.common.broadcast.BroadCastCallBack;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.fragment.guide.base.PatientGuideFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.BaseCustomRecycleListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 患者端   guide-挂单区
 */
public class PatientOngoingFragment extends PatientGuideFragment {

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
        broadCast = new BroadCast(getActivity(), BroadCast.REFRESH_GUIDE_PATIENT_ONGOING,
                new BroadCastCallBack() {
                    @Override
                    public void onRefresh(String action) {
                        if (action.equals(BroadCast.REFRESH_GUIDE_PATIENT_ONGOING)) {
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

    protected List<Map<String, Object>> setListToData(List<OnlineTreatmentBean> treatments, int type) {
        List<Map<String, Object>> list = new ArrayList<>();

        for (int i = 0; i < treatments.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            OnlineTreatmentBean onlineTreatment = treatments.get(i);
            FormattedInfoBean guideFormattedInfo = onlineTreatment.getFormattedInfo();

            //复诊
            String mStrState = getStageCount(guideFormattedInfo.getStageCount());
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
            //烧心，反酸
            String mStrDescription = UIUtils.getNotNullString(onlineTreatment.getDescription());
            //王大治医生
            String mStrDoctor = String.format(STRING_DOCTOR_NAME,
                    onlineTreatment.getDoctorProfile().getFullName());
            //guide 第几天
            String mStrCountDay = String.format(STRING_TREATMENT_DAYS,
                    UIUtils.timeCountDownOnToday(UIUtils.timeISO8601RemoveT(onlineTreatment.getUpdatedTime())));
            //Self-URL
            String mStrUrl = onlineTreatment.getSelfUrl();
            //pay
            boolean isPay = onlineTreatment.getPayInfo().isPayed();
            //pay-url
            String mStrPayUrl = onlineTreatment.getPayInfo().getWeiXinPayUnifiedOrderUrl();
            //query-url
            String mStrQuery = onlineTreatment.getPayInfo().getWeiXinPayQueryOrderUrl();
            //定向挂单 0-没有 1-有定向挂单
            int intOrderType = onlineTreatment.getOrderType();
            if (intOrderType == 1) {
                mStrUpdatedTime = String.format(STRING_DIRECTION_ORDER, mStrUpdatedTime);
            }

            map.put(DataKey.KEY_STATE, mStrState);
            map.put(DataKey.KEY_AVATAR, mStrAvatar);
            map.put(DataKey.KEY_GENDER, mStrGender);
            map.put(DataKey.KEY_CREATE_TIME, mStrUpdatedTime);
            map.put(DataKey.KEY_CONTENT, mStrDescription);
            map.put(DataKey.KEY_DOCTOR_COUNT, mStrDoctor);
            map.put(DataKey.KEY_MAX_PRICE, mStrCountDay);
            map.put(DataKey.KEY_NAME, mStrName);
            map.put(DataKey.KEY_URL, mStrUrl);
            map.put(DataKey.KEY_ENTRANCE, type);
            map.put(DataKey.KEY_PAY, isPay);
            map.put(DataKey.KEY_PAY_URL, mStrPayUrl);
            map.put(DataKey.KEY_QUERY_URL, mStrQuery);

            list.add(map);
        }

        return list;
    }

    public static String getStageCount(int id) {
        String str;

        switch (id) {
            case 0:
                str = "未诊";
                break;
            case 1:
                str = "首诊";
                break;
            default:
                str = "复诊";
        }

        return str;
    }

    @Override
    protected void onListItemClick(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        Class cls;
        String url = (String) adapterList.get(position).get(DataKey.KEY_URL);

        int entrance = (int) adapterList.get(position).get(DataKey.KEY_ENTRANCE);
        if (entrance == PatientGuideFragment.LIST_VIEW_MINE) {
            entrance = GUIDE_PATIENT_ORDER_MINE;//进行中的挂单，如果支付完成进入聊天界面
            cls = ChatPatientCustomActivity.class;

            boolean isPay = (boolean) adapterList.get(position).get(DataKey.KEY_PAY);
            if (!isPay) {//没有支付
                url = (String) adapterList.get(position).get(DataKey.KEY_PAY_URL);
                cls = PaymentActivity.class;
            }
        } else {
            entrance = GUIDE_PATIENT_ORDER_OTHERS;
            cls = PatientCaseDetailsActivity.class;
        }

        //开始界面
        startActivityBindData(cls, url, null, entrance);
    }
}
