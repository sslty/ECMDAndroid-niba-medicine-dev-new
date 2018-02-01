package com.nibatech.ecmd.fragment.base;

import android.annotation.SuppressLint;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ProgressBar;

import com.flyco.tablayout.SlidingTabLayout;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.BottomButton;

import java.util.ArrayList;


@SuppressLint("ValidFragment")
public class SlidingTabFragment extends BaseSlidingTabFragment {
    private ViewPager vpContent;
    private SlidingTabLayout topTabLayout;
    private InnerPagerAdapter innerAdapter;
    private ArrayList<Fragment> contentFragments = new ArrayList<>();
    private String[] topTitles;
    private FloatingActionButton btnFloat;
    private ProgressBar progressBar;
    private BottomButton bottomButton;

    @Override
    protected void onCreateSuccessView(View successView) {
        vpContent = (ViewPager) successView.findViewById(R.id.vp_content);
        topTabLayout = (SlidingTabLayout) successView.findViewById(R.id.tl_top);
        btnFloat = (FloatingActionButton) successView.findViewById(R.id.btn_float);
        progressBar = (ProgressBar) successView.findViewById(R.id.progress_bar);
        //确定，返回按钮
        bottomButton = (BottomButton) successView.findViewById(R.id.bottom_button);
    }

    @Override
    protected int getSuccessViewLayoutId() {
        return R.layout.fragment_sliding_tab;
    }

    public void addTabLayout(ArrayList<Fragment> fragments, String[] titles) {
        topTabLayout.setVisibility(View.VISIBLE);
        vpContent.setVisibility(View.VISIBLE);

        contentFragments.clear();
        for (int i = 0; i < fragments.size(); i++) {
            contentFragments.add(fragments.get(i));
        }

        if (contentFragments.size() == 2) {
            topTabLayout.setTabPadding(UIUtils.dip2px(20));
        } else if (contentFragments.size() == 3) {
            topTabLayout.setTabPadding(UIUtils.dip2px(10));
        }

        topTitles = titles;
        innerAdapter = new InnerPagerAdapter(getChildFragmentManager());
        vpContent.setAdapter(innerAdapter);
        topTabLayout.setViewPager(vpContent, topTitles);
    }

    public void addDefaultFragment(Fragment fragment) {
        topTabLayout.setVisibility(View.GONE);
        vpContent.setVisibility(View.VISIBLE);

        contentFragments.clear();
        contentFragments.add(fragment);
        innerAdapter = new InnerPagerAdapter(getChildFragmentManager());
        vpContent.setAdapter(innerAdapter);
        topTabLayout.setViewPager(vpContent, new String[]{""});
    }

    public void setPadding(int left, int top, int right, int bottom) {
        vpContent.setPadding(left, top, right, bottom);
    }

    public void setProgressBarVisible(boolean visible) {
        if (visible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }


    public void setSubmitAndBackButtonViewText(String backText, String submitText) {
        bottomButton.setVisibility(View.VISIBLE);
        bottomButton.setLeftText(backText)
                .setRightText(submitText);
    }

    public void setSubmitAndBackButtonViewOnClickListener(View.OnClickListener backListener, View.OnClickListener submitListener) {
        bottomButton.setLeftListener(backListener)
                .setRightListener(submitListener);
    }


    public void setFloatingButtonVisible(boolean visible) {
        if (visible) {
            btnFloat.setVisibility(View.VISIBLE);
        } else {
            btnFloat.setVisibility(View.GONE);
        }
    }

    public void setFloatingButtonClickListener(View.OnClickListener listener) {
        btnFloat.setOnClickListener(listener);
    }

    class InnerPagerAdapter extends FragmentPagerAdapter {
        public InnerPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return contentFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return topTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return contentFragments.get(position);
        }
    }

}