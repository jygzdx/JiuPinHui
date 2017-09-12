package com.jiupin.jiupinhui.model;

import java.io.File;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IMainActivityModel {

    /**
     * 获取版本信息
     */
    void getVersionInfo(IModel.CallBack callBack);

    /**
     * 获取安装apk
     */
    void getApk(IModel.CallBack callBack);

    /**
     * 获取安装apkurl
     */
    void installApp(String url, String name, File file,IModel.ProgressCallBack callBack);
}
