package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.entity.BannerEntity;
import com.jiupin.jiupinhui.entity.MainShowEntity;
import com.jiupin.jiupinhui.entity.MealTypeEntity;
import com.jiupin.jiupinhui.fragment.StoreFragment;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IStoreFragmentModel;
import com.jiupin.jiupinhui.model.impl.StoreFragmentModelImpl;
import com.jiupin.jiupinhui.presenter.IStoreFragmentPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IStoreFragmentView;

import java.util.List;

/**
 * 作者：czb on 2017/6/28 11:06
 */

public class StoreFragmentPresenterImpl implements IStoreFragmentPresenter {
    private IStoreFragmentModel model;
    private IStoreFragmentView view;
    public StoreFragmentPresenterImpl(IStoreFragmentView view) {
        this.view = view;
        model = new StoreFragmentModelImpl();
    }

    @Override
    public void getBanner() {
        model.getBanner(new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setBannerData((List<BannerEntity>) success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,((StoreFragment) view).getContext());
            }
        });
    }

    @Override
    public void getMealType() {
        model.getMealType(new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setMealTypeData(((List<MealTypeEntity>) success));
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,((StoreFragment) view).getContext());
            }
        });
    }

    @Override
    public void getMealInfo(String cid) {
        model.getMealInfo(cid, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setMealInfoData(((List<MainShowEntity.DataBean.ListBean>) success));
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,((StoreFragment) view).getContext());
            }
        });
    }
}
