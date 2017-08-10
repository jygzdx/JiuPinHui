package com.jiupin.jiupinhui.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 作者：czb on 2017/8/9 15:36
 */

public class WeChatPayEntity {
    /**
     * appid : wx420947e7a04ec1df
     * noncestr : v7bvyIB3ebVcCl8R
     * package : Sign=WXPay
     * partnerid : 1464526702
     * prepayid : wx2017081011194028cec524570551927397
     * sign : 66304BFE38062F4F7BFB3DAA442BEADF
     * timestamp : 1502335180
     */

    private String appid;
    private String noncestr;
    @SerializedName("package")
    private String packageX;
    private String partnerid;
    private String prepayid;
    private String sign;
    private String timestamp;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
