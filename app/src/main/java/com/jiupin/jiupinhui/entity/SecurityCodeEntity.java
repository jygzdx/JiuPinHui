package com.jiupin.jiupinhui.entity;

/**
 * 作者：czb on 2017/6/26 14:45
 */

public class SecurityCodeEntity {

    /**
     * status : 200
     * msg : OK
     * data : null
     */

    private int status;
    private String msg;
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
