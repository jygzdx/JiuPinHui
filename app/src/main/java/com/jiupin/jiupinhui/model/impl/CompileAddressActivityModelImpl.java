package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.model.ICompileAddressActivityModel;
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

public class CompileAddressActivityModelImpl implements ICompileAddressActivityModel {

    @Override
    public void saveAddress(String token, String area_id, String zip, String trueName, String area_info,
                            String id, String mobile, String area_main, boolean isDefault, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.PUSH_ADDRESS)
                .addParams("token", token)
                .addParams("area_id", area_id)
                .addParams("zip", zip)
                .addParams("trueName", trueName)
                .addParams("area_info", area_info)
                .addParams("id", id)
                .addParams("mobile", mobile)
                .addParams("area_main", area_main)
                .addParams("isDefault", isDefault+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("saveAddress" + e.getMessage());
                        callBack.onFailed("saveAddress-->onFailed");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                Gson gson = new Gson();
                                ResponseBase responseBase = gson.fromJson(response,ResponseBase.class);
                                callBack.onSuccess(responseBase);
                            } else {
                                callBack.onFailed("saveAddress-->onFailed"+jsonObject.getString("msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
