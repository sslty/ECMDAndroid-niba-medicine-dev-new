package com.nibatech.ecmd.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nibatech.ecmd.R;


public class PieChartLegendView extends LinearLayout {


    private final Button btnLegend;
    private final TextView tvLegend;

    public PieChartLegendView(final Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.view_pie_legend, this);
        btnLegend = (Button) findViewById(R.id.btn_legend);
        tvLegend = (TextView) findViewById(R.id.tv_legend);
    }

    public void setLegendBackgroundResource(Integer resId){
        btnLegend.setBackgroundResource(resId);
    }

    public void setLegendText(String text){
        tvLegend.setText(text);
    }

}
