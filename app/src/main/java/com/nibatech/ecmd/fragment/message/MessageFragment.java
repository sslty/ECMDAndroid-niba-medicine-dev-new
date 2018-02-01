package com.nibatech.ecmd.fragment.message;


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


public class MessageFragment extends ListFragment {
    private static final String KEY_TITLE = "title";
    private static final String KEY_DATE = "date";
    private static final String KEY_CONTENT = "content";

    SimpleAdapter mSimpleAdapter;

    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSimpleAdapter = new SimpleAdapter(
                getActivity(),
                getData(),
                R.layout.list_message,
                new String[]{KEY_TITLE, KEY_DATE, KEY_CONTENT},
                new int[]{R.id.tv_news_title, R.id.tv_news_date, R.id.tv_news_content});

        this.setListAdapter(mSimpleAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<>();

        for (int i = 0; i < 0; i++) { //这里隐藏list,以便以后使用
            Map<String, Object> map = new HashMap<>();

            map.put(KEY_TITLE, "消息标题");
            map.put(KEY_DATE, "5分钟前");
            map.put(KEY_CONTENT, "消息内容消息内容消息内容消息内容消息内容消息");
            list.add(map);
        }

        return list;
    }


}
