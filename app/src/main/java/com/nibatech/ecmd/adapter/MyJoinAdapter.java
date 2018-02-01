package com.nibatech.ecmd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.config.DataKey;

import java.util.List;
import java.util.Map;

import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.view.ColumnChartView;

import static com.nibatech.ecmd.config.DataKey.KEY_CONTENT;
import static com.nibatech.ecmd.config.DataKey.KEY_DATE;
import static com.nibatech.ecmd.config.DataKey.KEY_NAME;
import static com.nibatech.ecmd.config.DataKey.KEY_TITLE;


public class MyJoinAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private static List<Map<String, Object>> mList;

    private static MyJoinAdapter mInstance;

    public static MyJoinAdapter GetInstance(Context context, List<Map<String, Object>> list) {
        if (mInstance == null) {
            mInstance = new MyJoinAdapter(context, list);
        }
        mList = list;
        return mInstance;
    }

    private MyJoinAdapter(Context context, List<Map<String, Object>> list) {
        mInflater = LayoutInflater.from(context);
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = mInflater.inflate(R.layout.list_my_join, null);
        // 标题
        TextView tvTitle = (TextView) view.findViewById(R.id.id_txt_title);
        tvTitle.setText((String) mList.get(i).get(KEY_TITLE));
        // 方剂名
        Button btnName = (Button) view.findViewById(R.id.btn_name);
        btnName.setText((String) mList.get(i).get(KEY_NAME));
        // 时间
        TextView tvDate = (TextView) view.findViewById(R.id.id_tv_date);
        String strDate = mList.get(i).get(KEY_DATE).toString();
        tvDate.setText(strDate.substring(0, strDate.indexOf("T")));
        // 详细内容
        TextView tvContent = (TextView) view.findViewById(R.id.id_tv_content);
        tvContent.setText((String) mList.get(i).get(KEY_CONTENT));
        // 图表
        ColumnChartView columnChartView = (ColumnChartView) view.findViewById(R.id.chart_in);
        ColumnChartData data = (ColumnChartData) mList.get(i).get(DataKey.KEY_CHART);
        columnChartView.setColumnChartData(data);
        columnChartView.setFocusable(false);
        columnChartView.setValueSelectionEnabled(false);
        columnChartView.setInteractive(false);

        return view;
    }
}
