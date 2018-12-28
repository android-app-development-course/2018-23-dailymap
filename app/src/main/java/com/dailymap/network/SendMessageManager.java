package com.dailymap.network;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.dailymap.constant.Constants;
import com.dailymap.model.network.BaseResponseInfo;
import com.dailymap.model.network.DestinationResponseInfo;
import com.dailymap.model.network.FootsResponseInfo;
import com.dailymap.model.network.LoginResponseInfo;
import com.dailymap.model.network.MarkidImageInfo;
import com.dailymap.model.network.NewsResponseInfo;
import com.dailymap.model.network.RegisterResponseInfo;

import org.reactivestreams.Subscriber;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.Part;

/**
 * Created by WuchangI on 2018/11/17.
 */

public class SendMessageManager
{
    private static SendMessageManager sendMessageManager;

    private HttpChannel httpChannel;

    private ApiService apiService;

    public static SendMessageManager getInstance()
    {
        return sendMessageManager == null ? sendMessageManager = new SendMessageManager() : sendMessageManager;
    }

    private SendMessageManager()
    {
        httpChannel = HttpChannel.getInstance();
        apiService = httpChannel.getApiService();
    }

    /**
     * 发送“获取用户登录状态”的消息
     * @param username
     * @param password
     */
    public void getLoginStatus(String username, String password)
    {
        Observable<LoginResponseInfo> observable = apiService.getLoginStatus(username, password);
        httpChannel.sendMessage(observable, Constants.GET_LOGIN_STATUS_URL);
    }


    /**
     * 发送“获取用户注册状态”的消息
     * @param username
     * @param password
     * @param phone_num
     *
     */
    public void getRegisterStatus(String username, String password,String phone_num,String user_intro,String user_sex,String user_city,String user_email)
    {
        Observable<RegisterResponseInfo> observable = apiService.getRegisterStatus(username,password,phone_num,user_intro,user_sex,user_city,user_email);
        httpChannel.sendMessage(observable, Constants.GET_REGISTER_STATUS_URL);
    }

    public void getDestinationInfoFromUserId(String user_id){
        Observable<DestinationResponseInfo> observable = apiService.getDestinationInfoFromUserId(user_id);
        httpChannel.sendMessage(observable, Constants.GETDESTINATIONFROMUSERID);
    }
    public void getFootInfoFromUserId(String user_id){
        Observable<FootsResponseInfo> observable = apiService.getFootInfoFromUserId(user_id);
        httpChannel.sendMessage(observable, Constants.GETFOOTSINFOFROMUSERID);
    }
    public void getImagename(String marker_id){
        Observable<MarkidImageInfo> observable = apiService.getImagename(marker_id);
        httpChannel.sendMessage(observable, Constants.GETIMAGENAME);
    }

    public void insertFlagsInfo(String user_id,String latitude,String longitude,String place_name,String travel_plan){
        Observable<RegisterResponseInfo> observable = apiService.insertFlagsInfo(user_id,latitude,longitude,place_name,travel_plan);
        httpChannel.sendMessage(observable, Constants.INSERTFLAGSINFO);
    }

    public void insertFootInfo(String user_id,String latitude,String longitude,String place_name,String thought){
        Observable<RegisterResponseInfo> observable = apiService.insertFootInfo(user_id,latitude,longitude,place_name,thought);
        httpChannel.sendMessage(observable, Constants.INSERTFOOTINFO);
    }

    public void updateFootsInfo(String marker_id,String latitude,String longitude,String place_name,String thought){
        Observable<RegisterResponseInfo> observable = apiService.updateFootsInfo(marker_id,latitude,longitude,place_name,thought);
        httpChannel.sendMessage(observable, Constants.GET_REGISTER_STATUS_URL);
    }
    public void updateDestinationInfo(String marker_id,String latitude,String longitude,String place_name,String travel_plan){
        Observable<RegisterResponseInfo> observable = apiService.updateDestinationInfo(marker_id,latitude,longitude,place_name,travel_plan);
        httpChannel.sendMessage(observable, Constants.GET_REGISTER_STATUS_URL);
    }

    public void upImg(Map<String,String> params, MultipartBody.Part file){
        Map<String,MultipartBody.Part> map=new HashMap<>();
        map.put("file",file);
        Observable<BaseResponseInfo> observable = apiService.upImg(file,params);
        httpChannel.sendMessage(observable, Constants.INSERTPHPTOPATH);
    }

    public Bitmap readImage(String filename){
        //Observable<ResponseBody> observable = apiService.readImage(filename);
String dirurl=Constants.BASE_REQUEST_URL+"readImage/?filename="+filename;
return getimage(dirurl);
    }

    private Bitmap getimage(String dirurl) {
        Bitmap bitmap = null;
        try {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int len = 0;
            URL url = new URL(dirurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream inStream = conn.getInputStream();
            bitmap=BitmapFactory.decodeStream(inStream);

        }catch (Exception e){

        }
        return bitmap;
    }

    public void deleteDestinationInfo(String marker_id){
        Observable<BaseResponseInfo> observable = apiService.deleteDestinationInfo(marker_id);
        httpChannel.sendMessage(observable, Constants.DELETEDESTINATIONINFO);
    }

    public void deleteFootInfo(String marker_id){
        Observable<RegisterResponseInfo> observable = apiService.deleteFootInfo(marker_id);
        httpChannel.sendMessage(observable, Constants.INSERTFOOTINFO);
    }

}
