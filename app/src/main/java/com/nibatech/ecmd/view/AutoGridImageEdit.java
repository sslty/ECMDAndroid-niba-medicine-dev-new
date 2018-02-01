package com.nibatech.ecmd.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.photo.PhotoEditActivity;
import com.nibatech.ecmd.activity.photo.PhotoViewActivity;

import java.util.ArrayList;


public class AutoGridImageEdit extends LinearLayout {
    public static final String ACTIVITY_SHOW_IMAGE_PATH_LIST = "show_image_path_list";
    private ArrayList<String> imagePathList;
    private ArrayList<Drawable> drawableArrayList;
    private GridViewAdapter gridViewAdapter;
    private Activity activity;
    private int maxImageNumber;
    private int length;
    private final AutoGridView autoGridView;
    private final Integer numColumns;

    public AutoGridImageEdit(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        activity = (Activity) context;
        LayoutInflater.from(context).inflate(R.layout.view_image_list, this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoGridImageEdit, 0, 0);

        try {
            numColumns = typedArray.getInteger(R.styleable.AutoGridImageEdit_numColumns, 3);
            maxImageNumber = typedArray.getInteger(R.styleable.AutoGridImageEdit_maxImageNumber, 6);
        } finally {
            typedArray.recycle();
        }
        imagePathList = new ArrayList<>();
        drawableArrayList = new ArrayList<>();
        autoGridView = (AutoGridView) findViewById(R.id.agv_image_container);
        autoGridView.setNumColumns(numColumns);
        gridViewAdapter = new GridViewAdapter();
        autoGridView.setAdapter(gridViewAdapter);
        autoGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (maxImageNumber == 1 && drawableArrayList.size() == 1) {
                    selectShowPhoto(imagePathList, position);
                } else {
                    if (position < imagePathList.size()) {
                        selectShowPhoto(imagePathList, position);
                    } else {
                        openCameraOrGallery();
                    }
                }
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

    public void removeAllImages(){
        imagePathList.clear();
        addImages(new ArrayList<String>());
    }

    //当点击事件发生后,且多选图片完成,onActivityResult的时候调用
    public void addImages(ArrayList<String> pathList) {
        imagePathList.addAll(pathList);
        drawableArrayList.clear();
        for (String path : imagePathList) {
            Bitmap bitmap = BitmapFactory.decodeFile(path, null);
            Drawable drawable = new BitmapDrawable(bitmap);
            drawableArrayList.add(drawable);
        }
        gridViewAdapter.notifyDataSetChanged();
    }

    //删除图片
    public void removeImage(String path) {
        for (int i = 0; i < imagePathList.size(); i++) {
            if (path.compareTo(imagePathList.get(i)) == 0) {
                drawableArrayList.remove(i);
                imagePathList.remove(i);
                gridViewAdapter.notifyDataSetChanged();
                break;
            }
        }
    }

    //得到控件的路径名称
    public ArrayList<String> getImagesUrl() {
        return imagePathList;
    }

    class GridViewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return drawableArrayList.size() == maxImageNumber ? drawableArrayList.size() : drawableArrayList.size() + 1;
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
            ImageView imageView = new ImageView(activity);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            AbsListView.LayoutParams paramsImageView =
                    new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT,
                            AbsListView.LayoutParams.WRAP_CONTENT);
            paramsImageView.width = length / numColumns;
            paramsImageView.height = paramsImageView.width;
            imageView.setLayoutParams(paramsImageView);

            if (position == drawableArrayList.size()) {
                imageView.setBackgroundResource(R.drawable.camera);
            } else {
                imageView.setImageDrawable(drawableArrayList.get(position));
            }

            return imageView;
        }
    }

    //打开已显示的图片
    private void selectShowPhoto(ArrayList<String> pathList, int position) {
        Intent intent = new Intent();
        intent.putExtra(ACTIVITY_SHOW_IMAGE_PATH_LIST, pathList);
        intent.putExtra(AutoGridImageView.ACTIVITY_SHOW_IMAGE_POSITION, position);
        intent.putExtra(PhotoViewActivity.VIEW_ID, getId());
        intent.setClass(activity, PhotoEditActivity.class);
        activity.startActivityForResult(intent, PhotoViewActivity.REQUIRE_TYPE_DELETE_IMAGE);
    }

    private void openCameraOrGallery() {
        new AlertDialog.Builder(activity)
                .setTitle("请选择:")
                .setSingleChoiceItems(new String[]{"相机", "图库"}, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int type;
                        Intent intent = new Intent();
                        if (i == 0) {
                            type = PhotoViewActivity.REQUIRE_TYPE_CAMERA;
                        } else {
                            type = PhotoViewActivity.REQUIRE_TYPE_GALLERY;
                            intent.putExtra(PhotoViewActivity.IMAGE_NUM, maxImageNumber - imagePathList.size());//选择数量
                        }
                        intent.setClass(activity, PhotoViewActivity.class);
                        //传给PhotoViewActivity，进入图库或者相机
                        intent.putExtra(PhotoViewActivity.REQUIRE_TYPE, type);
                        intent.putExtra(PhotoViewActivity.VIEW_ID, getId());
                        //告诉调用的activity，返回的数据
                        activity.startActivityForResult(intent, type);
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }
}