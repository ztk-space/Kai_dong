package com.kai.kaidong.fragment;

import android.os.Bundle;
import android.view.View;

import com.kai.kaidong.R;
import com.kai.kaidong.base.BaseFragment;

public class MineFragment extends BaseFragment {
    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
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
        return R.layout.minefragment;
    }
}
