package com.dailymap.model.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by WuchangI on 2018/11/17.
 */

public class LoginResponseInfo extends BaseResponseInfo
{
    @SerializedName("result")
    public UserInfo result;

    public UserInfo getResult() {
        return result;
    }

    public void setResult(UserInfo result) {
        this.result = result;
    }
}
