package com.dailymap.pages.homepagelink;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dailymap.R;
import com.dailymap.view.StoryPlay;

public class Story extends AppCompatActivity {

    private LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        //去除标题栏
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        ll=(LinearLayout)findViewById(R.id.ll);
        initplaceview();
    }

    private void initplaceview() {
        View view= LayoutInflater.from(this).inflate(R.layout.story_place_view,null);
        LinearLayout.LayoutParams lpleft = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lpleft.gravity=Gravity.LEFT;
        ll.addView(view,lpleft);

        view= LayoutInflater.from(this).inflate(R.layout.story_place_view,null);
        LinearLayout.LayoutParams lpright = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lpright.gravity=Gravity.RIGHT;
        ll.addView(view,lpright);

        view= LayoutInflater.from(this).inflate(R.layout.story_place_view,null);

        ll.addView(view,lpleft);

        view= LayoutInflater.from(this).inflate(R.layout.story_place_view,null);
        ll.addView(view,lpright);

        view= LayoutInflater.from(this).inflate(R.layout.story_place_view,null);

        ll.addView(view,lpleft);

        view= LayoutInflater.from(this).inflate(R.layout.story_place_view,null);
        ll.addView(view,lpright);
    }

    public void playstory(View view) {
        Intent intent=new Intent(Story.this, StoryPlay.class);
        startActivityForResult(intent,121);
    }
}
