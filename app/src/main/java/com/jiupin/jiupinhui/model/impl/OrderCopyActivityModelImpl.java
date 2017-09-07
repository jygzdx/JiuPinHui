package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.AddressEntity;
import com.jiupin.jiupinhui.entity.OrderCopyEntity;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IOrderCopyActivityModel;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.StringUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * 作者：czb on 2017/6/26 14:30
 */

public class OrderCopyActivityModelImpl implements IOrderCopyActivityModel {
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
                        callBack.onFailed(HttpErrorUtils.NETWORK_ERROR,HttpErrorUtils.MSG_NETWORK_ERROR);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("getDefaultAddress=="+response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            int status = jsonObject.getInt("status");
                            if (200 == status) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                AddressEntity addressEntity = gson.fromJson(data,AddressEntity.class);
                                callBack.onSuccess(addressEntity);
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
    public void submitForm(String token, String msg, String couponInfoId, String order_type,
                           String addressId, String goodList, String invoiceType, String invoice,
                           String invoiceCode, String invoiceDesc, final IModel.CallBack callBack) {

        Map<String, String> params = new HashMap<>();
        if(!StringUtils.isEmpty(token)){
            params.put("token", token);
        }
        if(!StringUtils.isEmpty(msg)){
            params.put("msg", msg);
        }
        if(!StringUtils.isEmpty(couponInfoId)){
            params.put("couponInfoId", couponInfoId);
        }
        if(!StringUtils.isEmpty(order_type)){
            params.put("order_type", order_type);
        }
        if(!StringUtils.isEmpty(addressId)){
            params.put("addressId", addressId);
        }
        if(!StringUtils.isEmpty(goodList)){
            params.put("goodList", goodList);
        }
        if(!StringUtils.isEmpty(invoiceType)){
            params.put("invoiceType", invoiceType);
        }
        if(!StringUtils.isEmpty(invoice)){
            params.put("invoice", invoice);
        }
        if(!StringUtils.isEmpty(invoiceCode)){
            params.put("invoiceCode", invoiceCode);
        }
        if(!StringUtils.isEmpty(invoiceDesc)){
            params.put("invoiceDesc", invoiceDesc);
        }


        OkHttpUtils
                .post()
                .url(Constant.SUBMIT_CART_ORDERS)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(HttpErrorUtils.NETWORK_ERROR,HttpErrorUtils.MSG_NETWORK_ERROR);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("submitCartsForm=="+response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            int status = jsonObject.getInt("status");
                            if (200 == status) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                OrderCopyEntity orderCopyEntity = gson.fromJson(data,OrderCopyEntity.class);
                                callBack.onSuccess(orderCopyEntity);
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
