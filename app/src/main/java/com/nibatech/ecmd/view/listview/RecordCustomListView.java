package com.nibatech.ecmd.view.listview;

import android.content.Context;
import android.util.AttributeSet;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.view.BaseCustomRecycleListView;

/**
 * list布局   news
 */
public class RecordCustomListView extends BaseCustomRecycleListView {

    public RecordCustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getItemViewLayout() {
        return R.layout.list_details_case_record;
    }
}
