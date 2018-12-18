package com.dailymap.model.network;

public class Destination {
    /*"marker_id":"4",
    "latitude":"31.347828165009314",
    "longitude":"122.09253760581146",
    "place_name":"日本东京",
    "travel_plan":"上海不错呀"*/
    private String marker_id;
    private String latitude;
    private String longitude;
    private String place_name;
    private String travel_plan;

    public String getMarker_id() {
        return marker_id;
    }

    public void setMarker_id(String marker_id) {
        this.marker_id = marker_id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getTravel_plan() {
        return travel_plan;
    }

    public void setTravel_plan(String travel_plan) {
        this.travel_plan = travel_plan;
    }
}
