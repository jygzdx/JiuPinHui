package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.WineBrandEntity;
import com.jiupin.jiupinhui.entity.WineInfoEntity;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IWineFragmentModel;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/31.
 */

public class WineFragmentModelImpl implements IWineFragmentModel {
    @Override
    public void getWineList(String page, String rows, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.WINE_INFO_URL)
                .addParams("page",page)
                .addParams("rows",rows)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(HttpErrorUtils.NETWORK_ERROR,HttpErrorUtils.MSG_NETWORK_ERROR);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("getWineList" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            int status = jsonObject.getInt("status");
                            if (200 == status) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                JSONObject dataObj = new JSONObject(data);
                                String list = dataObj.getString("list");
                                List<WineInfoEntity> wineInfoList = gson.fromJson(list,new TypeToken<List<WineInfoEntity>>(){}.getType());
                                callBack.onSuccess(wineInfoList);
                            } else {
                                callBack.onFailed(status, msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void getWineListByBrandId(String brandId, String page, String rows, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.WINE_INFO_BY_ID_URL)
                .addParams("brandId",brandId)
                .addParams("page",page)
                .addParams("rows",rows)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(HttpErrorUtils.NETWORK_ERROR,HttpErrorUtils.MSG_NETWORK_ERROR);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("getWineListByBrandId" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            int status = jsonObject.getInt("status");
                            if (200 == status) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                JSONObject dataObj = new JSONObject(data);
                                String list = dataObj.getString("list");
                                List<WineInfoEntity> wineInfoList = gson.fromJson(list,new TypeToken<List<WineInfoEntity>>(){}.getType());
                                callBack.onSuccess(wineInfoList);
                            } else {
                                callBack.onFailed(status, msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void getBrandData(final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.WINE_BRAND_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(HttpErrorUtils.NETWORK_ERROR,HttpErrorUtils.MSG_NETWORK_ERROR);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("getBrandData" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            int status = jsonObject.getInt("status");
                            if (200 == status) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                JSONObject dataObj = new JSONObject(data);
                                String list = dataObj.getString("list");
                                List<WineBrandEntity> wineBrandList = gson.fromJson(list,new TypeToken<List<WineBrandEntity>>(){}.getType());
                                for (int i = 0; i < wineBrandList.size(); i++) {
                                    WineBrandEntity wine = gson.fromJson(wineBrandList.get(i).getCover_img(), WineBrandEntity.class);
                                    wineBrandList.get(i).setLarge_img(wine.getLarge_img());
                                    wineBrandList.get(i).setThumb_img(wine.getThumb_img());
                                }
                                callBack.onSuccess(wineBrandList);
                            } else {
                                callBack.onFailed(status, msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
