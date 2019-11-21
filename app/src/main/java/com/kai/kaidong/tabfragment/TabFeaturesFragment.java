package com.kai.kaidong.tabfragment;

import android.content.Context;
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
import com.kai.kaidong.base.BaseFragment;
import com.superluo.textbannerlibrary.ITextBannerItemClickListener;
import com.superluo.textbannerlibrary.TextBannerView;

import java.util.ArrayList;
import java.util.List;

//https://github.com/Victory-Over/SuperWeb


public class TabFeaturesFragment extends BaseFragment {

    private TextBannerView mTvBanner;
    private TextBannerView mTvBanner1;
    private TextBannerView mTvBanner2;
    private TextBannerView mTvBanner3;
    private TextBannerView mTvBanner4;
    private List<String> mList;
    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mList.add("学好Java、Android、C#、C、ios、html+css+js");
        mList.add("走遍天下都不怕！！！！！");
        mList.add("不是我吹，就怕你做不到，哈哈");
        mList.add("superluo");
        mList.add("你是最棒的，奔跑吧孩子！");
        /**
         * 设置数据，方式一
         */
        mTvBanner.setDatas(mList);
        mTvBanner.setDatas(mList);
        mTvBanner1.setDatas(mList);
        mTvBanner2.setDatas(mList);
        mTvBanner3.setDatas(mList);

        Drawable drawable = getResources().getDrawable(R.drawable.hottrue);
        /**
         * 设置数据（带图标的数据），方式二
         */
        //第一个参数：数据 。第二参数：drawable.  第三参数drawable尺寸。第四参数图标位置
        mTvBanner4.setDatasWithDrawableIcon(mList,drawable,18, Gravity.LEFT);
        setListener();
    }

    @Override
    protected void intView(View view) {
        mTvBanner = view.findViewById(R.id.tv_banner);
        mTvBanner1 = view.findViewById(R.id.tv_banner1);
        mTvBanner2 = view.findViewById(R.id.tv_banner2);
        mTvBanner3 = view.findViewById(R.id.tv_banner3);
        mTvBanner4 = view.findViewById(R.id.tv_banner4);
    }

    @Override
    protected int initLayout() {
        return R.layout.featuresfragment;
    }
    private void setListener() {
    mTvBanner.setItemOnClickListener(new ITextBannerItemClickListener() {
        @Override
        public void onItemClick(String data, int position) {
            Toast.makeText(mContext, String.valueOf(position)+">>"+data, Toast.LENGTH_SHORT).show();
        }
    });

    mTvBanner1.setItemOnClickListener(new ITextBannerItemClickListener() {
        @Override
        public void onItemClick(String data, int position) {
            Toast.makeText(mContext, String.valueOf(position)+">>"+data, Toast.LENGTH_SHORT).show();
        }
    });

    mTvBanner2.setItemOnClickListener(new ITextBannerItemClickListener() {
        @Override
        public void onItemClick(String data, int position) {
            Toast.makeText(mContext, String.valueOf(position)+">>"+data, Toast.LENGTH_SHORT).show();
        }
    });

    mTvBanner3.setItemOnClickListener(new ITextBannerItemClickListener() {
        @Override
        public void onItemClick(String data, int position) {
            Toast.makeText(mContext, String.valueOf(position)+">>"+data, Toast.LENGTH_SHORT).show();
        }
    });

    mTvBanner4.setItemOnClickListener(new ITextBannerItemClickListener() {
        @Override
        public void onItemClick(String data, int position) {
            Toast.makeText(mContext, String.valueOf(position)+">>"+data, Toast.LENGTH_SHORT).show();
        }
    });
}
    @Override
    public void onResume() {
        super.onResume();
        /**调用startViewAnimator()重新开始文字轮播*/
        mTvBanner.startViewAnimator();
        mTvBanner1.startViewAnimator();
        mTvBanner2.startViewAnimator();
        mTvBanner3.startViewAnimator();
        mTvBanner4.startViewAnimator();

    }

    @Override
    public void onStop() {
        super.onStop();
        /**调用stopViewAnimator()暂停文字轮播，避免文字重影*/
        mTvBanner.stopViewAnimator();
        mTvBanner1.stopViewAnimator();
        mTvBanner2.stopViewAnimator();
        mTvBanner3.stopViewAnimator();
        mTvBanner4.stopViewAnimator();
    }
}
