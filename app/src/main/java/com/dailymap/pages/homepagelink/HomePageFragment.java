package com.dailymap.pages.homepagelink;


/**
 * Created by WuchangI on 2018/10/31.
 */

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dailymap.R;
import com.dailymap.constant.Constants;
import com.dailymap.view.armap.ARActivity;
import com.dailymap.view.Login_regis;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

/**
 * “首页” 页面
 */
public class HomePageFragment extends Fragment implements View.OnClickListener{

    public HomePageFragment(){}
    public HomePageFragment(ViewPager viewPager){this.viewPager=viewPager;}
    private ViewPager viewPager;
    private Button btn_map;
    private ImageView imageView;
    private ImageView arcamera;
    @BindView(R.id.btn_story)
    protected Button btn_story;
    private TextView user_name;
    private Button btn_analysis;
    private Button btn_recommand;
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

        btn_recommand=(Button) view.findViewById(R.id.btn_recommand);
        btn_recommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ReActivity.class);
                startActivityForResult(intent,101);
            }
        });
        btn_analysis=(Button) view.findViewById(R.id.btn_analysis);
        btn_analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), analyze.class);
                startActivityForResult(intent,100);
            }
        });
        user_name=(TextView)view.findViewById(R.id.name);
        btn_map = (Button) view.findViewById(R.id.btn_map);
        Button btn_myself = (Button) view.findViewById(R.id.btn_myself);
        btn_myself.setOnClickListener(this);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.USER_INFO!=null){
            user_name.setText(Constants.USER_INFO.getName());
        }
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
                startActivityForResult(intent,264);
                break;
            case R.id.btn_myself:
                viewPager.setCurrentItem(3);
                break;
                default:
                    break;

        }
    }
}


