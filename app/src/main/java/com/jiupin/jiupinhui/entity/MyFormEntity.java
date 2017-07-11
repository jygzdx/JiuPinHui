package com.jiupin.jiupinhui.entity;

/**
 * 作者：czb on 2017/7/7 10:40
 */

public class MyFormEntity {
    @Override
    public String toString() {
        return "MyFormEntity{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * status : 200
     * msg : OK
     * data : {"waitDelivery":0,"waitComment":0,"afterSale":0,"waitPickup":0,"unpay":3}
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
         * waitDelivery : 0
         * waitComment : 0
         * afterSale : 0
         * waitPickup : 0
         * unpay : 3
         */

        private int waitDelivery;
        private int waitComment;
        private int afterSale;
        private int waitPickup;
        private int unpay;

        public int getWaitDelivery() {
            return waitDelivery;
        }

        public void setWaitDelivery(int waitDelivery) {
            this.waitDelivery = waitDelivery;
        }

        public int getWaitComment() {
            return waitComment;
        }

        public void setWaitComment(int waitComment) {
            this.waitComment = waitComment;
        }

        public int getAfterSale() {
            return afterSale;
        }

        public void setAfterSale(int afterSale) {
            this.afterSale = afterSale;
        }

        public int getWaitPickup() {
            return waitPickup;
        }

        public void setWaitPickup(int waitPickup) {
            this.waitPickup = waitPickup;
        }

        public int getUnpay() {
            return unpay;
        }

        public void setUnpay(int unpay) {
            this.unpay = unpay;
        }
    }
}
