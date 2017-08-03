package com.jiupin.jiupinhui.model;

import java.io.File;
import java.util.List;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface ISubmitQuestionActivityModel {


    /**
     * 提交用户评价信息
     * @param token token
     * @param content 内容
     * @param files 上传的图片文件
     *
     */
    void submitQuestion(String token, String content,List<File> files, IModel.CallBack callBack);
}
