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
import com.nibatech.ecmd.common.bean.mine.MineFollowerDoctorBean;
import com.nibatech.ecmd.common.broadcast.BroadCast;
import com.nibatech.ecmd.common.broadcast.BroadCastCallBack;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.BaseCustomRecycleListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 医生端   我的-关注我的医生
 */
public class FollowerDoctorListFragment extends FollowerFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_follower, container, false);
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
        broadCast = new BroadCast(getActivity(), BroadCast.REFRESH_MINE_FOLLOWER_DOCTOR,
                new BroadCastCallBack() {
                    @Override
                    public void onRefresh(String action) {
                        if (action.equals(BroadCast.REFRESH_MINE_FOLLOWER_DOCTOR)) {
                            getHttpData();
                        }
                    }
                });
    }

    protected List<Map<String, Object>> getJsonToMap(String json) {
        MineFollowerDoctorBean mineFollowerDoctorBean = UIUtils.fromJson(json, MineFollowerDoctorBean.class);

        return setDataToList(mineFollowerDoctorBean.getFollowers());
    }

    private List<Map<String, Object>> setDataToList(List<MineFollowerDoctorBean.Follower> followers) {
        List<Map<String, Object>> list = new ArrayList<>();

        for (int i = 0; i < followers.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            DoctorProfileBean doctorProfileBean = followers.get(i).getDoctorProfile();
            setDoctorProfileToMap(doctorProfileBean, map);
            list.add(map);
        }

        return list;
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
                .setIntentData((String) adapterList.get(position).get(DataKey.KEY_HOMEPAGE), BroadCast.REFRESH_MINE_FOLLOWER_DOCTOR)
                .showVIP(true);
    }
}

