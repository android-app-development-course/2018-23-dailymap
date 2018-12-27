package com.dailymap.pages.homepagelink;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dailymap.R;
import com.dailymap.constant.Constants;
import com.dailymap.model.network.Destination;
import com.dailymap.model.network.FootsInfo;
import com.dailymap.model.network.FootsResponseInfo;
import com.dailymap.model.network.LoginResponseInfo;
import com.dailymap.model.network.MarkidImageInfo;
import com.dailymap.network.SendMessageManager;
import com.dailymap.view.Album;
import com.dailymap.view.StoryPlay;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.LinkedList;
import java.util.List;

public class Story extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout ll;
    private List<MarkidImageInfo> markidImageInfos=new LinkedList<>();
    private List<FootsInfo> footsResponseInfos;
    private int viewnum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        EventBus.getDefault().register(this);
        //去除标题栏
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        ll=(LinearLayout)findViewById(R.id.ll);
        initplaceview();
    }

    @Override
    public void onClick(View view) {
        int no=view.getId();
        LinkedList<String> filenames=new LinkedList<>();
        String[] strings=new String[10];
        int size=markidImageInfos.get(no).getResult().size();
        for (int i=0;i<size;i++){
            //filenames.add(markidImageInfos.get(no).getResult().get(i).getFilename());
            strings[i]= markidImageInfos.get(no).getResult().get(i).getFilename();
        }

        Intent intent=new Intent(Story.this, Album.class);
        intent.putExtra("filenames",strings);
        intent.putExtra("size",size);
        startActivityForResult(intent,111);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event1(MarkidImageInfo messageEvent) {
        markidImageInfos.add(messageEvent);
        View view= LayoutInflater.from(this).inflate(R.layout.story_place_view,null);
        ImageView imageView=(ImageView)view.findViewById(R.id.friend_image0);
        Glide.with(this).load(Constants.BASE_REQUEST_URL+"readImage?filename="+messageEvent.getResult().get(0).getFilename()).into(imageView);
          //imageView.setImageBitmap(SendMessageManager.getInstance().readImage(markidImageInfos.get(0).getResult().get(0).getFilename()));
        view.setId(viewnum);
        view.setOnClickListener(this);
        LinearLayout.LayoutParams lpleft = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        switch (viewnum%2){
            case 0:lpleft.gravity=Gravity.LEFT;
            break;
            case 1:
                lpleft.gravity=Gravity.RIGHT;
                break;
                default:break;
        }
        viewnum++;
        ll.addView(view,lpleft);
        if (markidImageInfos.size()==footsResponseInfos.size()){
            for (MarkidImageInfo markidImageInfo:markidImageInfos){
               /* View view= LayoutInflater.from(this).inflate(R.layout.story_place_view,null);
                ImageView imageView=(ImageView)view.findViewById(R.id.friend_image0);
                imageView.setImageBitmap(SendMessageManager.getInstance().readImage(markidImageInfo.getResult().get(0).getFilename()));
                LinearLayout.LayoutParams lpleft = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lpleft.gravity=Gravity.LEFT;
                ll.addView(view,lpleft);*/
                //markidImageInfo.getResult().get(0).getFilename();
            }
        }
    }

    private void initplaceview() {
        if (Constants.footsResponseInfo==null){
            Toast.makeText(this, "你还没有登录哟", Toast.LENGTH_SHORT).show();
        return;
        }
        footsResponseInfos=Constants.footsResponseInfo.getResult();
        LinkedList<String> marker_ids=new LinkedList<>();
        for (FootsInfo footsInfo:footsResponseInfos){
            marker_ids.add(footsInfo.getMarker_id());
        }
        for (String marker_id:marker_ids){
            SendMessageManager.getInstance().getImagename(marker_id);
        }




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
