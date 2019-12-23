package com.kai.kaidong.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kai.kaidong.R;
import com.kai.kaidong.base.BaseActivity;

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

    public class XinDongFangAdpter extends RecyclerView.Adapter<XinDongFangAdpter.XinDongFangHolder>{

        private Context context;
        private List<String> list = new ArrayList<>();
        private boolean aBoolean = true;

        public XinDongFangAdpter(Context context, List<String> list, boolean aBoolean) {
            this.context = context;
            this.list = list;
            this.aBoolean = aBoolean;
        }

        @NonNull
        @Override
        public XinDongFangHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new XinDongFangHolder(LinearLayout.inflate(context,R.layout.xindongfanglayout,null));
        }

        @Override
        public void onBindViewHolder(@NonNull XinDongFangHolder holder, int position) {
            Log.i("TAG",aBoolean+"========");
            holder.textView.setText(list.get(position));
            if(aBoolean == false){
                holder.imageView.setVisibility(View.VISIBLE);
            }else {
                holder.imageView.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class XinDongFangHolder extends RecyclerView.ViewHolder{

            private TextView textView;
            private ImageView imageView;

            public XinDongFangHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.xindongfangtext);
                imageView = itemView.findViewById(R.id.image);
            }
        }

    }

}
