package com.jiupin.jiupinhui.utils;

import android.util.Log;

/**
 * Created by Administrator on 2017/3/16.
 */

public class LogUtils {
    private LogUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isDebug = false;// 是否需要打印bug，可以在application的onCreate函数里面初始化
    private static final String TAG = "JiuPinHui";

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (msg != null) {
            if (isDebug)
                Log.i(TAG, msg);
        }

    }

    public static void d(String msg) {
        if (msg != null) {
            if (isDebug)
                Log.d(TAG, msg);
        }

    }

    public static void e(String msg) {
        if (msg != null) {
            if (isDebug)
                Log.e(TAG, msg);
        }

    }

    public static void v(String msg) {
        if (msg != null) {
            if (isDebug)
                Log.v(TAG, msg);
        }

    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (msg != null) {
            if (isDebug)
                Log.i(tag, msg);
        }

    }

    public static void d(String tag, String msg) {
        if (msg != null) {
            if (isDebug)
                Log.d(tag, msg);
        }

    }

    public static void e(String tag, String msg) {
        if (msg != null) {
            if (isDebug)
                Log.e(tag, msg);
        }

    }

    public static void v(String tag, String msg) {
        if (msg != null) {
            if (isDebug)
                Log.v(tag, msg);
        }

    }
}
