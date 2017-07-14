package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.AddressEntity;
import com.jiupin.jiupinhui.entity.OrderSubmitEntity;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IOrderActivityModel;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * 作者：czb on 2017/6/26 14:30
 */

public class OrderActivityModelImpl implements IOrderActivityModel {
    @Override
    public void getDefaultAddress(String token, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.GET_DEFAULT_ADDRESS)
                .addParams("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("getDefaultAddress" + e.getMessage());
                        callBack.onFailed("getDefaultAddress-->onFailed");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("getDefaultAddress=="+response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                AddressEntity addressEntity = gson.fromJson(data,AddressEntity.class);
                                callBack.onSuccess(addressEntity);
                            } else {
                                callBack.onFailed("getDefaultAddress-->onFailed"+jsonObject.getString("msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void submitForm(String userId, String storeId, String token, String msg,
                           String couponInfoId, String order_type, String addressId, String goodList, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.SUBMIT_FORM)
                .addParams("userId", userId)
                .addParams("storeId", storeId)
                .addParams("token", token)
                .addParams("msg", msg)
                .addParams("couponInfoId", couponInfoId)
                .addParams("order_type", order_type)
                .addParams("addressId", addressId)
                .addParams("goodList",goodList)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("submitForm" + e.getMessage());
                        callBack.onFailed("submitForm-->onFailed");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("submitForm=="+response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                OrderSubmitEntity orderSubmitEntity = gson.fromJson(data,OrderSubmitEntity.class);
                                callBack.onSuccess(orderSubmitEntity);
                            } else {
                                callBack.onFailed("submitForm-->onFailed"+jsonObject.getString("msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
