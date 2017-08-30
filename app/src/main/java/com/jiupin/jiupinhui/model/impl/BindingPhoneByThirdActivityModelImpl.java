package com.jiupin.jiupinhui.model.impl;

import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.model.IBindingPhoneByThirdActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * 作者：czb on 2017/6/26 14:30
 */

public class BindingPhoneByThirdActivityModelImpl implements IBindingPhoneByThirdActivityModel {


    @Override
    public void getSecurityCode(final String mobile, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.BINDING_PHONE_SECURITY_CODE_URL)
                .addParams("mobile", mobile)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(HttpErrorUtils.NETWORK_ERROR, HttpErrorUtils.MSG_NETWORK_ERROR);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            int status = jsonObject.getInt("status");
                            if (200 == status) {
                                callBack.onSuccess("发送成功");
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
    public void updateMobile(String token, String moblie, String sms, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.UPDATE_MOBILE_URL)
                .addParams("token", token)
                .addParams("moblie", moblie)
                .addParams("sms", sms)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(HttpErrorUtils.NETWORK_ERROR, HttpErrorUtils.MSG_NETWORK_ERROR);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            int status = jsonObject.getInt("status");
                            if (200 == status) {
                                callBack.onSuccess("绑定手机号码成功");
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
