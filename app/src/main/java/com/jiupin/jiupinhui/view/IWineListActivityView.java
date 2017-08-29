package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.WineInfoEntity;

import java.util.List;

/**
 * 作者：czb on 2017/6/28 11:38
 */

public interface IWineListActivityView {

    /**
     * 设置数据,通过品牌id
     * @param wineInfoList 美酒信息
     */
    void setWineInfoById(List<WineInfoEntity> wineInfoList);
}
