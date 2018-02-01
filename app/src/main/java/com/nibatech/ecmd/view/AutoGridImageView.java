package com.nibatech.ecmd.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.photo.PhotoShowActivity;
import com.nibatech.ecmd.common.request.volley.VolleyNetworkImage;
import com.nibatech.ecmd.utils.UIUtils;

import java.util.ArrayList;

public class AutoGridImageView extends BaseView {
    public static final String ACTIVITY_SHOW_IMAGE_URL_LIST = "show_image_url_list";
    public static final String ACTIVITY_SHOW_IMAGE_POSITION = "show_image_position";
    private ArrayList<String> imagePathList = new ArrayList<>();
    private ArrayList<String> imageDesList = new ArrayList<>();
    private AutoGridView autoGridView;
    private GridViewAdapter gridViewAdapter;
    private int numColumns;
    private int maxImageNumber;
    private int length;


    public AutoGridImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = getSuccessView();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoGridImageView, 0, 0);
        try {
            numColumns = typedArray.getInteger(R.styleable.AutoGridImageView_numColumns, 3);
            maxImageNumber = typedArray.getInteger(R.styleable.AutoGridImageView_maxImageNumber, 6);
        } finally {
            typedArray.recycle();
        }
        autoGridView = (AutoGridView) view.findViewById(R.id.agv_image_container);
        autoGridView.setNumColumns(numColumns);
        gridViewAdapter = new GridViewAdapter();
        autoGridView.setAdapter(gridViewAdapter);
        autoGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                selectShowPhoto(imagePathList, position);
            }
        });
        autoGridView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (length != 0) {//当异步请求的时候，先绘制完成，后请求成功，所以length永远为0
                    autoGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                length = autoGridView.getWidth() - 2 * autoGridView.getPaddingLeft() - (numColumns - 1) * autoGridView.getHorizontalSpacing();
                gridViewAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected int getSuccessViewLayoutId() {
        return R.layout.view_image_list;
    }

    private void selectShowPhoto(ArrayList<String> urlList, int position) {
        Intent intent = new Intent();
        intent.putExtra(ACTIVITY_SHOW_IMAGE_URL_LIST, urlList);
        intent.putExtra(ACTIVITY_SHOW_IMAGE_POSITION, position);
        intent.setClass(activity, PhotoShowActivity.class);
        activity.startActivity(intent);
    }

    public void addImage(String path) {
        ArrayList<String> list = new ArrayList<>();
        list.add(path);
        addImages(list);
    }

    public void addImage(String path, String description) {
        ArrayList<String> pathList = new ArrayList<>();
        ArrayList<String> desList = new ArrayList<>();
        pathList.add(path);
        desList.add(description);
        addImages(pathList, desList);
    }

    public void addImages(ArrayList<String> pathList) {
        setVisibility(Visibility.SUCCESS);
        if (imagePathList.size() + pathList.size() > maxImageNumber) {
            Toast.makeText(activity, "添加图片不能超过" + maxImageNumber + "张", Toast.LENGTH_SHORT).show();
            return;
        }
        imagePathList = pathList;
        gridViewAdapter.notifyDataSetChanged();
    }

    public void addImages(ArrayList<String> pathList, ArrayList<String> desList) {
        if (imagePathList.size() + pathList.size() > maxImageNumber) {
            Toast.makeText(activity, "添加图片不能超过" + maxImageNumber + "张", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pathList.size() != desList.size()) {
            Toast.makeText(activity, "图片或者图片描述未填写", Toast.LENGTH_SHORT).show();
            return;
        }
        imagePathList = pathList;
        imageDesList = desList;
        gridViewAdapter.notifyDataSetChanged();
    }

    class GridViewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return imagePathList.size();
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
            LinearLayout llContainer = new LinearLayout(activity);
            llContainer.setOrientation(LinearLayout.VERTICAL);
            llContainer.setBackgroundResource(R.drawable.shape_button_circle_white);

            ImageView imageView = new ImageView(activity);
            imageView.setBackgroundResource(R.drawable.shape_button_circle_right_green);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            loadNetworkImage(imagePathList.get(position), imageView);

            AbsListView.LayoutParams paramsImageView = new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT);
            paramsImageView.width = length / numColumns - 2 * UIUtils.dip2px(5);
            paramsImageView.height = paramsImageView.width;
            imageView.setLayoutParams(paramsImageView);

            TextView textView = new TextView(activity);
            textView.setText(imageDesList.size() > 0 ? imageDesList.get(position) : "");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.BLACK);

            AbsListView.LayoutParams paramsTextView = new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT);
            paramsTextView.height = paramsImageView.height / 4;
            paramsTextView.width = paramsImageView.width;
            textView.setLayoutParams(paramsTextView);

            llContainer.addView(imageView);
            llContainer.addView(textView);

            AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT);
            llContainer.setPadding(UIUtils.dip2px(5), UIUtils.dip2px(5), UIUtils.dip2px(5), UIUtils.dip2px(5));
            params.width = length / numColumns;
            params.height = imageDesList.size() > 0 ? params.width + paramsTextView.height : params.width;
            llContainer.setLayoutParams(params);

            return llContainer;
        }
    }

    //同步缓存请求
    private void loadNetworkImage(String url, ImageView imageView) {
        ImageLoader mImageLoader = VolleyNetworkImage.getInstance(activity).getImageLoader();
        mImageLoader.get(url, ImageLoader.getImageListener(imageView,
                R.drawable.image_show_loading, R.drawable.image_show_error));
    }
}