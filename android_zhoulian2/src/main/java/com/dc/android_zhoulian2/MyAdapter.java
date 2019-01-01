package com.dc.android_zhoulian2;

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

public class MyAdapter extends XRecyclerView.Adapter<MyAdapter.XRecycler> {
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
    public XRecycler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, viewGroup, false);
        XRecycler xRecycler = new XRecycler(view);
        return xRecycler;
    }

    @Override
    public void onBindViewHolder(@NonNull XRecycler xRecycler, int i) {
        HttpBean.DataBean dataBean = list.get(i);
        xRecycler.name.setText(dataBean.title);
        String images = dataBean.images;
        String[] split = images.split("\\|");
        Glide.with(context).load(split[0]).into(xRecycler.img);

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class XRecycler extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        public XRecycler(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
        }
    }
}
