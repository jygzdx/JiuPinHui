package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.entity.AllCartEntity;
import com.jiupin.jiupinhui.entity.CartEntity;
import com.jiupin.jiupinhui.model.IBuyCartActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.BuyCartActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IBuyCartActivityPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IBuyCartActivityView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class BuyCartActivityPresenterImpl implements IBuyCartActivityPresenter {

    private static final String TAG = "BuyCartActivityPresenterImpl";

    private IBuyCartActivityModel model;
    private IBuyCartActivityView view;

    public BuyCartActivityPresenterImpl(IBuyCartActivityView view) {
        this.view = view;
        model = new BuyCartActivityModelImpl();
    }

    @Override
    public void getBuyCartList(String token) {
        model.getBuyCartList(token, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.buyCartList(((List<CartEntity>) success));
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, (Context) view);
            }
        });
    }

    @Override
    public void deleteGoods(String token, String id, String spec_id, final int position) {
        model.deleteGoods(token, id, spec_id, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.deleteGoods(position);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, (Context) view);
            }
        });
    }

    @Override
    public void emptyCart(String token) {
        model.emptyCart(token, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.emptyCartSuccess(success.toString());
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, (Context) view);
            }
        });
    }

    @Override
    public void notifyGoodsCount(String token, String id, String spec_id, final int count, final int position) {
        model.notifyGoodsCount(token, id, spec_id, count, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.notifyGoodsCountSuccess(position, count);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, (Context) view);
            }
        });
    }

    @Override
    public void submitGoodsInfo(String token, String goodStr) {
        model.submitGoodsInfo(token, goodStr, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.submitGoodsSuccess(((List<AllCartEntity.OnSaleBean>) success));
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, (Context) view);
            }
        });
    }
}
