package com.nibatech.ecmd.fragment.mydoctor;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.personal.DoctorPersonalActivity;
import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;
import com.nibatech.ecmd.common.bean.mydoctor.FollowedDoctorListBean;
import com.nibatech.ecmd.common.broadcast.BroadCast;
import com.nibatech.ecmd.common.broadcast.BroadCastCallBack;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.fragment.mine.follow.FollowerFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.BaseCustomRecycleListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 患者端   我的医生-关注的
 */
public class FollowedDoctorFragment extends FollowerFragment {

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
        broadCast = new BroadCast(getActivity(), BroadCast.REFRESH_MY_DOCTOR_FOLLOWED,
                new BroadCastCallBack() {
                    @Override
                    public void onRefresh(String action) {
                        if (action.equals(BroadCast.REFRESH_MY_DOCTOR_FOLLOWED)) {
                            getHttpData();
                        }
                    }
                });
    }

    protected List<Map<String, Object>> getJsonToMap(String json) {
        FollowedDoctorListBean followedDoctorListBean = UIUtils.fromJson(json, FollowedDoctorListBean.class);

        return setDataToList(followedDoctorListBean.getDoctors());
    }

    private List<Map<String, Object>> setDataToList(List<FollowedDoctorListBean.DoctorsBean> doctors) {
        List<Map<String, Object>> list = new ArrayList<>();

        for (int i = 0; i < doctors.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            DoctorProfileBean doctorProfile = doctors.get(i).getDoctorProfile();
            setDoctorProfileToMap(doctorProfile, map);
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
                .showVIP(true);
    }
}
