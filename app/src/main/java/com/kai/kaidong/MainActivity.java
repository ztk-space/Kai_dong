package com.kai.kaidong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.kai.kaidong.fragment.HomeFragment;
import com.kai.kaidong.fragment.HotFragment;
import com.kai.kaidong.fragment.MineFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{
    BottomNavigationBar mBottomNavigationBar;
    private TextBadgeItem mTextBadgeItem;
    private HomeFragment mHomeFragment;  // 首页
    private HotFragment mHotShowFragment;
    private MineFragment mMineFragment;
    private int lastSelectedPosition;
    // Fragment管理器，和执行器
    private FragmentManager mManager;
    private FragmentTransaction mTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        initView();
        //initData();
        setDefaultFragment();
    }
    private void initView() {
        // TODO 设置模式
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        // TODO 设置背景色样式
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setBarBackgroundColor(R.color.colorPrimary);

        mTextBadgeItem = new TextBadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.colorAccent)
                .setText("5")
                .setTextColorResource(R.color.colorPrimaryDark)
                .setBorderColorResource(R.color.colorPrimaryDark)  //外边界颜色
                .setHideOnSelect(false);

        ShapeBadgeItem mShapeBadgeItem = new ShapeBadgeItem()
                .setShape(ShapeBadgeItem.SHAPE_OVAL)
                .setShapeColor(R.color.design_default_color_primary)
                .setShapeColorResource(R.color.design_default_color_primary_dark)
                .setSizeInDp(this, 10, 10)
                .setEdgeMarginInDp(this, 2)
//                .setSizeInPixels(30,30)
//                .setEdgeMarginInPixels(-1)
                .setGravity(Gravity.TOP | Gravity.END)
                .setHideOnSelect(false);

        /**
         *  new BottomNavigationItem(R.mipmap.tab_home_pressed,"首页") ,选中的图标，文字
         *  setActiveColorResource：选中的颜色
         *  setInactiveIconResource：未选中的图标
         *  setInActiveColorResource：未选中的颜色
         */
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "首页").setActiveColorResource(R.color.design_default_color_primary_dark).setInactiveIconResource(R.mipmap.ic_launcher_round).setInActiveColorResource(R.color.colorPrimaryDark))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "热映").setActiveColorResource(R.color.design_default_color_primary_dark).setInactiveIconResource(R.mipmap.ic_launcher_round).setInActiveColorResource(R.color.colorPrimaryDark))
                // .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "资讯").setActiveColorResource(R.color.design_default_color_primary_dark).setInactiveIconResource(R.mipmap.ic_launcher_round).setInActiveColorResource(R.color.colorPrimaryDark).setBadgeItem(mShapeBadgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "我的").setActiveColorResource(R.color.design_default_color_primary_dark).setInactiveIconResource(R.mipmap.ic_launcher_round).setInActiveColorResource(R.color.colorPrimaryDark).setBadgeItem(mTextBadgeItem))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise();
        // mShapeBadgeItem.hide();

        mBottomNavigationBar.setTabSelectedListener(this);
    }
    /**
     * 初始化数据
     */
//    private void initData(){
//        mSharedPreferencesUtil=SharedPreferencesUtil.getInstance(this);
//    }
    private void setDefaultFragment() {
        mHomeFragment=new HomeFragment();
        mManager = getSupportFragmentManager();
        mTransaction = mManager.beginTransaction();
        mTransaction.add(R.id.fra, mHomeFragment);
        mTransaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        lastSelectedPosition = position;
        //开启事务
        mTransaction = mManager.beginTransaction();
        hideFragment(mTransaction);

        /**
         * fragment 用 add + show + hide 方式
         * 只有第一次切换会创建fragment，再次切换不创建
         *
         * fragment 用 replace 方式
         * 每次切换都会重新创建
         *
         */
        switch (position){
            case 0:   // 首页
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance();
                    mTransaction.add(R.id.fra,
                            mHomeFragment);
                } else {
                    mTransaction.show(mHomeFragment);
                }
                break;
            case 1:    // 热映
                if (mHotShowFragment == null) {
                    mHotShowFragment =  HotFragment.newInstance();
                    mTransaction.add(R.id.fra,
                            mHotShowFragment);
                } else {
                    mTransaction.show(mHotShowFragment);
                }
                break;
//            case 2:  // 资讯
//                if (mTopicFragment == null) {
//                    mTopicFragment = TopicFragment.newInstance();
//                    mTransaction.add(R.id.ll_content,
//                            mTopicFragment);
//                } else {
//                    mTransaction.show(mTopicFragment);
//                }
//                break;
            case 2:  // 我的
//                isLogin=mSharedPreferencesUtil.getBoolean(SharedPreferencesUtil.LOGIN_STATUS, false);
//                if (isLogin==false){
//                    Intent intent=new Intent(MainActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                }else {
                if (mMineFragment == null) {
                    mMineFragment = MineFragment.newInstance();
                    mTransaction.add(R.id.fra,
                            mMineFragment);
                } else {
                    mTransaction.show(mMineFragment);
                }
//                }

                break;
        }
        // 事务提交
        mTransaction.commit();
        //  mTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
    /**
     * 隐藏当前fragment
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction){
        if (mHomeFragment != null){
            transaction.hide(mHomeFragment);
        }

        if (mHotShowFragment != null){
            transaction.hide(mHotShowFragment);
        }
        if (mMineFragment != null){
            transaction.hide(mMineFragment);
        }
    }
}
