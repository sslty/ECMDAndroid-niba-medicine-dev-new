package com.nibatech.ecmd.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nibatech.ecmd.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

public class PullRefreshView extends FrameLayout implements PtrUIHandler {

    private final ImageView ivPullRefresh;
    private final TextView tvPullRefresh;

    public PullRefreshView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.view_pull_refresh, this);
        ivPullRefresh = (ImageView) findViewById(R.id.iv_pull_refresh);
        tvPullRefresh = (TextView) findViewById(R.id.tv_pull_refresh);
    }


    @Override
    public void onUIReset(PtrFrameLayout frame) {
        tvPullRefresh.setText("下拉刷新");
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        tvPullRefresh.setText("下拉刷新");
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        tvPullRefresh.setText("正在刷新");
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        tvPullRefresh.setText("刷新完成");
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        int offsetToRefresh = frame.getOffsetToRefresh();
        int currentPos = ptrIndicator.getCurrentPosY();
        int lastPos = ptrIndicator.getLastPosY();
        if (currentPos < offsetToRefresh && lastPos > offsetToRefresh) {
            tvPullRefresh.setText("正在刷新");
        } else if (currentPos > offsetToRefresh && lastPos < offsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                tvPullRefresh.setText("松开刷新");
            }
        }
    }
}
