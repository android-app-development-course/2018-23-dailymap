package com.dailymap.network;


import com.dailymap.model.network.BaseResponseInfo;
import com.dailymap.model.network.DestinationResponseInfo;
import com.dailymap.model.network.FootsResponseInfo;
import com.dailymap.model.network.LoginResponseInfo;
import com.dailymap.model.network.MarkidImageInfo;
import com.dailymap.model.network.NewsResponseInfo;
import com.dailymap.model.network.RegisterResponseInfo;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by WuchangI on 2018/11/17.
 */

public class ReceiveMessageManager
{
    private static ReceiveMessageManager receiveMessageManager;

    public static ReceiveMessageManager getInstance()
    {
        return receiveMessageManager == null ? receiveMessageManager = new ReceiveMessageManager() : receiveMessageManager;
    }

    private ReceiveMessageManager()
    {
    }


    /**
     * 分发消息
     *
     * @param baseResponseInfo
     * @param appendUrl
     */
    public void dispatchMessage(BaseResponseInfo baseResponseInfo, String appendUrl)
    {
        switch (appendUrl)
        {
            case "VerifyUser":

                LoginResponseInfo loginResponseInfo = (LoginResponseInfo) baseResponseInfo;
                EventBus.getDefault().post(loginResponseInfo);

                break;
            case "getImagename":

                MarkidImageInfo markidImageInfo = (MarkidImageInfo) baseResponseInfo;
                EventBus.getDefault().post(markidImageInfo);

                break;

            case "RegisterUser":

                RegisterResponseInfo registerResponseInfo = (RegisterResponseInfo) baseResponseInfo;
                EventBus.getDefault().post(registerResponseInfo);

                break;

            case "getDestinationInfoFromUserId":

                DestinationResponseInfo destinationResponseInfo = (DestinationResponseInfo) baseResponseInfo;
                EventBus.getDefault().post(destinationResponseInfo);

                break;

            case "getFootInfoFromUserId":

                FootsResponseInfo footsResponseInfo = (FootsResponseInfo) baseResponseInfo;
                EventBus.getDefault().post(footsResponseInfo);

                break;

            case "insertFlagsInfo":

                RegisterResponseInfo registerResponseInfo1 = (RegisterResponseInfo) baseResponseInfo;
                EventBus.getDefault().post(registerResponseInfo1);

                break;

            case "insertFootInfo":

                RegisterResponseInfo registerResponseInfo2 = (RegisterResponseInfo) baseResponseInfo;
                EventBus.getDefault().post(registerResponseInfo2);

                break;


            case "deleteFootInfo":
            case "deleteDestinationInfo":
            case "InsertPhotoPath":

                BaseResponseInfo baseResponseInfo1 = (BaseResponseInfo) baseResponseInfo;
                EventBus.getDefault().post(baseResponseInfo1);
                break;

            default:
                break;
        }
    }


}
