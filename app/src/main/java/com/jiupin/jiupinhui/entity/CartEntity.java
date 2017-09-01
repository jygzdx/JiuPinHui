package com.jiupin.jiupinhui.entity;

import java.util.List;

/**
 * 作者：czb on 2017/8/31 15:18
 */

public class CartEntity {
    /**
     * id : 6453
     * is_meal : 1
     * goods_name : 烈酒|白酒大师套餐
     * goods_price : 999
     * store_price : 999
     * path : http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170511/1f28809e-776a-45d7-9bde-4e0e1d2998d6.png
     * specArray : [{"spec_info":"月度会员（3次）","spec_id":"32775"},{"spec_info":"男神专属","spec_id":"32779"}]
     * count : 2
     * specIdsString : 32775_32779_
     */

    private int id;
    private int is_meal;
    private String goods_name;
    private double goods_price;
    private double store_price;
    private String path;
    private int count;
    private String specIdsString;
    private List<SpecEntity> specArray;
    private int storeId;
    private String storeName;
    private String storeLogo;
    private boolean isCompile;
    private boolean isSelected;

    public boolean isCompile() {
        return isCompile;
    }

    public void setCompile(boolean compile) {
        isCompile = compile;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIs_meal() {
        return is_meal;
    }

    public void setIs_meal(int is_meal) {
        this.is_meal = is_meal;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public double getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(double goods_price) {
        this.goods_price = goods_price;
    }

    public double getStore_price() {
        return store_price;
    }

    public void setStore_price(double store_price) {
        this.store_price = store_price;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSpecIdsString() {
        return specIdsString;
    }

    public void setSpecIdsString(String specIdsString) {
        this.specIdsString = specIdsString;
    }

    public List<SpecEntity> getSpecArray() {
        return specArray;
    }

    public void setSpecArray(List<SpecEntity> specArray) {
        this.specArray = specArray;
    }

}
