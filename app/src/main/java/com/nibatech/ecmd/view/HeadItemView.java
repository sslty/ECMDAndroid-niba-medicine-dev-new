package com.nibatech.ecmd.view;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nibatech.ecmd.R;


public class HeadItemView extends LinearLayout {

    private Activity activity;
    private final ListItemHeadPartView listItemHeadPartView;
    private final LinearLayout llBottom;
    private final TextView tvNameAndGender;
    private final TextView tvHospital;
    private final TextView tvVIP;
    private final LinearLayout llContentPart;
    private String picassoTag;


    public HeadItemView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        activity = (Activity) context;
        LayoutInflater.from(context).inflate(R.layout.view_head_item, this);
        listItemHeadPartView = (ListItemHeadPartView) findViewById(R.id.head_part);
        llContentPart = (LinearLayout) findViewById(R.id.ll_content_part);

        tvNameAndGender = (TextView) findViewById(R.id.tv_name_gender);
        llBottom = (LinearLayout) findViewById(R.id.ll_bottom);
        tvHospital = (TextView) findViewById(R.id.tv_hospital);
        tvVIP = (TextView) findViewById(R.id.tv_VIP);
    }

    public HeadItemView setPicassoTag(String picassoTag) {
        listItemHeadPartView.getHeadView().setPicassoTag(picassoTag);
        return this;
    }

    public HeadItemView setHeadViewImageAndGender(String imageUrl, String gender, String name) {
        String nameAndGender = "";
        if (name != null) {
            nameAndGender += name;
        }
        if (gender != null) {
            nameAndGender += (" " + gender);
        }

        listItemHeadPartView.setHeadViewImage(imageUrl, gender);
        if (!TextUtils.isEmpty(nameAndGender)) {
            tvNameAndGender.setVisibility(VISIBLE);
            tvNameAndGender.setText(nameAndGender);
        } else {
            tvNameAndGender.setVisibility(GONE);
        }
        return this;
    }

    public HeadItemView setHeadViewImageAndGender(String tag, String imageUrl, String gender, String name) {
        String nameAndGender = "";
        if (name != null) {
            nameAndGender += name;
        }
        if (gender != null) {
            nameAndGender += (" " + gender);
        }
        listItemHeadPartView.setHeadViewImage(imageUrl, gender);
        if (!TextUtils.isEmpty(nameAndGender)) {
            tvNameAndGender.setVisibility(VISIBLE);
            tvNameAndGender.setText(nameAndGender);
        } else {
            tvNameAndGender.setVisibility(GONE);
        }
        return this;
    }


    public HeadItemView showEmptyTopView() {
        listItemHeadPartView.setTopText("");
        return this;
    }

    public HeadItemView setHeadViewTip(Integer tip) {
        listItemHeadPartView.setHeadViewTip(tip);
        return this;
    }

    public HeadItemView setHeadViewSignature(String signature) {
        listItemHeadPartView.setHeadViewSignature(signature);
        return this;
    }

    public HeadItemView setHospital(String hospital) {
        if (!TextUtils.isEmpty(hospital)) {
            tvHospital.setVisibility(VISIBLE);
            tvHospital.setText(hospital);
        } else {
            tvHospital.setVisibility(GONE);
        }
        return this;
    }

    public HeadItemView showVIP(boolean show) {
        if (show) {
            tvVIP.setVisibility(VISIBLE);
        } else {
            tvVIP.setVisibility(GONE);
        }
        return this;
    }

    public HeadItemView setIntentData(String intentData, String entrance) {
        listItemHeadPartView.setIntentData(intentData, entrance);
        return this;
    }

}
