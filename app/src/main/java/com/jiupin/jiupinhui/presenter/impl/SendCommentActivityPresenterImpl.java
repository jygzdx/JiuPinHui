package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.ISendCommentActivityModel;
import com.jiupin.jiupinhui.model.impl.SendCommentActivityModelImpl;
import com.jiupin.jiupinhui.presenter.ISendCommentActivityPresenter;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.ISendCommentActivityView;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class SendCommentActivityPresenterImpl implements ISendCommentActivityPresenter {

    private static final String TAG = "SendCommentActivityPresenterImpl";

    private ISendCommentActivityModel model;
    private ISendCommentActivityView view;

    public SendCommentActivityPresenterImpl(ISendCommentActivityView view) {
        this.view = view;
        model = new SendCommentActivityModelImpl();
    }

    @Override
    public void sendComment(String token, String orderId, String eval_info, String desc_evaluate, String service_evaluate, String ship_evaluate, String rating, List<File> files) {
        model.sendComment(token, orderId, eval_info, desc_evaluate, service_evaluate, ship_evaluate, rating, files, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.sendCommentSuccess();
            }

            @Override
            public void onFailed(Object error) {
                ToastUtils.showShort((Context) view,"评论失败");
                LogUtils.d(((String) error));
            }
        });
    }
}
