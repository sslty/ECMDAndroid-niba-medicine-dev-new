package com.nibatech.ecmd.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.contact.Department;

import java.util.List;

public class DepartmentSearchView extends LinearLayout {
    private Activity activity;
    private boolean showCenterLetter;
    private int maxSelectCount;
    private final CustomDepartmentView departmentView;
    private final TextView tvClear;
    private final SearchView searchView;
    private CustomSearchViewListener listener;

    private List<Department> departmentList;
    private final ImageView ivSearch;

    public DepartmentSearchView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        activity = (Activity) context;
        LayoutInflater.from(context).inflate(R.layout.view_search, this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DepartmentSearchView);
        try {
            showCenterLetter = typedArray.getBoolean(R.styleable.DepartmentSearchView_showCenterLetter, true);
            maxSelectCount = typedArray.getInteger(R.styleable.DepartmentSearchView_maxSelectCount, 3);
        } finally {
            typedArray.recycle();
        }

        tvClear = (TextView) findViewById(R.id.tv_clear);
        ivSearch = (ImageView) findViewById(R.id.iv_search);
        searchView = (SearchView) findViewById(R.id.id_search_view);
        //设置该SearchView内默认显示的提示文本
        searchView.setQueryHint("查找");
        searchView.setIconifiedByDefault(false);
        searchView.setIconified(true);
        searchView.setSubmitButtonEnabled(false);


        departmentView = (CustomDepartmentView) findViewById(R.id.departView);
        departmentView.setShowCenterLetter(showCenterLetter);
        departmentView.setMaxSelectCount(maxSelectCount);

        setViewListener();

    }

    public void setSearchViewListener(CustomSearchViewListener listener){
        this.listener = listener;
    }

    public void addDepartments(List<Department> departmentList) {
        this.departmentList = departmentList;
        departmentView.addDepartments(departmentList);
    }

    public List<String> getSelectList() {
        return departmentView.getSelectList();
    }

    private void setViewListener() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String str) {
                scrollToItem(str);
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String str) {
                scrollToItem(str);
                return false;
            }

        });

        tvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                departmentView.clear();
            }
        });

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> list = getSelectList();
                if (list != null && list.size() > 0) {
                    if (listener != null) {
                        listener.onSearchClick();
                    }
                } else {
                    Toast.makeText(activity, "请至少选择一个标签", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void scrollToItem(String str) {
        for (Department department : departmentList) {
            if (department.getDepartment().contains(str)) {
                departmentView.scrollToPosition(department.getDepartment());
                break;
            } else {
                boolean scroll = false;
                for (String tag : department.getSubDepartmentList()) {
                    if (tag.contains(str)) {
                        scroll = true;
                        break;
                    }
                }
                if (scroll) {
                    departmentView.scrollToPosition(department.getDepartment());
                    break;
                }
            }
        }
    }

    public interface CustomSearchViewListener {
        void onSearchClick();
    }


}