package com.dc.kouwei20190102.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.dc.kouwei20190102.adapter.MyBaseAdapter;
import com.dc.kouwei20190102.R;
import com.dc.kouwei20190102.api.UserApi;
import com.dc.kouwei20190102.bean.HttpBean;
import com.dc.kouwei20190102.contract.Contract;
import com.dc.kouwei20190102.net.OkHttpUtils;
import com.dc.kouwei20190102.presenter.JsonPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements Contract.iJsonView{

    private GridView gv;
    private JsonPresenter jsonPresenter;
    private MyBaseAdapter myBaseAdapter;
    private HashMap<String, String> params;
    int pid = 21;
    private ArrayList<HttpBean.Data> data;

    /**
     * 10:44
     * 寇炜
     * 展示列表
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    /**
     * 存放数据
     * 获取资源架构
     */
    private void initData() {

        jsonPresenter = new JsonPresenter(this);
        myBaseAdapter = new MyBaseAdapter(this);
        gv.setAdapter(myBaseAdapter);
        params = new HashMap<>();
        params.put("pid",pid+"");
        jsonPresenter.goJson(UserApi.path,params);
    }

    /**
     * 获取资源id
     * 设置监听
     */
    private void initView() {
        gv = findViewById(R.id.gv);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, LogActivity.class);
                intent.putExtra("name",data.get(position).title);
                intent.putExtra("price",data.get(position).bargainPrice);
                intent.putExtra("img",data.get(position).images);
                startActivity(intent);
            }
        });
        gv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                data.remove(position);
                myBaseAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    /**
     * 成功回调方法
     * @param res
     */
    @Override
    public void success(String res) {
        HttpBean httpBean = new Gson().fromJson(res, HttpBean.class);
        data = httpBean.data;
        myBaseAdapter.setList(data);
    }

    @Override
    public void fail(String string) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getmInstance().cancellMvp();
    }
}
