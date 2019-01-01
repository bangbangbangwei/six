package com.dc.android_zhoulian3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

public class MyAdapter extends XRecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<HttpBean.DataBean> list;

    public MyAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setList(ArrayList<HttpBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public void add(ArrayList<HttpBean.DataBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.iten_list, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        HttpBean.DataBean dataBean = list.get(i);
        myViewHolder.name.setText(dataBean.title);
        String images = dataBean.images;
        String[] split = images.split("\\|");
        Glide.with(context).load(split[0]).into(myViewHolder.img);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 :list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
        }
    }
}
