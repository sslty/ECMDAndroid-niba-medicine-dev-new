package com.nibatech.ecmd.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nibatech.ecmd.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;


public class GifDrawableView extends FrameLayout implements AnimationListener {

    private ImageView ivGif;
    private final int LOOP_START = 0;
    private List<GifDrawable> gifDrawableList;
    private int currentLoop = LOOP_START;

    public GifDrawableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_gif_drawable, this);
        ivGif = (ImageView) findViewById(R.id.iv_gif);
    }

    public void showGif(Integer... drawableList) {
        if (gifDrawableList == null) addDrawableList(drawableList);
        if (gifDrawableList.size() == 0) return;
        currentLoop = LOOP_START;
        ivGif.setVisibility(View.VISIBLE);
        setVisibility(VISIBLE);
        ivGif.setImageDrawable(gifDrawableList.get(currentLoop));
        gifDrawableList.get(currentLoop).reset();
    }

    private void addDrawableList(Integer... drawableList) {
        gifDrawableList = new ArrayList<>();
        for (Integer drawableId : drawableList) {
            try {
                GifDrawable gifDrawable;
                gifDrawableList.add(gifDrawable = new GifDrawable(getResources(), drawableId));
                gifDrawable.addAnimationListener(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onAnimationCompleted(int loopNumber) {
        if (currentLoop < gifDrawableList.size() - 1) {
            ivGif.setImageDrawable(gifDrawableList.get(++currentLoop));
            gifDrawableList.get(currentLoop).seekToFrameAndGet(0);
            gifDrawableList.get(currentLoop).start();
        } else {
            ivGif.setVisibility(View.GONE);
            setVisibility(GONE);
        }
    }
}
