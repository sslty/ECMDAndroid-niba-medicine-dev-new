package com.nibatech.ecmd.fragment.mypatient;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.chat.ChatDoctorCustomActivity;
import com.nibatech.ecmd.common.bean.guide.FormattedInfoBean;
import com.nibatech.ecmd.common.bean.guide.GuideOrderListBean;
import com.nibatech.ecmd.common.bean.guide.OnlineTreatmentBean;
import com.nibatech.ecmd.common.message.ChatConversation;
import com.nibatech.ecmd.common.message.MessageEvent;
import com.nibatech.ecmd.common.request.GuideRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.fragment.base.BaseFragment;
import com.nibatech.ecmd.fragment.guide.PatientOngoingFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.BaseCustomRecycleListView;
import com.nibatech.ecmd.view.UniformCustomListView;
import com.tencent.TIMConversation;
import com.tencent.TIMMessage;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import static com.nibatech.ecmd.config.DataKey.KEY_AVATAR;
import static com.nibatech.ecmd.config.DataKey.KEY_GENDER;
import static com.nibatech.ecmd.config.DataKey.KEY_STATE;


public class MyPatientFragment extends BaseFragment implements Observer, UniformCustomListView.CustomListViewListener {

    private ArrayList<Map<String, Object>> list;
    private GuideOrderListBean guideOrderList;

    private ArrayList<String> peerList = new ArrayList<>();
    private ArrayList<Integer> unReadMessageNumList;
    private UniformCustomListView recycleListView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_guide, null);
        //获取控件对象
        getAllController(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //设置控件数据
        setControllerData();
    }

    private void getAllController(View view) {
        recycleListView = (UniformCustomListView) view.findViewById(R.id.uniform_recycle_view);
        recycleListView.setCustomListViewListener(this);
    }

    private void setControllerData() {
        requestPatientList();
    }


    //请求我的中医患者列表
    private void requestPatientList() {
        GuideRequest.getPatientListOnMyPatient(new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getPatientListSuccess(success.toString());
            }
        });
    }

    private void getPatientListSuccess(String success) {
        //把数据存储到list
        setDataToList(success);
        //消息观察者
        MessageEvent.getInstance().addObserver(this);
        recycleListView.refreshData(list);
    }

    private void setDataToList(String data) {
        //数据存储
        guideOrderList = new Gson().fromJson(data, GuideOrderListBean.class);
        List<OnlineTreatmentBean> onlineTreatments = guideOrderList.getOnlineTreatments();

        list = new ArrayList<>();
        peerList.clear();
        for (int i = 0; i < onlineTreatments.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            OnlineTreatmentBean treat = onlineTreatments.get(i);
            FormattedInfoBean formattedInfo = treat.getFormattedInfo();

            String mStrAvatar = null;
            String mStrGender = "";
            String mStrAge = "";
            String mStrName = "";
            String mStrCdNumber = null;
            if (treat.getPatient() != null) {
                //头像
                mStrAvatar = treat.getPatient().getAvatarUrl();
                //性别
                mStrGender = treat.getPatient().getGender();
                //年龄
                mStrAge = String.valueOf(treat.getPatient().getAge());
                //名字
                mStrName = treat.getPatient().getFullName();
                //CD
                mStrCdNumber = treat.getPatient().getCdNumber();
            }

            //状态：待复诊，未处理
            String mStrState = PatientOngoingFragment.getStageCount(formattedInfo.getStageCount());
            //更新时间
            String mStrUpdateTime = UIUtils.timeISO8601ConvertToNormal(treat.getUpdatedTime());
            //康复指导第几天
            String mStrDay = UIUtils.timeCountDownOnToday(mStrUpdateTime);
            //描述
            String mStrDescription = UIUtils.getNotNullString(treat.getDescription());
            //中医指导费用
            String mStrPrice = UIUtils.getNotNullInteger(treat.getExpense());
            //self-url
            String mStrUrl = treat.getSelfUrl();

            map.put(KEY_AVATAR, mStrAvatar);
            map.put(KEY_GENDER, mStrGender);
            map.put(DataKey.KEY_AGE, mStrAge);
            map.put(KEY_STATE, mStrState);
            map.put(DataKey.KEY_UPDATE_TIME, mStrUpdateTime);
            map.put(DataKey.KEY_DATE, mStrDay);
            map.put(DataKey.KEY_CONTENT, mStrDescription);
            map.put(DataKey.KEY_MAX_PRICE, mStrPrice);
            map.put(DataKey.KEY_URL, mStrUrl);
            map.put(DataKey.KEY_NAME, mStrName);
            map.put(DataKey.KEY_CD_NUMBER, mStrCdNumber);
            list.add(map);
            peerList.add(mStrCdNumber);
        }

        unReadMessageNumList = ChatConversation.getUnReadMessageNumList(peerList);
    }

    @Override
    public void update(Observable observable, Object data) {
        if (data == null) {
            return;
        }
        TIMConversation chatConversation = ((TIMMessage) data).getConversation();
        String peer = chatConversation.getPeer();

        for (int i = 0; i < peerList.size(); i++) {
            if (peer.equals(peerList.get(i))) {
                unReadMessageNumList.set(i, (int) chatConversation.getUnreadMessageNum());
                recycleListView.notifyDataChanged(i);
            }
        }
    }


    @Override
    public void onPullRefresh() {
    }

    @Override
    public void onLoadMore() {
    }

    @Override
    public void onItemClick(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        unReadMessageNumList.set(position, 0);
        recycleListView.notifyDataChanged(position);
        startActivityBindData(ChatDoctorCustomActivity.class, (String) list.get(position).get(DataKey.KEY_URL));
    }

    @Override
    public void onShow(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        viewHolder.getListItemView(R.id.list_item_view)
                .setHeadPartTopText((String) adapterList.get(position).get(KEY_STATE))
                .setHeadViewImageAndGender((String) adapterList.get(position).get(KEY_AVATAR),
                        (String) adapterList.get(position).get(KEY_GENDER))
                .setHeadViewSignature((String) adapterList.get(position).get(DataKey.KEY_AGE))
                .setHeadPartTopTextBackgroundResource(R.drawable.shape_button_circle_nomal)
                .setContentPartTopLeftText((String) adapterList.get(position).get(DataKey.KEY_UPDATE_TIME))
                .setContentPartTopRightText(String.format("中医指导第 %s天",
                        (String) adapterList.get(position).get(DataKey.KEY_DATE)))
                .setContentPartMiddleLeftText((String) adapterList.get(position).get(DataKey.KEY_CONTENT))
                .setContentPartBottomRightText(String.format("中医指导费用: %s元",
                        (String) adapterList.get(position).get(DataKey.KEY_MAX_PRICE)))
                .setHeadViewTip(unReadMessageNumList.get(position));
    }

}