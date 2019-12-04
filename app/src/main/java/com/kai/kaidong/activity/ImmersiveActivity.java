package com.kai.kaidong.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gyf.immersionbar.ImmersionBar;
import com.kai.kaidong.R;
import com.kai.kaidong.base.BaseActivity;
import com.kai.kaidong.bean.FunBean;
import com.kai.kaidong.util.GlideUtils;
import com.kai.kaidong.util.ImageLoaders;
import com.kai.kaidong.util.Utils;
import com.kai.kaidong.util.ViewUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class ImmersiveActivity extends BaseActivity {

     private RecyclerView recyclerView;
     private List<FunBean> list = new ArrayList<>();
     private ImageView imageView;
     private Banner banner;
     private ImmersiveAdpter immersiveAdpter;
    private int mBannerPosition =-1;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
      recyclerView = findViewById(R.id.immersivr_recy);
      for (int i = 0;i<20;i++){
          list.add(new FunBean("我想找"+i+"个女朋友"));
      }
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        immersiveAdpter = new ImmersiveAdpter();
        recyclerView.setAdapter(immersiveAdpter);
        immersiveAdpter.setNewData(list);
       addHeadView();
    }

    private void addHeadView() {
        View view = LayoutInflater.from(this).inflate(R.layout.banner_layout, recyclerView, false);
        imageView = view.findViewById(R.id.iv_banner);
        banner = view.findViewById(R.id.rv_content);
        ViewUtils.increaseViewHeightByStatusBarHeight(this, banner);
        //ImmersionBar.setTitleBarMarginTop(this, banner);
        ArrayList<String> pics = Utils.getPics();
        banner.setImages(pics);
        banner.setImageLoader(new ImageLoaders());
        banner.setDelayTime(3000);
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mBannerPosition = linearLayoutManager.findFirstVisibleItemPosition();
        String s = pics.get(position);
        GlideUtils.loadBlurry(imageView, s);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
});
        immersiveAdpter.addHeaderView(view);
    }

    @Override
    protected int findView() {
        return R.layout.activity_immersive;
    }


    public class ImmersiveAdpter extends BaseQuickAdapter<FunBean, BaseViewHolder> {
        public ImmersiveAdpter() {
            super(R.layout.waterfalllayout);
        }
        @Override
        protected void convert(BaseViewHolder helper, FunBean item) {
            helper.setText(R.id.Waterfalltext, item.getName());
//            ImageView ivIcon = helper.getView(R.id.ivIcon);
//            ImageView ivFlower = helper.getView(R.id.ivFlower);
//            ivFlower.setImageResource(item.getFlower());
//            Glide.with(mContext).load(item.getPic()).into(ivIcon);
        }
    }

}
