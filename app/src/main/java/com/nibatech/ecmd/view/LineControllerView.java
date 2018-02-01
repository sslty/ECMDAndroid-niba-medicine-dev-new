package com.nibatech.ecmd.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nibatech.ecmd.R;


/**
 * 设置等页面条状控制或显示信息的控件
 */
public class LineControllerView extends LinearLayout {

    private String title;
    private boolean isBottom;
    private String num;
    private boolean canNav;
    private Integer titleColor;


    public LineControllerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_line_controller, this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LineControllerView, 0, 0);
        try {
            title = ta.getString(R.styleable.LineControllerView_title);
            num = ta.getString(R.styleable.LineControllerView_num);
            isBottom = ta.getBoolean(R.styleable.LineControllerView_isBottom, false);
            canNav = ta.getBoolean(R.styleable.LineControllerView_canNav, false);
            titleColor = ta.getColor(R.styleable.LineControllerView_titleColor, Color.BLACK);
            setUpView();
        } finally {
            ta.recycle();
        }
    }


    private void setUpView() {
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(title);
        tvTitle.setTextColor(titleColor);
        TextView tvNum = (TextView) findViewById(R.id.tv_num);
        tvNum.setText(num);
        tvNum.setVisibility(num != null ? VISIBLE : GONE);

        View bottomLine = findViewById(R.id.bottomLine);
        bottomLine.setVisibility(isBottom ? VISIBLE : GONE);
        ImageView navArrow = (ImageView) findViewById(R.id.iv_rightArrow);
        navArrow.setVisibility(canNav ? VISIBLE : GONE);

    }

    public void setContent(String content){
        TextView tvContent = (TextView) findViewById(R.id.tv_content);
        tvContent.setVisibility(VISIBLE);
        tvContent.setText(content);
    }

    public String getContent(){
        TextView tvContent = (TextView) findViewById(R.id.tv_content);
        return tvContent.getText().toString();
    }


    /**
     * 设置数量
     *
     * @param num 内容
     */
    public void setNum(int num) {
        this.num = num + "";
        TextView tvNum = (TextView) findViewById(R.id.tv_num);
        tvNum.setVisibility(VISIBLE);
        tvNum.setText(this.num);
    }


    public void setTitleColor(int color) {
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setTextColor(color);
    }

    public void setTitle(String title) {
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(title);
    }

    /**
     * 获取数量
     */
    public String getNum() {
        TextView tvNum = (TextView) findViewById(R.id.tv_num);
        return tvNum.getText().toString();
    }


    /**
     * 设置是否可以跳转
     *
     * @param canNav 是否可以跳转
     */
    public void setCanNav(boolean canNav) {
        this.canNav = canNav;
        ImageView navArrow = (ImageView) findViewById(R.id.iv_rightArrow);
        navArrow.setVisibility(canNav ? VISIBLE : GONE);
    }

}
