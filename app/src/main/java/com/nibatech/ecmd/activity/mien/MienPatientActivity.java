package com.nibatech.ecmd.activity.mien;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.mien.list.MienListFragment;
import com.nibatech.ecmd.fragment.mien.list.EncyclopediaListFragment;

import java.util.ArrayList;

/**
 * 患者端   mien
 */
public class MienPatientActivity extends SlidingTabActivity {

    private String[] mTopTitles = new String[]{
            "风采", "百科"
    };

    private ArrayList<Fragment> fragments = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("风采");

        fragments.add(new MienListFragment());
        fragments.add(new EncyclopediaListFragment());

        addMultiTabFragments(fragments, mTopTitles);
    }
}
