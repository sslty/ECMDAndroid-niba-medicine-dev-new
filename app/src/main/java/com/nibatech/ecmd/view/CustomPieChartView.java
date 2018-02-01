package com.nibatech.ecmd.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.utils.Constants;
import com.nibatech.ecmd.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;


public class CustomPieChartView extends LinearLayout {
    private PieChartView pieChartView;
    private LinearLayout llLegend;
    private Context context;

    public CustomPieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.view_pie, this);
        pieChartView = (PieChartView) findViewById(R.id.custom_chart_pie);
        llLegend = (LinearLayout) findViewById(R.id.ll_legend);
    }

    public void setData(Map<String, Object> map) {
        int personTotal = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            personTotal += (int) entry.getValue();
        }
        if (personTotal == 0) {
            return;
        }

        List<SliceValue> values = new ArrayList<>();
        llLegend.removeAllViews();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            int personNum = (int) entry.getValue();
//            if (personNum == 0) {
//                continue;
//            }
            PieChartLegendView legendView = new PieChartLegendView(context);
            legendView.setLegendBackgroundResource((Integer) getColorMap().get(entry.getKey()));
            legendView.setLegendText(entry.getKey() + " " + personNum);
            llLegend.addView(legendView, llLegend.getChildCount() - 1);

            SliceValue sliceValue = new SliceValue(personNum, context.getResources().getColor((Integer) UIUtils.getColor().get(entry.getKey())));
            sliceValue.setLabel(Math.round((((float) personNum / personTotal) * 100)) + "%");
            values.add(sliceValue);

        }

        PieChartData data = new PieChartData(values);
        data.setHasLabels(true);
        data.setValueLabelBackgroundEnabled(false);//数据背景不显示
        data.setSlicesSpacing(0);

        pieChartView.setPieChartData(data);
        pieChartView.setChartRotation(90, false);//似乎此控件有缓存，旋转一下就好了，然后禁止旋转，因为再旋转就会又是缓存
        pieChartView.setChartRotationEnabled(false);
        pieChartView.setVisibility(VISIBLE);
    }

    public static Map<String, Object> getColorMap() {
        Map<String, Object> colorMap = new HashMap<>();
        int[] drawableArray = {
                R.drawable.shape_oval_button_recovery,
                R.drawable.shape_oval_button_remarkable,
                R.drawable.shape_oval_button_valid,
                R.drawable.shape_oval_button_invalid,
                R.drawable.shape_oval_button_processing,
        };

        colorMap.put(Constants.RECOVER, drawableArray[0]);
        colorMap.put(Constants.RECOVERY, drawableArray[0]);
        colorMap.put(Constants.REMARKABLE, drawableArray[1]);
        colorMap.put(Constants.VALID, drawableArray[2]);
        colorMap.put(Constants.INVALID, drawableArray[3]);
        colorMap.put(Constants.PROCESSING, drawableArray[4]);

        return colorMap;
    }
}
