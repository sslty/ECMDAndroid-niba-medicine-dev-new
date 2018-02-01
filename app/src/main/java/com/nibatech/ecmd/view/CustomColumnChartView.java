package com.nibatech.ecmd.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.utils.Constants;
import com.nibatech.ecmd.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;


public class CustomColumnChartView extends LinearLayout {
    private Context context;
    private final ColumnChartView columnChartView;
    private final TextView tvBottom;

    public CustomColumnChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.view_column, this);
        columnChartView = (ColumnChartView) findViewById(R.id.chart_column);
        columnChartView.setFocusable(false);
        columnChartView.setValueSelectionEnabled(false);
        columnChartView.setInteractive(false);

        tvBottom = (TextView) findViewById(R.id.tv_bottom);
    }

    public void setData(Map<String, Object> map) {
        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> values = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getKey().equals(Constants.REMARKABLE_RATE)) {
                tvBottom.setText("显愈率：" + entry.getValue() + "%");
                continue;
            }
            values.add(new SubcolumnValue((int) entry.getValue(), context.getResources().getColor((Integer) UIUtils.getColor().get(entry.getKey()))));

        }
        Column column = new Column(values);
        columns.add(column);

        ColumnChartData columnChartData = new ColumnChartData(columns);
        columnChartData.setAxisXBottom(null);
        columnChartData.setAxisYLeft(null);

        columnChartView.setColumnChartData(columnChartData);
    }
}
