package com.dailymap.pages.homepagelink;

import android.content.Intent;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.dailymap.R;
import com.dailymap.constant.Constants;
import com.dailymap.model.FootMarker;
import com.dailymap.model.network.Destination;
import com.dailymap.model.network.DestinationResponseInfo;
import com.dailymap.model.network.FootsResponseInfo;
import com.dailymap.model.network.LoginResponseInfo;
import com.dailymap.network.SendMessageManager;
import com.dailymap.utils.HttpUtils;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.dailymap.view.ARActivity;
import com.dailymap.view.FlagsInfo;
import com.dailymap.view.FootsInfo;
import com.dailymap.view.MoreFunction;
import com.dailymap.view.Mylocationlistener;
import com.dailymap.view.TopView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Vector;

public class DailyMap extends AppCompatActivity {
    public static MapView mMapView;
    public static BaiduMap mBaiduMap;
    private ImageView morefunction;
    public static BDLocation location;
    private ImageView flag;
    private ImageView foot;
    private Vector<Marker> flags;
    private Vector<Marker> foots;
    public static ImageView open_tra_title;
    private TopView topView;
    private SuggestionSearch mSuggestionSearch;
    private EditText placename;
    private ListView searched_places;
    private SimpleAdapter showplaces;
    private int user_id=4;
    private int FLAGINFO=20;
    private int FOOTINFO=30;
    private Boolean isfinish=false;
    private ArrayList<HashMap<String, Object>>  places;

    private Polyline flagsline;
    private Polyline footsline;
    private int index=0;
    private Boolean isflags=false;

    private CheckBox showflagline;
    private CheckBox showfootline;

    private LocationClient mLocationClient;

    //搜索框的POI列表
    private List<SuggestionResult.SuggestionInfo> resl=new List<SuggestionResult.SuggestionInfo>() {
        @Override
        public void add(int location, SuggestionResult.SuggestionInfo object) {

        }

        @Override
        public boolean add(SuggestionResult.SuggestionInfo object) {
            return false;
        }

        @Override
        public boolean addAll(int location, @NonNull Collection<? extends SuggestionResult.SuggestionInfo> collection) {
            return false;
        }

        @Override
        public boolean addAll(@NonNull Collection<? extends SuggestionResult.SuggestionInfo> collection) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public boolean contains(Object object) {
            return false;
        }

        @Override
        public boolean containsAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public SuggestionResult.SuggestionInfo get(int location) {
            return null;
        }

        @Override
        public int indexOf(Object object) {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @NonNull
        @Override
        public Iterator<SuggestionResult.SuggestionInfo> iterator() {
            return null;
        }

        @Override
        public int lastIndexOf(Object object) {
            return 0;
        }

        @NonNull
        @Override
        public ListIterator<SuggestionResult.SuggestionInfo> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<SuggestionResult.SuggestionInfo> listIterator(int location) {
            return null;
        }

        @Override
        public SuggestionResult.SuggestionInfo remove(int location) {
            return null;
        }

        @Override
        public boolean remove(Object object) {
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public boolean retainAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public SuggestionResult.SuggestionInfo set(int location, SuggestionResult.SuggestionInfo object) {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }

        @NonNull
        @Override
        public List<SuggestionResult.SuggestionInfo> subList(int start, int end) {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(@NonNull T[] array) {
            return null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//要在setContentView之前初始化
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_daily_map);

        //去除标题栏
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        EventBus.getDefault().register(this);

        /*SharedPreferences preferences = getSharedPreferences("userInfo",Activity.MODE_PRIVATE);
        user_id = Integer.parseInt(preferences.getString("user_id", -1+""));
*/
        //初始化控件和变量
        initdata();
        //设置监听器
        initlistener();
//设置点击旗子的事件
        addflaginit();
//设置足迹的监听事件
        addfootinit();
//点击marker的事件
        markerlistenerinit();
        BDlocationinit();
//站看popupwindow
        trainfotitleinit();


//定位
        MapStatus.Builder builder = new MapStatus.Builder();
        /*LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
        builder.target(latLng);*/
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
       // bdlocation(location.getLatitude(),location.getLongitude());



    }
    //初始化百度定位
    private void BDlocationinit() {
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        LocationClientOption option = new LocationClientOption();

        option.setIsNeedAddress(true);
//可选，是否需要地址信息，默认为不需要，即参数为false
//如果开发者需要获得当前点的地址信息，此处必须为true

        option.setIsNeedLocationPoiList(true);
//可选，是否需要周边POI信息，默认为不需要，即参数为false
        mLocationClient.setLocOption(option);
//mLocationClient为第二步初始化过的LocationClient对象
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
//更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
        mLocationClient.registerLocationListener(new Mylocationlistener());
        //注册监听函数
        mLocationClient.start();

    }
    private void initlistener() {
        morefunction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DailyMap.this,MoreFunction.class);
                startActivity(intent);
            }
        });

        searched_places.setAdapter(showplaces);
        searched_places.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DailyMap.this, resl.get(position).key, Toast.LENGTH_SHORT).show();
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(resl.get(position).pt);
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                isfinish=true;
                searched_places.setVisibility(View.GONE);
                placename.setText(resl.get(position).key);
                bdlocation(resl.get(position).pt.latitude,resl.get(position).pt.longitude);
            }
        });
        placename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isfinish=false;
            }
        });
        placename.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isfinish)return;
                if (s.toString().equals("")){
                    searched_places.setVisibility(View.GONE);
                }
                else {
                    searched_places.setVisibility(View.VISIBLE);
                }

                mSuggestionSearch.requestSuggestion((new SuggestionSearchOption())

                        .keyword(s.toString()).city("东京"));

            }
        });
        location= ARActivity.bdLocation;
        //设置推荐监听
        mSuggestionSearch.setOnGetSuggestionResultListener(listener);
    }

    private void initdata() {
        showflagline=(CheckBox)findViewById(R.id.showflagline);
        showflagline.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){

                    if (flags.isEmpty())
                        return;
                    List<LatLng> points = new ArrayList<LatLng>();
                    //构建折线点坐标
                    for (int i=0;i<flags.size();i++){
                        LatLng p=new LatLng(flags.get(i).getPosition().latitude,flags.get(i).getPosition().longitude);
                        points.add(p);
                    }
                    //绘制折线
                    OverlayOptions ooPolyline = new PolylineOptions().width(10)
                            .color(0xAAFF0000).points(points);
                    flagsline=(Polyline) mBaiduMap.addOverlay(ooPolyline);
                }
                else {
                    if (flagsline!=null)
                    flagsline.remove();
                }
            }
        });
        showfootline=(CheckBox)findViewById(R.id.showfootline);
        showfootline.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){

                    if (foots.isEmpty())
                        return;
                    List<LatLng> points = new ArrayList<LatLng>();
                    //构建折线点坐标
                    for (int i=0;i<foots.size();i++){
                        LatLng p=new LatLng(foots.get(i).getPosition().latitude,foots.get(i).getPosition().longitude);
                        points.add(p);
                    }
                    //绘制折线
                    OverlayOptions ooPolyline = new PolylineOptions().width(10)
                            .color(0xAA000000).points(points);
                    footsline=(Polyline) mBaiduMap.addOverlay(ooPolyline);
                }
                else {
                    if (footsline!=null)
                    footsline.remove();
                }
            }
        });
        places=new ArrayList<>();
        flags=new Vector<>();
        foots=new Vector<>();
        mMapView = (MapView) findViewById(R.id.mmap);
        morefunction=(ImageView)findViewById(R.id.morefunction);
        mBaiduMap = mMapView.getMap();
        //获取推荐实例
        mSuggestionSearch = SuggestionSearch.newInstance();
        open_tra_title=(ImageView)findViewById(R.id.open_tra_title);
        placename=(EditText)findViewById(R.id.placename);
        searched_places=(ListView)findViewById(R.id.searched_places);
        showplaces=new SimpleAdapter(this,places,android.R.layout.simple_list_item_2,
                new String[]{"text"},new int[]{android.R.id.text2});
    }


    @Override
    protected void onResume() {
        super.onResume();
        //获取用户的marker信息
        markerinfoinit();

    }



    //开辟进程获取后台数据
    private void markerinfoinit() {

        foots.clear();
        flags.clear();
        if (Constants.USER_INFO==null)
        {
            Toast.makeText(this, "您还没有登录哟~", Toast.LENGTH_SHORT).show();
            return;
        }
        String user_id=Constants.USER_INFO.getUser_id();
        SendMessageManager.getInstance().getDestinationInfoFromUserId(user_id);
        SendMessageManager.getInstance().getFootInfoFromUserId(user_id);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(DestinationResponseInfo messageEvent) {
        Constants.destinationResponseInfo=messageEvent;
        List<Destination> destinations=messageEvent.getResult();
        for (int i=0;i<destinations.size();i++){
            Bundle mBundle = new Bundle();
            mBundle.putString("marker_id", destinations.get(i).getMarker_id());
            mBundle.putString("place_name", destinations.get(i).getPlace_name());
            mBundle.putString("travel_plan", destinations.get(i).getTravel_plan());
            mBundle.putBoolean("isfoot",false);

            LatLng point = new LatLng(Double.parseDouble(destinations.get(i).getLatitude()), Double.parseDouble(destinations.get(i).getLongitude()));
            //构建Marker图标

            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.flags);
//构建MarkerOption，用于在地图上添加Marker

            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap).draggable(true).extraInfo(mBundle).title(destinations.get(i).getPlace_name());

//在地图上添加Marker，并显示
            Marker marker;
            marker=(Marker) mBaiduMap.addOverlay(option);
            marker.setExtraInfo(mBundle);
            flags.add(marker);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event1(FootsResponseInfo messageEvent) {
        Constants.footsResponseInfo=messageEvent;
        List<com.dailymap.model.network.FootsInfo> footsInfos=messageEvent.getResult();
        for (int i=0;i<footsInfos.size();i++){
            Bundle mBundle = new Bundle();
            mBundle.putString("marker_id", footsInfos.get(i).getMarker_id());
            mBundle.putString("place_name", footsInfos.get(i).getPlace_name());
            mBundle.putString("thought", footsInfos.get(i).getThought());
            mBundle.putString("photos_path", footsInfos.get(i).getPhotos_path());
            mBundle.putBoolean("isfoot",true);

            LatLng point = new LatLng(Double.parseDouble(footsInfos.get(i).getLatitude()), Double.parseDouble(footsInfos.get(i).getLongitude()));
            //构建Marker图标

            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.foots);
//构建MarkerOption，用于在地图上添加Marker

            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap).draggable(true).extraInfo(mBundle).title(footsInfos.get(i).getPlace_name());

//在地图上添加Marker，并显示
            Marker marker;
            marker=(Marker) mBaiduMap.addOverlay(option);
            marker.setExtraInfo(mBundle);
            foots.add(marker);
        }
    }


    //获取后台数据后更新vector并显示图层
    private void refleshmarker() {
        for (int i=0;i<foots.size();i++){
            Marker marker=foots.get(i);
            LatLng point = marker.getPosition();
            //构建Marker图标

            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.foots);
//构建MarkerOption，用于在地图上添加Marker

            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap).draggable(true);

//在地图上添加Marker，并显示

            mBaiduMap.addOverlay(option);

        }

        for (int i=0;i<flags.size();i++){
            Marker marker=flags.get(i);
            LatLng point = marker.getPosition();
            //构建Marker图标

            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.flags);
//构建MarkerOption，用于在地图上添加Marker

            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap).draggable(true);

//在地图上添加Marker，并显示

            mBaiduMap.addOverlay(option);

        }
    }


    //显示定位图标
    private void bdlocation(double lat,double lon) {
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

// 构造定位数据
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(lat)
                .longitude(lon).build();

// 设置定位数据
        mBaiduMap.setMyLocationData(locData);

// 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）

      /*  MyLocationConfiguration.LocationMode mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.more);*/
        MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, BitmapDescriptorFactory .fromResource(R.drawable.marker_red));
        mBaiduMap.setMyLocationConfiguration(config);


        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

            }

            @Override
            public void onMarkerDragStart(Marker marker) {

            }
        });
        mBaiduMap.setOnMapTouchListener(new BaiduMap.OnMapTouchListener() {
            @Override
            public void onTouch(MotionEvent motionEvent) {

            }
        });

        mMapView.getMap().setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {
                //左上角经纬度
                Point pt = new Point();
                pt.x=0;
                pt.y=0;
                LatLng ll = mBaiduMap.getProjection().fromScreenLocation(pt);
                //右下角经纬度
                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                Point pty = new Point();
                pty.x=dm.widthPixels;
                pty.y=(int)(0.5*dm.heightPixels);
                LatLng lly = mBaiduMap.getProjection().fromScreenLocation(pty);
            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {

            }
        });

        //  mMapView = (MapView) findViewById(R.id.bmapView);
    }

    OnGetSuggestionResultListener listener = new OnGetSuggestionResultListener() {

        public void onGetSuggestionResult(SuggestionResult res) {
            if (res == null || res.getAllSuggestions() == null) {
                return;
                //未找到相关结果
            }else
            {
                places.clear();
                resl.clear();
                resl=res.getAllSuggestions();
                for(int i=0;i<resl.size();i++)
                {
                    Log.d("result: ","city"+resl.get(i).city+" dis "+resl.get(i).district+"key "+resl.get(i).key);
                    HashMap<String,Object> map=new HashMap<>();
                    map.put("text",resl.get(i).key);
                    places.add(map);
                }
                showplaces.notifyDataSetChanged();
            }
            //获取在线建议检索结果
        }
    };

    //显示头部topview的监听事件
    private void trainfotitleinit() {

        open_tra_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                topView= new TopView(DailyMap.this);
                refleshmarkernum();
                open_tra_title.setVisibility(View.GONE);
            }
        });

    }

    //标志的监听事件
    private void markerlistenerinit() {
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Bundle bundle = marker.getExtraInfo();
                Boolean isfoot=bundle.getBoolean("isfoot",true);
                String marker_id=bundle.getString("marker_id",null);
                if (marker_id==null){
                    if (isfoot){
                        Intent intent=new Intent(DailyMap.this,FootsInfo.class);
                        intent.putExtra("latitude",marker.getPosition().latitude+"");
                        intent.putExtra("longitude",marker.getPosition().longitude+"");
                        startActivityForResult(intent,FOOTINFO);
                    }
                    else {
                        Intent intent=new Intent(DailyMap.this,FlagsInfo.class);
                        intent.putExtra("latitude",marker.getPosition().latitude+"");
                        intent.putExtra("longitude",marker.getPosition().longitude+"");
                        startActivityForResult(intent,FLAGINFO);
                    }
                    return true;
                }
                if (isfoot){
                    String s= bundle.getString("thought");
                    Intent intent=new Intent(DailyMap.this,FootsInfo.class);
                    intent.putExtra("marker_id",marker_id);
                    intent.putExtra("latitude",marker.getPosition().latitude+"");
                    intent.putExtra("longitude",marker.getPosition().longitude+"");
                    intent.putExtra("thought",s);
                    startActivityForResult(intent,FOOTINFO);
                }
                else {
                    String s= bundle.getString("travel_plan");
                    Intent intent=new Intent(DailyMap.this,FlagsInfo.class);
                    intent.putExtra("marker_id",marker_id);
                    intent.putExtra("latitude",marker.getPosition().latitude+"");
                    intent.putExtra("longitude",marker.getPosition().longitude+"");
                    intent.putExtra("travel_plan",s);
                    startActivityForResult(intent,FLAGINFO);
                }
                return true;
            }
        });

    }

    //添加足迹的监听事件
    private void addfootinit() {

        foot=(ImageView)findViewById(R.id.foot);



        foot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              new Thread(new Runnable() {
                  @Override
                  public void run() {

                      //右下角经纬度


                      DisplayMetrics dm = new DisplayMetrics();
                      getWindowManager().getDefaultDisplay().getMetrics(dm);
                      Point pty = new Point();
                      pty.x=(int)(0.5*dm.widthPixels);
                      pty.y=(int)(dm.heightPixels*0.5);
                      LatLng lly = DailyMap.mBaiduMap.getProjection().fromScreenLocation(pty);
                      LatLng point = new LatLng(lly.latitude, lly.longitude);
                      //构建Marker图标

                      BitmapDescriptor bitmap = BitmapDescriptorFactory
                              .fromResource(R.drawable.foots);
//构建MarkerOption，用于在地图上添加Marker

                      OverlayOptions option = new MarkerOptions()
                              .position(point)
                              .icon(bitmap).draggable(true);

//在地图上添加Marker，并显示
                      Marker marker;
                      marker=(Marker) DailyMap.mBaiduMap.addOverlay(option);

                      Bundle mBundle=new Bundle();
                      mBundle.putBoolean("isfoot",true);
                      marker.setExtraInfo(mBundle);
                      foots.add(marker);
                  }
              }).start();
                refleshmarkernum();
            }
        });
    }
    //添加旗子的监听事件
    private void addflaginit() {
        flag=(ImageView)findViewById(R.id.flag);



        flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        //右下角经纬度
                        DisplayMetrics dm = new DisplayMetrics();
                        getWindowManager().getDefaultDisplay().getMetrics(dm);
                        Point pty = new Point();
                        pty.x=(int)(0.5*dm.widthPixels);
                        pty.y=(int)(dm.heightPixels*0.5);
                        LatLng lly = DailyMap.mBaiduMap.getProjection().fromScreenLocation(pty);
                        LatLng point = new LatLng(lly.latitude, lly.longitude);
                        //构建Marker图标

                        BitmapDescriptor bitmap = BitmapDescriptorFactory
                                .fromResource(R.drawable.flags);
//构建MarkerOption，用于在地图上添加Marker

                        OverlayOptions option = new MarkerOptions()
                                .position(point)
                                .icon(bitmap).draggable(true);

//在地图上添加Marker，并显示
                        Marker marker;
                        marker=(Marker) DailyMap.mBaiduMap.addOverlay(option);
                        Bundle mBundle = new Bundle();
                        mBundle.putBoolean("isfoot",false);
                        marker.setExtraInfo(mBundle);
                        flags.add(marker);
                    }
                }).start();
                refleshmarkernum();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==FOOTINFO){
            //Toast.makeText(this, "添加足迹信息成功", Toast.LENGTH_SHORT).show();
        }
        else {
            //Toast.makeText(this, "添加旗子信息成功", Toast.LENGTH_SHORT).show();
        }
        onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

// 当不需要定位图层时关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mSuggestionSearch.destroy();
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }


    public int getfootnum(){
        return foots.size();
    }
    public int getflagnum(){
        return flags.size();
    }

    //更新显示足迹数和旗子数
    public void refleshmarkernum(){
        if (topView!=null){
            TextView textView=(TextView)topView.popupWindowView.findViewById(R.id.marker_number);
            textView.setText("足迹数:"+getfootnum()+"    旗子数:"+getflagnum());
        }
    }

}
