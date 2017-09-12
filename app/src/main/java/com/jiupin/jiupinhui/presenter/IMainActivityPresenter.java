package com.jiupin.jiupinhui.presenter;

import java.io.File;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IMainActivityPresenter {

    /**
     * 获取版本信息
     */
    void getVersionInfo();

    /**
     * 获取安装apkurl
     */
    void getApk(String versionName);

    /**
     * 获取安装apkurl
     */
    void installApp(String url, String versionName, File file);
}
