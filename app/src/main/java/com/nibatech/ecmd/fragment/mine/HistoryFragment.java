package com.nibatech.ecmd.fragment.mine;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.nibatech.ecmd.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class HistoryFragment extends ListFragment {
    private List<Map<String, Object>> list;

    private static final String KEY_STATE = "state";
    private static final String KEY_IMG = "img";
    private static final String KEY_DATE = "date";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DOCTOR = "doctor";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, null);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),
                getData(),
                R.layout.list_my_history,
                new String[]{
                        KEY_STATE, KEY_IMG, KEY_DATE, KEY_CONTENT, KEY_DOCTOR
                },
                new int[]{
                        R.id.id_txt_state, R.id.id_image_avatar, R.id.id_txt_date, R.id.id_txt_content, R.id.id_txt_doctor
                });
        this.setListAdapter(simpleAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    }

    private List<Map<String, Object>> getData() {
        list = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Map<String, Object> map = new HashMap<>();

            map.put(KEY_STATE, "复诊");
            map.put(KEY_IMG, R.drawable.man);
            map.put(KEY_DATE, getString(R.string.rx_date));
            map.put(KEY_CONTENT, getString(R.string.case_describe));
            map.put(KEY_DOCTOR, "王大志");
            list.add(map);
        }

        return list;
    }
}
