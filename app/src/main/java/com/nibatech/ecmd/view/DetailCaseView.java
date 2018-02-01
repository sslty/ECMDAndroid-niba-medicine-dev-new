package com.nibatech.ecmd.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nibatech.ecmd.R;

import java.util.ArrayList;


public class DetailCaseView extends LinearLayout {

    private Activity activity;
    private final TextView tvDescription;
    private final TextView tvSymptom;
    private final AutoGridImageView autoGridImageView;
    private final TextView tvGender;
    private final TextView tvAge;
    private final LinearLayout llGenderAndAge;
    private final HeadView headView;


    public DetailCaseView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        activity = (Activity) context;
        LayoutInflater.from(context).inflate(R.layout.view_detail_case, this);
        tvDescription = (TextView) findViewById(R.id.id_txt_description);
        tvSymptom = (TextView) findViewById(R.id.id_txt_symptom);
        autoGridImageView = (AutoGridImageView) findViewById(R.id.auto_image_container);
        tvGender = (TextView) findViewById(R.id.tv_gender);
        tvAge = (TextView) findViewById(R.id.tv_age);
        llGenderAndAge = (LinearLayout) findViewById(R.id.ll_gender_and_age);
        headView = (HeadView) findViewById(R.id.headView);
    }

    public DetailCaseView setGender(String gender) {
        if (gender != null) {
            llGenderAndAge.setVisibility(VISIBLE);
            tvGender.setText(gender);
            headView.setVisibility(VISIBLE);
            headView.setGender(gender);
        }
        return this;
    }

    public DetailCaseView setSignature(String signature) {
        if (signature != null) {
            headView.setVisibility(VISIBLE);
            headView.setSignature(signature);
        }
        return this;
    }

    public DetailCaseView setSignatureDrawble(Drawable drawable) {
        if (drawable != null) {
            headView.setVisibility(VISIBLE);
            headView.setSignatureDrawable(drawable);
        }
        return this;
    }

    public DetailCaseView setHeadPhoto(String imageUrl) {
        if (imageUrl != null) {
            headView.setVisibility(VISIBLE);
            headView.setHeadPhoto(imageUrl);
        }
        return this;
    }

    private DetailCaseView setHeadResource(Integer resid) {
        if (resid != null) {
            headView.setVisibility(VISIBLE);
            headView.setHeadResource(resid);
        }
        return this;
    }

    public DetailCaseView setAge(Integer age) {
        if (age != null) {
            llGenderAndAge.setVisibility(VISIBLE);
            tvAge.setText(age + "岁");
        }
        return this;
    }

    public DetailCaseView setDescription(String description) {
        if (description != null) {
            tvDescription.setText(description);
        }
        return this;
    }

    public DetailCaseView setSymptom(String symptom) {
        if (symptom != null) {
            tvSymptom.setText(symptom);
        }
        return this;
    }

    public DetailCaseView setImageListView(ArrayList<String> imageList) {
        if (imageList != null) {
            autoGridImageView.setVisibility(VISIBLE);
            autoGridImageView.addImages(imageList);
        }
        return this;
    }

}
