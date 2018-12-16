package com.dailymap.definedview;


import android.content.Context;

import android.graphics.drawable.AnimationDrawable;

import android.os.Handler;

import android.util.AttributeSet;

import android.widget.ImageView;

import com.dailymap.listener.OnFrameAnimationListener;


public class AnimationImageView extends ImageView {



    public AnimationImageView(Context context) {

        super(context);

        // TODO Auto-generated constructor stub

    }



    public AnimationImageView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);

        // TODO Auto-generated constructor stub

    }



    public AnimationImageView(Context context, AttributeSet attrs) {

        super(context, attrs);

        // TODO Auto-generated constructor stub

    }






    /**

     * 不带动画监听的播放

     * @param resId

     */

    public void loadAnimation(int resId){

        setImageResource(resId);

        AnimationDrawable anim = (AnimationDrawable)getDrawable();

        anim.start();

    }



    /**

     * 带动画监听的播放

     * @param resId

     * @param listener

     */

    public void loadAnimation(int resId, final OnFrameAnimationListener listener) {

        setImageResource(resId);

        AnimationDrawable anim = (AnimationDrawable)getDrawable();

        anim.start();

        if(listener != null){

            // 调用回调函数onStart

            listener.onStart();

        }



        // 计算动态图片所花费的事件

        int durationTime = 0;

        for (int i = 0; i < anim.getNumberOfFrames(); i++) {

            durationTime += anim.getDuration(i);

        }



        // 动画结束后

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {



            @Override

            public void run() {

                if(listener != null){

                    // 调用回调函数onEnd

                    listener.onEnd();

                }

            }

        }, durationTime);

    }



}
