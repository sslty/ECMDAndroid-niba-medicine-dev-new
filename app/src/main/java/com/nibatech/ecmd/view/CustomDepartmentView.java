package com.nibatech.ecmd.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.contact.Department;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomDepartmentView extends LinearLayout implements DepartmentView.DepartmentListener {
    private Activity activity;
    private boolean showCenterLetter;
    private int maxSelectCount;
    private final TagFlowLayout tagFlowLayout;
    private final DepartmentView departmentView;

    public CustomDepartmentView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        activity = (Activity) context;
        LayoutInflater.from(context).inflate(R.layout.view_department, this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomDepartmentView);
        try {
            showCenterLetter = typedArray.getBoolean(R.styleable.CustomDepartmentView_showCenterLetter, true);
            maxSelectCount = typedArray.getInteger(R.styleable.CustomDepartmentView_maxSelectCount, 3);
        } finally {
            typedArray.recycle();
        }
        tagFlowLayout = (TagFlowLayout) findViewById(R.id.tag_select_container);
        departmentView = (DepartmentView) findViewById(R.id.departmentView);

        departmentView.setShowCenterLetter(showCenterLetter);
        departmentView.setMaxSelectCount(maxSelectCount);
        departmentView.setDepartmentListener(this);

    }


    public void setShowCenterLetter(boolean showCenterLetter) {
        departmentView.setShowCenterLetter(showCenterLetter);
    }

    public void setMaxSelectCount(int maxSelectCount) {
        departmentView.setMaxSelectCount(maxSelectCount);
    }

    public void scrollToPosition(String department) {
        departmentView.onTouchingLetterChanged(department);
    }

    public void addDepartments(List<Department> departmentList) {
        departmentView.addDepartments(departmentList);
    }

    public Map<String, ArrayList<String>> getSelectedStringMap() {
        return departmentView.getSelectedStringMap();
    }

    private Map<Integer, Set<Integer>> getSelectedIntegerMap() {
        return departmentView.getSelectedIntegerMap();

    }

    public void clear() {
        getSelectedIntegerMap().clear();
        onTagSelected();
        departmentView.notifyDataSetChanged();
    }

    public List<String> getSelectList() {
        List<String> list = new ArrayList<>();
        for (DepartmentView.SelectBean selectBean : getSelectBeanList()) {
            list.add(selectBean.subDepartmentString);
        }
        return list;
    }


    private List<DepartmentView.SelectBean> getSelectBeanList() {
        return departmentView.getSelectedBeanList();
    }

    private void setTagFlowLayoutData() {
        TagAdapter<DepartmentView.SelectBean> tagAdapter = new TagAdapter<DepartmentView.SelectBean>(getSelectBeanList()) {
            @Override
            public View getView(FlowLayout parent, final int position, DepartmentView.SelectBean selectBean) {
                View view = View.inflate(activity, R.layout.view_tag_select, null);
                TextView textView = (TextView) view.findViewById(R.id.tv_tag);
                textView.setText(selectBean.subDepartmentString);

                ImageView ivDeleteImage = (ImageView) view.findViewById(R.id.iv_tag);
                ivDeleteImage.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        List<DepartmentView.SelectBean> list = getSelectBeanList();
                        for (DepartmentView.SelectBean selectBean : list) {
                            if (position == selectBean.position) {
                                removeSelectTag(selectBean.departmentIndex, selectBean.subDepartmentIndex);
                                break;
                            }
                        }
                    }
                });
                return view;
            }

        };

        tagFlowLayout.setAdapter(tagAdapter);
        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            private View clickedView;

            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                onTagClick(view, position, false);
                return false;
            }

            private void onTagClick(View view, int position, boolean showDeleteImage) {//showDeleteImage表示是否显示右上角的删除角标
                if (showDeleteImage) {
                    if (clickedView != null && clickedView != view) {
                        ImageView imageView = (ImageView) clickedView.findViewById(R.id.iv_tag);
                        if (imageView.getVisibility() == VISIBLE) {
                            imageView.setVisibility(INVISIBLE);
                        }
                    }
                    clickedView = view;
                    ImageView imageView = (ImageView) view.findViewById(R.id.iv_tag);
                    if (imageView.getVisibility() == VISIBLE) {
                        imageView.setVisibility(INVISIBLE);
                    } else {
                        imageView.setVisibility(VISIBLE);
                        scrollTo(position);
                    }
                } else {
                    scrollTo(position);
                }
            }

            private void scrollTo(int position) {
                List<DepartmentView.SelectBean> list = getSelectBeanList();
                for (DepartmentView.SelectBean selectBean : list) {
                    if (position == selectBean.position) {
                        scrollToPosition(selectBean.departmentString);
                        break;
                    }
                }
            }
        });
    }


    private void removeSelectTag(int departmentIndex, int subDepartmentIndex) {
        Map<Integer, Set<Integer>> map = getSelectedIntegerMap();
        Set<Integer> set = map.get(departmentIndex);
        set.remove(subDepartmentIndex);
        onTagSelected();//这个是更新select界面
        departmentView.notifyDataSetChanged();//这个是更新listview界面
    }


    @Override
    public void onTagSelected() {
        setTagFlowLayoutData();
    }


}