package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.AppraiseEntity;
import com.jiupin.jiupinhui.model.ICommentActivityModel;
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

public class CommentActivityModelImpl implements ICommentActivityModel {

    @Override
    public void getAppraise(int goodsId, int page ,final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.APPRAISE_INFO)
                .addParams("goodsId", goodsId+"")
                .addParams("page", page+"")
                .addParams("rows", 10+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("getAppraise" + e.getMessage());
                        callBack.onFailed("getAppraise-->onFailed");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d(response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                JSONObject dataObj = new JSONObject(data);
                                String list = dataObj.getString("list");
                                List<AppraiseEntity> appraiseList = gson.fromJson(list,new TypeToken<List<AppraiseEntity>>(){}.getType());
                                callBack.onSuccess(appraiseList);
                            } else {
                                callBack.onFailed("getAppraise-->onFailed");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
