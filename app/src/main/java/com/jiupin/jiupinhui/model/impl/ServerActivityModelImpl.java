package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.ChatHistotyEntity;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IServerActivityModel;
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

public class ServerActivityModelImpl implements IServerActivityModel {
    @Override
    public void getChatHistory(String token, String page, String rows, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.BEFORE_CONSULT_LIST)
                .addParams("token", token)
                .addParams("page", page)
                .addParams("rows", rows)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("getChatHistory" + e.getMessage());
                        callBack.onFailed("getChatHistory-->onFailed");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("getChatHistory" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                JSONObject dataObj = new JSONObject(data);
                                String list = dataObj.getString("list");
                                List<ChatHistotyEntity> histotyList = gson.fromJson(list,new TypeToken<List<ChatHistotyEntity>>(){}.getType());
                                callBack.onSuccess(histotyList);
                            } else {
                                callBack.onFailed("getChatHistory-->onFailed");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
