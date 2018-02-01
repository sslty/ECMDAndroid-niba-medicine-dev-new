package com.nibatech.ecmd.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.nibatech.ecmd.R;


public class ListItemView extends LinearLayout {

    private Activity activity;
    private final ListItemHeadPartView listItemHeadPartView;
    private final ListItemContentPartView listItemContentPartView;


    public ListItemView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        activity = (Activity) context;
        LayoutInflater.from(context).inflate(R.layout.view_list_item, this);
        listItemHeadPartView = (ListItemHeadPartView) findViewById(R.id.head_part);
        listItemContentPartView = (ListItemContentPartView) findViewById(R.id.content_part);
    }

    public HeadView getHeadView(){//用于动画
        return listItemHeadPartView.getHeadView();
    }

    public ListItemView setHeadPartShow() {
        listItemHeadPartView.setVisibility(VISIBLE);
        return this;
    }

    public ListItemView setContentPartShow() {
        listItemContentPartView.setVisibility(VISIBLE);
        return this;
    }

    public ListItemView setHeadPartBottomShow() {
        setHeadPartShow();
        listItemHeadPartView.setBottomShow();
        return this;
    }

    public ListItemView setHeadPartTopShow() {
        setHeadPartShow();
        listItemHeadPartView.setTopShow();
        return this;
    }

    public ListItemView setContentPartTopShow() {
        setContentPartShow();
        listItemContentPartView.setTopShow();
        return this;
    }

    public ListItemView setContentPartMiddleShow() {
        setContentPartShow();
        listItemContentPartView.setMiddldeShow();
        return this;
    }

    public ListItemView setContentPartBottomShow() {
        setContentPartShow();
        listItemContentPartView.setBottomShow();
        return this;
    }


    public ListItemView setHeadPartTopText(String topText) {
        setHeadPartShow();
        listItemHeadPartView.setTopText(topText);
        return this;
    }

    public ListItemView setHeadPartTopTextBackgroundResource(Integer resid) {
        listItemHeadPartView.setTopTextBackgroundResource(resid);
        return this;
    }

    public ListItemView setHeadPartBottomText(String bottomText) {
        setHeadPartShow();
        listItemHeadPartView.setBottomText(bottomText);
        return this;
    }

    public ListItemView setHeadViewImageAndGender(String imageUrl, String gender) {
        setHeadPartShow();
        listItemHeadPartView.setHeadViewImage(imageUrl, gender);
        return this;
    }

    public ListItemView setHeadViewTip(Integer tip) {
        setHeadPartShow();
        listItemHeadPartView.setHeadViewTip(tip);
        return this;
    }

    public ListItemView setHeadViewSignature(String signature) {
        setHeadPartShow();
        listItemHeadPartView.setHeadViewSignature(signature);
        return this;
    }

    public ListItemView setHeadViewSignature(Integer signature) {
        setHeadPartShow();
        listItemHeadPartView.setHeadViewSignature(signature);
        return this;
    }

    public ListItemView setHeadViewSignatureDrawable(Drawable drawable) {
        listItemHeadPartView.setHeadViewSignatureDrawable(drawable);
        return this;
    }

    public ListItemView setContentPartTopLeftText(String topLeftText) {
        setContentPartShow();
        listItemContentPartView.setTopLeftText(topLeftText);
        return this;
    }

    public ListItemView setContentPartTopRightText(String topRightText) {
        setContentPartShow();
        listItemContentPartView.setTopRightText(topRightText);
        return this;
    }

    public ListItemView setContentPartMiddleLeftText(String middleLeftText) {
        setContentPartShow();
        listItemContentPartView.setMiddleLeftText(middleLeftText);
        return this;
    }

    public ListItemView setContentPartMiddleRightText(String middleRightText) {
        setContentPartShow();
        listItemContentPartView.setMiddleRightText(middleRightText);
        return this;
    }

    public ListItemView setContentPartBottomLeftText(String bottomLeftText) {
        setContentPartShow();
        listItemContentPartView.setBottomLeftText(bottomLeftText);
        return this;
    }

    public ListItemView setContentPartBottomRightText(String bottomRightText) {
        setContentPartShow();
        listItemContentPartView.setBottomRightText(bottomRightText);
        return this;
    }

    public ListItemView setContentPartBottomLeftDrawable(Drawable drawable) {
        listItemContentPartView.setBottomLeftDrawable(drawable);
        return this;
    }

    public ListItemView setContentPartBottomRightDrawable(Drawable drawable) {
        listItemContentPartView.setBottomRightDrawable(drawable);
        return this;
    }

    public ListItemView setContentPartTopRightDrawable(Drawable drawable) {
        listItemContentPartView.setTopRightDrawable(drawable);
        return this;
    }

    public ListItemView setContentPartTopRightBackgroundResource(Integer resid) {
        listItemContentPartView.setTopRightBackgroundResource(resid);
        return this;
    }

    public ListItemView setContentPartTopRightOnClickListener(OnClickListener listener) {
        listItemContentPartView.setTopRightClickListener(listener);
        return this;
    }

    public ListItemView setIntentData(String intentData, String entrance) {
        listItemHeadPartView.setIntentData(intentData, entrance);
        return this;
    }

}
