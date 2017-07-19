package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.FormParticularEntity;
import com.jiupin.jiupinhui.model.IFormParticularActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * 作者：czb on 2017/6/26 14:30
 */

public class FormParticularActivityModelImpl implements IFormParticularActivityModel {

    @Override
    public void getFormInfo(String orderId, String token, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.GET_SINGLE_FORM_INFO)
                .addParams("orderId", orderId)
                .addParams("token",token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("getFormInfo" + e.getMessage());
                        callBack.onFailed("getFormInfo-->onFailed = "+e.toString());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                FormParticularEntity formParticularEntity = gson.fromJson(data,FormParticularEntity.class);
                                callBack.onSuccess(formParticularEntity);
                            } else {
                                callBack.onFailed("getFormInfo-->onFailed = "+jsonObject.getString("msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void cancelForm(String orderId, String token, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.CANCEL_FORM)
                .addParams("orderId", orderId)
                .addParams("token",token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("cancelForm" + e.getMessage());
                        callBack.onFailed("cancelForm-->onFailed = "+e.toString());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                String data = jsonObject.getString("data");
                                callBack.onSuccess(data);
                            } else {
                                callBack.onFailed("cancelForm-->onFailed = "+jsonObject.getString("msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void ensureGainGoods(String orderId, String token, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.ENSURE_GAIN_GOODS)
                .addParams("orderId", orderId)
                .addParams("token",token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("ensureGainGoods" + e.getMessage());
                        callBack.onFailed("ensureGainGoods-->onFailed = "+e.toString());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                String data = jsonObject.getString("data");
                                callBack.onSuccess(data);
                            } else {
                                callBack.onFailed("ensureGainGoods-->onFailed = "+jsonObject.getString("msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void deleteForm(String orderId, String token, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.DELETE_FORM)
                .addParams("orderId", orderId)
                .addParams("token",token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("deleteForm" + e.getMessage());
                        callBack.onFailed("deleteForm-->onFailed = "+e.toString());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                String data = jsonObject.getString("data");
                                callBack.onSuccess(data);
                            } else {
                                callBack.onFailed("deleteForm-->onFailed = "+jsonObject.getString("msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

}
