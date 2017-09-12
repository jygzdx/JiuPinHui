package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.VersionEntity;
import com.jiupin.jiupinhui.model.IMainActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/31.
 */

public class MainActivityModelImpl implements IMainActivityModel {
    private static final String TAG = "MainActivityModelImpl";

    @Override
    public void getVersionInfo(final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.VERSION_INFO)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(HttpErrorUtils.NETWORK_ERROR,HttpErrorUtils.MSG_NETWORK_ERROR);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d(TAG,"getVersionInfo" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            int status = jsonObject.getInt("status");
                            if (200 == status) {
                                String data = jsonObject.getString("data");
                                VersionEntity versionEntity = new Gson().fromJson(data,VersionEntity.class);
                                callBack.onSuccess(versionEntity);
                            } else {
                                callBack.onFailed(status, msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void getApk(final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.GET_APK_DOWNLOAD)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(HttpErrorUtils.NETWORK_ERROR,HttpErrorUtils.MSG_NETWORK_ERROR);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d(TAG,"getApkUrl" + response);
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

    @Override
    public void installApp(String url, String name, File file,final IModel.ProgressCallBack callBack) {
        OkHttpUtils//
                .get()//
                .url(url)//
                .build()//
//                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath()+"/jiupinhui/apk/","jiupinhui_"+name+".apk")//
                .execute(new FileCallBack(file.getParent(),file.getName())
                {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        LogUtils.d(TAG,"onError :" + e.getMessage()+" i = "+i);
                        callBack.onFailed(444,e.getMessage());
                    }

                    @Override
                    public void onResponse(File file, int i) {
                        LogUtils.d(TAG,"onResponse :" + file.getAbsolutePath()+" i = "+i);
                        callBack.onSuccess(file.getAbsolutePath());
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        LogUtils.d(TAG,"progress = "+progress +" total = "+total + " id = "+id);
                    }

                });
    }
}
