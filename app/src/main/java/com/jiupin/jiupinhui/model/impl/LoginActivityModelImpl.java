package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.RegisterEntity;
import com.jiupin.jiupinhui.entity.SecurityCodeEntity;
import com.jiupin.jiupinhui.model.ILoginActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 作者：czb on 2017/6/26 14:30
 */

public class LoginActivityModelImpl implements ILoginActivityModel {



    @Override
    public void getSecurityCode(final String mobile, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.SECURITY_CODE_URL)
                .addParams("mobile",mobile)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        SecurityCodeEntity securityCodeEntity = gson.fromJson(response, SecurityCodeEntity.class);
                        callBack.onSuccess(securityCodeEntity);
                    }
                });
    }

    @Override
    public void registerUser(String mobile, String code, String pwd, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.REGISTER_USER_URL)
                .addParams("mobile",mobile)
                .addParams("pwd",pwd)
                .addParams("sms",code)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        RegisterEntity registerEntity = gson.fromJson(response, RegisterEntity.class);
                        callBack.onSuccess(registerEntity);
                    }
                });
    }
}
