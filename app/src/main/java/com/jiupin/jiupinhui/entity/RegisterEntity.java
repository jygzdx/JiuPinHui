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
        private UserBean user;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * id : 32857
             * addTime : 1498276078234
             * deleteStatus : false
             * userName : w-344357672909
             * trueName :
             * nickName :
             * userRole : BUYER
             * birthday :
             * telephone :
             * years : 0
             * address :
             * sex : 0
             * email :
             * mobile : 13560301401
             * imageUrl :
             * area :
             * status : 0
             * lastLoginDate : 1498276078412
             * loginDate :
             * lastLoginIp : 119.129.203.183
             * loginIp :
             * loginCount : 0
             * integral : 0
             * store_id :
             * qq :
             * msn :
             * ww :
             */

            private int id;
            private long addTime;
            private boolean deleteStatus;
            private String userName;
            private String trueName;
            private String nickName;
            private String userRole;
            private String birthday;
            private String telephone;
            private int years;
            private String address;
            private int sex;
            private String email;
            private String mobile;
            private String imageUrl;
            private String area;
            private int status;
            private long lastLoginDate;
            private String loginDate;
            private String lastLoginIp;
            private String loginIp;
            private int loginCount;
            private int integral;
            private String store_id;
            private String qq;
            private String msn;
            private String ww;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public boolean isDeleteStatus() {
                return deleteStatus;
            }

            public void setDeleteStatus(boolean deleteStatus) {
                this.deleteStatus = deleteStatus;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getTrueName() {
                return trueName;
            }

            public void setTrueName(String trueName) {
                this.trueName = trueName;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getUserRole() {
                return userRole;
            }

            public void setUserRole(String userRole) {
                this.userRole = userRole;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public int getYears() {
                return years;
            }

            public void setYears(int years) {
                this.years = years;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public long getLastLoginDate() {
                return lastLoginDate;
            }

            public void setLastLoginDate(long lastLoginDate) {
                this.lastLoginDate = lastLoginDate;
            }

            public String getLoginDate() {
                return loginDate;
            }

            public void setLoginDate(String loginDate) {
                this.loginDate = loginDate;
            }

            public String getLastLoginIp() {
                return lastLoginIp;
            }

            public void setLastLoginIp(String lastLoginIp) {
                this.lastLoginIp = lastLoginIp;
            }

            public String getLoginIp() {
                return loginIp;
            }

            public void setLoginIp(String loginIp) {
                this.loginIp = loginIp;
            }

            public int getLoginCount() {
                return loginCount;
            }

            public void setLoginCount(int loginCount) {
                this.loginCount = loginCount;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getQq() {
                return qq;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }

            public String getMsn() {
                return msn;
            }

            public void setMsn(String msn) {
                this.msn = msn;
            }

            public String getWw() {
                return ww;
            }

            public void setWw(String ww) {
                this.ww = ww;
            }
        }
    }
}
