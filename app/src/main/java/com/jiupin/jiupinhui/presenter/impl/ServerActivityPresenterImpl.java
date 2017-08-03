package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.entity.ChatHistotyEntity;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IServerActivityModel;
import com.jiupin.jiupinhui.model.impl.ServerActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IServerActivityPresenter;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.view.IServerActivityView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class ServerActivityPresenterImpl implements IServerActivityPresenter {

    private static final String TAG = "ManageAddressActivityPresenterImpl";

    private IServerActivityModel model;
    private IServerActivityView view;

    public ServerActivityPresenterImpl(IServerActivityView view) {
        this.view = view;
        model = new ServerActivityModelImpl();
    }

    @Override
    public void getChatHistory(String token, String page, String rows) {
        model.getChatHistory(token, page, rows, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setChatHistoryList((List<ChatHistotyEntity>) success);
            }

            @Override
            public void onFailed(Object error) {
                LogUtils.d(TAG, "error = " + error);
            }
        });
    }
}
