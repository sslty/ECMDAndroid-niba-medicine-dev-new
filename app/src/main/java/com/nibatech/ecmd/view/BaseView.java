package com.nibatech.ecmd.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.nibatech.ecmd.R;


public abstract class BaseView extends FrameLayout {
    private static final int VISIBILITY_UNDO = 0;
    private static final int VISIBILITY_LOADING = 1;
    private static final int VISIBILITY_SUCCESS = 2;
    private static final int VISIBILITY_EMPTY = 3;
    private static final int VISIBILITY_ERROR = 4;

    private View loadingView;
    private View errorView;
    private View emptyView;
    private View successView;

    private int currentVisibility = VISIBILITY_UNDO;
    protected Activity activity;


    public BaseView(Context context) {
        super(context);
        initView(context);
    }


    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(final Context context) {
        this.activity = (Activity) context;
        if (loadingView == null) {
            loadingView = LayoutInflater.from(context).inflate(R.layout.page_loading, this, false);//这里传入false，因为传入true，就不能在调用addView，true的返回值第二个参数this，自己不能添加自己，false的返回值是this的子View，所以可以添加，同时true会把子View直接添加到this，但是拿不到子View的引用，所以后面要单独设置子View的Visible属性就变得很难，所以综合我们传false
            addView(loadingView);
        }

        if (errorView == null) {
            errorView = LayoutInflater.from(context).inflate(R.layout.page_error, this, false);
            Button btnRetry = (Button) errorView.findViewById(R.id.btn_retry);
            btnRetry.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "重新加载网络数据", Toast.LENGTH_SHORT).show();
                }
            });

            addView(errorView);
        }

        if (emptyView == null) {
            emptyView = LayoutInflater.from(context).inflate(R.layout.page_empty, this, false);
            addView(emptyView);
        }

        if (successView == null) {
            successView = LayoutInflater.from(context).inflate(getSuccessViewLayoutId(), this, false);
            if (successView != null) {
                addView(successView);
            }
        }

        showVisibility();

    }

    private void showVisibility() {
        loadingView.setVisibility(currentVisibility == VISIBILITY_LOADING ? VISIBLE : GONE);
        emptyView.setVisibility(currentVisibility == VISIBILITY_EMPTY ? VISIBLE : GONE);
        errorView.setVisibility(currentVisibility == VISIBILITY_ERROR ? VISIBLE : GONE);

        if (successView != null) {
            successView.setVisibility(currentVisibility == VISIBILITY_SUCCESS ? VISIBLE : GONE);
        }

        setVisibility(currentVisibility != VISIBILITY_UNDO ? VISIBLE : GONE);
    }

    //异步调用成功时调用,异步调用成功一定是回到主线程
    public void setVisibility(Visibility visibility) {
        currentVisibility = visibility.visibility;
        showVisibility();
    }

    protected abstract int getSuccessViewLayoutId();


    public View getSuccessView(){
        return successView;
    }

    public enum Visibility {
        LOADING(VISIBILITY_LOADING),
        SUCCESS(VISIBILITY_SUCCESS),
        EMPTY(VISIBILITY_EMPTY),
        ERROR(VISIBILITY_ERROR);

        private int visibility;

        Visibility(int visibility) {
            this.visibility = visibility;
        }

    }


}
