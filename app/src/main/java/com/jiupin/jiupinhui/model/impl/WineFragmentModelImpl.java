package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.WineBrandEntity;
import com.jiupin.jiupinhui.entity.WineBrandKindEntity;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IWineFragmentModel;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/31.
 */

public class WineFragmentModelImpl implements IWineFragmentModel {

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

    @Override
    public void getBrandKind(final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.GET_BRAND_CLASS_LIST)
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

    @Override
    public void getwineBrandKind(String cid, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.GET_BRAND_LIST_BY_CID)
                .addParams("cid",cid)
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
                                //返回的数据跟我想要的结构有点不一致，把他改了
                                List<WineBrandKindEntity> wineBrandKindEntityList = gson.fromJson(list,new TypeToken<List<WineBrandKindEntity>>(){}.getType());
                                List<WineBrandEntity> wineBrandList = new ArrayList<WineBrandEntity>();
                                for (int i = 0; i < wineBrandKindEntityList.size(); i++) {
                                    List<WineBrandEntity> brandlist = wineBrandKindEntityList.get(i).getList();
                                    for (int j = 0; j < brandlist.size(); j++) {
                                        WineBrandEntity wine = gson.fromJson(brandlist.get(j).getCover_img(), WineBrandEntity.class);
                                        brandlist.get(j).setLarge_img(wine.getLarge_img());
                                        brandlist.get(j).setThumb_img(wine.getThumb_img());
                                    }
                                    wineBrandList.addAll(brandlist);
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
