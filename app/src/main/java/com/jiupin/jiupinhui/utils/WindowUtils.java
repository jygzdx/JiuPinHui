package com.jiupin.jiupinhui.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by Administrator on 2017/4/18.
 */

public class WindowUtils {
    /**
     * 获取屏幕高度
     * @param activity
     * @return
     */
    public static int getWindowHeight(Activity activity){
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        //窗口高度
        int screenHeight = dm.heightPixels;
        return screenHeight;
    }
    /**
     * 获取屏幕宽度
     * @param activity
     * @return
     */
    public static int getWindowWidth(Activity activity){
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        //窗口的宽度
        int screenWidth = dm.widthPixels;

        return screenWidth;
    }
}
