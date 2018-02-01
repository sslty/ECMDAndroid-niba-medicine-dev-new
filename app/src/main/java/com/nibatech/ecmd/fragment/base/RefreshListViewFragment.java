package com.nibatech.ecmd.fragment.base;


import android.support.v7.widget.RecyclerView;

import com.nibatech.ecmd.view.BaseCustomRecycleListView;

import java.util.List;
import java.util.Map;


/**
 * 医生端/患者端/游客端   没有上拉加载更多，只有下拉刷新的refresh-list-view
 */
public class RefreshListViewFragment extends ViewPageLoadingFragment implements
        BaseCustomRecycleListView.CustomListViewListener {
    public interface ShowRefreshListViewDataListener {
        //点击每一个item
        void clickViewItem(RecyclerView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position);

        //显示每一条item
        void showViewItem(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position);

        //从服务器获取数据成功后填充到data
        List<Map<String, Object>> getDataFromJson(String json);
    }

    private BaseCustomRecycleListView refreshListView;//只有下拉刷新，没有上拉加载更多的list
    private ShowRefreshListViewDataListener listener;//必须实现的接口

    //初始化recycle-list-view
    protected void initRefreshListView(BaseCustomRecycleListView view, ShowRefreshListViewDataListener listener) {
        refreshListView = view;
        refreshListView.setCustomListViewListener(this);
        this.listener = listener;
        setCreateView(true);
    }

    @Override
    protected void getHttpData() {
        //父类，子类实现
    }

    //成功获取最新的数据
    protected void getRefreshListSuccess(String json) {
        if (isSetDataToView()) {
            //最新的数据
            List<Map<String, Object>> refreshList = listener.getDataFromJson(json);
            //更新数据到recycle-list-view
            refreshListView.refreshData(refreshList, true);
        }
    }

    protected void notifyDataChanged() {
        refreshListView.notifyDataChanged();
    }

    protected void notifyDataChanged(int position) {
        refreshListView.notifyDataChanged(position);
    }

    @Override
    public void onPullRefresh() {
        getHttpData();
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onItemClick(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        listener.clickViewItem(viewHolder, adapterList, position);
    }

    @Override
    public void onShow(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        listener.showViewItem(viewHolder, adapterList, position);
    }
}
