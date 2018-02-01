/*
 * Copyright (c) 2015 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nibatech.ecmd.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.nibatech.ecmd.utils.UIUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

/**
 * 侧边栏
 */
public class SideBar extends View {
    // 触摸事件
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    // 26个字母
    public String[] b = null;
    private int choose = -1;// 代表数组下标越界，未选中
    private Paint paint = new Paint();

    private TextView mTextDialog;
    private float singleHeight = UIUtils.dip2px(10);


    public SideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SideBar(Context context) {
        super(context);
    }

    public void setSideBarLetter(Set<String> set) {
        setSideBarLetter(set, true);
    }

    public void setSideBarLetter(Set<String> set, boolean sort) {
        b = set.toArray(new String[set.size()]);
        if (sort) {
            Collections.sort(Arrays.asList(b));
        }
        requestLayoutInner();
    }

    public void requestLayoutInner() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                requestLayout();
            }
        });
    }

    public void setTextView(TextView mTextDialog) {
        this.mTextDialog = mTextDialog;
    }

    /**
     * 重写这个方法
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (b == null) {
            return;
        }
        // 获取每一个字母的高度
        for (int i = 0; i < b.length; i++) {
            setPaint();
            Rect bounds = new Rect();
            paint.getTextBounds(b[i], 0, b[i].length(), bounds);
            Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
            int baseLine = (int) ((singleHeight - fontMetricsInt.bottom + fontMetricsInt.top) / 2 - fontMetricsInt.top);

            // x坐标等于中间-字符串宽度的一半.
            float xPos = getWidth() / 2 - paint.measureText(b[i]) / 2;
            float yPos = baseLine + singleHeight * i;
            canvas.drawText(b[i], xPos, yPos, paint);
            paint.reset();// 重置画笔
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (b != null) {
//            setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), resolveSize((int) singleHeight * b.length, heightMeasureSpec));
            setMeasuredDimension(resolveSize((int) getWidth(b), widthMeasureSpec), resolveSize((int) singleHeight * b.length, heightMeasureSpec));
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (b == null) return true;
        final int action = event.getAction();
        final float y = event.getY();// 点击y坐标,相对于自身左上角的Y坐标，getRawY()是相对于屏幕左上角的Y坐标，（0，0）点在自身左上角
        final int oldChoose = choose;//choose也是数组下标，当手指一直按下，记录当前的数组下标，当手指移动时，保证连续性
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        final int c = (int) (y / getHeight() * b.length);// 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.将点击y坐标转换为数组下标

        switch (action) {
            case MotionEvent.ACTION_UP:
                setBackgroundDrawable(new ColorDrawable(0x00000000));
                choose = -1;//代表数组下标越界，未被选中
                invalidate();
                if (mTextDialog != null) {
                    mTextDialog.setVisibility(View.INVISIBLE);
                }
                break;

            default://代表action_down和action_move,action_move导致y坐标变化
                setBackgroundDrawable(new ColorDrawable(0x13161316));
                if (oldChoose != c) {
                    if (c >= 0 && c < b.length) {
                        if (listener != null) {
                            //根源是y坐标变化，算法是数组下标变化，实现是listview的section变化
                            listener.onTouchingLetterChanged(b[c]);//y坐标变化，导致数组下标变化，（具体实现，数组下标变化，导致listview的section变化）
                        }
                        if (mTextDialog != null) {
                            mTextDialog.setText(b[c]);
                            mTextDialog.setVisibility(View.VISIBLE);
                        }

                        choose = c;
                        invalidate();
                    }
                }

                break;
        }
        return true;//表示到此拦截事件，不再派发触摸事件
    }

    /**
     * 向外公开的方法
     *
     * @param onTouchingLetterChangedListener
     */
    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener
                                                           onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    /**
     * 接口
     *
     * @author coder
     */
    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(String s);
    }

    private float getWidth(String[] strings) {
        int width = 0;
        int index = 0;
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].length() > width) {
                width = strings[i].length();
            }
        }

        for (int i = 0; i < strings.length; i++) {
            if (strings[i].length() == width) {
                index = i;
            }
        }
        setPaint();
        return paint.measureText(b[index]) * 4 / 3;//让宽度看起来有padding
    }

    private void setPaint() {
        paint.setColor(Color.rgb(86, 86, 86));
        paint.setTypeface(Typeface.DEFAULT);
        paint.setAntiAlias(true);
        paint.setTextSize(23);
    }
}