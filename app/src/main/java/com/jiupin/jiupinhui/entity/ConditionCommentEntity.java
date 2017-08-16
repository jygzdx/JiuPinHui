package com.jiupin.jiupinhui.entity;

/**
 * 作者：czb on 2017/8/16 16:30
 */

public class ConditionCommentEntity {

    /**
     * id : 5
     * user_id : 32792
     * nickName : ba b
     * user_img : http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/appshop/user_logo_image/96076fd0-f7ff-4fbe-bebe-5e9ae64bdb6f.jpg
     * comment_time : 1500976434000
     * comment_content : 欢迎您
     */

    private int id;
    private int user_id;
    private String nickName;
    private String user_img;
    private long comment_time;
    private String comment_content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public long getComment_time() {
        return comment_time;
    }

    public void setComment_time(long comment_time) {
        this.comment_time = comment_time;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }
}
