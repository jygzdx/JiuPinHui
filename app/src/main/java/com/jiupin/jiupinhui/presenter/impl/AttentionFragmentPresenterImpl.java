package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.entity.CommunityEntity;
import com.jiupin.jiupinhui.entity.UserEntity;
import com.jiupin.jiupinhui.fragment.RecommendFragment;
import com.jiupin.jiupinhui.model.IAttentionFragmentModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.AttentionFragmentModelImpl;
import com.jiupin.jiupinhui.presenter.IAttentionFragmentPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IAttentionFragmentView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class AttentionFragmentPresenterImpl implements IAttentionFragmentPresenter {
    private IAttentionFragmentView view;
    private IAttentionFragmentModel model;

    public AttentionFragmentPresenterImpl(IAttentionFragmentView view) {
        this.view = view;
        model = new AttentionFragmentModelImpl();
    }

    @Override
    public void getAttentionList(String token, String page, String rows) {
        model.getAttentionList(token, page, rows, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setAttentionInfo((List<CommunityEntity>) success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, ((RecommendFragment) view).getContext());
            }
        });
    }

    @Override
    public void setThumbDynamic(String token, String communityId, final int position) {
        model.setThumbDynamic(token, communityId, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.thumbDynamic(((String) success),position);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, ((RecommendFragment) view).getContext());
            }
        });
    }

    @Override
    public void cancelCondition(String token, final String userId, String concernStatus, final int position) {
        model.cancelCondition(token, userId, concernStatus, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.concernExpert((String) success,userId);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, ((RecommendFragment) view).getContext());
            }
        });
    }

    @Override
    public void getUserInfo(String token) {
        model.getUserInfo(token, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setUserInfo(((UserEntity) success));
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, ((RecommendFragment) view).getContext());
            }
        });
    }
}
