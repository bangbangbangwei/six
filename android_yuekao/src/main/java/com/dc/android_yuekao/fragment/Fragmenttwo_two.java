package com.dc.android_yuekao.fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.dc.android_yuekao.bean.HttpBean;
import com.dc.android_yuekao.adapter.MyBaseAdapter;
import com.dc.android_yuekao.NenUtils;
import com.dc.android_yuekao.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class Fragmenttwo_two extends BaseFragment {

    private PullToRefreshListView plv;
    String path = "http://172.17.8.100/movieApi/movie/v1/findReleaseMovieList?page=";
    int page = 0;
    private MyBaseAdapter myBaseAdapter;
    private ListView listview;

    @Override
    public int getLayout() {
        return R.layout.fragmenttwo_two;
    }

    @Override
    public void initView(View view) {
        plv = view.findViewById(R.id.plv);
        plv.setMode(PullToRefreshBase.Mode.BOTH);
        myBaseAdapter = new MyBaseAdapter(getActivity());
        listview = plv.getRefreshableView();
        listview.setAdapter(myBaseAdapter);
        plv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
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

    @Override
    public void initData() {
        if (new NenUtils().isHasNetWork(getActivity())){
            new NenUtils().getRequset3(path + page + "&count=10", HttpBean.class, new NenUtils.CallBack() {
                @Override
                public void onsuccess(Object o) {
                    HttpBean httpBean = (HttpBean) o;
                    if (page == 1){
                        myBaseAdapter.setData(httpBean.getResult());
                    }else{
                        myBaseAdapter.add(httpBean.getResult());
                    }
                    plv.onRefreshComplete();
                    page++;
                }
            });
        }
    }
}
