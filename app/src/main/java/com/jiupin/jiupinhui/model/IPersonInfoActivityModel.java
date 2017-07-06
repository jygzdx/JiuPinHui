package com.jiupin.jiupinhui.model;

import java.io.File;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface IPersonInfoActivityModel {
    /**
     * 根据token获取用户数据
     * @param token token
     * @param callBack callBack
     */
    void getUserInfoByToken(String token, IModel.CallBack callBack);

    /**
     * 上传图片
     *
     * @param file     file 上传的图片路径
     * @param name     图片名字
     * @param token    token
     * @param callBack callBack
     */
    void pushPicture(File file, String name, String token, IModel.CallBack callBack);
}
