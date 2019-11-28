package com.kai.kaidong.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.douyinloadingview.DYLoadingView;
import com.kai.kaidong.R;
import com.kai.kaidong.base.BaseActivity;
//https://blog.csdn.net/ccy0122/article/details/82387053
public class DouYinActivity extends BaseActivity implements View.OnClickListener {

    private Button button1,button2;
    private DYLoadingView dyLoadingView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void init() {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        dyLoadingView = findViewById(R.id.dy3);
    }

    @Override
    protected int findView() {
        return R.layout.activity_dou_yin;
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.button1:
              dyLoadingView.setDuration(400,200);
              dyLoadingView.start();
              break;
          case R.id.button2:
              dyLoadingView.stop();
              break;

     }
    }
}
