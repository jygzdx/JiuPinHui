package com.jiupin.jiupinhui.entity;

import java.util.List;

/**
 * 作者：czb on 2017/8/28 15:36
 */

public class WineBrandKindEntity {

    /**
     * list : [{"id":25,"name":"板城","cover_img":"","first_word":"B"},{"id":24,"name":"扳倒井","cover_img":"","first_word":"B"},{"id":26,"name":"宝丰酒","cover_img":"","first_word":"B"},{"id":54,"name":"白金酒","cover_img":"","first_word":"B"},{"id":6,"name":"白云边","cover_img":"","first_word":"B"}]
     * head_word : B
     */

    private String head_word;
    private List<WineBrandEntity> list;

    public String getHead_word() {
        return head_word;
    }

    public void setHead_word(String head_word) {
        this.head_word = head_word;
    }

    public List<WineBrandEntity> getList() {
        return list;
    }

    public void setList(List<WineBrandEntity> list) {
        this.list = list;
    }

}
