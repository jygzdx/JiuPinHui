package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.ChatEntity;
import com.jiupin.jiupinhui.model.IChatActivityModel;
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

public class ChatActivityModelImpl implements IChatActivityModel {

    @Override
    public void getChatInfo(String token, String orderNum, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.AFTER_CONSULT_HISTORY)
                .addParams("token", token)
                .addParams("orderNum", orderNum)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("getChatInfo" + e.getMessage());
                        callBack.onFailed("getChatInfo-->onFailed");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("getChatInfo" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                JSONObject dataObj = new JSONObject(data);
                                String list = dataObj.getString("list");
                                String hint = dataObj.getString("hint");
                                List<ChatEntity> chatList = gson.fromJson(list, new TypeToken<List<ChatEntity>>() {
                                }.getType());
                                callBack.onSuccess(chatList);
                            } else {
                                callBack.onFailed("getChatInfo-->onFailed");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
