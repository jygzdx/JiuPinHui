package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.Wine;
import com.jiupin.jiupinhui.entity.WineBrand;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IWineFragmentView {
    /**
     * 设置数据
     */
    void setData(Wine wine);

    /**
     * 设置adapter
     */
    void SetAdapter();
    /**
     * 设置品牌项数据
     */
    void setBrandData(WineBrand wineBrand);

    /**
     * 设置品牌项adapter
     */
    void SetBrandAdapter();

}
