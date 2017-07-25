package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.BannerEntity;
import com.jiupin.jiupinhui.entity.MainShowEntity;
import com.jiupin.jiupinhui.entity.MealTypeEntity;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IStoreFragmentModel;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;

/**
 * 作者：czb on 2017/6/28 11:17
 */

public class StoreFragmentModelImpl implements IStoreFragmentModel {

    @Override
    public void getBanner(final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.STORE_BANNER_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("getBanner" + e.getMessage());
                        callBack.onFailed("getBanner-->onFailed");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("getBanner" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                JSONObject dataObj = new JSONObject(data);
                                String list = dataObj.getString("list");
                                List<BannerEntity> bannerList = gson.fromJson(list,new TypeToken<List<BannerEntity>>(){}.getType());
                                callBack.onSuccess(bannerList);
                            } else {
                                callBack.onFailed("getBanner-->onFailed");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void getMealType(final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.MEAL_TYPE_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("getMealType" + e.getMessage());
                        callBack.onFailed("getMealType-->onFailed");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("getMealType" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                JSONObject dataObj = new JSONObject(data);
                                String list = dataObj.getString("list");
                                List<MealTypeEntity> mealTypeList = gson.fromJson(list,new TypeToken<List<MealTypeEntity>>(){}.getType());
                                callBack.onSuccess(mealTypeList);
                            } else {
                                callBack.onFailed("getBanner-->onFailed");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void getMealInfo(String cid, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.MEAL_INFO_URL)
                .addParams("cid",cid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("getMealInfo" + e.getMessage());
                        callBack.onFailed("getMealInfo-->onFailed");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("getMealInfo" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                JSONObject dataObj = new JSONObject(data);
                                String list = dataObj.getString("list");
                                List<MainShowEntity.DataBean.ListBean> mealInfoList = gson.fromJson(list,new TypeToken<List<MainShowEntity.DataBean.ListBean>>(){}.getType());
                                callBack.onSuccess(mealInfoList);
                            } else {
                                callBack.onFailed("getMealInfo-->onFailed");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
