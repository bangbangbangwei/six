package com.dc.android_zhou1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements Contract.iJsonView{

    private GridView gv;
    private JsonPresenter jsonPresenter;
    private MyBaseAdapter myBaseAdapter;
    private HashMap<String, String> params;
    int pid = 21;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        jsonPresenter = new JsonPresenter(this);
        myBaseAdapter = new MyBaseAdapter(this);
        gv.setAdapter(myBaseAdapter);
        params = new HashMap<>();
        params.put("pid",pid+"");
        jsonPresenter.goJson(UserApi.path,params);
    }

    private void initView() {
        gv = findViewById(R.id.gv);
    }

    @Override
    public void success(String res) {
        HttpBean httpBean = new Gson().fromJson(res, HttpBean.class);
        ArrayList<HttpBean.Data> data = httpBean.data;
        myBaseAdapter.setList(data);

    }

    @Override
    public void fail(String string) {

    }
}
