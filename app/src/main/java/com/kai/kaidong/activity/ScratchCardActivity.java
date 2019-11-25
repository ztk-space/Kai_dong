package com.kai.kaidong.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kai.kaidong.R;
import com.kai.kaidong.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import in.myinnos.androidscratchcard.ScratchCard;

public class ScratchCardActivity extends BaseActivity {
    private TextView textView, txReload;
    private ScratchCard mScratchCard;
    private List<String> list = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void init() {
        list.add("焦子鹏");
        list.add("卢超");
        list.add("赵腾开");
        list.add("杨延勋");
        list.add("林廷归");
        list.add("黄禹铭");
        list.add("屈前磊");
        textView = (TextView) findViewById(R.id.textView);
        txReload = (TextView) findViewById(R.id.txReload);

        loadRandomNumber();

        mScratchCard = (ScratchCard) findViewById(R.id.scratchCard);
        mScratchCard.setOnScratchListener(new ScratchCard.OnScratchListener() {
            @Override
            public void onScratch(ScratchCard scratchCard, float visiblePercent) {
                if (visiblePercent > 0.3) {
                    mScratchCard.setVisibility(View.GONE);
                    Toast.makeText(ScratchCardActivity.this, "Content Visible", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadRandomNumber();
            }
        });
    }

    @Override
    protected int findView() {
        return R.layout.activity_scratch_card;
    }
    private void loadRandomNumber(){
        Random rand = new Random();
        String randomNum = String.valueOf(20 + rand.nextInt((100 - 20) + 1));
        String s = list.get(0 + rand.nextInt(list.size()));
        textView.setText(s);
    }
}
