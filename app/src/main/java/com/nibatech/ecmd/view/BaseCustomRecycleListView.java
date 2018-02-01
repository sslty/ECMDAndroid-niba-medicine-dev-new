package com.nibatech.ecmd.view;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import lecho.lib.hellocharts.view.ColumnChartView;


public abstract class BaseCustomRecycleListView extends LinearLayout {
    private static int PAGE_PER = BaseVolleyRequest.PAGES_PER_PAGE_NORMAL;
    private CustomListViewListener customListViewListener;
    private MyAdapter myAdapter;
    private UltimateRecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private PtrFrameLayout ptrFrameLayout;
    private Context context;
    private int offset;

    public BaseCustomRecycleListView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.view_custom_list_view, this);
        recyclerView = (UltimateRecyclerView) findViewById(R.id.ultimate_recycler_view);

        recyclerView.setEmptyView(R.layout.refresh_bottom_progressbar, UltimateRecyclerView.EMPTY_CLEAR_ALL);
        recyclerView.setLoadMoreView(LayoutInflater.from(context)
                .inflate(R.layout.refresh_bottom_progressbar, null));
        recyclerView.mSwipeRefreshLayout = null;

        //必须放在设置adapter之前
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        myAdapter = new MyAdapter(new ArrayList<Map<String, Object>>());
        recyclerView.setAdapter(myAdapter);
        recyclerView.setItemAnimator(null);

        recyclerView.disableLoadmore();
        recyclerView.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(final int itemsCount, final int maxLastVisiblePosition) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (customListViewListener != null) {
                            customListViewListener.onLoadMore();
                        }
                    }
                }, 500);
            }
        });

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                offset = recyclerView.computeVerticalScrollOffset();
            }
        });

    }


    public interface CustomListViewListener {
        void onPullRefresh();

        void onLoadMore();

        void onItemClick(ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position);

        void onShow(ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position);
    }


    public void setCustomListViewListener(CustomListViewListener listener) {
        this.customListViewListener = listener;
    }

    public void notifyDataChanged() {
        myAdapter.notifyDataSetChanged();
    }

    public void notifyDataChanged(int position) {
        myAdapter.notifyItemChanged(position);
    }

    public void addData(List<Map<String, Object>> list) {
        insertLast(list);
    }

    /**
     * @param list
     * @param canRefreshAndLoadMore true:只有下拉刷新，false：既没有下拉刷新，也没有加载更多
     */
    public void refreshData(List<Map<String, Object>> list, boolean canRefreshAndLoadMore) {
        myAdapter.innerClear();
        linearLayoutManager.scrollToPosition(0);
        setCustomSwipeToRefresh(canRefreshAndLoadMore);
        addData(list);
    }

    public void clear() {
        myAdapter.innerClear();
    }

    /**
     * 默认既有下拉刷新，又有加载更多
     *
     * @param list
     */
    public void refreshData(List<Map<String, Object>> list) {
        myAdapter.innerClear();
        linearLayoutManager.scrollToPosition(0);
        setCustomSwipeToRefresh(true);
        if (list != null && list.size() == PAGE_PER) {
            recyclerView.reenableLoadmore();
        }
        addData(list);
    }

    private void insertLast(List<Map<String, Object>> list) {
        if (list != null && list.size() > 0) {
            setLoadMoreHasData(true);
            for (Map<String, Object> map : list) {
                myAdapter.innerInsertLast(map);
            }
        } else {
            setLoadMoreHasData(false);
        }
    }


    private void setLoadMoreHasData(boolean hasData) {
        myAdapter.setLoadMoreHasData(hasData);
    }


    class MyAdapter extends UltimateViewAdapter {

        public List<Map<String, Object>> adapterList;

        MyAdapter(List<Map<String, Object>> adapterList) {
            this.adapterList = adapterList;
        }

        void innerInsertLast(Map<String, Object> map) {
            insertLastInternal(adapterList, map);
        }

        void setAdapterList(List<Map<String, Object>> adapterList) {
            this.adapterList = adapterList;
        }

        void innerClear() {
            clearInternal(adapterList);
        }

        void setLoadMoreHasData(boolean hasData) {
            if (hasData) {
                getCustomLoadMoreView().findViewById(R.id.ll_has_more).setVisibility(VISIBLE);
                getCustomLoadMoreView().findViewById(R.id.ll_no_more).setVisibility(GONE);
            } else {
                getCustomLoadMoreView().findViewById(R.id.ll_has_more).setVisibility(GONE);
                getCustomLoadMoreView().findViewById(R.id.ll_no_more).setVisibility(VISIBLE);
            }
        }

        @Override
        public RecyclerView.ViewHolder newFooterHolder(View view) {
            return new UltimateRecyclerviewViewHolder<>(view);
        }

        @Override
        public RecyclerView.ViewHolder newHeaderHolder(View view) {
            return new UltimateRecyclerviewViewHolder<>(view);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(getItemViewLayout(), parent, false);
            final ViewHolder viewHolder = new ViewHolder(itemView);
            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (customListViewListener != null) {
                        customListViewListener.onItemClick(viewHolder, adapterList, (Integer) view.getTag());
                    }
                }
            });
            return viewHolder;
        }

        @Override
        public int getAdapterItemCount() {
            return adapterList != null ? adapterList.size() : 0;
        }

        @Override
        public long generateHeaderId(int position) {
            return -1;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (position < getItemCount() && (customHeaderView != null ? position <= adapterList.size() : position < adapterList.size()) && (customHeaderView != null ? position > 0 : true)) {
                if (customListViewListener != null) {
                    customListViewListener.onShow((ViewHolder) holder, adapterList, position);
                }
                holder.itemView.setTag(position);
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
            return null;
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

        }
    }


    public class ViewHolder extends UltimateRecyclerviewViewHolder {
        private SparseArray<View> views;
        private View itemView;

        ViewHolder(View itemView) {
            super(itemView);
            this.views = new SparseArray<>();
            this.itemView = itemView;
        }

        public <T extends View> T getView(int viewId) {
            View view = views.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                views.put(viewId, view);
            }
            return (T) view;
        }

        public TextView getTextView(int viewId) {
            return getView(viewId);
        }

        public ImageView getImageView(int viewId) {
            return getView(viewId);
        }

        public Button getButton(int viewId) {
            return getView(viewId);
        }

        public RadioButton getRadioButton(int viewId) {
            return getView(viewId);
        }

        public CheckBox getCheckBox(int viewId) {
            return getView(viewId);
        }

        public ImageButton getImageButton(int viewId) {
            return getView(viewId);
        }

        public EditText getEditText(int viewId) {
            return getView(viewId);
        }

        public ListItemView getListItemView(int viewId) {
            return getView(viewId);
        }

        public ColumnChartView getColumnChartView(int viewId) {
            return getView(viewId);
        }

        public CustomColumnChartView getCustomColumnChartView(int viewId) {
            return getView(viewId);
        }

        public HeadItemView getHeadItemView(int viewId) {
            return getView(viewId);
        }
    }

    protected abstract int getItemViewLayout();


    public void setCustomSwipeToRefresh(final boolean canRefresh) {
        ptrFrameLayout = (PtrFrameLayout) findViewById(R.id.store_house_ptr_frame);
        ptrFrameLayout.autoRefresh(false);
//        StoreHouseHeader pullRefreshView = new StoreHouseHeader(context);
//        pullRefreshView.initWithString("Loading");
//        pullRefreshView.setTextColor(Color.BLACK);
        PullRefreshView pullRefreshView = new PullRefreshView(context);
        ptrFrameLayout.setHeaderView(pullRefreshView);
        ptrFrameLayout.addPtrUIHandler(pullRefreshView);
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View content, View header) {
                return canRefresh && offset == 0;
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout ptrFrameLayout) {
                ptrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrameLayout.refreshComplete();
                        if (customListViewListener != null) {
                            customListViewListener.onPullRefresh();
                        }
                    }
                }, 1800);
            }
        });

    }

}
