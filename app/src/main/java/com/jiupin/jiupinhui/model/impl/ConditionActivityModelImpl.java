package com.jiupin.jiupinhui.model.impl;

import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.model.IConditionActivityModel;
import com.jiupin.jiupinhui.model.IModel;
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
public class ConditionActivityModelImpl implements IConditionActivityModel{
    @Override
    public void sendCondition(String token, String content, String dynamicType, List<File> files, final IModel.CallBack callBack) {
        Map<String, String> params = new HashMap<>();
        params.put("token", token);
        params.put("content", content);
        params.put("dynamicType", dynamicType);

        Map<String, File> filesMap = new HashMap<>();
        for (int i = 0; i < files.size(); i++) {
            filesMap.put(files.get(i).getName(),files.get(i));
        }

        OkHttpUtils.post()
                .url(Constant.SAVE_CONDITION)
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
