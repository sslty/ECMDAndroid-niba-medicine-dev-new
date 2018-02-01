package com.nibatech.ecmd.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;

import com.nibatech.ecmd.R;

public class CustomSendView extends LinearLayout {

    public static final int MODE_VIEW_GUIDE = 0;//指导意见编辑后，只能查看
    public static final int MODE_VIEW_SUPPLY = 1;//医生端补充资料发布后，只能查看，患者端，患者在补充资料完毕后也只能查看
    public static final int MODE_EDIT_GUIDE = 2;//医生发送指导意见
    public static final int MODE_EDIT_SUPPLY = 3;//患者端和医生端补充资料

    public static final int FOCUSED_ALL = 4;//底部两个按钮指导意见和补充资料都能点击
    public static final int FOCUSED_NONE = 5;//都不能点击
    public static final int FOCUSED_LEFT = 6;//左边能点击，右边不能
    public static final int FOCUSED_RIGHT = 7;//左边不能点击，右边能


    private Button btnLeft;
    private Button btnRight;
    public static final int VIEW_HEIGHT = 80;

    public CustomSendView(Context context) {
        super(context);
        init(context);
    }

    protected void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_chat_bottom_button, this);
        btnLeft = (Button) findViewById(R.id.btn_left);
        btnRight = (Button) findViewById(R.id.btn_right);

    }

    public Button getBtnLeft() {
        return btnLeft;
    }

    public Button getBtnRight() {
        return btnRight;
    }

    public void setLeftAndRightText(String left, String right) {
        btnLeft.setText(left);
        btnRight.setText(right);
    }

    public void setBtnLeftClickListener(OnClickListener listener) {
        btnLeft.setOnClickListener(listener);
    }

    public void setBtnRightClickListener(OnClickListener listener) {
        btnRight.setOnClickListener(listener);
    }


    //设置底部button的状态，可点击与否
    public void setBottomButtonState(int state) {
        switch (state) {
            case FOCUSED_ALL:
                setButtonClick(btnLeft);
                setButtonClick(btnRight);
                break;
            case FOCUSED_NONE:
                setButtonNoClick(btnLeft);
                setButtonNoClick(btnRight);
                break;
            case FOCUSED_RIGHT:
                setButtonNoClick(btnLeft);
                setButtonClick(btnRight);
                break;
            case FOCUSED_LEFT:
                setButtonClick(btnLeft);
                setButtonNoClick(btnRight);
                break;
        }
    }

    //button可以点击
    @TargetApi(Build.VERSION_CODES.M)
    private void setButtonClick(Button btn) {
        btn.setClickable(true);
        btn.setBackgroundResource(R.drawable.shape_button_circle_nomal);
        btn.setTextColor(Color.WHITE);
    }

    //button不能点击
    @TargetApi(Build.VERSION_CODES.M)
    private void setButtonNoClick(Button btn) {
        btn.setClickable(false);
        btn.setBackgroundResource(R.drawable.shape_oval_stroke);
        btn.setTextColor(Color.BLACK);
    }

    public static ButtonState getButtonState(String type, boolean finished, boolean empty, boolean isDoctor) {
        ButtonState bs;
        if (isDoctor) {
            bs = new ButtonState(MODE_EDIT_SUPPLY, false, FOCUSED_RIGHT);
            if (!empty) {
                if (type.compareTo("guide") == 0) {
                    bs.setState(MODE_VIEW_GUIDE, FOCUSED_NONE);
                    if (!finished) {//指导意见
                        bs.setState(MODE_VIEW_GUIDE, true, FOCUSED_NONE);
                    }
                } else {
                    if (finished) {//患者补充资料修改完毕
                        bs.setState(MODE_VIEW_SUPPLY, true, FOCUSED_ALL);
                    } else {//发送补充资料
                        bs.setState(MODE_VIEW_SUPPLY, false, FOCUSED_NONE);
                    }
                }
            }
        } else {
            bs = new ButtonState(MODE_VIEW_SUPPLY, false, FOCUSED_NONE);
            if (!empty) {
                if (type.compareTo("guide") == 0) {
                    bs.setState(MODE_VIEW_GUIDE, FOCUSED_ALL);
                    if (!finished) {//查看指导意见
                        bs.setState(MODE_VIEW_GUIDE, true, FOCUSED_ALL);
                    }
                } else {
                    if (!finished) {//开始补充资料
                        bs.setState(MODE_EDIT_SUPPLY, true, FOCUSED_NONE);
                    }
                }
            }

        }

        return bs;
    }

    //底部button状态
    public static class ButtonState {
        public int mode;//查看，编辑指导意见还是补充资料
        public boolean highLight;//是否是高亮状态
        public int bottomState;//底部两个按钮是否能点击

        ButtonState(int mode, boolean highLight, int bottomState) {
            this.mode = mode;
            this.highLight = highLight;
            this.bottomState = bottomState;
        }

        void setState(int mode, boolean highLight, int bottomState) {
            this.mode = mode;
            this.highLight = highLight;
            this.bottomState = bottomState;
        }

        void setState(int mode, int bottomState) {
            this.mode = mode;
            this.highLight = false;
            this.bottomState = bottomState;
        }
    }


}
