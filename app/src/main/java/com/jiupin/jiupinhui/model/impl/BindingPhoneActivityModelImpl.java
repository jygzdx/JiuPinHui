package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.model.IBindingPhoneActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 作者：czb on 2017/6/26 14:30
 */

public class BindingPhoneActivityModelImpl implements IBindingPhoneActivityModel {


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
                        callBack.onFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        ResponseBase responseBase = gson.fromJson(response, ResponseBase.class);
                        callBack.onSuccess(responseBase);
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
                        callBack.onFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d(response);
                        Gson gson = new Gson();
                        ResponseBase responseBase = gson.fromJson(response, ResponseBase.class);
                        callBack.onSuccess(responseBase);
                    }
                });
    }
}
