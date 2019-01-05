package com.dailymap.view;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.dailymap.R;
import com.dailymap.pages.homepagelink.Story;
import com.dailymap.services.MusicService;


public class StoryPlay extends AppCompatActivity {

    //显示旅游信息，地点，时间等
    private TextView tra_info_play;
    private int imagenum=6;
    // 准备图片资源
    int[] imgs = { R.drawable.friend1, R.drawable.friend2, R.drawable.friend3,
            R.drawable.friend1, R.drawable.friend2, R.drawable.friend3};
    //自动播放图片的控件
    private ViewFlipper flipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_play);
        tra_info_play=(TextView)findViewById(R.id.tra_info_play);
        flipper = (ViewFlipper) findViewById(R.id.images);
        //设置动画背景
        story_play();

//将图片资源循环放入控件中
        for (int i = 0; i < imgs.length; i++) {
//创建imageview控件
            ImageView iv=new ImageView(StoryPlay.this);
//将图片资源放入imageview中
            iv.setImageResource(imgs[i]);
//不按比例缩放图片，此处将图片填充整个view
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
//将imageview控件放入ViewFlipper中，参数一：imageview控件，参数二：控件在布局中的位置，默认居中父窗口
            flipper.addView(iv);
//flipper.addView(iv, new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
        }

//图片播放的时间
        flipper.setFlipInterval(4000);
//是否允许自动播放
        flipper.setAutoStart(true);
//调用isFlipping方法来判断ViewFlipper是否在进行页面的轮播或者切换，isAutoStart是否允许自动播放
        if(flipper.isAutoStart() && !flipper.isFlipping()) {
            //向上消失
            flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.push_up_in));
            flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.push_up_out));
//旋转消失
            flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.hyperspace_in));
            flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.hyperspace_out));
            //启动，开始播放
            flipper.startFlipping();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        //播放音乐
        playmusic();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(conn);
        musicControl.play();
        Intent intent2 = new Intent(getApplicationContext(), MusicService.class);
        stopService(intent2);// 关闭服务
    }

    private void playmusic() {
        conn=new MyConnection();
        Intent intent3 = new Intent(this, MusicService.class);
        conn = new MyConnection();
        //使用混合的方法开启服务，
        startService(intent3);
        bindService(intent3, conn, BIND_AUTO_CREATE);
    }

    private void story_play() {
        flipper.setVisibility(View.VISIBLE);
        tra_info_play.setVisibility(View.GONE);
        //imageView1.loadAnimation(R.drawable.story_play_anim);
       /* imageView1.setBackgroundResource(R.drawable.story_play_anim);//其中R.anim.animation_list就是上一步准备的动画描述文件的资源名
//获得动画对象
        AnimationDrawable animation = (AnimationDrawable)imageView1.getBackground();
        //是否仅仅启动一次？
        animation.setOneShot(false);
        if(animation.isRunning())//是否正在运行
        {
            animation.stop();//停止
        }
        animation.start();//启

        // 计算动态图片所花费的事件

        int durationTime = 0;

        for (int i = 0; i < animation.getNumberOfFrames(); i++) {

            durationTime += animation.getDuration(i);

        }

*/
        // 动画结束后
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {



            @Override

            public void run() {
                flipper.setVisibility(View.GONE);
                tra_info_play.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       story_play();
                    }
                }, 3000);//3秒后执行Runnable中的run方法
            }

        }, imagenum*1000);
    }

    private MyConnection conn;
    private MusicService.MyBinder musicControl;

    private class MyConnection implements ServiceConnection {
        //服务启动完成后会进入到这个方法
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获得service中的MyBinder
            musicControl = (MusicService.MyBinder) service;
            musicControl.play();
        }



        @Override

        public void onServiceDisconnected(ComponentName name) {
        }

    }
}
