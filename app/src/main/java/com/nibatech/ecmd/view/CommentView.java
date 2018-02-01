package com.nibatech.ecmd.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nibatech.ecmd.R;


public class CommentView extends LinearLayout {
    private final HeadView headView;
    private final TextView tvName;
    private final TextView tvComment;
    private final TextView tvTime;
    private final Activity activity;


    public CommentView(final Context context) {
        super(context);
        activity = (Activity) context;
        LayoutInflater.from(context).inflate(R.layout.view_comment, this);
        headView = (HeadView) findViewById(R.id.head_view);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvComment = (TextView) findViewById(R.id.tv_comment);

    }

    public CommentView setHeadPhotoAndGender(String imageUrl, String gender) {
        headView.setHeadPhotoAndGender(imageUrl, gender);
        return this;
    }

    public CommentView setComment(String comment) {
        if (comment != null) {
            tvComment.setText(comment);
        } else {
            tvComment.setText("");
        }
        return this;
    }

    public CommentView setName(String name) {
        if (name != null) {
            tvName.setText(name);
        } else {
            tvName.setText("");
        }
        return this;
    }

    public CommentView setTime(String time) {
        if (time != null) {
            tvTime.setText(time);
        } else {
            tvTime.setText("");
        }
        return this;
    }

    public CommentView setIntentData(String intentData, String entrance) {
        headView.setIntentData(intentData, entrance);
        return this;
    }


}
