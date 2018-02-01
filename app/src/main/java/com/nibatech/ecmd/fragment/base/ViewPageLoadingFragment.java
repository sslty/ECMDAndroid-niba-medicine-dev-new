package com.nibatech.ecmd.fragment.base;

/**
 * 在这里实现Fragment数据的缓加载,适用于view-page
 */
public abstract class ViewPageLoadingFragment extends BaseFragment {
    protected Boolean isVisible;
    protected Boolean isCreateView;

    /**
     * 在这里实现Fragment数据的缓加载.
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * fragment 显示
     */
    protected void onVisible() {
        getHttpData();
    }

    /**
     * 获取数据
     */
    protected abstract void getHttpData();

    /**
     * fragment 不显示
     */
    protected void onInvisible() {
    }

    /**
     * fragment 设置view已经成功创建标记
     */
    public void setCreateView(Boolean createView) {
        isCreateView = createView;
    }

    /**
     * fragment 是否应该获取数据设置到view
     */
    protected boolean isSetDataToView() {
        boolean ok = false;

        //view已经布局且在当前界面
        if (isCreateView != null && isCreateView && isVisible != null && isVisible) {
            ok = true;
        }

        return ok;
    }
}
