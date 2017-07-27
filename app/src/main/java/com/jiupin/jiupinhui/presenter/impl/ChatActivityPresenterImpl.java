package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.entity.ChatEntity;
import com.jiupin.jiupinhui.model.IChatActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.ChatActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IChatActivityPresenter;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.view.IChatActivityView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class ChatActivityPresenterImpl implements IChatActivityPresenter {

    private static final String TAG = "ChatActivityPresenterImpl";

    private IChatActivityModel model;
    private IChatActivityView view;

    public ChatActivityPresenterImpl(IChatActivityView view) {
        this.view = view;
        model = new ChatActivityModelImpl();
    }


    @Override
    public void getChatInfo(String token, String orderNum) {
        model.getChatInfo(token, orderNum, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setChatList((List<ChatEntity>) success, "客服稍后处理您的问题");
            }

            @Override
            public void onFailed(Object error) {
                LogUtils.d(error.toString());
            }
        });
    }
}
