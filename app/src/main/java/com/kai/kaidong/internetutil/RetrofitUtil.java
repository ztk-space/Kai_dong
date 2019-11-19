package com.kai.kaidong.internetutil;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    public static RetrofitUtil reUtil;
    public static RetrofitUtil getin(){
        if(reUtil==null){
            synchronized (RetrofitUtil.class){
                if(reUtil==null){
                    reUtil = new RetrofitUtil();
                }
            }
        }
        return reUtil;
    }
    public OkHttpClient getClient(){
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .connectTimeout(5000, TimeUnit.SECONDS)
                .writeTimeout(5000, TimeUnit.SECONDS)
                .readTimeout(5000, TimeUnit.SECONDS)
                .build();
        return client;
    }

    public Retrofit getUrl(String URL){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).client(getClient()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        return  retrofit;
    }
}
