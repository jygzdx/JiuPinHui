package com.jiupin.jiupinhui.entity;

/**
 * 作者：czb on 2017/6/26 16:56
 */

public class RegisterEntity {

    /**
     * status : 200
     * msg : OK
     * data : {"token":"129cc0eb-ce82-4081-8856-b593b81ed4ba","user":{"id":32857,"addTime":1498276078234,"deleteStatus":false,"userName":"w-344357672909","trueName":"","nickName":"","userRole":"BUYER","birthday":"","telephone":"","years":0,"address":"","sex":0,"email":"","mobile":"13560301401","imageUrl":"","area":"","status":0,"lastLoginDate":1498276078412,"loginDate":"","lastLoginIp":"119.129.203.183","loginIp":"","loginCount":0,"integral":0,"store_id":"","qq":"","msn":"","ww":""}}
     */

    private int status;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token : 129cc0eb-ce82-4081-8856-b593b81ed4ba
         * user : {"id":32857,"addTime":1498276078234,"deleteStatus":false,"userName":"w-344357672909","trueName":"","nickName":"","userRole":"BUYER","birthday":"","telephone":"","years":0,"address":"","sex":0,"email":"","mobile":"13560301401","imageUrl":"","area":"","status":0,"lastLoginDate":1498276078412,"loginDate":"","lastLoginIp":"119.129.203.183","loginIp":"","loginCount":0,"integral":0,"store_id":"","qq":"","msn":"","ww":""}
         */

        private String token;
        private UserEntity.DataBean user;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserEntity.DataBean getUser() {
            return user;
        }

        public void setUser(UserEntity.DataBean user) {
            this.user = user;
        }
    }
}
