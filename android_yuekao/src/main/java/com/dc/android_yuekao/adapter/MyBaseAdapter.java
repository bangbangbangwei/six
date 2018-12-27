package com.dc.android_yuekao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dc.android_yuekao.bean.HttpBean;
import com.dc.android_yuekao.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class MyBaseAdapter extends BaseAdapter {
    ArrayList<HttpBean.Data> data;
    Context context;

    public MyBaseAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(ArrayList<HttpBean.Data> data) {
        this.data = data;
        notifyDataSetChanged();
    }
    public void add(ArrayList<HttpBean.Data> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
            holder.name = convertView.findViewById(R.id.name);
            holder.summary = convertView.findViewById(R.id.summary);
            holder.img = convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(data.get(position).getName());
        holder.summary.setText(data.get(position).getSummary());
        DisplayImageOptions options = new DisplayImageOptions.Builder().build();
        ImageLoader.getInstance().displayImage(data.get(position).getImageUrl(),holder.img,options);
        return convertView;
    }
    class ViewHolder{
        TextView name,summary;
        ImageView img;
    }
}
