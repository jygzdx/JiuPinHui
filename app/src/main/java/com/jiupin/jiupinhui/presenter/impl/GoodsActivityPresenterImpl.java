package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.entity.AppraiseEntity;
import com.jiupin.jiupinhui.entity.GoodsEntity;
import com.jiupin.jiupinhui.model.IGoodsActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.GoodsActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IGoodsActivityPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IGoodsActivityView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class GoodsActivityPresenterImpl implements IGoodsActivityPresenter {

    private static final String TAG = "GoodsActivityPresenterImpl";

    private IGoodsActivityModel model;
    private IGoodsActivityView view;

    public GoodsActivityPresenterImpl(IGoodsActivityView view) {
        this.view = view;
        model = new GoodsActivityModelImpl();
    }


    @Override
    public void getGoodsInfo(int id) {
        model.getGoodsInfo(id, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setData((GoodsEntity) success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, (Context) view);
            }
        });
    }

    @Override
    public void getAppraise(int goodsId) {
        model.getAppraise(goodsId, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                List<AppraiseEntity> appraiseList = (List<AppraiseEntity>) success;

                view.setUserAppraise(appraiseList);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, (Context) view);
            }
        });
    }

    @Override
    public void addGoodsToCar(String token, String id, String spec_id, String count) {
        model.addGoodsToCar(token, id, spec_id, count, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.addGoodsToCar((String) success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, (Context) view);
            }
        });
    }

    @Override
    public void getCartGoodsCount(String token) {
        model.getCartGoodsCount(token, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.getCartGoodsCount((String) success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, (Context) view);
            }
        });
    }
}
