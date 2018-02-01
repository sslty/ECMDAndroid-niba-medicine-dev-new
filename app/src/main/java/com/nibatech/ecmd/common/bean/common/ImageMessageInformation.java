package com.nibatech.ecmd.common.bean.common;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.photo.PhotoShowActivity;
import com.nibatech.ecmd.adapter.MsgAdapter;
import com.nibatech.ecmd.common.bean.chat.ChatIdentityBean;
import com.nibatech.ecmd.common.bean.chat.ChatMaterialBean;
import com.nibatech.ecmd.common.message.ChatConversation;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.AutoGridImageEdit;
import com.tencent.TIMMessage;

import java.util.ArrayList;

import static com.nibatech.ecmd.view.AutoGridImageView.ACTIVITY_SHOW_IMAGE_POSITION;

public class ImageMessageInformation extends MessageInformation {
    private String imageUri;//信息内容.为imageUri
    private ArrayList<String> imagePathList;

    public ImageMessageInformation(ChatIdentityBean identityBean, ChatMaterialBean chatMaterialBean, boolean isMe) {
        super(identityBean, chatMaterialBean, isMe);
        this.imageUri = chatMaterialBean.getName();

    }

    public ImageMessageInformation(ChatConversation chatConversation, TIMMessage message, ChatIdentityBean identityBean, ChatMaterialBean chatMaterialBean, boolean isMe) {
        super(chatConversation, message, identityBean, chatMaterialBean, isMe);
        this.imageUri = chatMaterialBean.getName();

    }

    @Override
    public void showMessage(MsgAdapter.ViewHolder viewHolder, final Context context) {
        LinearLayout llChatView = getChatView(viewHolder, context);
        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, UIUtils.dip2px(10));
        Bitmap bitmap = getThumb(imageUri);
        if (bitmap == null) {//重构之前的goodgoodStudy表情，type就是MSG_IMAGE，所以会出现bitmap==null
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.chat_error);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "这是一张错误图片！", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            imagePathList = new ArrayList<>();
            imagePathList.add(imageUri);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra(AutoGridImageEdit.ACTIVITY_SHOW_IMAGE_PATH_LIST, imagePathList);
                    intent.putExtra(ACTIVITY_SHOW_IMAGE_POSITION, 0);
                    intent.setClass(context, PhotoShowActivity.class);
                    context.startActivity(intent);
                }
            });
        }
        imageView.setImageBitmap(bitmap);
        imageView.setLayoutParams(params);
        if (IsMyMsg()) {
            llChatView.removeViews(1, llChatView.getChildCount() - 1);
            llChatView.addView(imageView);
        } else {
            llChatView.removeAllViews();
            llChatView.addView(imageView);
        }

    }


    /**
     * 生成缩略图
     * 缩略图是将原图等比压缩，压缩后宽、高中较小的一个等于198像素
     * 详细信息参见文档
     */
    private Bitmap getThumb(String path) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        int reqWidth, reqHeight, width = options.outWidth, height = options.outHeight;
        try {
            //上半部分
            if (width > height) {
                reqWidth = 198;
                reqHeight = (reqWidth * height) / width;
            } else {
                reqHeight = 198;
                reqWidth = (width * reqHeight) / height;
            }
            int inSampleSize = 1;
            if (height > reqHeight || width > reqWidth) {
                final int halfHeight = height / 2;
                final int halfWidth = width / 2;
                while ((halfHeight / inSampleSize) > reqHeight
                        && (halfWidth / inSampleSize) > reqWidth) {
                    inSampleSize *= 2;
                }
            }
            //下半部分
            options.inSampleSize = inSampleSize;
            options.inJustDecodeBounds = false;
            Matrix mat = new Matrix();
            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
            ExifInterface ei = new ExifInterface(path);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    mat.postRotate(90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    mat.postRotate(180);
                    break;
            }
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mat, true);
        } catch (Exception e) {
            return null;
        }
    }


}
