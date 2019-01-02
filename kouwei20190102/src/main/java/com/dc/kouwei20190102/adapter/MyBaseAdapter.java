package com.dc.kouwei20190102.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dc.kouwei20190102.R;
import com.dc.kouwei20190102.bean.HttpBean;

import java.util.ArrayList;

public class MyBaseAdapter extends BaseAdapter {
    Context context;
    ArrayList<HttpBean.Data.Miaosha.List> list;

    public MyBaseAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setList(ArrayList<HttpBean.Data.Miaosha.List> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    /**
     * 获取数量
     * @return
     */
    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    /**
     * 获取坐标
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    /**
     * 坐标
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 视图展示
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
            holder.img = convertView.findViewById(R.id.img);
            holder.name = convertView.findViewById(R.id.name);
            holder.price = convertView.findViewById(R.id.price);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(list.get(position).title);
        holder.price.setText(list.get(position).bargainPrice+"");
        String[] split = list.get(position).images.split("\\|");
        Glide.with(context).load(split[0]).into(holder.img);
        return convertView;
    }

    /**
     * 优化Viewholder
     */
    class ViewHolder{
        TextView name,price;
        ImageView img;
    }
}
