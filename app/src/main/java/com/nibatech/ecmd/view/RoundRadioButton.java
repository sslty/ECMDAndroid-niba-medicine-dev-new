package com.nibatech.ecmd.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.flyco.roundview.RoundViewDelegate;

/**
 * 用于需要圆角矩形框背景的TextView的情况,减少直接使用TextView时引入的shape资源文件
 */
public class RoundRadioButton extends RadioButton {
    private RoundViewDelegate delegate;
    private int bgCheckedColor;
    private int bgUnCheckedColor;
    private int txtCheckedColor;
    private int txtUnCheckedColor;
    private int strokeCheckedColor;
    private int strokeUnCheckedColor;

    public RoundRadioButton(Context context) {
        this(context, null);
    }

    public RoundRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        delegate = new RoundViewDelegate(this, context, attrs);
        bgCheckedColor = delegate.getBackgroundPressColor();
        bgUnCheckedColor = delegate.getBackgroundColor();
        strokeCheckedColor = delegate.getStrokePressColor();
        strokeUnCheckedColor = delegate.getStrokeColor();
        txtCheckedColor = delegate.getTextPressColor();
        txtUnCheckedColor = getCurrentTextColor();
        delegate.setStrokeWidth(1);

    }

    /**
     * use delegate to set attr
     */
    public RoundViewDelegate getDelegate() {
        return delegate;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            RadioGroup radioGroup = (RadioGroup) getParent();
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                ((RoundRadioButton) radioGroup.getChildAt(i)).setChecked(false);
                ((RoundRadioButton) radioGroup.getChildAt(i)).getDelegate().setBackgroundColor(bgUnCheckedColor);
                ((RoundRadioButton) radioGroup.getChildAt(i)).getDelegate().setStrokeColor(strokeUnCheckedColor);
                ((RoundRadioButton) radioGroup.getChildAt(i)).setTextColor(txtUnCheckedColor);
            }
            setChecked(true);
            getDelegate().setBackgroundColor(bgCheckedColor);
            getDelegate().setStrokeColor(strokeCheckedColor);
            setTextColor(txtCheckedColor);
        }
        return super.onTouchEvent(event);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (delegate.isWidthHeightEqual() && getWidth() > 0 && getHeight() > 0) {
            int max = Math.max(getWidth(), getHeight());
            int measureSpec = MeasureSpec.makeMeasureSpec(max, MeasureSpec.EXACTLY);
            super.onMeasure(measureSpec, measureSpec);
            return;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (delegate.isRadiusHalfHeight()) {
            delegate.setCornerRadius(getHeight() / 2);
        } else {
            delegate.setBgSelector();
        }
    }


}
