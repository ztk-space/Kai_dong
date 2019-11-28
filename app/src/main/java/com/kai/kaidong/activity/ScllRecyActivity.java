package com.kai.kaidong.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kai.kaidong.R;
import com.kai.kaidong.recy.GridViewActivity;
import com.kai.kaidong.recy.ListViewActivity;

public class ScllRecyActivity extends AppCompatActivity {

    private Button button1,button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scll_recy);
        button1 = findViewById(R.id.button3);
        button2 = findViewById(R.id.button4);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScllRecyActivity.this, ListViewActivity.class));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScllRecyActivity.this, GridViewActivity.class));
            }
        });
    }
}
