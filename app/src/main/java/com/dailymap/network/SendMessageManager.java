package com.dailymap.network;


import com.dailymap.constant.Constants;
import com.dailymap.model.network.DestinationResponseInfo;
import com.dailymap.model.network.FootsResponseInfo;
import com.dailymap.model.network.LoginResponseInfo;
import com.dailymap.model.network.NewsResponseInfo;
import com.dailymap.model.network.RegisterResponseInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;

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

    /**
     * 发送“获取新闻列表”的消息
     */
    public void getNews()
    {
        Observable<NewsResponseInfo> observable = apiService.getNews();
        httpChannel.sendMessage(observable, Constants.GET_NEWS_URL);
    }

    public void getDestinationInfoFromUserId(String user_id){
        Observable<DestinationResponseInfo> observable = apiService.getDestinationInfoFromUserId(user_id);
        httpChannel.sendMessage(observable, Constants.GETDESTINATIONFROMUSERID);
    }
    public void getFootInfoFromUserId(String user_id){
        Observable<FootsResponseInfo> observable = apiService.getFootInfoFromUserId(user_id);
        httpChannel.sendMessage(observable, Constants.GETFOOTSINFOFROMUSERID);
    }
    public void getDestinationInfoFromMarkerId(String marker_id){
        Observable<DestinationResponseInfo> observable = apiService.getDestinationInfoFromMarkerId(marker_id);
        httpChannel.sendMessage(observable, Constants.GET_NEWS_URL);
    }

    public void insertFlagsInfo(String user_id,String latitude,String longitude,String place_name,String travel_plan){
        Observable<RegisterResponseInfo> observable = apiService.insertFlagsInfo(user_id,latitude,longitude,place_name,travel_plan);
        httpChannel.sendMessage(observable, Constants.INSERTFLAGSINFO);
    }


}
