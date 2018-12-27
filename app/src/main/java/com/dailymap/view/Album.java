package com.dailymap.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.dailymap.R;

import java.util.LinkedList;

public class Album extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        String[] filenames=new String[10];
        filenames=getIntent().getStringArrayExtra("filenames");
        int size=getIntent().getIntExtra("size",0);
        if (filenames!=null){
            Toast.makeText(this, "有图片"+ size, Toast.LENGTH_SHORT).show();
        }
    }
}
