package com.dailymap.view;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dailymap.R;
import com.dailymap.definedview.AnimationImageView;

public class StoryPlay extends AppCompatActivity {

    private TextView tra_info_play;
    private ImageView imageView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_play);
        tra_info_play=(TextView)findViewById(R.id.tra_info_play);

        imageView1 =(ImageView)findViewById(R.id.displayphotos);//放置的ImageView控件
//设置动画背景
        story_play();
    }

    private void story_play() {
        imageView1.setVisibility(View.VISIBLE);
        tra_info_play.setVisibility(View.GONE);
        //imageView1.loadAnimation(R.drawable.story_play_anim);
        imageView1.setBackgroundResource(R.drawable.story_play_anim);//其中R.anim.animation_list就是上一步准备的动画描述文件的资源名
//获得动画对象
        AnimationDrawable animation = (AnimationDrawable)imageView1.getBackground();
        //是否仅仅启动一次？
        animation.setOneShot(false);
        if(animation.isRunning())//是否正在运行
        {
            animation.stop();//停止
        }
        animation.start();//启动

        // 计算动态图片所花费的事件

        int durationTime = 0;

        for (int i = 0; i < animation.getNumberOfFrames(); i++) {

            durationTime += animation.getDuration(i);

        }



        // 动画结束后

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {



            @Override

            public void run() {
                imageView1.setVisibility(View.GONE);
                tra_info_play.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       story_play();
                    }
                }, 2000);//3秒后执行Runnable中的run方法

            }

        }, durationTime);
    }
}
