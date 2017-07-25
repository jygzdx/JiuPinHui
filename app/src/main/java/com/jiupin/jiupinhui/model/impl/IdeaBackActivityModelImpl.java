package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.model.IIdeaBackActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * 作者：czb on 2017/6/26 14:30
 */

public class IdeaBackActivityModelImpl implements IIdeaBackActivityModel {

    @Override
    public void submitIdea(String token, String content, String way, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.IDEA_BACK_URL)
                .addParams("token", token)
                .addParams("content", content)
                .addParams("way", way)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("submitIdea" + e.getMessage());
                        callBack.onFailed("submitIdea-->onFailed");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d(response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                Gson gson = new Gson();
                                ResponseBase responseBase = gson.fromJson(response,ResponseBase.class);
                                callBack.onSuccess(responseBase);
                            } else {
                                callBack.onFailed("submitIdea-->onFailed");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}