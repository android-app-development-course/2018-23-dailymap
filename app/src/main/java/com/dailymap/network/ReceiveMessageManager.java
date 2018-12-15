package com.dailymap.network;


import com.dailymap.model.network.BaseResponseInfo;
import com.dailymap.model.network.LoginResponseInfo;
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
            case "login.php":

                LoginResponseInfo loginResponseInfo = (LoginResponseInfo) baseResponseInfo;
                EventBus.getDefault().post(loginResponseInfo);

                break;


            case "RegisterUser":

                RegisterResponseInfo registerResponseInfo = (RegisterResponseInfo) baseResponseInfo;
                EventBus.getDefault().post(registerResponseInfo);

                break;


            case "newsContent.php":

                NewsResponseInfo newsResponseInfo = (NewsResponseInfo) baseResponseInfo;
                EventBus.getDefault().post(newsResponseInfo);

                break;

            default:
                break;
        }
    }


}
