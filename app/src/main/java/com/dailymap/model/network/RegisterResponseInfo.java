package com.dailymap.model.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by WuchangI on 2018/11/17.
 */

public class RegisterResponseInfo extends BaseResponseInfo
{
    /**
     * 注册状态，error = true表示注册成功，error = false 表示注册失败
     */
    @SerializedName("result")
    public String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
