package com.dailymap.view;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.dailymap.R;

public class StoryPlay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_play);
        ImageView imageView1 =(ImageView)findViewById(R.id.displayphotos);//放置的ImageView控件
//设置动画背景
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
    }
}
