package com.nibatech.ecmd.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.utils.UIUtils;


public class AutoProgressLayout extends LinearLayout {

    private RelativeLayout rlProgress;
    private int barMaxWidth;
    private TextView tvTitle;
    private TextView tvNum;
    private TextView tvProcess;

    public AutoProgressLayout(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.view_progress_bar, this);
        setUpView();

    }

    public AutoProgressLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_progress_bar, this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AutoProgressLayout);
        try {
            barMaxWidth = (int) ta.getDimension(R.styleable.AutoProgressLayout_barMaxLength, UIUtils.dip2px(300));
            setUpView();
        } finally {
            ta.recycle();
        }
    }

    private void setUpView() {
        rlProgress = (RelativeLayout) findViewById(R.id.rl_progress);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvNum = (TextView) findViewById(R.id.tv_num);
        tvProcess = (TextView) findViewById(R.id.tv_process);
    }

    public void setProgress(int progress) {
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) rlProgress.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
        linearParams.width = (int) (barMaxWidth * ((float) progress / 100));
        rlProgress.setLayoutParams(linearParams);
        tvProcess.setTextColor(Color.BLACK);
        tvProcess.setText(progress + "%");
    }

    public void setTitle(String title) {
        tvTitle.setTextColor(Color.BLACK);
        tvTitle.setText(title);
    }

    public void setJoinNum(int num) {
        tvNum.setTextColor(Color.BLACK);
        tvNum.setText(num + "人");
    }

    public void setProgressColor(int color) {
        rlProgress.setBackgroundColor(color);
    }

    public void setProgressBarMaxWidth(int maxWidth){
        this.barMaxWidth = maxWidth;
    }

    public void setProgressBarHeight(int height){
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearParams.height = height;
        setLayoutParams(linearParams);
    }
}

