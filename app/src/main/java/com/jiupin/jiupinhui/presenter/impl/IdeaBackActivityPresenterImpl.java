package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.model.IIdeaBackActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.IdeaBackActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IIdeaBackActivityPresenter;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.view.IIdeaBackActivityView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class IdeaBackActivityPresenterImpl implements IIdeaBackActivityPresenter {

    private static final String TAG = "IdeaBackActivityPresenterImpl";

    private IIdeaBackActivityModel model;
    private IIdeaBackActivityView view;

    public IdeaBackActivityPresenterImpl(IIdeaBackActivityView view) {
        this.view = view;
        model = new IdeaBackActivityModelImpl();
    }

    @Override
    public void submitIdea(String token, String content, String way) {
        model.submitIdea(token, content, way, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.submitIdeaSuccess(((ResponseBase) success).getData().toString());
            }

            @Override
            public void onFailed(Object error) {
                LogUtils.d(error.toString());
            }
        });
    }
}
