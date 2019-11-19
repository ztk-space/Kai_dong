package com.kai.kaidong.fragment;

import android.os.Bundle;
import android.view.View;

import com.kai.kaidong.R;
import com.kai.kaidong.base.BaseFragment;
import com.kai.kaidong.internetutil.RetrofitUtil;

public class HomeFragment extends BaseFragment {
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        git remote add origin https://github.com/ztk-space/Kai_dong.git
    }

    @Override
    protected void intView(View view) {

    }

    @Override
    protected int initLayout() {
        return R.layout.homefragment;
    }
}
