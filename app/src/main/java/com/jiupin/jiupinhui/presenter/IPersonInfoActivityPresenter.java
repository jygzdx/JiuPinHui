package com.jiupin.jiupinhui.presenter;

import java.io.File;

/**
 * 作者：czb on 2017/6/30 16:20
 */

public interface IPersonInfoActivityPresenter {

    /**
     * 根据token获取用户数据
     *
     * @param token token
     */
    void getUserInfoByToken(String token);

    /**
     * 上传图片
     *
     * @param file     file 上传的图片路径
     * @param name     图片名字
     * @param token    token
     */
    void pushPicture(File file, String name, String token );
}
