package com.kai.kaidong.tabfragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kai.kaidong.R;
import com.kai.kaidong.activity.CardActivity;
import com.kai.kaidong.activity.MarqueeActivity;
import com.kai.kaidong.base.BaseFragment;
import com.superluo.textbannerlibrary.ITextBannerItemClickListener;
import com.superluo.textbannerlibrary.TextBannerView;

import java.util.ArrayList;
import java.util.List;

//https://github.com/Victory-Over/SuperWeb


public class TabFeaturesFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private List<String> list = new ArrayList<>();

    @Override
    protected void initData() {
        list.clear();
        list.add("跑马灯");
        list.add("cardview");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        FeayuresAdpter feayuresAdpter = new FeayuresAdpter(list,getActivity());
        recyclerView.setAdapter(feayuresAdpter);
        feayuresAdpter.setOnClickOnListener(new OnClickOnListener() {
            @Override
            public void onClick(int position) {
                switch (position){
                    case 0:
                        startActivity(new Intent(getActivity(), MarqueeActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), CardActivity.class));
                        break;
                }
            }
        });

    }

    @Override
    protected void intView(View view) {
        recyclerView = view.findViewById(R.id.featuress_recy);
    }

    @Override
    protected int initLayout() {
        return R.layout.featuresfragment;
    }

    public class FeayuresAdpter extends RecyclerView.Adapter<FeayuresAdpter.FeaturesHolder>{

        private List<String> list;
        private Context context;
        OnClickOnListener onClickOnListener;

        public void setOnClickOnListener(OnClickOnListener onClickOnListener) {
            this.onClickOnListener = onClickOnListener;
        }
        public FeayuresAdpter(List<String> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @NonNull
        @Override
        public FeaturesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new FeaturesHolder(LinearLayout.inflate(context,R.layout.featureslayout,null));
        }

        @Override
        public void onBindViewHolder(@NonNull FeaturesHolder holder, final int position) {
            holder.button.setText(list.get(position));
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickOnListener.onClick(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class FeaturesHolder extends RecyclerView.ViewHolder{

            private Button button;

            public FeaturesHolder(@NonNull View itemView) {
                super(itemView);
                button = itemView.findViewById(R.id.btn_fratures);
            }
        }

    }

    public interface OnClickOnListener{
        void onClick(int position);
    }

}
