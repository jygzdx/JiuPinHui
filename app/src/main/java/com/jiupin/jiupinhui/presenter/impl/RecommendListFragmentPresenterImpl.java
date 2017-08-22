package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.entity.AttListEntity;
import com.jiupin.jiupinhui.fragment.RecommendListFragment;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IRecommendListFragmentModel;
import com.jiupin.jiupinhui.model.impl.RecommendListFragmentModelImpl;
import com.jiupin.jiupinhui.presenter.IRecommendListFragmentPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IRecommendListFragmentView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class RecommendListFragmentPresenterImpl implements IRecommendListFragmentPresenter {
    private IRecommendListFragmentView view;
    private IRecommendListFragmentModel model;

    public RecommendListFragmentPresenterImpl(IRecommendListFragmentView view) {
        this.view = view;
        model = new RecommendListFragmentModelImpl();
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
                HttpErrorUtils.manageErrorHttp(status,msg,((RecommendListFragment) view).getContext());
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
                HttpErrorUtils.manageErrorHttp(status, msg, ((RecommendListFragment) view).getContext());
            }
        });
    }
}
