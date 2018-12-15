package com.dailymap.pages.homepagelink;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dailymap.R;
import com.dailymap.view.StoryPlay;

public class Story extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
    }

    public void playstory(View view) {
        Intent intent=new Intent(Story.this, StoryPlay.class);
        startActivityForResult(intent,121);
    }
}
