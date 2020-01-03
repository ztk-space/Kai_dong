package com.kai.kaidong.util;

import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.PopupWindow;

public class PopUtil {
   // public static final int CENTER = 0;
    private static PopUtil instance;

    private PopupWindow mPopupWindow;

    // 私有化构造方法，变成单例模式
    private PopUtil() {

    }

    // 对外提供一个该类的实例，考虑多线程问题，进行同步操作
    public static PopUtil getInstance() {
        if (instance == null) {
            synchronized (PopUtil.class) {
                if (instance == null) {
                    instance = new PopUtil();
                }
            }
        }
        return instance;
    }


    /**
     *
     * @param activity
     *            设置activity的作用是为了防止popuwindow在进入界面在未执行Oncreate()方法显示所报的异常所以执行以下方法
     *            View decorView = activity.getWindow().getDecorView();
     *            decorView.post(new Runnable() {
     *             @Override
     *             public void run() {
     *                 if (null != mPopupWindow) {
     *                     mPopupWindow.showAtLocation(contentView, Gravity.CENTER, 0, 0);
     *                 }
     *             }
     *         });
     *
     * @param view
     *            传入需要显示在什么控件下
     * @param view1
     *            传入内容的view
     * @param CENTER
     *      *      显示的位置
     * @return
     */

    public View makePopupWindow(Activity activity,int view, int view1,int CENTER) {
        //设置contentView
        View contentView = LayoutInflater.from(activity).inflate(view1, null);
        mPopupWindow = new PopupWindow(contentView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        contentView.setFocusable(true); // 这个很重要
        contentView.setFocusableInTouchMode(true);
        mPopupWindow.setFocusable(false);// 这个很重要
        mPopupWindow.setOutsideTouchable(false);
        contentView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    mPopupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });

        View rootview = LayoutInflater.from(activity).inflate(view, null);
        View decorView = activity.getWindow().getDecorView();
        decorView.post(new Runnable() {
            @Override
            public void run() {
                if (null != mPopupWindow) {
                    mPopupWindow.showAtLocation(contentView, CENTER, 0, 0);
                }
            }
        });
        return contentView;
    }


    //mPopupWindow消失
   public void mPopupWindowdismiss(){
       mPopupWindow.dismiss();
    }



    //变亮
    //dimBackground(0.5f,1.0f);

        //变暗
    //dimBackground(1.0f,0.5f);

    //屏幕变暗
    public void dimBackground(Activity activity,final float from, final float to) {
        final Window window = activity.getWindow();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                WindowManager.LayoutParams params = window.getAttributes();
                params.alpha = (Float) animation.getAnimatedValue();
                window.setAttributes(params);
            }
        });

        valueAnimator.start();
    }
}
