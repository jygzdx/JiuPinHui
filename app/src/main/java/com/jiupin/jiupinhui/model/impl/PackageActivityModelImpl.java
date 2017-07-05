package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.GoodsEntity;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IPackageActivityModel;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * 作者：czb on 2017/6/26 14:30
 */

public class PackageActivityModelImpl implements IPackageActivityModel {

    @Override
    public void getGoodsInfo(int id, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.GOODS_INFO)
                .addParams("id", id+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("getGoodsInfo" + e.getMessage());
                        callBack.onFailed("getGoodsInfo-->onFailed");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d(response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                Gson gson = new Gson();
                                GoodsEntity goodsEntity = gson.fromJson(response,GoodsEntity.class);
                                callBack.onSuccess(goodsEntity);
                            } else {
                                callBack.onFailed("getGoodsInfo-->onFailed");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
