package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.AddressEntity;
import com.jiupin.jiupinhui.model.IManageAddressActivityModel;
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

public class ManageAddressActivityModelImpl implements IManageAddressActivityModel {

    @Override
    public void getAddressList(String token, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.ADDRESS_LIST)
                .addParams("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("getAddressList" + e.getMessage());
                        callBack.onFailed("getAddressList-->onFailed");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("getAddressList" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                JSONObject dataObj = new JSONObject(data);
                                String list = dataObj.getString("list");
                                List<AddressEntity> adds = gson.fromJson(list,new TypeToken<List<AddressEntity>>(){}.getType());
                                callBack.onSuccess(adds);
                            } else {
                                callBack.onFailed("getAddressList-->onFailed");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void changeDefaultAddress(String token, String id, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.CHANGE_DEFAULT_ADDRESS)
                .addParams("token", token)
                .addParams("id",id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("changeDefaultAddress" + e.getMessage());
                        callBack.onFailed("changeDefaultAddress-->onFailed");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("changeDefaultAddress" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                String data = jsonObject.getString("data");
                                callBack.onSuccess(data);
                            } else {
                                callBack.onFailed("changeDefaultAddress-->onFailed");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
