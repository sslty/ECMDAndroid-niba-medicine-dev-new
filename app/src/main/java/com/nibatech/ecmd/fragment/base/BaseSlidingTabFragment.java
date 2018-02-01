package com.nibatech.ecmd.fragment.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nibatech.ecmd.view.BaseView;


@SuppressLint("ValidFragment")
public abstract class BaseSlidingTabFragment extends Fragment {

    private BaseView baseView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        baseView = new BaseView(getActivity()) {
            @Override
            protected int getSuccessViewLayoutId() {
                return BaseSlidingTabFragment.this.getSuccessViewLayoutId();
            }
        };
        baseView.setVisibility(BaseView.Visibility.SUCCESS);
        onCreateSuccessView(baseView.getSuccessView());
        return baseView;
    }

    protected abstract int getSuccessViewLayoutId();

    protected abstract void onCreateSuccessView(View successView);

    public void setVisibility(BaseView.Visibility visibility) {
        baseView.setVisibility(visibility);
    }

    protected View getSuccessView(){

        return baseView.getSuccessView();
    }

}