package com.dailymap.view;

import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.dailymap.R;
import com.dailymap.constant.Constants;
import com.dailymap.model.network.LoginResponseInfo;
import com.dailymap.model.network.RegisterResponseInfo;
import com.dailymap.network.SendMessageManager;
import com.dailymap.utils.HttpUtils;
import com.dailymap.utils.UploadUtil;
import com.baidu.mapapi.model.LatLng;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FootsInfo extends AppCompatActivity {

    private static final int SELECT_PHOTO=100;
    private Button select_image;
    private ImageView imageView;
    private LatLng point;
    private String latitude;
    private String longitude;
    private String marker_id;
    private TextView foots_title;
    private LinkedList<String> img_src=new LinkedList<>();
    private EditText tra_thought;

    private GridView gridView1;              //网格显示缩略图
    private Button buttonPublish;            //发布按钮
    private final int IMAGE_OPEN = 1;      //打开图片标记
    private String pathImage=null;                //选择图片路径
    private Bitmap bmp;                      //导入临时图片
    private ArrayList<HashMap<String, Object>> imageItem;
    private SimpleAdapter simpleAdapter;     //适配器
    private Button save;
    private String thought;
    private Button cancel;
    private Bitmap addbmp;
    private EditText place_name;
    //获取图片路径 响应startActivityForResult
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //打开图片
        if(resultCode==RESULT_OK && requestCode==IMAGE_OPEN) {
            Uri uri = data.getData();

            String img_url = uri.getPath();//这是本机的图片路径
            ContentResolver cr = this.getContentResolver();
            try {
                addbmp = BitmapFactory.decodeStream(cr.openInputStream(uri));

            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(),e);
            }

            //img_src.add(getRealPathFromUri(FootsInfo.this,uri));
            if (!TextUtils.isEmpty(uri.getAuthority())) {
                //查询选择图片
                Cursor cursor = getContentResolver().query(
                        uri,
                        new String[] { MediaStore.Images.Media.DATA },
                        null,
                        null,
                        null);
                //返回 没找到选择图片
                if (null == cursor) {
                    Toast.makeText(this, "没找到图片", Toast.LENGTH_SHORT).show();
                    return;
                }
                //光标移动至开头 获取图片路径
                cursor.moveToFirst();
                pathImage = cursor.getString(cursor
                        .getColumnIndex(MediaStore.Images.Media.DATA));
                img_src.add(pathImage);
            }
        }  //end if 打开图片
    }

    //刷新图片
    @Override
    protected void onResume() {
        super.onResume();
        if(!TextUtils.isEmpty(pathImage)){
           // Bitmap addbmp=BitmapFactory.decodeFile(pathImage);

            //Bitmap addbmp=convertToBitmap(pathImage,30,30);
            //addbmp=BitmapFactory.decodeResource(getResources(),R.drawable.addpicture);
           // Bitmap addbmp = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(pathImage), 90, 90, true);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemImage", addbmp);
            imageItem.add(map);
            simpleAdapter = new SimpleAdapter(this,
                    imageItem, R.layout.griditem_addpic,
                    new String[] {"itemImage"}, new int[] { R.id.imageView1});
            simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
                @Override
                public boolean setViewValue(View view, Object data,
                                            String textRepresentation) {
                    // TODO Auto-generated method stub
                    if(view instanceof ImageView && data instanceof Bitmap){
                        ImageView i = (ImageView)view;
                        i.setImageBitmap((Bitmap) data);
                        return true;
                    }
                    return false;
                }
            });
            gridView1.setAdapter(simpleAdapter);
            simpleAdapter.notifyDataSetChanged();
            //刷新后释放防止手机休眠后自动添加
            pathImage = null;
        }
    }

    public Bitmap convertToBitmap(String path, int w, int h) {
                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    // 设置为ture只获取图片大小
                    opts.inJustDecodeBounds = true;
                    opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    // 返回为空
                  BitmapFactory.decodeFile(path, opts);
                  int width = opts.outWidth;
                     int height = opts.outHeight;
                    float scaleWidth = 0.f, scaleHeight = 0.f;
                   if (width > w || height > h) {
                            // 缩放
                            scaleWidth = ((float) width) / w;
                             scaleHeight = ((float) height) / h;
                       }
                   opts.inJustDecodeBounds = false;
                    float scale = Math.max(scaleWidth, scaleHeight);
                    opts.inSampleSize = (int)scale;
                   WeakReference<Bitmap> weak = new WeakReference<Bitmap>(BitmapFactory.decodeFile(path, opts));
                  return Bitmap.createScaledBitmap(weak.get(), w, h, true);
              }


    /*
     * Dialog对话框提示用户删除操作
     * position为删除图片位置
     */
    protected void dialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(FootsInfo.this);
        builder.setMessage("确认移除已添加图片吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                imageItem.remove(position);
                img_src.remove(position-1);
                simpleAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foots_info);
        //去除标题栏
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        Toast.makeText(this, "在此添加修改您的足迹信息", Toast.LENGTH_SHORT).show();
        EventBus.getDefault().register(this);
        latitude=this.getIntent().getStringExtra("latitude");
        longitude=this.getIntent().getStringExtra("longitude");
        thought=this.getIntent().getStringExtra("thought");
        marker_id=getIntent().getStringExtra("marker_id");
        cancel=(Button)findViewById(R.id.button2);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tra_thought=(EditText)findViewById(R.id.editText1);
        tra_thought.setText(thought);
        save=(Button)findViewById(R.id.button1);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addfootinfo();
                if (marker_id!=null)
                uploadImage(img_src);
                else {
                    /*while (marker_id==null){

                    }
                    uploadImage(img_src);*/
                }
                finish();
            }
        });

        place_name=(EditText)findViewById(R.id.place_name);
        /*
         * 防止键盘挡住输入框
         * 不希望遮挡设置activity属性 android:windowSoftInputMode="adjustPan"
         * 希望动态调整高度 android:windowSoftInputMode="adjustResize"
         */
        getWindow().setSoftInputMode(WindowManager.LayoutParams.
                SOFT_INPUT_ADJUST_PAN);
        //锁定屏幕
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //获取控件对象
        gridView1 = (GridView) findViewById(R.id.gridView1);

        gridView1init();
    }

    private void gridView1init() {

        /*
         * 载入默认图片添加图片加号
         * 通过适配器实现
         * SimpleAdapter参数imageItem为数据源 R.layout.griditem_addpic为布局
         */
        //获取资源图片加号
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.addpicture);
        imageItem = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("itemImage", bmp);
        imageItem.add(map);
        simpleAdapter = new SimpleAdapter(this,
                imageItem, R.layout.griditem_addpic,
                new String[] { "itemImage"}, new int[] { R.id.imageView1});
        /*
         * HashMap载入bmp图片在GridView中不显示,但是如果载入资源ID能显示 如
         * map.put("itemImage", R.drawable.img);
         * 解决方法:
         *              1.自定义继承BaseAdapter实现
         *              2.ViewBinder()接口实现
         *  参考 http://blog.csdn.net/admin_/article/details/7257901
         */
        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data,
                                        String textRepresentation) {
                // TODO Auto-generated method stub
                if(view instanceof ImageView && data instanceof Bitmap){
                    ImageView i = (ImageView)view;
                    i.setImageBitmap((Bitmap) data);
                    return true;
                }
                return false;
            }
        });
        gridView1.setAdapter(simpleAdapter);

        /*
         * 监听GridView点击事件
         * 报错:该函数必须抽象方法 故需要手动导入import android.view.View;
         */
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                if( imageItem.size() == 10) { //第一张为默认图片
                    Toast.makeText(FootsInfo.this, "图片数9张已满", Toast.LENGTH_SHORT).show();
                }
                else if(position == 0) { //点击图片位置为+ 0对应0张图片
                    Toast.makeText(FootsInfo.this, "添加图片", Toast.LENGTH_SHORT).show();
                    //选择图片
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, IMAGE_OPEN);
                    //通过onResume()刷新数据
                }
                else {
                    dialog(position);
                    //Toast.makeText(MainActivity.this, "点击第"+(position + 1)+" 号图片",
                    //      Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void addfootinfo() {
        if (marker_id==null){
            SendMessageManager.getInstance().insertFootInfo(Constants.USER_INFO.getUser_id(),latitude,longitude,place_name.getText().toString(),tra_thought.getText().toString());
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        }
        else
        {
            SendMessageManager.getInstance().updateFootsInfo(marker_id,latitude,longitude,place_name.getText().toString(),tra_thought.getText().toString());
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event1(RegisterResponseInfo messageEvent) {
        if (marker_id!=null)return;
        //Toast.makeText(this, messageEvent.getError(), Toast.LENGTH_SHORT).show();
        if (messageEvent.getResult()==null){
            Toast.makeText(this,"网络出现错误", Toast.LENGTH_SHORT).show();
        }else {
            marker_id=messageEvent.getResult();
            this.finish();
        }
    }

    /**

     * 从相册选取图片

     */
 public void uploadImage(LinkedList<String> imgpaths) {


     for (int i=0;i<imgpaths.size();i++){
         File file = new File(imgpaths.get(i));
         String fileNameByTimeStamp ="";
         RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file);
         MultipartBody.Part body = MultipartBody.Part.createFormData("app_user_header", fileNameByTimeStamp, requestFile);
         Map<String,String> params=new HashMap<>();
         params.put("marker_id",marker_id);
         SendMessageManager.getInstance().upImg(params,body);
     }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
 }

    /**

     * 根据图片的Uri获取图片的绝对路径。@uri 图片的uri

     * @return 如果Uri对应的图片存在,那么返回该图片的绝对路径,否则返回null

     */

    public static String getRealPathFromUri(Context context, Uri uri) {

        if(context == null || uri == null) {

            return null;

        }

        if("file".equalsIgnoreCase(uri.getScheme())) {

            return getRealPathFromUri_Byfile(context,uri);

        } else if("content".equalsIgnoreCase(uri.getScheme())) {

            return getRealPathFromUri_Api11To18(context,uri);

        }

//        int sdkVersion = Build.VERSION.SDK_INT;

//        if (sdkVersion < 11) {

//            // SDK < Api11

//            return getRealPathFromUri_BelowApi11(context, uri);

//        }

////        if (sdkVersion < 19) {

////             SDK > 11 && SDK < 19

////            return getRealPathFromUri_Api11To18(context, uri);

//            return getRealPathFromUri_ByXiaomi(context, uri);

////        }

//        // SDK > 19

        return getRealPathFromUri_AboveApi19(context, uri);//没用到

    }



    //针对图片URI格式为Uri:: file:///storage/emulated/0/DCIM/Camera/IMG_20170613_132837.jpg

    private static String getRealPathFromUri_Byfile(Context context,Uri uri){

        String uri2Str = uri.toString();

        String filePath = uri2Str.substring(uri2Str.indexOf(":") + 3);

        return filePath;

    }



    /**

     * 适配api19以上,根据uri获取图片的绝对路径

     */


    private static String getRealPathFromUri_AboveApi19(Context context, Uri uri) {

        String filePath = null;

        String wholeID = null;



        wholeID = DocumentsContract.getDocumentId(uri);



        // 使用':'分割

        String id = wholeID.split(":")[1];



        String[] projection = { MediaStore.Images.Media.DATA };

        String selection = MediaStore.Images.Media._ID + "=?";

        String[] selectionArgs = { id };



        Cursor cursor = context.getContentResolver().query(

                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection,

                selection, selectionArgs, null);

        int columnIndex = cursor.getColumnIndex(projection[0]);



        if (cursor.moveToFirst()) {

            filePath = cursor.getString(columnIndex);

        }

        cursor.close();

        return filePath;

    }



    /**

     * //适配api11-api18,根据uri获取图片的绝对路径。

     * 针对图片URI格式为Uri:: content://media/external/images/media/1028

     */

    private static String getRealPathFromUri_Api11To18(Context context, Uri uri) {

        String filePath = null;

        String[] projection = { MediaStore.Images.Media.DATA };



        CursorLoader loader = new CursorLoader(context, uri, projection, null,

                null, null);

        Cursor cursor = loader.loadInBackground();



        if (cursor != null) {

            cursor.moveToFirst();

            filePath = cursor.getString(cursor.getColumnIndex(projection[0]));

            cursor.close();

        }

        return filePath;

    }



    /**

     * 适配api11以下(不包括api11),根据uri获取图片的绝对路径

     */

    private static String getRealPathFromUri_BelowApi11(Context context, Uri uri) {

        String filePath = null;

        String[] projection = { MediaStore.Images.Media.DATA };

        Cursor cursor = context.getContentResolver().query(uri, projection,

                null, null, null);

        if (cursor != null) {

            cursor.moveToFirst();

            filePath = cursor.getString(cursor.getColumnIndex(projection[0]));

            cursor.close();

        }

        return filePath;

    }


    public void deletemarker(View view) {

        if (marker_id!=null){
            SendMessageManager.getInstance().deleteFootInfo(marker_id);
        }

        finish();
    }

}
