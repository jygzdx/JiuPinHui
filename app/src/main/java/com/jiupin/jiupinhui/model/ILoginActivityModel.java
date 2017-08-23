package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface ILoginActivityModel {

    /**
     * 用户登录
     * @param mobile 电话号码
     * @param pwd 密码
     * @param way 登陆途径：1.微信商城，2.App商城，3.待加
     */
    void loginUser(String mobile,String pwd,String way, IModel.CallBack callBack);
}
