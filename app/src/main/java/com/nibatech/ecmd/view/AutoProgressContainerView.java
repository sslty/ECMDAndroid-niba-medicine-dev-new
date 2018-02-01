package com.nibatech.ecmd.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.utils.UIUtils;


public class AutoProgressContainerView extends LinearLayout {
    private int barMaxWidth;
    private int barHeight;
    private final LinearLayout llProgressBarContainer;

    public AutoProgressContainerView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_progress_container, this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoProgressContainerView, 0, 0);
        try {
            barMaxWidth = (int) typedArray.getDimension(R.styleable.AutoProgressContainerView_barMaxLength, UIUtils.dip2px(300));
            barHeight = (int) typedArray.getDimension(R.styleable.AutoProgressContainerView_barHeight, UIUtils.dip2px(30));
            llProgressBarContainer = (LinearLayout) findViewById(R.id.ll_progress_bar_container);
        } finally {
            typedArray.recycle();
        }

    }

    public void addProgressBar(String title, int progress, int personNum, int color) {
        AutoProgressLayout autoProgressLayout = setDefaultProgressBar(title, progress, personNum);
        autoProgressLayout.setProgressColor(color);
        llProgressBarContainer.addView(autoProgressLayout);
    }

    public void addProgressBar(String title, int progress, int personNum) {
        AutoProgressLayout autoProgressLayout = setDefaultProgressBar(title, progress, personNum);
        llProgressBarContainer.addView(autoProgressLayout);
    }

    private AutoProgressLayout setDefaultProgressBar(String title, int progress, int personNum) {
        AutoProgressLayout autoProgressLayout = new AutoProgressLayout(UIUtils.getContext());
        autoProgressLayout.setProgressBarMaxWidth(barMaxWidth);
        autoProgressLayout.setProgressBarHeight(barHeight);
        autoProgressLayout.setTitle(title);
        autoProgressLayout.setProgress(progress);
        autoProgressLayout.setJoinNum(personNum);
        return autoProgressLayout;
    }

}
