package com.dc.android_yuekao;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NenUtils {
    public NenUtils instence;
    Handler handler = new Handler();
    public interface CallBack<T>{
        void onsuccess(T o);
    }
    public void getRequset3(final String apiUrl, final Class clazz, final CallBack callBack){
       new Thread(new Runnable() {
           @Override
           public void run() {
               final Object requset2 = getRequset2(apiUrl, clazz);

                   handler.post(new Runnable() {
                       @Override
                       public void run() {
                           callBack.onsuccess(requset2);
                       }
                   });
           }
       }).start();
    }
    public <T> T getRequset2(String apiUrl,Class clazz){
        String requset = getRequset(apiUrl, clazz);
        T o = (T) new Gson().fromJson(requset,clazz);
        return o;
    }
    public String getRequset(String apiUrl,Class clazz){
        String json = "";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200){
                json = Stream(urlConnection.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
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
    public boolean isHasNetWork(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }
}
