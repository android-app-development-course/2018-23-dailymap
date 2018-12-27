package com.dailymap.model.network;

import java.util.List;

public class MarkidImageInfo extends BaseResponseInfo{
    public List<data> result;

    public List<data> getResult() {
        return result;
    }

    public void setResult(List<data> result) {
        this.result = result;
    }

    public class data{
        public String id;
        public String marker_id;
        public String filename;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMarker_id() {
            return marker_id;
        }

        public void setMarker_id(String marker_id) {
            this.marker_id = marker_id;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }
    }
}
