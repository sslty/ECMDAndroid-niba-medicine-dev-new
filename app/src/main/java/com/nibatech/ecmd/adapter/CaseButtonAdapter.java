package com.nibatech.ecmd.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;


public class CaseButtonAdapter extends BaseAdapter {

    private ArrayList<Button> mDdata = new ArrayList<>();

    public CaseButtonAdapter(ArrayList<Button> data) {
        if (data != null) {
            this.mDdata = data;
        }
    }

    @Override
    public int getCount() {
        return mDdata.size();
    }

    @Override
    public Object getItem(int position) {
        return mDdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        return mDdata.get(position);
    }
}