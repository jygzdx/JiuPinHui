package com.jiupin.jiupinhui.app;

import android.app.Application;

import com.bumptech.glide.request.target.ViewTarget;
import com.jiupin.jiupinhui.R;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/3/16.
 */

public class JiuPinApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //配置Okhttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
        //配置Glide
        ViewTarget.setTagId(R.id.glide_tag);
    }
}
