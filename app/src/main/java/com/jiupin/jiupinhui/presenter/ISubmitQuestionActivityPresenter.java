package com.jiupin.jiupinhui.presenter;

import java.io.File;
import java.util.List;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface ISubmitQuestionActivityPresenter {

    /**
     * 提交用户评价信息
     * @param token token
     * @param content 内容
     * @param files 上传的图片文件
     *
     */
    void submitQuestion(String token, String content,List<File> files);
}
