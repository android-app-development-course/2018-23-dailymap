package com.dailymap.pages.homepagelink;


/**
 * Created by WuchangI on 2018/10/31.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.dailymap.R;
import com.dailymap.view.ARActivity;
import com.dailymap.view.Login_regis;

import butterknife.BindView;

/**
 * “首页” 页面
 */
public class HomePageFragment extends Fragment implements View.OnClickListener{
    public HomePageFragment(){}
    private Button btn_map;
    private ImageView imageView;
    private ImageView arcamera;
    @BindView(R.id.btn_story)
    protected Button btn_story;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.homepage, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
    }

    

    private void initView(View view)
    {
        btn_map = (Button) view.findViewById(R.id.btn_map);
        btn_story = (Button) view.findViewById(R.id.btn_story);
        btn_map.setOnClickListener(this);
        imageView=(ImageView)view.findViewById(R.id.imageView);
        imageView.setOnClickListener(this);
        arcamera=(ImageView)view.findViewById(R.id.arcamera);
        arcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ARActivity.class);
                startActivityForResult(intent,101);
            }
        });
        btn_story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Story.class);
                startActivityForResult(intent,100);
            }
        });

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.btn_map:
                intent=new Intent(getContext(),DailyMap.class);
                startActivity(intent);
                break;
            case R.id.imageView:
                intent=new Intent(getContext(),Login_regis.class);
                startActivity(intent);
                break;
                default:
                    break;

        }
    }
}


