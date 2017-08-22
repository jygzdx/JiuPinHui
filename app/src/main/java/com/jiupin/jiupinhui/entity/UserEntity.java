package com.jiupin.jiupinhui.entity;

import java.io.Serializable;

/**
 * 作者：czb on 2017/7/5 14:14
 */

public class UserEntity implements Serializable{


    /**
     * status : 200
     * msg : OK
     * data : {"id":32792,"addTime":"","deleteStatus":false,"userName":"Rare","trueName":"","nickName":"","userRole":"","birthday":"","telephone":"","years":0,"address":"","sex":0,"email":"","mobile":"15627236863","imageUrl":"","area":"","status":0,"lastLoginDate":"","loginDate":"","lastLoginIp":"","loginIp":"","loginCount":0,"integral":0,"store_id":"","qq":"","msn":"","ww":""}
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

    public static class DataBean implements Serializable{
        /**
         * id : 32792
         * addTime :
         * deleteStatus : false
         * userName : Rare
         * trueName :
         * nickName :
         * userRole :
         * birthday :
         * telephone :
         * years : 0
         * address :
         * sex : 0
         * email :
         * mobile : 15627236863
         * imageUrl :
         * area :
         * status : 0
         * lastLoginDate :
         * loginDate :
         * lastLoginIp :
         * loginIp :
         * loginCount : 0
         * integral : 0
         * store_id :
         * qq :
         * msn :
         * ww :
         */

        /**后面添加的参数
         * fans_num : 0
         * concern_num : 0
         * signature :
         * intro :
         * education :
         * location :
         * level : 1
         * openid : oZ-s1t4WNBJW3aq59bna-eKZPdWA
         * unionId : oPYvKwuvyfQx7ZDrME-XVURbJBS8
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
        private String lastLoginDate;
        private String loginDate;
        private String lastLoginIp;
        private String loginIp;
        private int loginCount;
        private int integral;
        private String store_id;
        private String qq;
        private String msn;
        private String ww;


        private int fans_num;
        private int concern_num;
        private String signature;
        private String intro;
        private String education;
        private String location;
        private int level;
        private String openid;
        private String unionId;


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

        public String getLastLoginDate() {
            return lastLoginDate;
        }

        public void setLastLoginDate(String lastLoginDate) {
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

        public int getFans_num() {
            return fans_num;
        }

        public void setFans_num(int fans_num) {
            this.fans_num = fans_num;
        }

        public int getConcern_num() {
            return concern_num;
        }

        public void setConcern_num(int concern_num) {
            this.concern_num = concern_num;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getUnionId() {
            return unionId;
        }

        public void setUnionId(String unionId) {
            this.unionId = unionId;
        }
    }
}
