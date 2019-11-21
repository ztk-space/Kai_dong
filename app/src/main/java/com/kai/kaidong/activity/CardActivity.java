package com.kai.kaidong.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.kai.kaidong.R;
import com.kai.kaidong.base.BaseActivity;

import io.armcha.elasticview.ElasticView;
//引用的事第三方elastic_view
public class CardActivity extends BaseActivity {

    private ElasticView imageView;
    private AppCompatSeekBar seekBar;
    private ElasticView button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
     imageView = findViewById(R.id.imageElasticView);
     seekBar = findViewById(R.id.seekBar);
     button = findViewById(R.id.buttonElasticView);
     button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(CardActivity.this,CardListActivity.class));
         }
     });
     seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
         @Override
         public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
           imageView.setFlexibility(progress / 10f + 1f);
         }
         @Override
         public void onStartTrackingTouch(SeekBar seekBar) {

         }

         @Override
         public void onStopTrackingTouch(SeekBar seekBar) {

         }
     });

    }

    @Override
    protected int findView() {
        return R.layout.activity_card;
    }
}
