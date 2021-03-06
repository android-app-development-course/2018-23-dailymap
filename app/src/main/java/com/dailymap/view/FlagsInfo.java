package com.dailymap.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.dailymap.R;
import com.dailymap.constant.Constants;
import com.dailymap.model.network.BaseResponseInfo;
import com.dailymap.model.network.Destination;
import com.dailymap.model.network.DestinationResponseInfo;
import com.dailymap.model.network.RegisterResponseInfo;
import com.dailymap.network.SendMessageManager;
import com.dailymap.utils.HttpUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlagsInfo extends AppCompatActivity {
private TextView markid;
private String marker_id;
 private String latitude;
 private String longitude;
 private Button save;
 private Button cancel;
 private EditText travel_plan;
 private String travelplan;
 private EditText place_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flags_info);

        //去除标题栏
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        place_name=(EditText)findViewById(R.id.place_name);
        Toast.makeText(this, "添加你的旅行计划", Toast.LENGTH_SHORT).show();
        EventBus.getDefault().register(this);

        //markid=(TextView) findViewById(R.id.markid);

        //markid.setText(this.getIntent().getStringExtra("markid"));
        marker_id=this.getIntent().getStringExtra("marker_id");
        latitude=this.getIntent().getStringExtra("latitude");
        longitude=this.getIntent().getStringExtra("longitude");
        travelplan=this.getIntent().getStringExtra("travel_plan");
        travel_plan=(EditText)findViewById(R.id.editText1);
        travel_plan.setText(travelplan);
        cancel=(Button)findViewById(R.id.button2);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        save=(Button)findViewById(R.id.button1);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (marker_id==null){
                    SendMessageManager.getInstance().insertFlagsInfo(Constants.USER_INFO.getUser_id(),latitude,longitude,place_name.getText().toString(),travel_plan.getText().toString());
                    Toast.makeText(FlagsInfo.this, "添加旗子成功", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SendMessageManager.getInstance().updateDestinationInfo(marker_id,latitude,longitude,place_name.getText().toString(),travel_plan.getText().toString());
                    Toast.makeText(FlagsInfo.this, "修改旗子信息成功", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(RegisterResponseInfo messageEvent) {
        String marker_id=messageEvent.getResult();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event1(BaseResponseInfo messageEvent) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    public void deletemarker(View view) {
        if (marker_id!=null){
            SendMessageManager.getInstance().deleteDestinationInfo(marker_id);
        }
        Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}
