package com.kai.kaidong.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kai.kaidong.R;
import com.kai.kaidong.adapter.XinDongFangAdpter;
import com.kai.kaidong.base.BaseActivity;
import com.kai.kaidong.util.PopUtil;
import com.kai.kaidong.util.PopWindowUtil;

import java.util.ArrayList;
import java.util.List;

public class XinDongFangActivity extends BaseActivity {

    private List<String> list = new ArrayList<>();
    private XinDongFangAdpter xinDongFangAdpter;
    private RecyclerView recyclerView;
    private boolean aBoolean = true;
    private Button bianji,baocun;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        getData();
    }

    @Override
    protected int findView() {
        return R.layout.activity_xin_dong_fang;
    }
   void getData(){
         recyclerView = findViewById(R.id.recy);
         bianji = findViewById(R.id.bianji);
         baocun = findViewById(R.id.baocun);
         list.add("hahahha");
         list.add("hahahha");
         list.add("hahahha");
         list.add("hahahha");
         list.add("hahahha");
         list.add("hahahha");
         list.add("hahahha");
         list.add("hahahha");
         list.add("hahahha");
         list.add("hahahha");
         list.add("hahahha");
         list.add("hahahha");
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         xinDongFangAdpter = new XinDongFangAdpter(this,list,aBoolean);
         recyclerView.setAdapter(xinDongFangAdpter);
 xinDongFangAdpter.setOnClickListeners(new XinDongFangAdpter.OnClickListeners() {
     @Override
     public void setOnClick() {
         Log.i("TAG","__________");
     }
 });
         bianji.setOnClickListener(new View.OnClickListener() {
            @Override
               public void onClick(View v) {
               aBoolean = false;
               recyclerView.setLayoutManager(new LinearLayoutManager(XinDongFangActivity.this));
               xinDongFangAdpter = new XinDongFangAdpter(XinDongFangActivity.this,list,aBoolean);
               recyclerView.setAdapter(xinDongFangAdpter);
           }
       });
          baocun.setOnClickListener(new View.OnClickListener() {
             @Override
               public void onClick(View v) {
               aBoolean = true;
               recyclerView.setLayoutManager(new LinearLayoutManager(XinDongFangActivity.this));
               xinDongFangAdpter = new XinDongFangAdpter(XinDongFangActivity.this,list,aBoolean);
               recyclerView.setAdapter(xinDongFangAdpter);
           }
       });


   };



}
