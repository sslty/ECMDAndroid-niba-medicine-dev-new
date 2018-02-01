package com.nibatech.ecmd.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.request.volley.VolleyNetworkImage;
import com.nibatech.ecmd.utils.UIUtils;

import java.util.ArrayList;


public class AutoGridImageOriginalView extends LinearLayout {

    private ArrayList<String> imagePathList = new ArrayList<>();

    private Activity activity;
    private int maxImageNumber;
    private final LinearLayout llImageList;

    public AutoGridImageOriginalView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        activity = (Activity) context;
        LayoutInflater.from(context).inflate(R.layout.view_image_list_origin, this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoGridImageOriginalView, 0, 0);
        try {
            maxImageNumber = typedArray.getInteger(R.styleable.AutoGridImageOriginalView_maxImageNumber, 6);
            llImageList = (LinearLayout) findViewById(R.id.ll_image_list_origin);
        } finally {
            typedArray.recycle();
        }

    }


    public void addImage(String path) {
        ArrayList<String> list = new ArrayList<>();
        list.add(path);
        addImages(list);
    }


    public void addImages(ArrayList<String> pathList) {
        if (imagePathList.size() + pathList.size() > maxImageNumber) {
            Toast.makeText(UIUtils.getContext(), "添加图片不能超过" + maxImageNumber + "张", Toast.LENGTH_SHORT).show();
            return;
        }
        imagePathList = pathList;
        setImageList();
    }


    private void setImageList() {
        for (int i = 0; i < imagePathList.size(); i++) {
            ImageView imageView = new ImageView(UIUtils.getContext());
            loadNetworkImage(imagePathList.get(i), imageView);
            llImageList.addView(imageView);
        }
    }

    //同步缓存请求
    private void loadNetworkImage(String url, ImageView imageView) {
        ImageLoader mImageLoader = VolleyNetworkImage.getInstance(activity).getImageLoader();
        mImageLoader.get(url, ImageLoader.getImageListener(imageView,
                R.drawable.image_show_loading, R.drawable.image_show_error));
    }
}
