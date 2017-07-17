package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.FormEntity;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IMyFormActivityModel;
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

public class MyFormActivityModelImpl implements IMyFormActivityModel {


    @Override
    public void getFormInfo(String token, String orderStatus, String page, String rows, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.GET_ALL_FORM_INFO)
                .addParams("token", token)
                .addParams("orderStatus", orderStatus)
                .addParams("page", page)
                .addParams("rows", rows)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("getFormInfo" + e.getMessage());
                        callBack.onFailed("getFormInfo-->onFailed");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("getFormInfo=="+response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                JSONObject dataObj = new JSONObject(data);
                                String list = dataObj.getString("list");
                                List<FormEntity> forms = gson.fromJson(list,new TypeToken<List<FormEntity>>(){}.getType());
                                callBack.onSuccess(forms);
                            } else {
                                callBack.onFailed("getFormInfo-->onFailed"+jsonObject.getString("msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
