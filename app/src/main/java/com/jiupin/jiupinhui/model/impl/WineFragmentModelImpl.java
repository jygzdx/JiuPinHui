package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.Wine;
import com.jiupin.jiupinhui.entity.WineBrand;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IWineFragmentModel;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/31.
 */

public class WineFragmentModelImpl implements IWineFragmentModel {
    @Override
    public void getData(final IModel.CallBack callBack) {
        OkHttpUtils
                .get()
                .url(Constant.WINE_URL)
                .addParams("categoryId","1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        Wine wine = gson.fromJson(response, Wine.class);
                        callBack.onSuccess(wine);
                    }
                });
    }

    @Override
    public void getBrandData(final IModel.CallBack callBack) {
        OkHttpUtils
                .get()
                .url(Constant.WINE_Brand_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        WineBrand brand = gson.fromJson(response, WineBrand.class);
                        callBack.onSuccess(brand);
                    }
                });
    }
}
