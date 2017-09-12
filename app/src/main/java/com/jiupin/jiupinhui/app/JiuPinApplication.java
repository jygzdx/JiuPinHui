package com.jiupin.jiupinhui.app;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.request.target.ViewTarget;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.config.Constant;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
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

        final IWXAPI msgApi = WXAPIFactory.createWXAPI(this, null);
        // 将该app注册到微信
        msgApi.registerApp(Constant.APP_ID);

        //初始化工具类
        Utils.init(this);
    }
}
