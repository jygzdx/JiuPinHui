package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.entity.Wine;
import com.jiupin.jiupinhui.entity.WineBrand;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IWineFragmentModel;
import com.jiupin.jiupinhui.model.impl.WineFragmentModelImpl;
import com.jiupin.jiupinhui.presenter.IWineFragmentPresenter;
import com.jiupin.jiupinhui.view.IWineFragmentView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class WineFragmentPresenterImpl implements IWineFragmentPresenter {
    private IWineFragmentView view;
    private IWineFragmentModel model;

    public WineFragmentPresenterImpl(IWineFragmentView view) {
        this.view = view;
        model = new WineFragmentModelImpl();
    }

    @Override
    public void getData() {
        model.getData(new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setData((Wine) success);
                view.SetAdapter();
            }

            @Override
            public void onFailed(Object error) {

            }
        });
    }

    @Override
    public void getBrandData() {
        model.getBrandData(new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setBrandData((WineBrand) success);
                view.SetBrandAdapter();
            }

            @Override
            public void onFailed(Object error) {
                //打印错误信息
            }
        });
    }
}
