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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flags_info);

        //去除标题栏
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        EventBus.getDefault().register(this);

        markid=(TextView) findViewById(R.id.markid);

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
                SendMessageManager.getInstance().insertFlagsInfo(Constants.USERID,latitude,longitude,"",travel_plan.getText().toString());
            }
        });

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(RegisterResponseInfo messageEvent) {
        String marker_id=messageEvent.getResult();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    public String streampost(String latitude, String longitude) {
        URL infoUrl = null;

//        String remote_addr="http://api.weatherdt.com/common/?area="+citycode+"&type="+datacode+"&key=90d48635e440fc4c032e4f5b5b11e996";
        String remote_addr="http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=35.658651,139.745415&output=json&pois=1&ak=md2H7GauTf9yNE2yrDaWNt83e9unmoaB&mcode=6A:EA:FB:A8:01:0B:AA:41:22:50:EC:1D:D8:A5:96:D2:EF:3A:68:BD";
        try {
            infoUrl = new URL(remote_addr);
            URLConnection connection = infoUrl.openConnection();
            HttpURLConnection httpconnection = (HttpURLConnection) connection;
            httpconnection.setRequestMethod("GET");
            httpconnection.setReadTimeout(5000);
            InputStream inStream = httpconnection.getInputStream();
            ByteArrayOutputStream data = new ByteArrayOutputStream();

            byte[] buffer = new byte[1000];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                data.write(buffer, 0, len);
            }
            inStream.close();
            String test=new String(data.toByteArray(), "utf-8");
            Log.i("test",test);
            return new String(data.toByteArray(), "utf-8");

        } catch (MalformedURLException e) {
            e.printStackTrace();
            Toast.makeText(this,"网络异常",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this,"输出异常",Toast.LENGTH_SHORT).show();
        }

        return "异常";
    }
}
