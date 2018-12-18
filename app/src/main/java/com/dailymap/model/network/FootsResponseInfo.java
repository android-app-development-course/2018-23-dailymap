package com.dailymap.model.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FootsResponseInfo extends BaseResponseInfo {
    @SerializedName("result")
    public List<FootsInfo> result;

    public List<FootsInfo> getResult() {
        return result;
    }

    public void setResult(List<FootsInfo> result) {
        this.result = result;
    }

}
