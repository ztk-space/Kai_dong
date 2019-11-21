package com.kai.kaidong.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.kai.kaidong.R;
import com.kai.kaidong.base.BaseActivity;

public class VideoPlayActivity extends BaseActivity {
    private WebView webView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {

    }

    @Override
    protected int findView() {
        return R.layout.activity_video_play;
    }
}
