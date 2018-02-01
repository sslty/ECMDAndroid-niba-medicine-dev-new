package com.nibatech.ecmd.activity.photo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;


public class PhotoEditActivity extends BasePhotoActivity {

    @Override
    protected View.OnLongClickListener getOnLongClickListener(final String pathUrl, final int viewId) {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                getSnackbar().setAction("点击删除", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        //设置返回数据
                        intent.putExtra(PhotoViewActivity.RESULT_PATH, pathUrl);
                        intent.putExtra(PhotoViewActivity.VIEW_ID, viewId);
                        setResult(Activity.RESULT_OK, intent);
                        //关闭Activity
                        finish();

                    }
                }).setActionTextColor(Color.WHITE).show();
                return true;
            }
        };
    }


}
