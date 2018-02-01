package com.nibatech.ecmd.activity.mine;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.mine.ContactFragment;


public class ContactActivity extends SlidingTabActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("联系我们");
        addDefaultFragment(new ContactFragment());
    }
}
