package com.nibatech.ecmd.fragment.base;


import android.support.v7.widget.RecyclerView;

import com.nibatech.ecmd.view.BaseCustomRecycleListView;

import java.util.List;
import java.util.Map;

/**
 * 医生端/患者端/游客端   没有上拉加载更多，下拉刷新的消息会话列表message-list-view
 */
public class MessageListViewFragment extends ViewPageLoadingFragment implements
        BaseCustomRecycleListView.CustomListViewListener {
    public interface ShowMessageListViewDataListener {
        void getHostUrlDataSuccess(String json);

        //点击每一个item
        void clickViewItem(RecyclerView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position);

        //显示每一条item
        void showViewItem(BaseCustomRecycleListView.ViewHolder viewHolder,
                          List<Map<String, Object>> adapterList, int position);

        //对数据库进行排序后填入list
        List<Map<String, Object>> sortRealmToList();
    }

    private BaseCustomRecycleListView messageListView;//带消息提示的会话list
    private ShowMessageListViewDataListener listener;//必须实现的接口

    //初始化recycle-list-view
    protected void initMessageListView(BaseCustomRecycleListView view,
                                       ShowMessageListViewDataListener listener) {
        //事件监听者
        messageListView = view;
        messageListView.setCustomListViewListener(this);
        this.listener = listener;
        setCreateView(true);
    }

    @Override
    protected void getHttpData() {
        //父类，子类实现
    }

    //更新数据
    protected void showSimpleListData() {
        //最新的数据
        List<Map<String, Object>> simpleList = listener.sortRealmToList();
        //更新数据到recycle-list-view
        messageListView.refreshData(simpleList, false);
    }

    protected void notifyDataChanged() {
        messageListView.notifyDataChanged();
    }

    protected void notifyDataChanged(int position) {
        messageListView.notifyDataChanged(position);
    }

    @Override
    public void onPullRefresh() {
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onItemClick(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        listener.clickViewItem(viewHolder, adapterList, position);
    }

    @Override
    public void onShow(BaseCustomRecycleListView.ViewHolder viewHolder,
                       List<Map<String, Object>> adapterList, int position) {
        listener.showViewItem(viewHolder, adapterList, position);
    }

}
