package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.RegisterEntity;
import com.jiupin.jiupinhui.model.ILoginActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * 作者：czb on 2017/6/26 14:30
 */

public class LoginActivityModelImpl implements ILoginActivityModel {

    @Override
    public void loginUser(String mobile, String pwd, String way, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.LOGIN_USER_URL)
                .addParams("mobile", mobile)
                .addParams("pwd", pwd)
                .addParams("way", way)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(HttpErrorUtils.NETWORK_ERROR,HttpErrorUtils.MSG_NETWORK_ERROR);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("response = " + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            int status = jsonObject.getInt("status");
                            if ("OK".equals(msg)) {
                                JSONObject dataJson = jsonObject.getJSONObject("data");
                                String token = dataJson.getString("token");
                                String user = dataJson.getJSONObject("user").toString();

                                LogUtils.d("token = " + token + "  User = " + user);
                                Gson gson = new Gson();
                                RegisterEntity registerEntity = gson.fromJson(response, RegisterEntity.class);
                                callBack.onSuccess(registerEntity);
                            } else {
                                callBack.onFailed(status,msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
