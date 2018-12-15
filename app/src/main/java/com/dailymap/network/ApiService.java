package com.dailymap.network;

import com.dailymap.model.network.LoginResponseInfo;
import com.dailymap.model.network.NewsResponseInfo;
import com.dailymap.model.network.RegisterResponseInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by WuchangI on 2018/11/17.
 */

public interface ApiService
{
    /**
     * 获取用户登录状态
     * @param email
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("VerifyUser")
    Observable<LoginResponseInfo> getLoginStatus(@Field("username") String email, @Field("password") String password);

    /**
     * 获取用户注册状态
     * @param username
     * @param email
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("RegisterUser")
    Observable<RegisterResponseInfo> getRegisterStatus(@Field("name") String username, @Field("password") String password,
                                                       @Field("phone_num") String phone_num,@Field("user_intro") String user_intro,@Field("user_sex") String user_sex,@Field("user_city") String user_city,@Field("user_email") String user_email);

    /**
     * 获取新闻列表
     * @return
     */
    @GET("newsContent.php")
    Observable<NewsResponseInfo> getNews();

}
