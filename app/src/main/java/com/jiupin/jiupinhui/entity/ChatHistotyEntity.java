package com.jiupin.jiupinhui.entity;

/**
 * 作者：czb on 2017/8/2 15:01
 */

public class ChatHistotyEntity {

    /**
     * id : 24
     * create_time : 1495764507000
     * consulter_id : 32792
     * store_id : 32778
     * status : 0
     * close_time : 123456
     * title : SDFDSF
     * read_by_consulter : true
     * read_by_store : false
     * img:sdfsadfa
     */

    private int id;
    private long create_time;
    private int consulter_id;
    private int store_id;
    private int status;
    private long close_time;
    private String title;
    private boolean read_by_consulter;
    private boolean read_by_store;
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public int getConsulter_id() {
        return consulter_id;
    }

    public void setConsulter_id(int consulter_id) {
        this.consulter_id = consulter_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getClose_time() {
        return close_time;
    }

    public void setClose_time(long close_time) {
        this.close_time = close_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRead_by_consulter() {
        return read_by_consulter;
    }

    public void setRead_by_consulter(boolean read_by_consulter) {
        this.read_by_consulter = read_by_consulter;
    }

    public boolean isRead_by_store() {
        return read_by_store;
    }

    public void setRead_by_store(boolean read_by_store) {
        this.read_by_store = read_by_store;
    }

    @Override
    public String toString() {
        return "ChatHistotyEntity{" +
                "id=" + id +
                ", create_time=" + create_time +
                ", consulter_id=" + consulter_id +
                ", store_id=" + store_id +
                ", status=" + status +
                ", close_time=" + close_time +
                ", title='" + title + '\'' +
                ", read_by_consulter=" + read_by_consulter +
                ", read_by_store=" + read_by_store +
                ", img='" + img + '\'' +
                '}';
    }
}
