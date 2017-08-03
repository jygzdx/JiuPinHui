package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.entity.ChatEntity;
import com.jiupin.jiupinhui.entity.ChatListEntity;
import com.jiupin.jiupinhui.model.IBeforeChatActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.BeforeChatActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IBeforeChatActivityPresenter;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IBeforeChatActivityView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class BeforeChatActivityPresenterImpl implements IBeforeChatActivityPresenter {

    private static final String TAG = "ChatActivityPresenterImpl";

    private IBeforeChatActivityModel model;
    private IBeforeChatActivityView view;

    public BeforeChatActivityPresenterImpl(IBeforeChatActivityView view) {
        this.view = view;
        model = new BeforeChatActivityModelImpl();
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
    public void getAgainAppraise(String token, String orderNum, String content) {
        model.getAgainAppraise(token, orderNum, content, new IModel.CallBack() {
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
