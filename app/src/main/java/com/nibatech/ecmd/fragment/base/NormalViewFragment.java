package com.nibatech.ecmd.fragment.base;


import android.view.View;

public class NormalViewFragment extends BaseFragment {
    public interface ShowNormalViewDataListener {
        void initView(View view);

        void setViewListener();

        void getHostUrlData();

        void getHostUrlDataSuccess(String json);

        void setViewData(Object object);
    }
}
