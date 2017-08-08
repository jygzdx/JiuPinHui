package com.jiupin.jiupinhui.presenter;

import java.io.File;
import java.util.List;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface IConditionActivityPresenter {

    /**
     * 发布动态
     * @param token token
     * @param content 动态内容
     * @param dynamicType 动态类型
     * @param files 上传的图片文件
     *
     */
    void sendCondition(String token, String content, String dynamicType, List<File> files);
}
