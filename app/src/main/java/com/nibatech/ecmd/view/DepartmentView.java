package com.nibatech.ecmd.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.contact.Department;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DepartmentView extends LinearLayout implements SideBar.OnTouchingLetterChangedListener, SectionIndexer {
    private Activity activity;
    private final ListView lvDepartment;
    private final SideBar sbDepartment;
    private final TextView tvDepartment;
    private DepartmentAdapter departmentAdapter;
    private List<Department> departmentList = new ArrayList<>();
    private boolean showCenterLetter;
    private int maxSelectCount;
    private DepartmentListener departmentListener;

    public DepartmentView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        activity = (Activity) context;
        LayoutInflater.from(context).inflate(R.layout.view_sidebar_index, this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DepartmentView);
        try {
            showCenterLetter = typedArray.getBoolean(R.styleable.DepartmentView_showCenterLetter, true);
            maxSelectCount = typedArray.getInteger(R.styleable.DepartmentView_maxSelectCount, 3);
        } finally {
            typedArray.recycle();
        }
        lvDepartment = (ListView) findViewById(R.id.lv_sidebar);
        sbDepartment = (SideBar) findViewById(R.id.sb_sidebar);
        tvDepartment = (TextView) findViewById(R.id.tv_sidebar);

        departmentAdapter = new DepartmentAdapter();
        lvDepartment.setAdapter(departmentAdapter);

        if (showCenterLetter) {
            sbDepartment.setTextView(tvDepartment);
        }
        sbDepartment.setOnTouchingLetterChangedListener(this);

    }

    public void setDepartmentListener(DepartmentListener departmentListener) {
        this.departmentListener = departmentListener;
    }


    public void addDepartments(List<Department> departmentList) {
        if (departmentList == null || departmentList.size() == 0) {
            return;
        }
        this.departmentList = departmentList;
        Set<String> set = new LinkedHashSet<>();
        for (Department department : this.departmentList) {
            set.add(department.getDepartment());
        }
        sbDepartment.setSideBarLetter(set, false);
        departmentAdapter.notifyDataSetChanged();
    }

    public Map<String, ArrayList<String>> getSelectedStringMap() {
        return departmentAdapter.getSelectedStringMap();
    }

    public Map<Integer, Set<Integer>> getSelectedIntegerMap() {
        return departmentAdapter.getSelectedIntegerMap();
    }

    public List<SelectBean> getSelectedBeanList() {
        return departmentAdapter.getSelectedBeanList();
    }

    public void setShowCenterLetter(boolean showCenterLetter) {
        if (showCenterLetter) {
            sbDepartment.setTextView(tvDepartment);
        } else {
            sbDepartment.setTextView(null);
        }
    }

    public void notifyDataSetChanged() {
        departmentAdapter.notifyDataSetChanged();
    }

    public void setMaxSelectCount(int maxSelectCount) {
        this.maxSelectCount = maxSelectCount;
    }


    @Override
    public void onTouchingLetterChanged(String s) {
        for (int i = 0; i < departmentList.size(); i++) {
            if (s.equals(departmentList.get(i).getDepartment())) {
                lvDepartment.setSelection(i);
            }
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    @Override
    public int getPositionForSection(int section) {
        return section;
    }

    @Override
    public int getSectionForPosition(int position) {
        return position;
    }

    class DepartmentAdapter extends BaseAdapter {
        Map<Integer, Set<Integer>> selectedMap = new HashMap<>();

        @Override
        public int getCount() {
            return departmentList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = View.inflate(activity, R.layout.list_department, null);

            TagFlowLayout tagDepartment = (TagFlowLayout) view.findViewById(R.id.tag_department);

            TagAdapter<String> tagAdapter = new TagAdapter<String>(departmentList.get(position).getSubDepartmentList()) {
                @Override
                public View getView(com.zhy.view.flowlayout.FlowLayout parent, int position, String subDepartment) {
                    TextView textView = (TextView) View.inflate(activity, R.layout.view_text, null);
                    textView.setText(subDepartment);
                    return textView;
                }
            };
            tagDepartment.setMaxSelectCount((selectedMap.get(position) != null ? selectedMap.get(position).size() : 0) + maxSelectCount - getSelectedBeanList().size());
            tagDepartment.setAdapter(tagAdapter);
            tagAdapter.setSelectedList(selectedMap.get(position));
            tagDepartment.setOnSelectListener(new TagFlowLayout.OnSelectListener() {

                @Override
                public void onSelected(Set<Integer> selectPosSet) {
                    if (getSelectedBeanList().size() < maxSelectCount) {
                        selectedMap.put(position, selectPosSet);//符合此条件，调用此函数，既可以增加，又可以减少所选择的标签
                    } else if (selectedMap.get(position) != null && selectedMap.get(position).size() == selectPosSet.size() + 1) {//+1表示当数目比之前减少1，即取消选择时
                        selectedMap.put(position, selectPosSet);//符合此条件，调用此函数，只能减少所选择的标签
                    }
                    notifyDataSetChanged();
                    if (departmentListener != null) {
                        departmentListener.onTagSelected();
                    }
                }
            });

            tagDepartment.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    if (getSelectedBeanList().size() >= maxSelectCount) {
                        Toast.makeText(activity, "最多选择" + maxSelectCount + "条标签", Toast.LENGTH_SHORT).show();
                    }
                    return false;
                }
            });


            TextView tvLetter = (TextView) view.findViewById(R.id.department_catalog);
            tvLetter.setVisibility(View.VISIBLE);
            tvLetter.setText(departmentList.get(position).getDepartment());

            return view;
        }

        private Map<Integer, Set<Integer>> getSelectedIntegerMap() {
            return selectedMap;
        }

        private Map<String, ArrayList<String>> getSelectedStringMap() {
            Map<String, ArrayList<String>> map = new HashMap<>();
            for (Map.Entry<Integer, Set<Integer>> entry : selectedMap.entrySet()) {
                ArrayList<String> stringList = new ArrayList<>();
                for (Integer set : entry.getValue()) {
                    stringList.add(departmentList.get(entry.getKey()).getSubDepartmentList().get(set));
                }
                if (stringList.size() > 0) {
                    map.put(departmentList.get(entry.getKey()).getDepartment(), stringList);
                }
            }
            return map;
        }

        private List<SelectBean> getSelectedBeanList() {
            int i = 0;
            List<SelectBean> list = new ArrayList<>();
            for (Map.Entry<Integer, Set<Integer>> entry : selectedMap.entrySet()) {
                for (Integer set : entry.getValue()) {
                    SelectBean selectBean = new SelectBean();
                    selectBean.departmentIndex = entry.getKey();
                    selectBean.subDepartmentIndex = set;
                    selectBean.position = i++;
                    selectBean.departmentString = departmentList.get(entry.getKey()).getDepartment();
                    selectBean.subDepartmentString = departmentList.get(entry.getKey()).getSubDepartmentList().get(set);
                    list.add(selectBean);
                }
            }
            return list;
        }
    }

    protected class SelectBean {
        int departmentIndex;
        int subDepartmentIndex;
        int position;

        String departmentString;
        String subDepartmentString;
    }

    public interface DepartmentListener {
        void onTagSelected();
    }

}