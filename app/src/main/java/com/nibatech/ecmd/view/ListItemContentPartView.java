package com.nibatech.ecmd.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.utils.UIUtils;


public class ListItemContentPartView extends LinearLayout {

    private Activity activity;
    private final TextView tvTopLeft;
    private final TextView tvTopRight;
    private final TextView tvMiddleLeft;
    private final TextView tvMiddleRight;
    private final TextView tvBottomLeft;
    private final TextView tvBottomRight;
    private final LinearLayout llTop;
    private final LinearLayout llMiddle;
    private final LinearLayout llBottom;
    private final LinearLayout llBottomLeft;
    private final LinearLayout llBottomRight;
    private final LinearLayout llTopRight;


    public ListItemContentPartView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        activity = (Activity) context;
        LayoutInflater.from(context).inflate(R.layout.view_list_item_content_part, this);
        tvTopLeft = (TextView) findViewById(R.id.tv_top_left);
        tvTopRight = (TextView) findViewById(R.id.tv_top_right);
        tvMiddleLeft = (TextView) findViewById(R.id.tv_middle_left);
        tvMiddleRight = (TextView) findViewById(R.id.tv_middle_right);
        tvBottomLeft = (TextView) findViewById(R.id.tv_bottom_left);
        tvBottomRight = (TextView) findViewById(R.id.tv_bottom_right);

        llTop = (LinearLayout) findViewById(R.id.ll_top);
        llMiddle = (LinearLayout) findViewById(R.id.ll_middle);
        llBottom = (LinearLayout) findViewById(R.id.ll_bottom);

        llBottomLeft = (LinearLayout) findViewById(R.id.ll_bottom_left);
        llBottomRight = (LinearLayout) findViewById(R.id.ll_bottom_right);

        llTopRight = (LinearLayout) findViewById(R.id.ll_top_right);
    }

    public ListItemContentPartView setTopShow() {
        llTop.setVisibility(VISIBLE);
        return this;
    }

    public ListItemContentPartView setMiddldeShow() {
        llMiddle.setVisibility(VISIBLE);
        return this;
    }

    public ListItemContentPartView setBottomShow() {
        llBottom.setVisibility(VISIBLE);
        return this;
    }

    public ListItemContentPartView setTopLeftText(String topLeftText) {
        if (topLeftText != null) {
            llTop.setVisibility(VISIBLE);
            tvTopLeft.setVisibility(VISIBLE);
            tvTopLeft.setText(topLeftText);
        } else {
            tvTopLeft.setVisibility(GONE);
        }
        return this;
    }

    public ListItemContentPartView setTopRightText(String topRightText) {
        if (topRightText != null) {
            llTop.setVisibility(VISIBLE);
            llTopRight.setVisibility(VISIBLE);
            tvTopRight.setText(topRightText);
        } else {
            llTopRight.setVisibility(GONE);
        }
        return this;
    }


    public ListItemContentPartView setMiddleLeftText(String middleLeftText) {
        if (middleLeftText != null) {
            llMiddle.setVisibility(VISIBLE);
            tvMiddleLeft.setVisibility(VISIBLE);
            tvMiddleLeft.setText(middleLeftText);
        } else {
            tvMiddleLeft.setVisibility(GONE);
        }
        return this;
    }

    public ListItemContentPartView setMiddleRightText(String middleRightText) {
        if (middleRightText != null) {
            llMiddle.setVisibility(VISIBLE);
            tvMiddleRight.setVisibility(VISIBLE);
            tvMiddleRight.setText(middleRightText);
        } else {
            tvBottomRight.setVisibility(GONE);
        }
        return this;
    }

    public ListItemContentPartView setBottomLeftText(String bottomLeftText) {
        if (bottomLeftText != null) {
            llBottom.setVisibility(VISIBLE);
            llBottomLeft.setVisibility(VISIBLE);
            tvBottomLeft.setText(bottomLeftText);
        } else {
            tvBottomLeft.setVisibility(GONE);
        }
        return this;
    }

    public ListItemContentPartView setBottomRightText(String bottomRightText) {
        if (bottomRightText != null) {
            llBottom.setVisibility(VISIBLE);
            llBottomRight.setVisibility(VISIBLE);
            tvBottomRight.setText(bottomRightText);
        } else {
            tvBottomRight.setVisibility(GONE);
        }
        return this;
    }


    public ListItemContentPartView setBottomLeftDrawable(Drawable drawable) {
        if (drawable != null) {
            tvBottomLeft.setPadding(UIUtils.dip2px(20), UIUtils.dip2px(5), UIUtils.dip2px(20), UIUtils.dip2px(5));
            tvBottomLeft.setTextColor(Color.WHITE);
        }
        tvBottomLeft.setBackground(drawable);
        return this;
    }

    public ListItemContentPartView setBottomLeftDrawable(Drawable drawable, int lrPadding) {
        if (drawable != null) {
            tvBottomLeft.setPadding(UIUtils.dip2px(lrPadding), UIUtils.dip2px(5), UIUtils.dip2px(lrPadding), UIUtils.dip2px(5));
            tvBottomLeft.setTextColor(Color.WHITE);
        }
        tvBottomLeft.setBackground(drawable);
        return this;
    }

    public ListItemContentPartView setBottomRightDrawable(Drawable drawable) {
        if (drawable != null) {
            tvBottomRight.setPadding(UIUtils.dip2px(20), UIUtils.dip2px(5), UIUtils.dip2px(20), UIUtils.dip2px(5));
            tvBottomRight.setTextColor(Color.WHITE);
        }
        tvBottomRight.setBackground(drawable);
        return this;
    }

    public ListItemContentPartView setBottomRightDrawable(Drawable drawable, int lrPadding) {
        if (drawable != null) {
            tvBottomRight.setPadding(UIUtils.dip2px(lrPadding), UIUtils.dip2px(5), UIUtils.dip2px(lrPadding), UIUtils.dip2px(5));
            tvBottomRight.setTextColor(Color.WHITE);
        }
        tvBottomRight.setBackground(drawable);
        return this;
    }

    public ListItemContentPartView setTopRightDrawable(Drawable drawable) {
        if (drawable != null) {
            tvTopRight.setPadding(UIUtils.dip2px(20), UIUtils.dip2px(5), UIUtils.dip2px(20), UIUtils.dip2px(5));
            tvTopRight.setTextColor(Color.WHITE);
        }
        tvTopRight.setBackground(drawable);
        return this;
    }

    public ListItemContentPartView setTopRightBackgroundResource(Integer resid) {
        if (resid != null) {
            tvTopRight.setPadding(UIUtils.dip2px(20), UIUtils.dip2px(5), UIUtils.dip2px(20), UIUtils.dip2px(5));
            tvTopRight.setTextColor(Color.WHITE);
            tvTopRight.setBackgroundResource(resid);
        } else {
            tvTopRight.setPadding(0, 0, 0, 0);
            tvTopRight.setTextColor(Color.BLACK);
            tvTopRight.setBackground(null);
        }
        return this;
    }

    public ListItemContentPartView setTopRightClickListener(OnClickListener listener) {
        tvTopRight.setOnClickListener(listener);
        return this;
    }

}
