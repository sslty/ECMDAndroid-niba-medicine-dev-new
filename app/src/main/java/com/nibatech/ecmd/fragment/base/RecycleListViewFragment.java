package com.nibatech.ecmd.fragment.base;


import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.view.BaseCustomRecycleListView;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;


/**
 * 医生端/患者端/游客端   带下拉刷新，上拉加载更多的recycle-list-view
 */
public class RecycleListViewFragment extends ViewPageLoadingFragment
        implements BaseCustomRecycleListView.CustomListViewListener {
    public interface ShowRecycleListViewDataListener {
        //设置下一页数据的url
        void setNextUrl(String url);

        //得到下一页数据的url
        String getNextUrl();

        //点击每一个item
        void clickViewItem(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position);

        //显示每一条item
        void showViewItem(BaseCustomRecycleListView.ViewHolder viewHolder,
                          List<Map<String, Object>> adapterList, int position);

        //从服务器获取数据成功后填充到data
        List<Map<String, Object>> getDataFromJson(String json);
    }

    private BaseCustomRecycleListView recycleListView;//带下拉刷新，上拉加载更多的list
    private String strNextUrl;//下一页数据到url
    private List<Map<String, Object>> refreshList;//最新的服务器数据
    private ShowRecycleListViewDataListener listener;//必须实现的接口

    @Override
    protected void getHttpData() {
        //父类，子类实现
    }

    //下拉刷新
    @Override
    public void onPullRefresh() {
        getHttpData();
    }

    //加载更多
    @Override
    public void onLoadMore() {
        loadMoreData();
    }

    //点击item事件
    @Override
    public void onItemClick(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        listener.clickViewItem(viewHolder, adapterList, position);
    }

    //显示每一条item的信息
    @Override
    public void onShow(BaseCustomRecycleListView.ViewHolder viewHolder,
                       List<Map<String, Object>> adapterList, int position) {
        listener.showViewItem(viewHolder, adapterList, position);
    }

    //初始化recycle-list-view
    protected void initRecycleListView(BaseCustomRecycleListView view, ShowRecycleListViewDataListener listener) {
        recycleListView = view;
        recycleListView.setCustomListViewListener(this);
        this.listener = listener;
        setCreateView(true);
    }

    //下拉刷新更多数据
    protected void loadRefreshData() {
        String url = getIntentSelfUrl();
        if (url != null) {
            CommonRequest.getUrlDataForPage(url, new VolleyCallback() {
                @Override
                public void onSuccessResponse(JSONObject success) {
                    getRefreshListSuccess(success.toString());
                }
            });
        } else {
            connectToHostFailure();
        }
    }

    //成功获取最新的数据
    protected void getRefreshListSuccess(String json) {
//        if (isSetDataToView()) {
//            //最新的数据
//            refreshList = listener.getDataFromJson(json);
//            //下一页的url
//            strNextUrl = listener.getNextUrl();
//            //更新数据到recycle-list-view
//            recycleListView.refreshData(refreshList);
//        }
        if (isSetDataToView()) {
            json = "";
            refreshList = listener.getDataFromJson(json);
            recycleListView.refreshData(refreshList);
        }
    }

    //加载更多的数据，如果strNextUrl为空，则没有数据，否则，直接拉取数据
    private void loadMoreData() {
        if (strNextUrl != null) {
            CommonRequest.getUrlData(strNextUrl, new VolleyCallback() {
                @Override
                public void onSuccessResponse(JSONObject success) {
                    getMoreListSuccess(success.toString());
                }
            });
        } else {
            recycleListView.addData(null);
        }
    }

    //成果获取更多的数据
    private void getMoreListSuccess(String json) {
        //更多的数据
        List<Map<String, Object>> moreList = listener.getDataFromJson(json);
        //下一页的url
        strNextUrl = listener.getNextUrl();
        //把数据增加到list
        refreshList.addAll(moreList);
        //把数据增加到recycle-list-view
        recycleListView.addData(moreList);
    }

    //添加数据并刷新list
    protected void addDataToRefreshRecycleView(List<Map<String, Object>> list) {
        recycleListView.refreshData(list);
    }
}
