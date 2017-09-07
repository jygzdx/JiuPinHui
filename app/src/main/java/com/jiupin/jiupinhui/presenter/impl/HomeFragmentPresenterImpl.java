package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.entity.ArticleEntity;
import com.jiupin.jiupinhui.entity.BannerEntity;
import com.jiupin.jiupinhui.entity.HomeLoveEntity;
import com.jiupin.jiupinhui.entity.HotRecommentEntity;
import com.jiupin.jiupinhui.entity.MainShowEntity;
import com.jiupin.jiupinhui.fragment.HomeFragment;
import com.jiupin.jiupinhui.model.IHomeFragmentModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.HomeFragmentModelImpl;
import com.jiupin.jiupinhui.presenter.IHomeFragmentPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IHomeFragmentView;

import java.util.List;

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
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,((HomeFragment) view).getContext());
            }
        });
    }

    @Override
    public void getMainShow() {
        model.getMainShow(new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setMainShow((MainShowEntity)success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,((HomeFragment) view).getContext());
            }
        });
    }

    @Override
    public void getHomeLove(int pager) {
        model.getHomeLove(pager,new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setHomeLove((HomeLoveEntity) success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,((HomeFragment) view).getContext());
            }
        });
    }

    @Override
    public void getBanner() {
        model.getBanner(new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setBannerData((List<BannerEntity>) success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,((HomeFragment) view).getContext());
            }
        });
    }

    @Override
    public void getArticle() {
        model.getArticle(new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setArticleData((List<ArticleEntity>)success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,((HomeFragment) view).getContext());
            }
        });
    }

    @Override
    public void getCartGoodsCount(String token) {
        model.getCartGoodsCount(token, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.getCartGoodsCount((String) success);
            }

            @Override
            public void onFailed(int status, String msg) {
                if(status!=HttpErrorUtils.TOKEN_ERROR){
                    HttpErrorUtils.manageErrorHttp(status, msg, ((HomeFragment) view).getContext());
                }
            }
        });
    }
}
