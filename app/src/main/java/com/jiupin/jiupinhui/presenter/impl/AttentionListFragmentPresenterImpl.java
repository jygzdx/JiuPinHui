package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.entity.AttListEntity;
import com.jiupin.jiupinhui.fragment.RecommendListFragment;
import com.jiupin.jiupinhui.model.IAttentionListFragmentModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.AttentionListFragmentModelImpl;
import com.jiupin.jiupinhui.presenter.IAttentionListFragmentPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IAttentionListFragmentView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class AttentionListFragmentPresenterImpl implements IAttentionListFragmentPresenter {
    private IAttentionListFragmentView view;
    private IAttentionListFragmentModel model;

    public AttentionListFragmentPresenterImpl(IAttentionListFragmentView view) {
        this.view = view;
        model = new AttentionListFragmentModelImpl();
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
