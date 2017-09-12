package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.VersionEntity;

/**
 * 作者：czb on 2017/6/28 11:38
 */

public interface IMainActivityView {

    void getVersionInfo(VersionEntity versionEntity);

    /**
     * 获取apk的下载路径
     * @param apkUrl 下载路径
     * @param versionName
     */
    void setApkUrl(String apkUrl,String versionName);

    /**
     * 安装apk
     * @param installUrl 手机安装路径
     */
    void installApp(String installUrl);

}
