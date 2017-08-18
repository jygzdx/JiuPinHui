package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.entity.CommunityEntity;
import com.jiupin.jiupinhui.fragment.PersonConditionFragment;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IPersonConditionFragmentModel;
import com.jiupin.jiupinhui.model.impl.PersonConditionFragmentModelImpl;
import com.jiupin.jiupinhui.presenter.IPersonConditionFragmentPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IPersonConditionFragmentView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class PersonConditionFragmentPresenterImpl implements IPersonConditionFragmentPresenter {
    private IPersonConditionFragmentView view;
    private IPersonConditionFragmentModel model;

    public PersonConditionFragmentPresenterImpl(IPersonConditionFragmentView view) {
        this.view = view;
        model = new PersonConditionFragmentModelImpl();
    }

    @Override
    public void getPersonConditionList(String token, String userId, String page, String rows) {
        model.getPersonConditionList(token, userId, page, rows, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setPersonConditionInfo((List<CommunityEntity>) success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, ((PersonConditionFragment) view).getContext());
            }
        });
    }

    @Override
    public void setThumbDynamic(String token, String communityId, final int position) {
        model.setThumbDynamic(token, communityId, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.thumbDynamic(((String) success), position);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, ((PersonConditionFragment) view).getContext());
            }
        });
    }

    @Override
    public void deleteCondition(String token, String dynamicId, final int position) {
        model.deleteCondition(token, dynamicId, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.deleteCondition(((String) success), position);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, ((PersonConditionFragment) view).getContext());
            }
        });
    }

    @Override
    public void isOnlyMyselfLook(String token, String dynamicId, String isVisible) {
        model.isOnlyMyselfLook(token, dynamicId, isVisible, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.onlymyselflook((List<CommunityEntity>) success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, ((PersonConditionFragment) view).getContext());
            }
        });
    }

    @Override
    public void moveConditionToTop(String token, String dynamicId) {
        model.moveConditionToTop(token, dynamicId, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.moveConditionToTop((List<CommunityEntity>) success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, ((PersonConditionFragment) view).getContext());
            }
        });
    }
}
