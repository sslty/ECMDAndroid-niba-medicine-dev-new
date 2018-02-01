package com.nibatech.ecmd.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nibatech.ecmd.R;


public class MsgOutgoingView extends LinearLayout {

    public MsgOutgoingView(final Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.msg_outgoing, this);
    }


    public TextView getMsgTextView() {
        return (TextView) findViewById(R.id.tv_msg);
    }

    public ImageView getCustomImageView() {
        return (ImageView) findViewById(R.id.iv_custom);
    }

}
