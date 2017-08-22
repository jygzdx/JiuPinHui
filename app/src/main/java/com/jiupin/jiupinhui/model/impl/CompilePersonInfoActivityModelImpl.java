package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.UserEntity;
import com.jiupin.jiupinhui.model.ICompilePersonInfoActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * 作者：czb on 2017/6/26 14:30
 */

public class CompilePersonInfoActivityModelImpl implements ICompilePersonInfoActivityModel {

    @Override
    public void savePersonInfo(String token, String nickName, String sex, String location, String intro, String education, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.COMPILE_PERSON_INFO)
                .addParams("token", token)
                .addParams("nickName", nickName)
                .addParams("sex", sex)
                .addParams("location", location)
                .addParams("intro", intro)
                .addParams("education", education)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(HttpErrorUtils.NETWORK_ERROR,HttpErrorUtils.MSG_NETWORK_ERROR);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("response = "+response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            int status = jsonObject.getInt("status");
                            if (200 == status) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                JSONObject dataObj = new JSONObject(data);
                                String user = dataObj.getString("user");
                                UserEntity.DataBean userEntity = gson.fromJson(user, UserEntity.DataBean.class);
                                callBack.onSuccess(userEntity);
                            } else {
                                callBack.onFailed(status, msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
