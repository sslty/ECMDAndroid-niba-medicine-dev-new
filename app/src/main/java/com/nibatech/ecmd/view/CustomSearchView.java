package com.nibatech.ecmd.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;


public class CustomSearchView extends LinearLayout implements CustomEditText.CustomEditTextListener {
    private CustomEditText cetSearch;
    private TextView tvSearch;
    private MySearchViewListener listener;
    private TagFlowLayout tagFlowLayout;
    private SlidingTabActivity activity;
    private LinearLayout llTagContainer;
    private List<String> tagList = new ArrayList<>();

    public CustomSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        activity = (SlidingTabActivity) context;
        LayoutInflater.from(context).inflate(R.layout.view_search_view, this);
        initView();
        setViewListener();
    }

    private void initView() {
        cetSearch = (CustomEditText) findViewById(R.id.cet_search);
        cetSearch.setCustomEditListener(this);
        tvSearch = (TextView) findViewById(R.id.tv_search);
        llTagContainer = (LinearLayout) findViewById(R.id.ll_tag_container);
        tagFlowLayout = (TagFlowLayout) findViewById(R.id.tag_select_container);
    }

    public void setSearchViewListener(MySearchViewListener listener) {
        this.listener = listener;
    }

    public void addData(String tag) {
        if (TextUtils.isEmpty(tag)) {
            return;
        }
        this.tagList.add(tag);
        setTagFlowLayoutAdapter(this.tagList);
    }

    public void setTagViewData(List<String> tagList) {
        if (tagList.size() > 0) {
            this.tagList.addAll(tagList);
            setTagFlowLayoutAdapter(this.tagList);
        }
    }

    private void setTagFlowLayoutAdapter(List<String> tagList) {
        tagFlowLayout.setAdapter(new TagAdapter<String>(tagList) {
            @Override
            public View getView(com.zhy.view.flowlayout.FlowLayout parent, int position, String tag) {
                TextView textView = (TextView) View.inflate(activity, R.layout.view_tag_container, null);
                textView.setText(tag);
                return textView;
            }
        });
    }

    private void setViewListener() {
        tvSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    setTagFlowLayoutVisible(cetSearch.getText().toString());
                    listener.onSearch(cetSearch.getText().toString());//请求数据时间长，放在上个函数后面，而且异步
                }
            }
        });

        cetSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (listener != null) {
                        setTagFlowLayoutVisible(cetSearch.getText().toString());
                        listener.onSearch(cetSearch.getText().toString());
                    }
                    return true;
                }
                return false;
            }
        });

        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, com.zhy.view.flowlayout.FlowLayout parent) {
                if (listener != null) {
                    setTagFlowLayoutVisible(false);//不管tab显示不显示，后面统一处理，前面异步请求数据，这句话就有风险，这里让不显示，异步请求回来让显示，所以异步不行
                    listener.onSearch(((TextView) view).getText().toString());//先请求数据，每次请求addtab会使Tab显示，但是异步请求数据，后面一句话就不顶用了
                }
                return false;
            }
        });
    }

    private void setTagFlowLayoutVisible(String search) {
        if (TextUtils.isEmpty(search)) {
            setTagFlowLayoutVisible(true);
        } else {
            setTagFlowLayoutVisible(false);
        }
    }

    public void setTagFlowLayoutVisible(boolean visible) {//异步请求数据的时候调用
        llTagContainer.setVisibility(visible ? VISIBLE : GONE);
        setTopTabVisible(!visible);//在activity中不能每次请求服务器addTab，因为每add一次TabLayout,就会使TabLayout重新显示
    }

    private void setTopTabVisible(boolean visible) {
        if (visible) {
            activity.setTopTabVisible(VISIBLE);
        } else {
            activity.setTopTabVisible(INVISIBLE);//这里是invisible，保证fragment被add,如果是gone,则fragment刚开始总不能被add
        }
    }

    @Override
    public void onClear() {
        setTagFlowLayoutVisible(true);
    }

    public interface MySearchViewListener {
        void onSearch(String search);
    }


}
