package com.nibatech.ecmd.activity.mien;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.mien.list.MienListFragment;
import com.nibatech.ecmd.fragment.mien.list.MomentListFragment;

import java.util.ArrayList;


/**
 * 医生端   风采
 */
public class MienDoctorActivity extends SlidingTabActivity {
    private String[] mTopTitles = new String[]{
            "中医圈", "风采"
    };

    private ArrayList<Fragment> fragments = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("风采");

        fragments.add(new MomentListFragment());
        fragments.add(new MienListFragment());

        addMultiTabFragments(fragments, mTopTitles);
    }
}
