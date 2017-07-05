package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IReviseNicknameActivityModel;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 作者：czb on 2017/6/26 14:30
 */

public class ReviseNicknameActivityModelImpl implements IReviseNicknameActivityModel {

    @Override
    public void updateNickname(String token, String nickName, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.UPDATE_NICKNAME_URL)
                .addParams("token", token)
                .addParams("nickName", nickName)
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
