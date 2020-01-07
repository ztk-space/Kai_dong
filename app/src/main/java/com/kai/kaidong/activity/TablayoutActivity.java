package com.kai.kaidong.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kai.kaidong.R;
import com.kai.kaidong.base.BaseActivity;
import com.antiless.support.widget.TabLayout;
import com.kai.kaidong.bottomfragment.MineFragment;
import com.kai.kaidong.sqLite.Helper;
import com.kai.kaidong.tabfragment.TabFeaturesFragment;
import com.kai.kaidong.tabfragment.TabHelpFragment;
import com.kai.kaidong.tabfragment.TabLoginFragment;
import com.kai.kaidong.tabfragment.TabRegisterediFragment;

import java.util.ArrayList;
import java.util.List;
//引用的事tablayout第三方
public class TablayoutActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
//原生
//        ViewPager viewPager = findViewById(R.id.viewPager);
//        viewPager.setAdapter(new ContentPagerAdapter());
//        TabLayout tabLayout = findViewById(R.id.tabLayout);
//        tabLayout.setupWithViewPager(viewPager);

        List<String> tabs = new ArrayList<>();
        tabs.add("登录");
        tabs.add("注册");
        tabs.add("帮助");
        tabs.add("功能");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new TabLoginFragment());
        fragments.add(new TabRegisterediFragment());
        fragments.add(new TabHelpFragment());
        fragments.add(new TabFeaturesFragment());
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new MovieAdapter(getSupportFragmentManager(),tabs,fragments));
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected int findView() {
        return R.layout.activity_tablayout;
    }

    //原生
//    public static class ContentPagerAdapter extends PagerAdapter {
//        private static final int PAGE_COUNT = 3;
//        @Override
//        public int getCount() {
//            return PAGE_COUNT;
//        }
//
//        @NonNull
//        @Override
//        public Object instantiateItem(@NonNull ViewGroup container, int position) {
//            TextView textView = new TextView(container.getContext());
//            textView.setText(getPageTitle(position));
//            container.addView(textView);
//            return textView;
//        }
//
//        @Override
//        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            if (((View) object).getParent() == container) {
//                container.removeView((View) object);
//            }
//        }
//
//        @Override
//        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
//            return view == o;
//        }
//
//        @Nullable
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return "Tab-" + position;
//        }
//    }
    public class MovieAdapter extends FragmentPagerAdapter {
        List<String> tabs;
        List<Fragment> fragments;

        public MovieAdapter(FragmentManager fm, List<String> tabs, List<Fragment> fragments) {
            super(fm);
            this.tabs = tabs;
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }
    }
}
