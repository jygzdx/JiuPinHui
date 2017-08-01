package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.AppraiseEntity;
import com.jiupin.jiupinhui.entity.GoodsEntity;
import com.jiupin.jiupinhui.model.IGoodsActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;

/**
 * 作者：czb on 2017/6/26 14:30
 */

public class GoodsActivityModelImpl implements IGoodsActivityModel {

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

    @Override
    public void getAppraise(int goodsId, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.APPRAISE_INFO)
                .addParams("goodsId", goodsId+"")
                .addParams("page", 1+"")
                .addParams("rows", 1+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("getAppraise" + e.getMessage());
                        callBack.onFailed("getAppraise-->onFailed");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d(response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                JSONObject dataObj = new JSONObject(data);
                                String list = dataObj.getString("list");
                                List<AppraiseEntity> appraiseList = gson.fromJson(list,new TypeToken<List<AppraiseEntity>>(){}.getType());
                                callBack.onSuccess(appraiseList);
                            } else {
                                callBack.onFailed("getAppraise-->onFailed");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
