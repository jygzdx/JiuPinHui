package com.jiupin.jiupinhui.view;

/**
 * 作者：czb on 2017/7/27 15:09
 */

public interface IBaseView {
    /**
     * 如果网络请求失败或者后台返回错误信息，需要提示
     * @param error 提示的错误信息
     */
    void requestError(String error);
}
