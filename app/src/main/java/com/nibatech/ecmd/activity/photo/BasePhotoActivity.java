package com.nibatech.ecmd.activity.photo;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.AutoGridImageEdit;
import com.nibatech.ecmd.view.AutoGridImageView;
import com.stx.xhb.mylibrary.XBanner;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;


public class BasePhotoActivity extends Activity implements XBanner.XBannerAdapter {
    private ArrayList<String> imgUrlList;
    private ArrayList<String> imgPathList;
    private ArrayList<String> imgList;
    private int position;
    private int viewId;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_show_photo_list);
        //显示图片
        imgUrlList = getIntent().getStringArrayListExtra(AutoGridImageView.ACTIVITY_SHOW_IMAGE_URL_LIST);
        imgPathList = getIntent().getStringArrayListExtra(AutoGridImageEdit.ACTIVITY_SHOW_IMAGE_PATH_LIST);

        imgList = imgUrlList != null ? imgUrlList : imgPathList;
        position = getIntent().getIntExtra(AutoGridImageView.ACTIVITY_SHOW_IMAGE_POSITION, 0);
        viewId = getIntent().getIntExtra(PhotoViewActivity.VIEW_ID, -1);
        XBanner xBanner = (XBanner) findViewById(R.id.xbanner_photo);
        snackbar = Snackbar.make(xBanner, "您是否要删除此图片？", Snackbar.LENGTH_INDEFINITE);

        xBanner.setData(imgList, null);
        xBanner.setmAdapter(this);
        xBanner.getViewPager().setCurrentItem(position);
        xBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (snackbar.isShown()) {
                    snackbar.dismiss();
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void loadBanner(XBanner banner, View view, int position) {
        String pathUrl = imgList.get(position);
        if (imgUrlList != null) {
            UIUtils.loadNetworkImage(BasePhotoActivity.this, imgList.get(position), (ImageView) view);
        } else {
            UIUtils.loadLocalImage(imgList.get(position), (ImageView) view);
        }
        PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher((ImageView) view);
        photoViewAttacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                if (snackbar.isShown()) {
                    snackbar.dismiss();
                } else {
                    finish();
                }
            }
        });
        photoViewAttacher.setOnSingleFlingListener(new PhotoViewAttacher.OnSingleFlingListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (snackbar.isShown()) {
                    snackbar.dismiss();
                }
                return true;
            }
        });
        if (getOnLongClickListener(pathUrl, viewId) != null) {
            photoViewAttacher.setOnLongClickListener(getOnLongClickListener(pathUrl, viewId));
        }
    }

    protected View.OnLongClickListener getOnLongClickListener(String pathUrl, int viewId) {
        return null;
    }

    protected Snackbar getSnackbar() {
        return snackbar;
    }
}
