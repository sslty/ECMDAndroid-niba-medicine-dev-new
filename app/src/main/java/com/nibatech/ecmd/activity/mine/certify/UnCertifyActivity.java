package com.nibatech.ecmd.activity.mine.certify;


import android.content.Intent;
import android.os.Bundle;

import com.nibatech.ecmd.activity.base.BaseActivity;
import com.nibatech.ecmd.fragment.mine.certify.UnCertifyFragment;

/**
 * 医生端   我的-个人资料-医师证明-未认证
 */
public class UnCertifyActivity extends BaseActivity {

    private UnCertifyFragment unCertifyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("医师证明");
        addDefaultFragment(unCertifyFragment = new UnCertifyFragment());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        unCertifyFragment.onActivityResult(requestCode, resultCode, data);
    }
}
