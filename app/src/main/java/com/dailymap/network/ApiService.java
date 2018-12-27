package com.dailymap.network;

import com.dailymap.model.network.BaseResponseInfo;
import com.dailymap.model.network.Destination;
import com.dailymap.model.network.DestinationResponseInfo;
import com.dailymap.model.network.FootsResponseInfo;
import com.dailymap.model.network.LoginResponseInfo;
import com.dailymap.model.network.MarkidImageInfo;
import com.dailymap.model.network.NewsResponseInfo;
import com.dailymap.model.network.RegisterResponseInfo;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;


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
     * @param name
     * @param password
     * @param phone_num
     * @param user_intro
     * @param user_sex
     *  @param user_city
     * @param user_email
     * @return
     */
    @FormUrlEncoded
    @POST("RegisterUser")
    Observable<RegisterResponseInfo> getRegisterStatus(@Field("name") String name, @Field("password") String password,
                                                       @Field("phone_num") String phone_num,@Field("user_intro") String user_intro,@Field("user_sex") String user_sex,@Field("user_city") String user_city,@Field("user_email") String user_email);


    @GET("newsContent.php")
    Observable<NewsResponseInfo> getNews();

    @FormUrlEncoded
    @POST("getDestinationInfoFromUserId")
    Observable<DestinationResponseInfo> getDestinationInfoFromUserId(@Field("user_id") String user_id);
    @FormUrlEncoded
    @POST("getFootInfoFromUserId")
    Observable<FootsResponseInfo> getFootInfoFromUserId(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("getDestinationInfoFromMarkerId")
    Observable<DestinationResponseInfo> getDestinationInfoFromMarkerId(@Field("marker_id") String marker_id);

    @FormUrlEncoded
    @POST("insertFlagsInfo")
    Observable<RegisterResponseInfo> insertFlagsInfo(@Field("user_id") String user_id,@Field("latitude") String latitude,@Field("longitude") String longitude,@Field("place_name") String place_name,@Field("travel_plan") String travel_plan);

    @FormUrlEncoded
    @POST("insertFootInfo")
    Observable<RegisterResponseInfo> insertFootInfo(@Field("user_id") String user_id,@Field("latitude") String latitude,@Field("longitude") String longitude,@Field("place_name") String place_name,@Field("thought") String thought);

    @FormUrlEncoded
    @POST("updateDestinationInfo")
    Observable<RegisterResponseInfo> updateDestinationInfo(@Field("marker_id") String marker_id,@Field("latitude") String latitude,@Field("place_name") String place_name,@Field("longitude") String longitude,@Field("travel_plan") String travel_plan);

    @FormUrlEncoded
    @POST("updateFootsInfo")
    Observable<RegisterResponseInfo> updateFootsInfo(@Field("marker_id") String marker_id,@Field("latitude") String latitude,@Field("longitude") String longitude,@Field("place_name") String place_name,@Field("travel_plan") String travel_plan);



    @FormUrlEncoded
    @POST("deleteDestinationInfo")
    Observable<BaseResponseInfo> deleteDestinationInfo(@Field("marker_id") String marker_id);


    @FormUrlEncoded
    @POST("deleteFootInfo")
    Observable<RegisterResponseInfo> deleteFootInfo(@Field("marker_id") String marker_id);


    @FormUrlEncoded
    @POST("getFootInfoFromMarkerId")
    Observable<FootsResponseInfo> getFootInfoFromMarkerId(@Field("marker_id") String marker_id);

    @FormUrlEncoded
    @POST("getImagename")
    Observable<MarkidImageInfo> getImagename(@Field("marker_id") String marker_id);

    @Multipart
    @POST("InsertPhotoPath")
    Observable<BaseResponseInfo> upImg(@PartMap Map<String,String> params ,@Part MultipartBody.Part file);


    @FormUrlEncoded
    @POST("readImage")
    Observable<ResponseBody> readImage(@Field("filename") String filename);


}
