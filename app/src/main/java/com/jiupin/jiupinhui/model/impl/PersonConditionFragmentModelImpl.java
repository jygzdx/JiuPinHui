package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.CommunityEntity;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IPersonConditionFragmentModel;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/31.
 */

public class PersonConditionFragmentModelImpl implements IPersonConditionFragmentModel {

    @Override
    public void getPersonConditionList(String token,String userId,  String page, String rows, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.PERSON_CONDITION)
                .addParams("token",token)
                .addParams("userId",userId)
                .addParams("page",page)
                .addParams("rows",rows)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(HttpErrorUtils.NETWORK_ERROR,HttpErrorUtils.MSG_NETWORK_ERROR);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("getRecommendList" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            int status = jsonObject.getInt("status");
                            if (200 == status) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                JSONObject dataObj = new JSONObject(data);
                                String list = dataObj.getString("list");
                                List<CommunityEntity> communityEntityList = gson.fromJson(list,new TypeToken<List<CommunityEntity>>(){}.getType());
                                callBack.onSuccess(communityEntityList);
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
    public void setThumbDynamic(String token, String communityId, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.THUMB_DYNAMIC)
                .addParams("token",token)
                .addParams("dynamicId",communityId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(HttpErrorUtils.NETWORK_ERROR,HttpErrorUtils.MSG_NETWORK_ERROR);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("setThumbDynamic" + response);
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
    public void deleteCondition(String token, String dynamicId, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.DELETE_CONDITION)
                .addParams("token",token)
                .addParams("dynamicId",dynamicId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(HttpErrorUtils.NETWORK_ERROR,HttpErrorUtils.MSG_NETWORK_ERROR);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("setThumbDynamic" + response);
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
    public void isOnlyMyselfLook(String token, String dynamicId, String isVisible, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.ONLY_MYSELF_LOOK)
                .addParams("token",token)
                .addParams("dynamicId",dynamicId)
                .addParams("isVisible",isVisible)
                .addParams("page","1")
                .addParams("rows","10")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(HttpErrorUtils.NETWORK_ERROR,HttpErrorUtils.MSG_NETWORK_ERROR);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("getRecommendList" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            int status = jsonObject.getInt("status");
                            if (200 == status) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                JSONObject dataObj = new JSONObject(data);
                                String list = dataObj.getString("list");
                                List<CommunityEntity> communityEntityList = gson.fromJson(list,new TypeToken<List<CommunityEntity>>(){}.getType());
                                callBack.onSuccess(communityEntityList);
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
    public void moveConditionToTop(String token, String dynamicId, final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.MOVE_CONDITION_TO_TOP)
                .addParams("token",token)
                .addParams("dynamicId",dynamicId)
                .addParams("page","1")
                .addParams("rows","10")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onFailed(HttpErrorUtils.NETWORK_ERROR,HttpErrorUtils.MSG_NETWORK_ERROR);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("getRecommendList" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            int status = jsonObject.getInt("status");
                            if (200 == status) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                JSONObject dataObj = new JSONObject(data);
                                String list = dataObj.getString("list");
                                List<CommunityEntity> communityEntityList = gson.fromJson(list,new TypeToken<List<CommunityEntity>>(){}.getType());
                                callBack.onSuccess(communityEntityList);
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
