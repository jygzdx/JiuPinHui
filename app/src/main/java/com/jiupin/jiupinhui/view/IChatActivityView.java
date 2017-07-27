package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.ChatEntity;

import java.util.List;

/**
 * 作者：czb on 2017/6/28 11:38
 */

public interface IChatActivityView {
    /**
     * 设置聊天信息
     *
     * @param chatList 聊天信息
     * @param hint     提示信息
     */
    void setChatList(List<ChatEntity> chatList, String hint);
}
