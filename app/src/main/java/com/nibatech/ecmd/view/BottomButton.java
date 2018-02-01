package com.nibatech.ecmd.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;

import com.nibatech.ecmd.R;


public class BottomButton extends LinearLayout {
    private String leftText;
    private String rightText;


    public BottomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_bottom_button, this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BottomButton, 0, 0);
        try {
            leftText = ta.getString(R.styleable.BottomButton_leftText);
            rightText = ta.getString(R.styleable.BottomButton_rightText);
            initView();
        } finally {
            ta.recycle();
        }
    }

    private void initView() {
        Button btnLeft = (Button) findViewById(R.id.btn_left);
        btnLeft.setText(leftText);

        Button btnRight = (Button) findViewById(R.id.btn_right);
        btnRight.setText(rightText);
    }


    public BottomButton setLeftText(String backText) {
        Button btnLeft = (Button) findViewById(R.id.btn_left);
        btnLeft.setText(backText);
        return this;
    }

    public BottomButton setLeftListener(OnClickListener listener) {
        Button btnLeft = (Button) findViewById(R.id.btn_left);
        btnLeft.setOnClickListener(listener);
        return this;
    }


    public BottomButton setRightText(String submitText) {
        Button btnLeft = (Button) findViewById(R.id.btn_right);
        btnLeft.setText(submitText);
        return this;
    }

    public BottomButton setRightListener(OnClickListener listener) {
        Button btnLeft = (Button) findViewById(R.id.btn_right);
        btnLeft.setOnClickListener(listener);
        return this;
    }

    public BottomButton setTwoClickable(boolean clickable) {
        Button btnLeft = (Button) findViewById(R.id.btn_right);
        Button btnRight = (Button) findViewById(R.id.btn_left);

        btnLeft.setClickable(clickable);
        btnRight.setClickable(clickable);
        return this;
    }

}
