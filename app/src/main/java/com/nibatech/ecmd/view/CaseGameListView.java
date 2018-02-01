package com.nibatech.ecmd.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nibatech.ecmd.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.nibatech.ecmd.R.id.lv_normal_list;


public class CaseGameListView extends LinearLayout {

    private ArrayList<Map<CaseGameEnum, String>> dataList = new ArrayList<>();
    private ListView lvCaseGame;
    private CaseGameAdapter caseGameAdapter;

    private Activity activity;
    private Map<Integer, Drawable> posAndDrawableMap = new HashMap<>();

    public CaseGameListView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        activity = (Activity) context;
        LayoutInflater.from(context).inflate(R.layout.view_normal_list, this);
        lvCaseGame = (ListView) findViewById(lv_normal_list);
        caseGameAdapter = new CaseGameAdapter();
        lvCaseGame.setAdapter(caseGameAdapter);

    }

    public void addData(Map<CaseGameEnum, String> map) {
        ArrayList<Map<CaseGameEnum, String>> list = new ArrayList<>();
        list.add(map);
        addDataList(list);
    }

    public void addDataList(ArrayList<Map<CaseGameEnum, String>> mapList) {
        dataList = mapList;
        caseGameAdapter.notifyDataSetChanged();
    }

    public void setItemBackgroundDrawable(int position, Drawable drawable) {
        posAndDrawableMap.put(position, drawable);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        lvCaseGame.setOnItemClickListener(listener);
    }

    class CaseGameAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = View.inflate(activity, R.layout.list_case_game, null);
            TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
            TextView tvLeft = (TextView) view.findViewById(R.id.tv_left_content);
            TextView tvRight = (TextView) view.findViewById(R.id.tv_right_content);

            tvTitle.setText(dataList.get(position).get(CaseGameEnum.CASE_GAME_ITEM_TITLE));
            tvLeft.setText(dataList.get(position).get(CaseGameEnum.CASE_GAME_ITEM_LEFT_CONTENT));
            tvRight.setText(dataList.get(position).get(CaseGameEnum.CASE_GAME_ITEM_RIGHT_CONTENT));


            if (posAndDrawableMap.size() != 0) {
                Iterator iterator = posAndDrawableMap.entrySet().iterator();
                LinearLayout llBackground = (LinearLayout) view.findViewById(R.id.ll_background);
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    if (position == (int) entry.getKey()) {
                        llBackground.setBackgroundDrawable((Drawable) entry.getValue());
                        break;
                    }
                }
            }

            return view;
        }


    }

    public enum CaseGameEnum {
        CASE_GAME_ITEM_TITLE,
        CASE_GAME_ITEM_LEFT_CONTENT,
        CASE_GAME_ITEM_RIGHT_CONTENT
    }


}
