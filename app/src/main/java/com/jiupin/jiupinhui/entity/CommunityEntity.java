package com.jiupin.jiupinhui.entity;

import java.io.Serializable;

/**
 * 作者：czb on 2017/8/8 18:02
 */

public class CommunityEntity implements Serializable{


    /**
     * id : 30
     * user_id : 32815
     * nickName : adsas
     * user_img : http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/appshop/user_logo_image/51fb13c4-a969-4199-9fc3-1a796b2ea3ab.jpg
     * addTime : 1501147838000
     * content : 哈喽酷狗
     * dynamic_type : 0
     * image_list : http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/appshop/social_dynamic_img/e9711e5a-0939-453c-9134-1136cc0e19df.jpg
     * thumb_status : true
     * trans_id : 1131
     * trans_user_id : 2131
     * trans_nickName :
     * trans_user_img :
     * trans_content :
     * trans_img_list :
     * trans_dynamic_type : 1
     * is_trans : true
     * concern_status : true 关注状态
     * comment_count
     */

    private int id;
    private int user_id;
    private String nickName;
    private String user_img;
    private long addTime;
    private String content;
    private int dynamic_type;
    private String image_list;
    private boolean thumb_status;
    private int trans_id;
    private int trans_user_id;
    private String trans_nickName;
    private String trans_user_img;
    private String trans_content;
    private String trans_img_list;
    private int trans_dynamic_type;
    private boolean is_trans;
    private boolean concern_status;
    private boolean is_visible;
    private int comment_count;

    @Override
    public String toString() {
        return "CommunityEntity{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", nickName='" + nickName + '\'' +
                ", user_img='" + user_img + '\'' +
                ", addTime=" + addTime +
                ", content='" + content + '\'' +
                ", dynamic_type=" + dynamic_type +
                ", image_list='" + image_list + '\'' +
                ", thumb_status=" + thumb_status +
                ", trans_id=" + trans_id +
                ", trans_user_id=" + trans_user_id +
                ", trans_nickName='" + trans_nickName + '\'' +
                ", trans_user_img='" + trans_user_img + '\'' +
                ", trans_content='" + trans_content + '\'' +
                ", trans_img_list='" + trans_img_list + '\'' +
                ", trans_dynamic_type=" + trans_dynamic_type +
                ", is_trans=" + is_trans +
                ", concern_status=" + concern_status +
                '}';
    }

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

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDynamic_type() {
        return dynamic_type;
    }

    public void setDynamic_type(int dynamic_type) {
        this.dynamic_type = dynamic_type;
    }

    public String getImage_list() {
        return image_list;
    }

    public void setImage_list(String image_list) {
        this.image_list = image_list;
    }

    public boolean isThumb_status() {
        return thumb_status;
    }

    public void setThumb_status(boolean thumb_status) {
        this.thumb_status = thumb_status;
    }

    public int getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(int trans_id) {
        this.trans_id = trans_id;
    }

    public int getTrans_user_id() {
        return trans_user_id;
    }

    public void setTrans_user_id(int trans_user_id) {
        this.trans_user_id = trans_user_id;
    }

    public String getTrans_nickName() {
        return trans_nickName;
    }

    public void setTrans_nickName(String trans_nickName) {
        this.trans_nickName = trans_nickName;
    }

    public String getTrans_user_img() {
        return trans_user_img;
    }

    public void setTrans_user_img(String trans_user_img) {
        this.trans_user_img = trans_user_img;
    }

    public String getTrans_content() {
        return trans_content;
    }

    public void setTrans_content(String trans_content) {
        this.trans_content = trans_content;
    }

    public String getTrans_img_list() {
        return trans_img_list;
    }

    public void setTrans_img_list(String trans_img_list) {
        this.trans_img_list = trans_img_list;
    }

    public int getTrans_dynamic_type() {
        return trans_dynamic_type;
    }

    public void setTrans_dynamic_type(int trans_dynamic_type) {
        this.trans_dynamic_type = trans_dynamic_type;
    }

    public boolean isIs_trans() {
        return is_trans;
    }

    public void setIs_trans(boolean is_trans) {
        this.is_trans = is_trans;
    }

    public boolean isConcern_status() {
        return concern_status;
    }

    public void setConcern_status(boolean concern_status) {
        this.concern_status = concern_status;
    }

    public boolean is_visible() {
        return is_visible;
    }

    public void setIs_visible(boolean is_visible) {
        this.is_visible = is_visible;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }
}
