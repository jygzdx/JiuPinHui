package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.AllCartEntity;
import com.jiupin.jiupinhui.entity.CartEntity;

import java.util.List;

/**
 * 作者：czb on 2017/6/26 17:03
 */
public interface IBuyCartActivityView {

    void buyCartList(List<CartEntity> cartList);

    void deleteGoods(int position);

    void emptyCartSuccess(String msg);

    void notifyGoodsCountSuccess(int position,int count);

    void submitGoodsSuccess(List<AllCartEntity.OnSaleBean> goodsList);
}
