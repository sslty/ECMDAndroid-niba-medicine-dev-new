package com.nibatech.ecmd.activity.photo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.nibatech.ecmd.config.SaveFile;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import id.zelory.compressor.Compressor;


public class PhotoViewActivity extends TakePhotoActivity {
    public final static String REQUIRE_TYPE = "RequireType";
    public final static String IMAGE_NUM = "ImageNum";
    public final static String RESULT_IMAGES = "ResultImages";
    public final static String RESULT_PATH = "ResultPath";
    public final static String VIEW_ID = "ViewId";

    public final static int REQUIRE_TYPE_CAMERA = 4;//打开相机
    public final static int REQUIRE_TYPE_GALLERY = 5;//打开图库
    public final static int REQUIRE_TYPE_CUT_CAMERA = 6;//打开相机并对照片进行裁剪
    public final static int REQUIRE_TYPE_CUT_GALLERY = 7;//打开相机并对图库中对照片进行裁剪
    public final static int REQUIRE_TYPE_WRITE_EXTERNAL_STORAGE = 8;//文件读写
    public final static int REQUIRE_TYPE_DELETE_IMAGE = 9;//删除照片
    private ArrayList<String> resultImages;
    private String pathOpenCamera;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化图片
        resultImages = new ArrayList<>();
        TakePhoto takePhoto = getTakePhoto();

        //获取外面传来的请求命令
        int requireType = getIntent().getIntExtra(REQUIRE_TYPE, 0);

        switch (requireType) {
            case REQUIRE_TYPE_CAMERA://相机
                onPickCamera();//由于红米note相机照片尺寸过大，导致takePhoto无法正常压缩，需要自行打开相机
                break;
            case REQUIRE_TYPE_GALLERY://图库
                takePhoto.onPickMultiple(getIntent().getIntExtra(IMAGE_NUM, 1));
                break;
            case REQUIRE_TYPE_CUT_CAMERA://相机裁剪
                takePhoto.onPickFromCaptureWithCrop(getUri(), getCropOptions());
                break;
            case REQUIRE_TYPE_CUT_GALLERY://图库裁剪
                takePhoto.onPickFromGalleryWithCrop(getUri(), getCropOptions());
                break;
            default:
                break;
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        for (TImage tImage : result.getImages()) {
            resultImages.add(tImage.getPath());
        }

        onCompressImages();
        onResult();
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
        finish();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case Activity.RESULT_OK:
                if (requestCode == REQUIRE_TYPE_CAMERA) {
                    //保存相片文件路径
                    resultImages.add(pathOpenCamera);
                    //压缩
                    onCompressImages();
                    //退出
                    onResult();
                }
                break;
            case Activity.RESULT_CANCELED:
                finish();
                break;
        }

    }

    //压缩图片
    private void onCompressImages() {
        for (int i = 0; i < resultImages.size(); i++) {
            File file = compressByTools(new File(resultImages.get(i)));
            resultImages.set(i, file.getAbsolutePath());

            Log.d(SaveFile.DEBUG_TAG, "file=" + file.getName() + ", size=" +
                    getReadableFileSize(file.length()));
        }
    }

    //退出
    private void onResult() {
        Intent intent = new Intent();
        intent.putStringArrayListExtra(RESULT_IMAGES, resultImages);
        intent.putExtra(VIEW_ID, getIntent().getIntExtra(VIEW_ID, -1));
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void onPickCamera() {
        //请求打开相机权限
        requestPermissionCamera();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUIRE_TYPE_CAMERA) {
            if (permissions[0].equals(Manifest.permission.CAMERA)) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//允许
                    requestPermissionWriteExStorage();
                } else {
                    Toast.makeText(this, "请在设置中打开相机权限", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        } else if (requestCode == REQUIRE_TYPE_WRITE_EXTERNAL_STORAGE) {
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//允许
                    openCamera();
                } else {
                    Toast.makeText(this, "请在设置中打开文件存储权限", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    //打开相机
    private void openCamera() {
        //打开相机，用既定的imageUri存储原始尺寸大小图片，如果imageUri为Null，则为缩略图
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getUri());
        startActivityForResult(intent, REQUIRE_TYPE_CAMERA);
    }

    //启用压缩工具
    private File compressByTools(File file) {
        if (file.length() > 70 * 1024) {//大于70K才压缩
            return new Compressor.Builder(this)
                    .setMaxWidth(1080)
                    .setMaxHeight(720)
                    .setQuality(70)
                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES).getAbsolutePath())
                    .build()
                    .compressToFile(file);
        }

        return file;
    }

    //android 6.0的权限需要在app内申请
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestPermissionCamera() {
        int hasPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);//检查权限是否已经获取
        if (hasPermission != PackageManager.PERMISSION_GRANTED) {//相机权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                //Show an explanation to the user asynchronously.说明请求原因,之前被用户拒绝过
                Toast.makeText(this, "请在设置中打开相机权限", Toast.LENGTH_SHORT).show();
            } else {
                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        REQUIRE_TYPE_CAMERA);//申请相机权限
            }
        } else {
            requestPermissionWriteExStorage();
        }
    }

    //android 6.0的权限需要在app内申请
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestPermissionWriteExStorage() {
        int hasPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);//检查权限是否已经获取
        if (hasPermission != PackageManager.PERMISSION_GRANTED) {//申请文件存储权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //Show an explanation to the user asynchronously.说明请求原因,之前被用户拒绝过
                Toast.makeText(this, "请在设置中打开文件存储权限", Toast.LENGTH_SHORT).show();
            } else {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUIRE_TYPE_WRITE_EXTERNAL_STORAGE);//申请文件存储权限
            }
        } else {
            openCamera();
        }
    }

    private Uri getUri() {
        File file = new File(new File(Environment.getExternalStorageDirectory().toString()),
                System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();

        //保存路径
        pathOpenCamera = file.getAbsolutePath();

        return Uri.fromFile(file);
    }

    private CropOptions getCropOptions() {
        int height = 800;//裁剪高度
        int width = 800;//裁剪宽度

        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setAspectX(width).setAspectY(height);

        //true = TakePhoto自带, false = 第三方
        builder.setWithOwnCrop(true);

        return builder.create();
    }

    //计算图片大小
    public String getReadableFileSize(long size) {
        if (size <= 0) {
            return "0";
        }
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }


}
