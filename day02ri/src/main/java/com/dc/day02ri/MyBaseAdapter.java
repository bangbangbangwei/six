package com.dc.day02ri;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class MyBaseAdapter extends BaseAdapter {
    ArrayList<HttpBean.Data> list;
    Context context;

    public MyBaseAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setList(ArrayList<HttpBean.Data> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
       if (position % 2 == 0){
           return 0;
       }
       return 1;
    }

    public void add(ArrayList<HttpBean.Data> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (getItemViewType(position) == 0){
            ViewHolder holder;
            if (convertView == null){
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_list_image,parent,false);
                holder.tv_title = convertView.findViewById(R.id.tv_title);
                holder.tv_price = convertView.findViewById(R.id.tv_price);
                holder.img = convertView.findViewById(R.id.img);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
                //
            }
            holder.tv_title.setText(list.get(position).getTitle());
            holder.tv_price.setText(list.get(position).getBargainPrice());
            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
            String substring = list.get(position).getImages().substring(0,98);
            ImageLoader.getInstance().displayImage(substring,holder.img,options);
        }else{
            PriceHolder holder;
            if (convertView == null){
                holder = new PriceHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
                holder.price = convertView.findViewById(R.id.price);
                holder.title = convertView.findViewById(R.id.title);
                convertView.setTag(holder);
            }else{
                holder = (PriceHolder) convertView.getTag();
            }
                holder.title.setText(list.get(position).getTitle());
                holder.price.setText(list.get(position).getBargainPrice());
        }
        return convertView;
    }
    class PriceHolder{
        TextView title,price;
    }
    class ViewHolder{
        ImageView img;
        TextView tv_title,tv_price;
    }
}
