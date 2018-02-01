package com.nibatech.ecmd.fragment.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.adapter.MyJoinAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;

import static com.nibatech.ecmd.config.DataKey.KEY_CHART;
import static com.nibatech.ecmd.config.DataKey.KEY_CONTENT;
import static com.nibatech.ecmd.config.DataKey.KEY_DATE;
import static com.nibatech.ecmd.config.DataKey.KEY_NAME;
import static com.nibatech.ecmd.config.DataKey.KEY_TITLE;



public class JoinFragment extends ListFragment {

    private ColumnChartData columnChartData;
    private MyJoinAdapter myJoinAdapter;
    private List<Map<String, Object>> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_join, null);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        myJoinAdapter = MyJoinAdapter.GetInstance(getActivity(), list);
        this.setListAdapter(myJoinAdapter);
    }

    private void getData() {
        list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Map<String, Object> map = new HashMap<>();

            map.put(KEY_NAME, "平台方剂");
            map.put(KEY_TITLE, "感冒颗粒");
            map.put(KEY_DATE, "2016.8.9T");
            map.put(KEY_CONTENT, "顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶挨个方法及国防军");
            getColumnChartData();
            map.put(KEY_CHART, columnChartData);
            list.add(map);
        }
    }

    private void getColumnChartData() {
        int numColumns = 4;
        int[] colorArray = {Color.GREEN, Color.BLUE, Color.YELLOW, Color.RED};
        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<>();
            values.add(new SubcolumnValue((float) Math.random() * 50f + 5, colorArray[i]));

            Column column = new Column(values);
            columns.add(column);
        }

        columnChartData = new ColumnChartData(columns);
        columnChartData.setAxisXBottom(null);
        columnChartData.setAxisYLeft(null);
    }
}
