package com.dc.android_zhoulian2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements Contract.IJsonView {
    int page = 1;
    private XRecyclerView xre;
    private Presenter presenter;
    private MyAdapter myAdapter;
    private HashMap<String, String> map;
    private ArrayList<HttpBean.DataBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        presenter = new Presenter(this);
        myAdapter = new MyAdapter(this);
        xre.setAdapter(myAdapter);
        map = new HashMap<>();
        map.put("keywords","手机");
        setLoadData(map,page);
    }

    private void setLoadData(HashMap<String,String> map, int page) {
        map.put("page",page+"");
        presenter.goJson(map,UserApi.Api);
    }

    private void initView() {
        xre = findViewById(R.id.xre);
        xre.setLayoutManager(new GridLayoutManager(this,2));
        xre.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                setLoadData(map,page);
            }

            @Override
            public void onLoadMore() {
                setLoadData(map,page);
            }
        });
    }

    @Override
    public void success(String res) {
        HttpBean httpBean = new Gson().fromJson(res, HttpBean.class);
        list = httpBean.data;
        if (page == 1){
            myAdapter.setList(list);
        }else{
            myAdapter.add(list);
        }
        xre.loadMoreComplete();
        page++;
    }

    @Override
    public void fail(String meg) {

    }
}
