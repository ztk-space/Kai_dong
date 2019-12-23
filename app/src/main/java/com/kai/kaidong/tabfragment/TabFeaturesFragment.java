package com.kai.kaidong.tabfragment;

import android.app.ListActivity;
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
import com.kai.kaidong.activity.ArcSeekBarActivity;
import com.kai.kaidong.activity.AudActivity;
import com.kai.kaidong.activity.CardActivity;
import com.kai.kaidong.activity.DouYinActivity;
import com.kai.kaidong.activity.ImmersiveActivity;
import com.kai.kaidong.activity.MarqueeActivity;
import com.kai.kaidong.activity.MvpActivity;
import com.kai.kaidong.activity.RecyclerviewActivity;
import com.kai.kaidong.activity.RingletterRegistrationloginActivity;
import com.kai.kaidong.activity.ScllRecyActivity;
import com.kai.kaidong.activity.ScratchCardActivity;
import com.kai.kaidong.activity.StacklabelviewActivity;
import com.kai.kaidong.activity.TwoPageLayoutActivity;
import com.kai.kaidong.activity.WaterfallActivity;
import com.kai.kaidong.activity.XianActivity;
import com.kai.kaidong.activity.XinDongFangActivity;
import com.kai.kaidong.base.BaseFragment;
import com.kai.kaidong.recy.ListViewActivity;
import com.superluo.textbannerlibrary.ITextBannerItemClickListener;
import com.superluo.textbannerlibrary.TextBannerView;

import java.util.ArrayList;
import java.util.List;

//https://github.com/Victory-Over/SuperWeb

//可以移动的高斯模糊 http://gank.io/2018/09/11#
public class TabFeaturesFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private List<String> list = new ArrayList<>();

    @Override
    protected void initData() {
        list.clear();
        list.add("跑马灯");
        list.add("cardview");
        list.add("Stacklabelview");
        list.add("刮刮卡");
        list.add("环信");
        list.add("股票");
        list.add("音乐动态图");
        list.add("抖音");
        list.add("RecyclerView 点击&长按事件、分割线、拖曳排序、滑动删除");
        list.add("重写LinearLayout，仿淘宝商品详情页，上拉查看更多详情。 ");
        list.add("Android弧形拖动条(ArcSeekBar)");
        list.add("Recyclerview瀑布流 ");
        list.add("沉浸式");
        list.add("新东方");
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
                    case 2:
                        startActivity(new Intent(getActivity(), StacklabelviewActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), ScratchCardActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), RingletterRegistrationloginActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(getActivity(), MvpActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(getActivity(), AudActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(getActivity(), DouYinActivity.class));
                        break;
                    case 8:
                        //http://gank.io/2018/09/19#
                        startActivity(new Intent(getActivity(), ScllRecyActivity.class));
                        break;
                    case 9:
                        //https://github.com/LineChen/TwoPageLayout
                        startActivity(new Intent(getActivity(), TwoPageLayoutActivity.class));
                        break;
                    case 10:
                        //https://github.com/GcsSloop/arc-seekbar
                        startActivity(new Intent(getActivity(), ArcSeekBarActivity.class));
                        break;
                    case 11:
                        startActivity(new Intent(getActivity(), WaterfallActivity.class));
                        break;
                    case 12:
                        startActivity(new Intent(getActivity(), ImmersiveActivity.class));
                        break;
                    case 13:
                        startActivity(new Intent(getActivity(), XinDongFangActivity.class));
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
