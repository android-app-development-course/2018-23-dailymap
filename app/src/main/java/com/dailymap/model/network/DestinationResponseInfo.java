package com.dailymap.model.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DestinationResponseInfo extends BaseResponseInfo {
    @SerializedName("result")
    public List<Destination> result;

    public List<Destination> getResult() {
        return result;
    }

    public void setResult(List<Destination> result) {
        this.result = result;
    }
}
