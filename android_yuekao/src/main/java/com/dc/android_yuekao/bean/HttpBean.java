package com.dc.android_yuekao.bean;

import java.util.ArrayList;

public class HttpBean {
    public ArrayList<Data> result;

    public ArrayList<Data> getResult() {
        return result;
    }

    public void setResult(ArrayList<Data> result) {
        this.result = result;
    }

    public class Data{
        public String imageUrl;
        public String name;
        public String summary;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }
    }
}
