package com.dc.kouwei20190102.bean;

import java.util.ArrayList;

public class HttpBean {
    public ArrayList<Data> data;

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public class Data{
        public double bargainPrice;
        public String images;
        public String title;
    }
}
