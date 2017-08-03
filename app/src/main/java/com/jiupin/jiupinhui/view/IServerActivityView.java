package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.ChatHistotyEntity;

import java.util.List;

/**
 * 作者：czb on 2017/6/28 11:38
 */

public interface IServerActivityView {
    /**
     * 咨询历史数据
     * @param historyList 咨询信息
     */
    void setChatHistoryList(List<ChatHistotyEntity> historyList);
}
