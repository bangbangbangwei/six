package com.dc.android_zhoulian32;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements Contract.IJsonView {

    private XRecyclerView xre;
    private EditText search;
    private MyAdapter myAdapter;
    private JsonPresenter jsonPresenter;
    int page = 1;
    private HashMap<String, String> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        myAdapter = new MyAdapter(this);
        jsonPresenter = new JsonPresenter(this);
        xre.setAdapter(myAdapter);
        params = new HashMap<>();
        params.put("keywords","手机");
        setDataLoad(params,page);
    }



    private void initView() {
        xre = findViewById(R.id.xre);
        search = findViewById(R.id.search);
        xre.setLoadingMoreEnabled(true);
        xre.setLayoutManager(new GridLayoutManager(this,1));
        xre.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                setDataLoad(params,page);
            }

            @Override
            public void onLoadMore() {
                setDataLoad(params,page);
            }

        });
    }
    private void setDataLoad(HashMap<String,String> params, int page) {
        params.put("page",1+"");
        jsonPresenter.goJson(UserApi.Path,params);
    }
    public void search1(View view) {
        dianji();
    }

    private void dianji() {
        page = 1;
        params.put("keywords",search.getText().toString());
        setDataLoad(params,page);
    }

    @Override
    public void success(String res) {
        HttpBean httpBean = new Gson().fromJson(res, HttpBean.class);
        ArrayList<HttpBean.DataBean> data = httpBean.data;
        if (page == 1){
            myAdapter.setList(data);
        }else{
            myAdapter.addd(data);
        }
        xre.loadMoreComplete();
        page++;
    }

    @Override
    public void fail(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getmInstance().okHttpDispatch();
    }
}
