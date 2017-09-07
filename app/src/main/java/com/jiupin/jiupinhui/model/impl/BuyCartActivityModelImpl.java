package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.AllCartEntity;
import com.jiupin.jiupinhui.entity.CartEntity;
import com.jiupin.jiupinhui.model.IBuyCartActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.StringUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

import static com.zhy.http.okhttp.OkHttpUtils.post;

/**
 * 作者：czb on 2017/6/26 14:30
 */

public class BuyCartActivityModelImpl implements IBuyCartActivityModel {


    @Override
    public void getBuyCartList(String token, final IModel.CallBack callBack) {
        post()
                .url(Constant.GET_CART_LIST)
                .addParams("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(HttpErrorUtils.NETWORK_ERROR,HttpErrorUtils.MSG_NETWORK_ERROR);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("getAddressList" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            int status = jsonObject.getInt("status");
                            if (200 == status) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                if(data.equals("成功")){
                                    callBack.onSuccess(new ArrayList<CartEntity>());
                                    return;
                                }
                                AllCartEntity allCartEntity = gson.fromJson(data, AllCartEntity.class);
                                List<CartEntity> cartList = new ArrayList<CartEntity>();
                                for (int i = 0; i < allCartEntity.getOnSale().size(); i++) {
                                    AllCartEntity.OnSaleBean sale = allCartEntity.getOnSale().get(i);
                                    for (int j = 0; j < sale.getGoodList().size(); j++) {
                                        CartEntity cart = new CartEntity();
                                        cart.setStoreId(sale.getStoreId());
                                        cart.setStoreLogo(sale.getStoreLogo());
                                        cart.setStoreName(sale.getStoreName());
                                        cart.setId(sale.getGoodList().get(j).getId());
                                        cart.setIs_meal(sale.getGoodList().get(j).getIs_meal());
                                        cart.setGoods_name(sale.getGoodList().get(j).getGoods_name());
                                        cart.setGoods_price(sale.getGoodList().get(j).getGoods_price());
                                        cart.setStore_price(sale.getGoodList().get(j).getStore_price());
                                        cart.setPath(sale.getGoodList().get(j).getPath());
                                        cart.setCount(sale.getGoodList().get(j).getCount());
                                        cart.setSpecIdsString(sale.getGoodList().get(j).getSpecIdsString());
                                        cart.setSpecArray(sale.getGoodList().get(j).getSpecArray());
                                        cartList.add(cart);
                                    }
                                }
                                callBack.onSuccess(cartList);
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
    public void deleteGoods(String token, String id, String spec_id, final IModel.CallBack callBack) {
        post()
                .url(Constant.DELETE_ITEM_FROM_CART)
                .addParams("token", token)
                .addParams("id",id)
                .addParams("spec_id",spec_id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(HttpErrorUtils.NETWORK_ERROR,HttpErrorUtils.MSG_NETWORK_ERROR);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("deleteGoods" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            int status = jsonObject.getInt("status");
                            if (200 == status) {
                                callBack.onSuccess("");
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
    public void emptyCart(String token, final IModel.CallBack callBack) {
        post()
                .url(Constant.EMPTY_CART)
                .addParams("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(HttpErrorUtils.NETWORK_ERROR,HttpErrorUtils.MSG_NETWORK_ERROR);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("deleteGoods" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            int status = jsonObject.getInt("status");
                            if (200 == status) {
                                String data = jsonObject.getString("data");
                                callBack.onSuccess(data);
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
    public void notifyGoodsCount(String token, String id, String spec_id, int count, final IModel.CallBack callBack) {
        Map<String,String> params = new HashMap<>();
        if(!StringUtils.isEmpty(token)){
            params.put("token", token);
        }
        if(!StringUtils.isEmpty(id)){
            params.put("id",id);
        }
        if(!StringUtils.isEmpty(spec_id)){
            params.put("spec_id",spec_id);
        }
        if(!StringUtils.isEmpty(count+"")){
            params.put("count",count+"");
        }
        OkHttpUtils
                .post()
                .url(Constant.NOTIFY_GOODS_COUNT)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(HttpErrorUtils.NETWORK_ERROR,HttpErrorUtils.MSG_NETWORK_ERROR);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("notifyGoodsCount" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            int status = jsonObject.getInt("status");
                            if (200 == status) {
                                String data = jsonObject.getString("data");
                                callBack.onSuccess(data);
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
    public void submitGoodsInfo(String token, String goodStr, final IModel.CallBack callBack) {
        post()
                .url(Constant.SUBMIT_GOODS_INFO)
                .addParams("token", token)
                .addParams("goodStr",goodStr)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(HttpErrorUtils.NETWORK_ERROR,HttpErrorUtils.MSG_NETWORK_ERROR);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("submitGoodsInfo" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            int status = jsonObject.getInt("status");
                            if (200 == status) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                List<AllCartEntity.OnSaleBean> goodsList = gson.fromJson(data, new TypeToken<List<AllCartEntity.OnSaleBean>>(){}.getType());
                                callBack.onSuccess(goodsList);
                            } else{
                                callBack.onFailed(status, msg);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
