package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IResetActivityModel;
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

public class ResetActivityModelImpl implements IResetActivityModel {

    @Override
    public void getResetSecurityCode(String mobile, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.RESET_CODE_URL)
                .addParams("mobile", mobile)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(HttpErrorUtils.NETWORK_ERROR,HttpErrorUtils.MSG_NETWORK_ERROR);

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("responde = " + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            int status = jsonObject.getInt("status");
                            if ("OK".equals(msg)) {
                                callBack.onSuccess("发送验证码成功");
                            } else {
                                callBack.onFailed(status,msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void resetPwd(String mobile, String code, String pwd, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.RESET_PASSWORD_URL)
                .addParams("mobile", mobile)
                .addParams("pwd", pwd)
                .addParams("sms", code)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(HttpErrorUtils.NETWORK_ERROR,HttpErrorUtils.MSG_NETWORK_ERROR);
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            int status = jsonObject.getInt("status");
                            if ("OK".equals(msg)) {
                                Gson gson = new Gson();
                                ResponseBase responseBase = gson.fromJson(response, ResponseBase.class);
                                callBack.onSuccess(responseBase);
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
