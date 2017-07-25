package com.jiupin.jiupinhui.model.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.ArticleEntity;
import com.jiupin.jiupinhui.entity.BannerEntity;
import com.jiupin.jiupinhui.entity.HomeLoveEntity;
import com.jiupin.jiupinhui.entity.HotRecommentEntity;
import com.jiupin.jiupinhui.entity.MainShowEntity;
import com.jiupin.jiupinhui.model.IHomeFragmentModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;

import static com.zhy.http.okhttp.OkHttpUtils.post;

/**
 * 作者：czb on 2017/6/28 11:17
 */

public class HomeFragmentModelImpl implements IHomeFragmentModel {

    @Override
    public void getHotRecomment(final IModel.CallBack callBack) {
        post()
                .url(Constant.HOT_RECOMMENT_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("网络出错" + e.getMessage());
                        callBack.onFailed(e.getMessage());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("response = "+response);
                        Gson gson = new Gson();
                        HotRecommentEntity hotRecommentEntity = gson.fromJson(response,HotRecommentEntity.class);
                        if ("OK".equals(hotRecommentEntity.getMsg())){
                            callBack.onSuccess(hotRecommentEntity);
                        }else{
                            callBack.onFailed(hotRecommentEntity.getMsg());
                        }

                    }
                });
    }

    @Override
    public void getMainShow(final IModel.CallBack callBack) {
        post()
                .url(Constant.MAIN_SHOW_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("网络出错" + e.getMessage());
                        callBack.onFailed(e.getMessage());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("response = "+response);
                        Gson gson = new Gson();
                        MainShowEntity mainShowEntity = gson.fromJson(response,MainShowEntity.class);
                        if ("OK".equals(mainShowEntity.getMsg())){
                            callBack.onSuccess(mainShowEntity);
                        }else{
                            callBack.onFailed(mainShowEntity.getMsg());
                        }

                    }
                });
    }

    @Override
    public void getHomeLove(int page,final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.GUESS_LOVE_URL)
                .addParams("page",page+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("网络出错" + e.getMessage());
                        callBack.onFailed(e.getMessage());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("response = "+response);
                        Gson gson = new Gson();
                        HomeLoveEntity homeLoveEntity = gson.fromJson(response,HomeLoveEntity.class);
                        if ("OK".equals(homeLoveEntity.getMsg())){
                            callBack.onSuccess(homeLoveEntity);
                        }else{
                            callBack.onFailed(homeLoveEntity.getMsg());
                        }

                    }
                });
    }

    @Override
    public void getBanner(final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.BANNER_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("getBanner" + e.getMessage());
                        callBack.onFailed("getBanner-->onFailed");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("getAddressList" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                JSONObject dataObj = new JSONObject(data);
                                String list = dataObj.getString("list");
                                List<BannerEntity> bannerList = gson.fromJson(list,new TypeToken<List<BannerEntity>>(){}.getType());
                                callBack.onSuccess(bannerList);
                            } else {
                                callBack.onFailed("getBanner-->onFailed");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void getArticle(final IModel.CallBack callBack) {
        OkHttpUtils
                .post()
                .url(Constant.ARTICLE_URL)
                .addParams("page",1+"")
                .addParams("rows",3+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("getArticle" + e.getMessage());
                        callBack.onFailed("getArticle-->onFailed");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("getArticle" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                JSONObject dataObj = new JSONObject(data);
                                String list = dataObj.getString("list");
                                List<ArticleEntity> articleList = gson.fromJson(list,new TypeToken<List<ArticleEntity>>(){}.getType());
                                callBack.onSuccess(articleList);
                            } else {
                                callBack.onFailed("getArticle-->onFailed");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
