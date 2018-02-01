package com.nibatech.ecmd.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.personal.DoctorPersonalActivity;
import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.utils.UIUtils;


public class HeadView extends LinearLayout {

    private Activity activity;
    private final ImageView ivHead;
    private final Button btnSig;
    private final Button btnTip;
    private Intent intent;


    public HeadView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        activity = (Activity) context;
        LayoutInflater.from(context).inflate(R.layout.view_head, this);
        ivHead = (ImageView) findViewById(R.id.civ_head);
        btnSig = (Button) findViewById(R.id.btn_sig);
        btnTip = (Button) findViewById(R.id.btn_tip);
    }

    public HeadView setShowWithoutData() {//用于动画,因为gone掉动画不执行
        ivHead.setVisibility(VISIBLE);
        return this;
    }


    public HeadView setGender(String gender) {
        if ("男".equals(gender)) {
            setHeadResource(R.drawable.head_man);
        } else if ("女".equals(gender)) {
            setHeadResource(R.drawable.head_woman);
        } else {
            setHeadResource(R.drawable.case_list_view_null);
        }
        return this;
    }


    public HeadView setTip(Integer tip) {
        if (tip != null) {
            if (tip > 0) {
                btnTip.setVisibility(VISIBLE);
            } else {
                btnTip.setVisibility(GONE);
            }
            btnTip.setText(tip < 100 ? tip + "" : "99");
        } else {
            btnTip.setVisibility(GONE);
        }
        return this;
    }

    public HeadView setSignatureDrawable(Drawable drawable) {
        if (drawable != null) {
            btnSig.setBackground(drawable);
        } else {
            btnSig.setBackgroundResource(R.drawable.shape_oval_button_normal_green);
        }
        return this;
    }

    public HeadView setSignature(String signature) {
        if (signature != null) {
            btnSig.setVisibility(VISIBLE);
            btnSig.setText(signature);
        } else {
            btnSig.setVisibility(GONE);
        }
        return this;
    }

    public HeadView setSignature(Integer signature) {
        if (signature != null) {
            btnSig.setVisibility(VISIBLE);
            btnSig.setText(signature + "");
        } else {
            btnSig.setVisibility(GONE);
        }
        return this;
    }


    public HeadView setHeadPhoto(String imageUrl) {
        if (imageUrl != null) {
            ivHead.setVisibility(VISIBLE);
            UIUtils.loadNetworkImage(activity, imageUrl, ivHead);
        }
        return this;
    }

    public HeadView setHeadResource(Integer resid) {
        if (resid != null) {
            ivHead.setVisibility(VISIBLE);
            ivHead.setImageBitmap(null);//清除网络图片加载之后的缓存
            ivHead.setImageResource(resid);
//            ivHead.setBackgroundResource(resid);
        }
        return this;
    }

    public HeadView setHeadBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            ivHead.setVisibility(VISIBLE);
            Drawable drawable = new BitmapDrawable(bitmap);
            ivHead.setBackground(drawable);
        }
        return this;
    }

    public HeadView setHeadPhotoAndGender(String imageUrl, String gender) {
        ivHead.setVisibility(VISIBLE);
        if (imageUrl == null) {
            setGender(gender);
        } else {
            setHeadPhoto(imageUrl);
        }
        return this;
    }

    public HeadView setIntentData(String intentData, String entrance) {
        intent = new Intent();
        intent.setClass(activity, DoctorPersonalActivity.class);
        intent.putExtra(ExtraPass.SELF_URL, intentData);
        intent.putExtra(ExtraPass.ENTRANCE, entrance);
        if (intentData != null) {
            ivHead.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.startActivity(intent);
                }
            });
        } else {
            ivHead.setOnClickListener(null);
        }

        return this;
    }


}
