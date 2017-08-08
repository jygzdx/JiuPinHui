package com.jiupin.jiupinhui.utils;

import android.content.Context;
import android.content.Intent;

import com.jiupin.jiupinhui.activity.LoginActivity;

/**
 * TODO<判断http请求是否出错的逻辑类>
 * @author cjl
 */
public class HttpErrorUtils {
    public static final int RESPONE_SUCCEED = 200;
    public static final int TOKEN_ERROR = 300;
    public static final int NETWORK_ERROR = -1;
    public static final String MSG_NETWORK_ERROR = "网络连接异常，请稍后重试";
    private static Context mContext;

    /**
     * 对返回码进行处理
     * @param status
     * @param msg
     * @param context
     */
    public static void manageErrorHttp(int status,String msg ,Context context){
        if(status== TOKEN_ERROR){
            ToastUtils.showShort(context,"登录已失效，请重新登录");
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        }else if(status == NETWORK_ERROR){
            ToastUtils.showShort(context,MSG_NETWORK_ERROR);
        }else {
            if(msg.length()>25||msg.length()<=0){
                msg = "发送请求失败";
            }
            ToastUtils.showShort(context,msg);
        }
    }

}
