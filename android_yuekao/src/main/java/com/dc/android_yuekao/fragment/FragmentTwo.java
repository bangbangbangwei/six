package com.dc.android_yuekao.fragment;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.dc.android_yuekao.R;

public class FragmentTwo extends BaseFragment {

    private TabLayout tab;
    private ViewPager pager1;

    @Override
    public int getLayout() {
        return R.layout.fragmenttwo;
    }

    @Override
    public void initView(View view) {
        tab = view.findViewById(R.id.tab);
        pager1 = view.findViewById(R.id.pager1);
        final String[] names = new String[]{
                "正在上映","即将上映"
        };
        pager1.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0:
                        return new Fragmenttwo_One();
                    case 1:
                        return new Fragmenttwo_Two1();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return names[position];
            }
        });
        tab.setupWithViewPager(pager1);
    }

    @Override
    public void initData() {

    }
}
