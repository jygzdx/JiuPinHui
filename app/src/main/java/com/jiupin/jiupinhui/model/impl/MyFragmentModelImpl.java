package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.MyFormEntity;
import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.entity.UserEntity;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IMyFragmentModel;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/31.
 */

public class MyFragmentModelImpl implements IMyFragmentModel {

    @Override
    public void getTokenStatus(String token, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.CHECK_TOKEN)
                .addParams("token",token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("onResponse.response = "+response);
                        Gson gson = new Gson();
                        ResponseBase responseBase = gson.fromJson(response, ResponseBase.class);
                        callBack.onSuccess(responseBase);
                    }
                });
    }

    @Override
    public void getUserInfoByToken(String token, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.GET_USER_INFO)
                .addParams("token",token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("onResponse.response = "+response);
                        Gson gson = new Gson();
                        UserEntity userEntity = gson.fromJson(response, UserEntity.class);
                        callBack.onSuccess(userEntity);
                    }
                });
    }

    @Override
    public void getformInfoByToken(String token, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.GET_FORM_INFO)
                .addParams("token",token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("onResponse.response = "+response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                Gson gson = new Gson();
                                MyFormEntity myFormEntity = gson.fromJson(response, MyFormEntity.class);
                                callBack.onSuccess(myFormEntity);
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
