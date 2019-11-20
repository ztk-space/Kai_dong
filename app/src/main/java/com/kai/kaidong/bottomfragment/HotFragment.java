package com.kai.kaidong.bottomfragment;

import android.os.Bundle;
import android.view.View;

import com.kai.kaidong.R;
import com.kai.kaidong.base.BaseFragment;

public class HotFragment extends BaseFragment {
    public static HotFragment newInstance() {
        HotFragment fragment = new HotFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void intView(View view) {

    }

    @Override
    protected int initLayout() {
        return R.layout.hotfragment;
    }
}
