package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.WineBrandEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IWineFragmentView {

    /**
     * 设置品牌项数据
     * @param wineBrandList 美酒品牌
     */
    void setBrandData(List<WineBrandEntity> wineBrandList);

    /**
     * 设置美酒种类
     * @param wineKindList 美酒种类
     */
    void setWineKind(List<WineBrandEntity> wineKindList);

    /**
     * 设置美酒品牌种类
     * @param wineKindList 美酒种类
     */
    void setWineBrandKind(List<WineBrandEntity> wineKindList);

}
