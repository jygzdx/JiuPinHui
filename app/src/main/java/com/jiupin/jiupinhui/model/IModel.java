package com.jiupin.jiupinhui.model;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IModel {
    public interface CallBack{
        /**
         * 回调成功调用
         * @param success 成功后传递的参数
         */
        void onSuccess(Object success);

        /**
         * 回调失败调用
         * @param status 失败后传递的返回码
         */
        void onFailed(int status ,String msg);
    }
}
