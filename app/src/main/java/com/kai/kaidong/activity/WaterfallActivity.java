package com.kai.kaidong.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kai.kaidong.R;
import com.kai.kaidong.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class WaterfallActivity extends BaseActivity {

    private List<String> list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void init() {
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        RecyclerView recyclerView = findViewById(R.id.waterfallrecy);
        recyclerView.setLayoutManager( new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new  WaterfallRecyAdpter(this,list));
    }

    @Override
    protected int findView() {
        return R.layout.activity_waterfall;
    }

    public class WaterfallRecyAdpter extends RecyclerView.Adapter<WaterfallRecyAdpter.WaterfallHolder>{

        private Context context;
        private List<String> list;
        private List<Integer> hiehts = new ArrayList<>();

        public WaterfallRecyAdpter(Context context, List<String> list) {
            this.context = context;
            this.list = list;
            for(int i = 0;i<list.size();i++){
                hiehts.add((int) (Math.random()*400+300));
            }
        }

        @NonNull
        @Override
        public WaterfallHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            WaterfallHolder holder = new WaterfallHolder(LinearLayout.inflate(context, R.layout.waterfalllayout, null));
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull WaterfallHolder holder, int position) {
            ViewGroup.LayoutParams params = holder.textView.getLayoutParams();
            params.height  = hiehts.get(position);
            holder.textView.setBackgroundColor(Color.rgb((int) (Math.random()*100+150),(int) (Math.random()*100+150),(int) (Math.random()*100+150)));
            holder.textView.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list==null?0:list.size();
        }

        public class WaterfallHolder extends RecyclerView.ViewHolder{

            private TextView textView;

            public WaterfallHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.Waterfalltext);
            }
        }

    }
}
