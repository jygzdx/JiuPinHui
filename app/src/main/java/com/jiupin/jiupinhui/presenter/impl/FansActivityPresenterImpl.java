package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.entity.AttListEntity;
import com.jiupin.jiupinhui.model.IFansActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.FansActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IFansActivityPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IFansActivityView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class FansActivityPresenterImpl implements IFansActivityPresenter {
    private static final String TAG = "FansActivityPresenterIm";
    private IFansActivityView view;
    private IFansActivityModel model;

    public FansActivityPresenterImpl(IFansActivityView view) {
        this.view = view;
        model = new FansActivityModelImpl();
    }

    @Override
    public void getRecommendList(String token, String page, String rows) {
        model.getRecommendList(token, page, rows, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setRecommendListInfo(((List<AttListEntity>) success));
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
            }
        });
    }

    @Override
    public void cancelCondition(String token, String userId, String concernStatus, final int position) {
        model.cancelCondition(token, userId, concernStatus, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.concernExpert((String) success,position);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, (Context) view);
            }
        });
    }
}
