package com.dc.day02ri;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HttpBean {
    public ArrayList<Data> data;

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public class Data{
        public String bargainPrice;
        public String images;
        public String title;

        public String getBargainPrice() {
            return bargainPrice;
        }

        public void setBargainPrice(String bargainPrice) {
            this.bargainPrice = bargainPrice;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
