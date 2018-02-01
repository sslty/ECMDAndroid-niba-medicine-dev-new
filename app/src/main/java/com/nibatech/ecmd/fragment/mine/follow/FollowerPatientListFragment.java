package com.nibatech.ecmd.fragment.mine.follow;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.bean.common.PatientProfileBean;
import com.nibatech.ecmd.common.bean.mine.MineFollowerPatientBean;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.BaseCustomRecycleListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 医生端   我的-关注我的患者
 */
public class FollowerPatientListFragment extends FollowerFragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_follower, container, false);
        initView(view);
        return view;
    }

    protected List<Map<String, Object>> getJsonToMap(String json) {
        MineFollowerPatientBean mineFollowerPatientBean = UIUtils.fromJson(json, MineFollowerPatientBean.class);

        return setDataToList(mineFollowerPatientBean.getPatients());
    }

    private List<Map<String, Object>> setDataToList(List<MineFollowerPatientBean.Patient> offlinePatients) {
        List<Map<String, Object>> list = new ArrayList<>();

        for (int i = 0; i < offlinePatients.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            PatientProfileBean patientProfileBean = offlinePatients.get(i).getPatientProfile();
            setPatientProfileToMap(patientProfileBean, map);
            list.add(map);
        }

        return list;
    }

    @Override
    public void clickViewItem(RecyclerView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
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
