package com.jiupin.jiupinhui.model.impl;

import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.ISendCommentActivityModel;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Map<String, String> params = new HashMap<>();
        params.put("token", token);
        params.put("orderId", orderId);
        params.put("eval_info", eval_info);
        params.put("desc_evaluate", desc_evaluate);
        params.put("service_evaluate", service_evaluate);
        params.put("ship_evaluate", ship_evaluate);
        params.put("rating", rating);

        Map<String, File> filesMap = new HashMap<>();
        for (int i = 0; i < files.size(); i++) {
            filesMap.put(files.get(i).getName(),files.get(i));
        }

        OkHttpUtils.post()
                .url(Constant.SEND_COMMENT)
                .files("photo",filesMap)
                .params(params)
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
                                String data = jsonObject.getString("data");
                                callBack.onSuccess(data);
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
