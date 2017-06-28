package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.entity.HotRecommentEntity;
import com.jiupin.jiupinhui.model.IHomeFragmentModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.HomeFragmentModelImpl;
import com.jiupin.jiupinhui.presenter.IHomeFragmentPresenter;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.view.IHomeFragmentView;

/**
 * 作者：czb on 2017/6/28 11:06
 */

public class HomeFragmentPresenterImpl implements IHomeFragmentPresenter {
    private IHomeFragmentModel model;
    private IHomeFragmentView view;
    public HomeFragmentPresenterImpl(IHomeFragmentView view) {
        this.view = view;
        model = new HomeFragmentModelImpl();
    }

    @Override
    public void getHotRecomment() {
        model.getHotRecomment(new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setHotRecommentData((HotRecommentEntity)success);
            }

            @Override
            public void onFailed(Object error) {
                LogUtils.d("error = "+error);
            }
        });
    }
}
