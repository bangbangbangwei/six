package com.dc.day02ri;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.utils.IoUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshListView plx;
    int page;
    private ListView listview;
    private MyBaseAdapter myBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plx = findViewById(R.id.plx);
        myBaseAdapter = new MyBaseAdapter(this);
        plx.setMode(PullToRefreshBase.Mode.BOTH);
        listview = plx.getRefreshableView();
        listview.setAdapter(myBaseAdapter);
        plx.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                initData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                initData();
            }
        });
        page = 1;
        initData();
    }

    @SuppressLint("StaticFieldLeak")
    private void initData() {
        new AsyncTask<String, Void, ArrayList<HttpBean.Data>>() {
            @Override
            protected ArrayList<HttpBean.Data> doInBackground(String... strings) {
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setReadTimeout(5000);
                    urlConnection.setConnectTimeout(5000);
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == 200){
                        String json = Stream(urlConnection.getInputStream());
                        HttpBean httpBean = new Gson().fromJson(json, HttpBean.class);
                        return httpBean.getData();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(ArrayList<HttpBean.Data> data) {
                if (page == 1){
                    myBaseAdapter.setList(data);
                }else{
                    myBaseAdapter.add(data);
                }
                plx.onRefreshComplete();
                page++;
            }
        }.execute("http://120.27.23.105/product/getProducts?pscid=39&page="+page);
    }
    public String Stream(InputStream inputStream) throws IOException{
        StringBuilder stringBuilder = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        for (String tmp = bufferedReader.readLine();tmp != null;tmp = bufferedReader.readLine()){
            stringBuilder.append(tmp);
        }
        return stringBuilder.toString();
    }
}
