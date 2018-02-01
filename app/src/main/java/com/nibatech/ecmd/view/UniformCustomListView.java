package com.nibatech.ecmd.view;

import android.content.Context;
import android.util.AttributeSet;

import com.nibatech.ecmd.R;


public class UniformCustomListView extends BaseCustomRecycleListView {

    public UniformCustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getItemViewLayout() {
        return R.layout.list_item;
    }
}
