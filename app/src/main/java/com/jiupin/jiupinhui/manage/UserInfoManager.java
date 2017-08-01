package com.jiupin.jiupinhui.manage;

import android.content.Context;

import com.jiupin.jiupinhui.entity.UserEntity;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.SPUtils;

/**
 * 作者：czb on 2017/7/17 11:01
 * 用户信息管理
 */

public class UserInfoManager {
    private boolean isLogin;

    private String token;
    private UserEntity.DataBean user;

    private static UserInfoManager mUserInfoManager = null;
    private UserInfoManager() { }
    public static UserInfoManager getInstance(){
        if (mUserInfoManager == null) {
            synchronized (UserInfoManager.class) {
                if (mUserInfoManager == null) {
                    mUserInfoManager = new UserInfoManager();
                }
            }
        }
        return mUserInfoManager;
    }

    public UserEntity.DataBean getUser() {
        return user;
    }

    public void setUser(UserEntity.DataBean user) {
        this.user = user;
    }


    public String getToken(Context context) {
        if (token==null||token == "") {
            return (String) SPUtils.get(context, SPUtils.LOGIN_TOKEN, "");
        }
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public boolean isLogin() {
        LogUtils.d("islogin = "+isLogin);
        return isLogin;
    }
}
