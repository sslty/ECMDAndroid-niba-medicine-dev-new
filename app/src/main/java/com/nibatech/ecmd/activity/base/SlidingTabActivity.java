package com.nibatech.ecmd.activity.base;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.MainActivity;
import com.nibatech.ecmd.activity.message.MessageActivity;
import com.nibatech.ecmd.activity.search.SearchListActivity;
import com.nibatech.ecmd.application.ECMDApplication;
import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.BaseView;
import com.nibatech.ecmd.view.BottomButton;
import com.nibatech.ecmd.view.CustomSearchView;
import com.nibatech.ecmd.view.GifDrawableView;
import com.tencent.TIMManager;
import com.tencent.TIMUserStatusListener;

import java.util.ArrayList;

/**
 * 医生端/患者端/游客端   父类控件
 */
public class SlidingTabActivity extends BaseSlidingTabActivity
        implements OnTabSelectListener, View.OnClickListener {

    private long mBackTime;
    private Toolbar toolBar;
    private FloatingActionButton floatingActionButton;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> topFragmentList = new ArrayList<>();
    private ArrayList<Fragment> bottomFragmentList = new ArrayList<>();
    private SlidingTabLayout tabLayoutTop;
    private CommonTabLayout tabLayoutBottom;
    private ViewPager vpTop;
    private FrameLayout flBottom;
    private ProgressBar progressBar;
    private BottomButton bottomButton;
    private CustomSearchView searchView;
    private GifDrawableView gifDrawableView;
    private LinearLayout llTop;

    @Override
    protected void onCreateSuccessView(View successView) {
        //添加所有的activity, 除了登录界面
        if (!this.getClass().getName().contains("MainActivity")) {
            ECMDApplication.ExitApp.getInstance().addOneActivity(this);
        }
        //竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //区域
//        View decorView = getWindow().getDecorView();
        //屏幕顶部bar
        toolBar = (Toolbar) successView.findViewById(R.id.id_toolbar);
        //浮动按钮
        floatingActionButton = (FloatingActionButton) successView.findViewById(R.id.btn_float);
        //进度条
        progressBar = (ProgressBar) successView.findViewById(R.id.progress_bar);
        //顶部view
        vpTop = (ViewPager) successView.findViewById(R.id.vp_top);
        setViewPageOnChangeListener();
        //搜索
        searchView = (CustomSearchView) successView.findViewById(R.id.custom_search_view);
        //tab
        tabLayoutTop = (SlidingTabLayout) successView.findViewById(R.id.tl_top);
//        tabLayoutTop.setOnTabSelectListener(this);//viewpage已经做了onselect,要不然会调用2次
        llTop = (LinearLayout) successView.findViewById(R.id.ll_top);
        //底部
        flBottom = (FrameLayout) successView.findViewById(R.id.fl_bottom);
        //确定，返回按钮
        bottomButton = (BottomButton) successView.findViewById(R.id.bottom_button);
        //gif
        gifDrawableView = (GifDrawableView) successView.findViewById(R.id.gifDrawableView);

        tabLayoutBottom = (CommonTabLayout) successView.findViewById(R.id.tl_bottom);
        tabLayoutBottom.setOnTabSelectListener(this);

        if (toolBar != null) {
            setSupportActionBar(toolBar);
            //noinspection ConstantConditions
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            //设置监听事件
            setToolbarButtonListener();
            //在toolbar上隐藏所有的图片按钮
            hideToolbarButtons();
            //显示后退按钮
            showBackOnToolbar();
        }

        //同一账号不能同时登录
        checkLoginOnOnce();
    }


    @Override
    protected int getSuccessViewLayoutId() {
        return R.layout.activity_sliding_tab;
    }


    public CustomSearchView getSearchView() {
        return searchView;
    }

    public void setSearchViewVisible(boolean visible) {
        if (visible) {
            searchView.setVisibility(View.VISIBLE);
        } else {
            searchView.setVisibility(View.GONE);
        }
    }

    protected void showGif() {
        gifDrawableView.showGif(R.drawable.opendoor_loading, R.drawable.opendoor_success);
    }

    private void setViewPageOnChangeListener() {
        vpTop.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                onTabSelect(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setCurrentTopTab(int position) {
        tabLayoutTop.setCurrentTab(position);
    }

    public int getCurrentTopTab() {
        return tabLayoutTop.getCurrentTab();
    }

    public void setCurrentBottomTab(int tabPosition) {
        tabLayoutBottom.setCurrentTab(tabPosition);
    }

    public int getCurrentBottomTab() {
        return tabLayoutBottom.getCurrentTab();
    }

    public void showMessageDot(int dot) {
        tabLayoutBottom.showDot(dot);
    }

    public void hideMessageDot(int dot) {
        tabLayoutBottom.hideMsg(dot);
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

    public TextView getTitleView(int tabPosition) {
        return tabLayoutBottom.getTitleView(tabPosition);
    }

    public ImageView getImageView(int tabPosition) {
        return tabLayoutBottom.getIconView(tabPosition);
    }


    public void setPadding(int left, int top, int right, int bottom) {
        vpTop.setPadding(left, top, right, bottom);
    }

    public void setToolbarVisible(boolean visible) {
        if (visible) {
            toolBar.setVisibility(View.VISIBLE);
        } else {
            toolBar.setVisibility(View.GONE);
        }
    }

    public void setToolBarText(String text) {
        if (text != null) {
            setToolbarVisible(true);
            TextView tvToolBar = (TextView) getSuccessView().findViewById(R.id.id_txt_title);
            tvToolBar.setText(text);
        }
    }

    public void setProgressBarVisible(boolean visible) {
        if (visible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    public void setFloatingButtonVisible(boolean visible) {
        if (visible) {
            floatingActionButton.setVisibility(View.VISIBLE);
        } else {
            floatingActionButton.setVisibility(View.GONE);
        }
    }

    public void setFloatingButtonClickListener(View.OnClickListener listener) {
        floatingActionButton.setOnClickListener(listener);
    }

    public BaseView getSelfView(final int layoutId) {
        BaseView selfView = new BaseView(this) {
            @Override
            protected int getSuccessViewLayoutId() {
                return layoutId;
            }
        };
        flBottom.addView(selfView);
        selfView.setVisibility(BaseView.Visibility.SUCCESS);
        flBottom.setVisibility(View.VISIBLE);
        return selfView;
    }

    public void addDefaultFragment(Fragment fragment) {
        tabLayoutTop.setVisibility(View.VISIBLE);
        vpTop.setVisibility(View.VISIBLE);

        topFragmentList.clear();
        topFragmentList.add(fragment);
        tabLayoutTop.setViewPager(vpTop, new String[]{""}, this, topFragmentList);
        tabLayoutTop.setVisibility(View.GONE);
    }

    public void addMultiTabFragments(ArrayList<Fragment> fragments, String[] titles) {
        tabLayoutTop.setVisibility(View.VISIBLE);
        vpTop.setVisibility(View.VISIBLE);

        topFragmentList.clear();
        for (int i = 0; i < fragments.size(); i++) {
            topFragmentList.add(fragments.get(i));
        }
        if (topFragmentList.size() == 2) {
            tabLayoutTop.setTabPadding(UIUtils.dip2px(20));
        } else if (topFragmentList.size() == 3) {
            tabLayoutTop.setTabPadding(UIUtils.dip2px(10));
        }
        tabLayoutTop.setViewPager(vpTop, titles, this, topFragmentList);
    }


    public void setTopTabVisible(int visibility) {
        tabLayoutTop.setVisibility(visibility);
        vpTop.setVisibility(visibility);
        llTop.setVisibility(visibility);
    }


    public void setBottomTabVisible(boolean visible) {
        if (visible) {
            tabLayoutBottom.setVisibility(View.VISIBLE);
            flBottom.setVisibility(View.VISIBLE);
        } else {
            tabLayoutBottom.setVisibility(View.GONE);
            flBottom.setVisibility(View.GONE);
        }
    }


    public void addBottomTab(ArrayList<Fragment> fragments, String[] titles, int[] iconUnselectIds, int[] iconSelectIds) {
        tabLayoutBottom.setVisibility(View.VISIBLE);
        flBottom.setVisibility(View.VISIBLE);

        bottomFragmentList.clear();
        bottomFragmentList = fragments;

        for (int i = 0; i < titles.length; i++) {
            mTabEntities.add(new TabEntity(titles[i], iconSelectIds[i], iconUnselectIds[i]));
        }
        tabLayoutBottom.setTabData(mTabEntities, this, R.id.fl_bottom, bottomFragmentList);

    }

    @Override
    public void onBackPressed() {
        if (this.getClass().getName().contains("HomePageActivity")) {//只有在首页的界面才直接跳出
            if (mBackTime == 0 || System.currentTimeMillis() - mBackTime > 3000) {
                mBackTime = System.currentTimeMillis();
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                return;
            }

            mBackTime = 0;
            ECMDApplication.ExitApp.getInstance().exitAllActivity();
            System.exit(0);
            finish();
        }

        super.onBackPressed();
    }

    @Override
    public void onTabSelect(int position) {
    }

    @Override
    public void onTabReselect(int position) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_search:
                startActivity(new Intent(getApplicationContext(), SearchListActivity.class));
                break;
            case R.id.img_message:
                startActivity(new Intent(getApplicationContext(), MessageActivity.class));
                break;
        }
    }

    private void checkLoginOnOnce() {
        TIMManager.getInstance().setUserStatusListener(new TIMUserStatusListener() {
            @Override
            public void onForceOffline() {
                UIUtils.logout();
                Toast.makeText(UIUtils.getContext(), "账号已经在其他地方登录", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }

            @Override
            public void onUserSigExpired() {

            }
        });
    }

    private void showBackOnToolbar() {
        toolBar.setNavigationIcon(R.drawable.toolbar_back);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    protected void hideBackOnToolbar() {
        toolBar.setNavigationIcon(null);
    }


    protected void hideToolbarButtons() {
        findSearchButton().setVisibility(View.GONE);
        findMessageButton().setVisibility(View.GONE);
        findChatButton().setVisibility(View.GONE);
        findDeleteButton().setVisibility(View.GONE);
        findMoreButton().setVisibility(View.GONE);
        findPatientNameButton().setVisibility(View.GONE);
        findSaveButton().setVisibility(View.GONE);
    }

    private void setToolbarButtonListener() {
        findSearchButton().setOnClickListener(this);
        findMessageButton().setOnClickListener(this);
    }

    /**
     * 患者姓名或者手机号
     * 搜索
     * 消息
     * 限时聊天
     * 删除
     * 更多-相机，图库
     * 保存资料
     */
    protected TextView findPatientNameButton() {
        return (TextView) getSuccessView().findViewById(R.id.tv_patient_name);
    }

    protected ImageView findSearchButton() {
        return (ImageView) getSuccessView().findViewById(R.id.img_search);
    }

    protected ImageView findMessageButton() {
        return (ImageView) getSuccessView().findViewById(R.id.img_message);
    }

    protected ImageView findChatButton() {
        return (ImageView) getSuccessView().findViewById(R.id.img_chat);
    }

    protected ImageView findDeleteButton() {
        return (ImageView) getSuccessView().findViewById(R.id.img_delete);
    }

    protected ImageView findMoreButton() {
        return (ImageView) getSuccessView().findViewById(R.id.img_more);
    }

    public ImageView findSaveButton() {
        return (ImageView) getSuccessView().findViewById(R.id.img_save);
    }


    class TabEntity implements CustomTabEntity {
        public String title;
        int selectedIcon;
        int unSelectedIcon;

        TabEntity(String title, int selectedIcon, int unSelectedIcon) {
            this.title = title;
            this.selectedIcon = selectedIcon;
            this.unSelectedIcon = unSelectedIcon;
        }

        @Override
        public String getTabTitle() {
            return title;
        }

        @Override
        public int getTabSelectedIcon() {
            return selectedIcon;
        }

        @Override
        public int getTabUnselectedIcon() {
            return unSelectedIcon;
        }
    }

    //得到上一层传过来的数据-标题
    protected String getIntentTitle() {
        return getIntent().getStringExtra(ExtraPass.TITLE);
    }

    //得到上一层传过来的数据-self-url
    protected String getIntentSelfUrl() {
        return getIntent().getStringExtra(ExtraPass.SELF_URL);
    }

    //得到上一层传过来的数据-details-url
    protected String getIntentDetailsUrl() {
        return getIntent().getStringExtra(ExtraPass.DETAILS_URL);
    }

    //得到上一层传过来的数据-entrance
    protected int getIntentEntrance() {
        return getIntent().getIntExtra(ExtraPass.ENTRANCE, -1);
    }
}
