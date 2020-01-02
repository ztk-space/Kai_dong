package com.kai.kaidong.splsah;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

import com.kai.kaidong.MainActivity;
import com.kai.kaidong.R;
import com.kai.kaidong.activity.LibActivity;
import com.kai.kaidong.activity.TablayoutActivity;
import com.kai.kaidong.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    private MyCountDownTimer mc;
    private Handler handler=new Handler();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void init() {
        mc = new MyCountDownTimer(1000, 1000);
        mc.start();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this, TablayoutActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    @Override
    protected int findView() {
        return R.layout.activity_splash;
    }
    class MyCountDownTimer extends CountDownTimer {
        /**
         *
         * @param millisInFuture
         *      表示以毫秒为单位 倒计时的总数
         *
         *      例如 millisInFuture=1000 表示1秒
         *
         * @param countDownInterval
         *      表示 间隔 多少微秒 调用一次 onTick 方法
         *
         *      例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
         *
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        public void onFinish() {

        }

        public void onTick(long millisUntilFinished) {

        }

    }
}
