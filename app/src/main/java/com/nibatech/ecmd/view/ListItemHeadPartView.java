package com.nibatech.ecmd.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nibatech.ecmd.R;


public class ListItemHeadPartView extends LinearLayout {

    private Activity activity;
    private final TextView tvTop;
    private final HeadView headView;
    private final TextView tvBottom;


    public ListItemHeadPartView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        activity = (Activity) context;
        LayoutInflater.from(context).inflate(R.layout.view_list_item_head_part, this);
        tvTop = (TextView) findViewById(R.id.tv_top);
        headView = (HeadView) findViewById(R.id.head_view);
        tvBottom = (TextView) findViewById(R.id.tv_bottom);
    }

    public HeadView getHeadView(){//用于动画
        return headView;
    }

    public ListItemHeadPartView setTopShow() {
        tvTop.setVisibility(VISIBLE);
        return this;
    }

    public ListItemHeadPartView setBottomShow() {
        tvBottom.setVisibility(VISIBLE);
        return this;
    }


    public ListItemHeadPartView setTopText(String topText) {
        if (topText != null) {
            tvTop.setVisibility(VISIBLE);
            tvTop.setText(topText);
        } else {
            tvTop.setVisibility(GONE);
        }
        return this;
    }

    public ListItemHeadPartView setTopTextBackgroundResource(Integer resid) {
        if (resid != null) {
            tvTop.setBackgroundResource(resid);
        } else {
            tvTop.setBackgroundResource(R.drawable.shape_button_circle_nomal);
        }
        return this;
    }

    public ListItemHeadPartView setHeadViewImage(String imageUrl, String gender) {
        headView.setVisibility(VISIBLE);
        headView.setHeadPhotoAndGender(imageUrl, gender);
        return this;
    }

    public ListItemHeadPartView setHeadViewTip(Integer tip) {
        headView.setVisibility(VISIBLE);
        headView.setTip(tip);
        return this;
    }

    public ListItemHeadPartView setHeadViewSignature(String signature) {
        headView.setVisibility(VISIBLE);
        headView.setSignature(signature);
        return this;
    }

    public ListItemHeadPartView setHeadViewSignature(Integer signature) {
        headView.setVisibility(VISIBLE);
        headView.setSignature(signature);
        return this;
    }

    public ListItemHeadPartView setHeadViewSignatureDrawable(Drawable drawable) {
        headView.setSignatureDrawable(drawable);
        return this;
    }

    public ListItemHeadPartView setBottomText(String bottomText) {
        if (bottomText != null) {
            tvBottom.setVisibility(VISIBLE);
            tvBottom.setText(bottomText);
        } else {
            tvBottom.setVisibility(GONE);
        }
        return this;
    }

    public ListItemHeadPartView setIntentData(String intentData, String entrance) {
        headView.setIntentData(intentData, entrance);
        return this;
    }

}
