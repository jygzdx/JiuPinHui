package com.jiupin.jiupinhui.model.impl;

import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.ISendCommentActivityModel;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import okhttp3.Call;

/**
 * 作者：czb on 2017/6/26 14:30
 */
public class SendCommentActivityModelImpl implements ISendCommentActivityModel {

    @Override
    public void sendComment(String token, String orderId, String eval_info,
                            String desc_evaluate, String service_evaluate,
                            String ship_evaluate, String rating, List<File> files,
                            final IModel.CallBack callBack) {

        PostFormBuilder builder = OkHttpUtils
                .post()
                .url(Constant.SEND_COMMENT)
                .addParams("token", token)
                .addParams("orderId", orderId)
                .addParams("eval_info", eval_info)
                .addParams("desc_evaluate", desc_evaluate)
                .addParams("service_evaluate", service_evaluate)
                .addParams("ship_evaluate", ship_evaluate)
                .addParams("rating", rating);
        if (files.size() == 1) {
            builder.addFile("photo", files.get(0).getName(), files.get(0));
        } else if (files.size() == 2) {
            builder.addFile("photo", files.get(0).getName(), files.get(0))
                    .addFile("photo", files.get(1).getName(), files.get(1));

        } else if (files.size() == 3) {
            builder.addFile("photo", files.get(0).getName(), files.get(0))
                    .addFile("photo", files.get(1).getName(), files.get(1))
                    .addFile("photo", files.get(2).getName(), files.get(2));
        }

        builder
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("response = "+response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            if ("OK".equals(msg)) {
                                String data = jsonObject.getString("data");
                                callBack.onSuccess(data);
                            } else {
                                callBack.onFailed("sendComment-->onFailed = "+jsonObject.getString("msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
