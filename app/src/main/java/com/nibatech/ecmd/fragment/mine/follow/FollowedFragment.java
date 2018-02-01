package com.nibatech.ecmd.fragment.mine.follow;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.personal.DoctorPersonalActivity;
import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;
import com.nibatech.ecmd.common.bean.mine.MineFollowedBean;
import com.nibatech.ecmd.common.broadcast.BroadCast;
import com.nibatech.ecmd.common.broadcast.BroadCastCallBack;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.fragment.base.RefreshListViewFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.BaseCustomRecycleListView;
import com.nibatech.ecmd.view.listview.InformationCustomListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 医生端   我的-我的关注
 */
public class FollowedFragment extends RefreshListViewFragment implements
        RefreshListViewFragment.ShowRefreshListViewDataListener {
    private BroadCast broadCast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_followed, container, false);
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
        broadCast = new BroadCast(getActivity(), BroadCast.REFRESH_MINE_FOLLOWED_DOCTOR,
                new BroadCastCallBack() {
                    @Override
                    public void onRefresh(String action) {
                        if (action.equals(BroadCast.REFRESH_MINE_FOLLOWED_DOCTOR)) {
                            getHttpData();
                        }
                    }
                });
    }

    private void unRegisterBroadCast() {
        if (broadCast != null) {
            broadCast.unRegister();
        }
    }

    private void initView(View view) {
        initRefreshListView((InformationCustomListView) view.findViewById(R.id.contact_refresh_view), this);
    }

    @Override
    public void getHttpData() {
        CommonRequest.getUrlData(getIntentSelfUrl(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getRefreshListSuccess(success.toString());
            }
        });
    }

    @Override
    public void clickViewItem(RecyclerView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        startActivityBindData(DoctorPersonalActivity.class, (String) adapterList.get(position).get(DataKey.KEY_HOMEPAGE));
    }

    @Override
    public void showViewItem(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        viewHolder.getHeadItemView(R.id.headItemView)
                .setHeadViewImageAndGender(
                        (String) adapterList.get(position).get(DataKey.KEY_AVATAR),
                        (String) adapterList.get(position).get(DataKey.KEY_GENDER),
                        (String) adapterList.get(position).get(DataKey.KEY_NAME))
                .setHospital((String) adapterList.get(position).get(DataKey.KEY_TYPE))
                .setIntentData((String) adapterList.get(position).get(DataKey.KEY_HOMEPAGE), BroadCast.REFRESH_MINE_FOLLOWED_DOCTOR)
                .showVIP(true);
    }

    @Override
    public List<Map<String, Object>> getDataFromJson(String json) {
        MineFollowedBean mineFollowedBean = UIUtils.fromJson(json, MineFollowedBean.class);

        return setDataToList(mineFollowedBean.getFollowed());
    }

    private List<Map<String, Object>> setDataToList(List<MineFollowedBean.Followed> followers) {
        List<Map<String, Object>> list = new ArrayList<>();

        for (int i = 0; i < followers.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            DoctorProfileBean doctorProfileBean = followers.get(i).getDoctorProfile();
            setDoctorProfileToMap(doctorProfileBean, map);
            list.add(map);
        }

        return list;
    }
}
