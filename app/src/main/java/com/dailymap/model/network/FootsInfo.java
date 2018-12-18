package com.dailymap.model.network;

public class FootsInfo {
    /**
     * "user_id":"4",
     * "marker_id":"10",
     * "latitude":"23.10253150086121","
     * longitude":"113.34650040030947",
     * "place_name":"冰岛",
     * "thought":"好呀啦啦啦",
     * "photos_path":"H:\\\\eclipse-workspace\\\\.metadata\\\\.plugins\\\\org.eclipse.wst.server.core\\\\tmp0\\\\wtpwebapps\\\\LittleTest\\\\uploadphpto"
     * */

    private String user_id;
    private String marker_id;
    private String latitude;
    private String longitude;
    private String place_name;
    private String thought;
    private String photos_path;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

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

    public String getThought() {
        return thought;
    }

    public void setThought(String thought) {
        this.thought = thought;
    }

    public String getPhotos_path() {
        return photos_path;
    }

    public void setPhotos_path(String photos_path) {
        this.photos_path = photos_path;
    }
}
