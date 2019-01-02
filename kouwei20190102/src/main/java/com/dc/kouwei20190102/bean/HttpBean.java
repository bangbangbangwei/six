package com.dc.kouwei20190102.bean;

import java.util.ArrayList;

public class HttpBean {
    public Data data;


    public class Data{
        public Miaosha miaosha;
        public class Miaosha{
            public ArrayList<List> list;
                public class List{
                    public int bargainPrice;
                    public String images;
                    public String title;
                }
        }
    }
}
