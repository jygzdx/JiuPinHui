package com.jiupin.jiupinhui.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

/**
 * 作者：czb on 2017/8/17 20:23
 */

public class ActivityUtils {
    /**
     * 判断activity是不是销毁了
     *
     * @param context
     * @return
     */
    public static boolean isFinish(Context context){
        if((Activity)context==null)return true;
        if(((Activity)context).isFinishing())return true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (((Activity)context).isDestroyed()) {
                return true;
            }
        }

        return false;
    }
}
