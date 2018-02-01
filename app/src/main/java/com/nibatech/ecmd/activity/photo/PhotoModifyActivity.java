package com.nibatech.ecmd.activity.photo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.photo.PhotoModifyFragment;



public class PhotoModifyActivity extends SlidingTabActivity {
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("个人头像");

        fragment = new PhotoModifyFragment();
        addDefaultFragment(fragment);

        //显示更多按钮
        findMoreButton().setVisibility(View.VISIBLE);
        findMoreButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCameraOrGallery();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        fragment.onActivityResult(requestCode, resultCode, data);
    }

    private void openCameraOrGallery() {
        new AlertDialog.Builder(this)
                .setTitle("请选择:")
                .setSingleChoiceItems(new String[]{"相机", "图库"}, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int type;
                        if (i == 0) {
                            type = PhotoViewActivity.REQUIRE_TYPE_CUT_CAMERA;
                        } else {
                            type = PhotoViewActivity.REQUIRE_TYPE_CUT_GALLERY;
                        }
                        Intent intent = new Intent();
                        intent.setClass(getApplicationContext(), PhotoViewActivity.class);
                        //传给PhotoViewActivity，进入图库或者相机
                        intent.putExtra(PhotoViewActivity.REQUIRE_TYPE, type);
                        //告诉调用的activity，返回的数据
                        startActivityForResult(intent, type);
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }
}
