package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.entity.AppraiseEntity;
import com.jiupin.jiupinhui.model.ICommentActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.CommentActivityModelImpl;
import com.jiupin.jiupinhui.presenter.ICommentActivityPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.ICommentActivityView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class CommentActivityPresenterImpl implements ICommentActivityPresenter {

    private static final String TAG = "GoodsActivityPresenterImpl";

    private ICommentActivityModel model;
    private ICommentActivityView view;

    public CommentActivityPresenterImpl(ICommentActivityView view) {
        this.view = view;
        model = new CommentActivityModelImpl();
    }

    @Override
    public void getAppraise(int goodsId ,int page) {
        model.getAppraise(goodsId, page,new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setUserAppraise((List<AppraiseEntity>) success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
            }
        });
    }
}
