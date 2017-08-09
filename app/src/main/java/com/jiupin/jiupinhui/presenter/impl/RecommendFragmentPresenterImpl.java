package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.entity.CommunityEntity;
import com.jiupin.jiupinhui.fragment.RecommendFragment;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IRecommendFragmentModel;
import com.jiupin.jiupinhui.model.impl.RecommendFragmentModelImpl;
import com.jiupin.jiupinhui.presenter.IRecommendFragmentPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IRecommendFragmentView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class RecommendFragmentPresenterImpl implements IRecommendFragmentPresenter {
    private IRecommendFragmentView view;
    private IRecommendFragmentModel model;

    public RecommendFragmentPresenterImpl(IRecommendFragmentView view) {
        this.view = view;
        model = new RecommendFragmentModelImpl();
    }

    @Override
    public void getRecommendList(String token, String page, String rows) {
        model.getRecommendList(token, page, rows, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setRecommendInfo((List<CommunityEntity>) success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, ((RecommendFragment) view).getContext());
            }
        });
    }
}
