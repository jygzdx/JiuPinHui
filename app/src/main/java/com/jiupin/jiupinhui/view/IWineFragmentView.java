package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.WineBrandEntity;
import com.jiupin.jiupinhui.entity.WineInfoEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IWineFragmentView {
    /**
     * 设置数据
     * @param wineInfoList 美酒信息
     */
    void setWineInfo(List<WineInfoEntity> wineInfoList);

    /**
     * 设置数据,通过品牌id
     * @param wineInfoList 美酒信息
     */
    void setWineInfoById(List<WineInfoEntity> wineInfoList);

    /**
     * 设置品牌项数据
     * @param wineBrandList 美酒品牌
     */
    void setBrandData(List<WineBrandEntity> wineBrandList);

}
