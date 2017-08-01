package com.jiupin.jiupinhui.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.jiupin.jiupinhui.activity.LoginActivity;

import org.json.JSONObject;

/**
 * TODO<判断http请求是否出错的逻辑类>
 *
 * @author cjl
 * @data: 2015年8月23日 下午4:33:25
 * @version: V1.0
 */
public class HttpErrorUtils {
    public static int RESPONE_SUCCEED = 200;
    private static Context mContext;


    /**
     * (接口请求是否出错)
     *
     * @param data
     * @return
     */
    // *************************************************************************
    public static boolean isReponseError(String data, Context context) {
        mContext = context;
        /**
         * todo
         * 测试所有全部返回false
         */
        if (StringUtils.isEmpty(data)) {
            return true;
        }
       // System.out.println("isReponseError.data=" + data.toString());
        boolean isError = false;
        try {
            JSONObject json = new JSONObject(data);

            if (json != null) {
                if (json.has("code")) {
                    int status = json.getInt("code");
                    if (status != (RESPONE_SUCCEED)) {//测试接口请求成功码：200
                        isError = true;
                    }
                } else {//因为生成支付宝订单 接口返回没有“code"字段， 又不算错误，我好纠结，把所有没有code字段的都算是正确的返回。我好纠结
                    isError = false;
                }
            }
        } catch (Exception e) {
            isError = true;
            e.printStackTrace();
        }
        return isError;
    }

    // *************************************************************************

    /**
     * (获取请求错误的原因)
     *
     * @param data
     * @return
     */
    // *************************************************************************
    public static String getResponseErrorReason(String data) {


        String errorReason = "返回数据为空";

        if (StringUtils.isEmpty(data))
            return errorReason;

        try {
            JSONObject json = new JSONObject(data);
            if (json != null) {
                int status = 200;
                if (json.has("code")) {
                    status = json.getInt("code");
                }
                errorReason = json.getString("message");
                if (status == 401) {
                    final Activity mContext = (Activity) HttpErrorUtils.mContext;
                    Toast.makeText(mContext, "登录已失效，请重新登录", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                    mContext.finish();
                } else {
                    Toast.makeText(mContext, errorReason, Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return errorReason;
    }
}
