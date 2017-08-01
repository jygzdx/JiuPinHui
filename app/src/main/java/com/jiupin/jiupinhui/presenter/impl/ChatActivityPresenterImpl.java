package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.entity.ChatEntity;
import com.jiupin.jiupinhui.entity.ChatListEntity;
import com.jiupin.jiupinhui.model.IChatActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.ChatActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IChatActivityPresenter;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IChatActivityView;

import java.io.File;
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
                view.setChatList(((ChatListEntity) success).getList(), ((ChatListEntity) success).getHint());
            }

            @Override
            public void onFailed(Object error) {
                LogUtils.d(error.toString());
            }
        });
    }

    @Override
    public void getAgainAppraise(File photo, String token, String orderNum, String content) {
        model.getAgainAppraise(photo, token, orderNum, content, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.sendChatSuccess(((ChatListEntity) success).getList(), ((ChatListEntity) success).getHint());
            }

            @Override
            public void onFailed(Object error) {
                ToastUtils.showShort(((Context) view), ((String) error));
            }
        });
    }

    @Override
    public void closeChat(String token, String orderNum) {
        model.closeChat(token, orderNum, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.closeChatSuccess(((List<ChatEntity>) success));
            }

            @Override
            public void onFailed(Object error) {
                ToastUtils.showShort(((Context) view), ((String) error));
            }
        });
    }
}
