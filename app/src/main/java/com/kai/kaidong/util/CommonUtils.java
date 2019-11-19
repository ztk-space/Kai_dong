package com.kai.kaidong.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;


/**
 * Created by jingbin on 2016/11/22.
 * 获取原生资源
 */
public class CommonUtils {
    private KProgressHUD mProgressDialog;//进度窗体
    private static volatile CommonUtils commonUtils;//构建单例模式

    public static CommonUtils getInstance(){
        if(commonUtils==null){
           synchronized (CommonUtils.class){
               if(commonUtils==null){
                   commonUtils=new CommonUtils();
               }
           }
        }
        return commonUtils;
    }


    /**
     * 随机颜色
     */
    public static int randomColor() {
        Random random = new Random();
        int red = random.nextInt(150) + 50;//50-199
        int green = random.nextInt(150) + 50;//50-199
        int blue = random.nextInt(150) + 50;//50-199
        return Color.rgb(red, green, blue);
    }

    /**
     * 得到年月日的"日"
     */
    private String getDate() {
        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("dd", Locale.CHINA);
        return dateFm.format(date);
    }

    /**
     * 获取屏幕px
     *
     * @param context
     * @return 分辨率
     */
    static public int getScreenWidthPixels(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getMetrics(dm);
        return dm.widthPixels;
    }

//	public static void RunOnUiThread(Runnable r) {
//		CloudReaderApplication.getInstance().getMainLooper().post(r);
//	}

    public static Drawable getDrawable(Context context, int resid) {
        return getResoure(context).getDrawable(resid);
    }

    public static int getColor(Context context, int resid) {
        return getResoure(context).getColor(resid);
    }

    public static Resources getResoure(Context context) {
        return context.getResources();
    }

    public static String[] getStringArray(Context context, int resid) {
        return getResoure(context).getStringArray(resid);
    }

    public static String getString(Context context, int resid) {
        return getResoure(context).getString(resid);
    }

    public static float getDimens(Context context, int resId) {
        return getResoure(context).getDimension(resId);
    }

    public static void removeSelfFromParent(View child) {

        if (child != null) {

            ViewParent parent = child.getParent();

            if (parent instanceof ViewGroup) {

                ViewGroup group = (ViewGroup) parent;

                group.removeView(child);
            }
        }
    }


    /**
     * 字符串是否为空
     *
     * @param input
     * @return
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input)||"null".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }
    /**
     * 判断字符串是否为空
     *
     * @param value
     * @return
     */
    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    protected static Toast toast = null;
    public static void showToast(Context context, String title) {

        if (toast == null) {
            toast = Toast.makeText(context, title, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            toast.setText(title);
            toast.show();
        }
    }
    /**
     * 显示进度框
     * @param str
     */
    public  void showInfoProgressDialog(Context context, final String... str) {
        if (mProgressDialog == null) {
            mProgressDialog = new KProgressHUD(context);
            mProgressDialog.setCancellable(true);
        }
        if (str.length == 0) {
            mProgressDialog.setLabel("加载中...");
        } else {
            mProgressDialog.setLabel(str[0]);
        }

        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    /**
     * 隐藏等待条
     */
    public  void hideInfoProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog=null;
        }
    }

    /**
     * 隐藏状态栏
     * @param context
     */
    public static void hideStatusLan(Activity context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }



    /**
     * 获取字符串
     * @param context
     * @param id
     * @return
     */
    public static String getXmlString(Context context, int id){
        return context.getResources().getString(id);
    }
    /**
     * 设置文本
     *
     * @param value
     * @return
     */
    public static void setTextValue(TextView textView, String value, String... defValue) {
        if (isNotEmpty(value))
            textView.setText(value);
        else {
            if (defValue!=null&&defValue.length==1) {
                textView.setText(defValue[0]);
            } else {
                textView.setText("");
            }

        }


    }

}
