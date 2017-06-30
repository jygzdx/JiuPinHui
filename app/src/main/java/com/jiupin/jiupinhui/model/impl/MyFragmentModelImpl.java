package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IMyFragmentModel;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/31.
 */

public class MyFragmentModelImpl implements IMyFragmentModel {

    @Override
    public void getTokenStatus(String token, final IModel.CallBack callBack) {
        OkHttpUtils
                .get()
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
                        Gson gson = new Gson();
                        ResponseBase responseBase = gson.fromJson(response, ResponseBase.class);
                        callBack.onSuccess(responseBase);
                    }
                });
    }
}
