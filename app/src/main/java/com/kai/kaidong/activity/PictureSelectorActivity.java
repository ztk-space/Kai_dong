package com.kai.kaidong.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kai.kaidong.R;
import com.kai.kaidong.base.BaseActivity;

public class PictureSelectorActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {

    }

    @Override
    protected int findView() {
        return R.layout.activity_picture_selector;
    }
}
