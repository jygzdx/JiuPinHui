package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.ChatEntity;
import com.jiupin.jiupinhui.entity.ChatListEntity;
import com.jiupin.jiupinhui.model.IBeforeChatActivityModel;
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

public class BeforeChatActivityModelImpl implements IBeforeChatActivityModel {


    @Override
    public void getChatInfo(String token, String consultId, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.BEFORE_CONSULT_HISTORY)
                .addParams("token", token)
                .addParams("consultId", consultId)
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
                                ChatListEntity chatList = gson.fromJson(data, ChatListEntity.class);
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

    @Override
    public void getAgainAppraise(String token, String consultId, String content, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.AGAIN_BEFORE_CONSULT)
                .addParams("token", token)
                .addParams("consultId", consultId)
                .addParams("content",content)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("getAgainAppraise" + e.getMessage());
                        callBack.onFailed("getAgainAppraise-->onFailed");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("getAgainAppraise" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                ChatListEntity chatList = gson.fromJson(data, ChatListEntity.class);
                                callBack.onSuccess(chatList);
                            } else {
                                callBack.onFailed(jsonObject.getString("msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void closeChat(String token, String consultId, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.CLOSE_BEFORE_CONSULT)
                .addParams("token", token)
                .addParams("consultId", consultId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("closeChat" + e.getMessage());
                        callBack.onFailed("closeChat-->onFailed");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("closeChat" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                String list = new JSONObject(data).getString("list");
                                List<ChatEntity> chatList = gson.fromJson(list, new TypeToken<List<ChatEntity>>(){}.getType());
                                callBack.onSuccess(chatList);
                            } else {
                                callBack.onFailed(jsonObject.getString("msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
