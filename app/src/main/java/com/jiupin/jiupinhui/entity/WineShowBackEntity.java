package com.jiupin.jiupinhui.entity;

import java.util.List;

/**
 * 作者：czb on 2017/8/29 15:22
 */

public class WineShowBackEntity {

    /**
     * specification_item_list : [{"val":"浓香型","title":"香型"},{"val":"水、高粱、大米、小麦、糯米、玉米","title":"原料"},{"val":"中国;四川","title":"产地"},{"val":"519ml","title":"净含量"},{"val":"单瓶","title":"规格"},{"val":"1*6","title":"箱规"},{"val":"52%vol","title":"酒精度"},{"val":"常温","title":"储藏条件"},{"val":"四川绵竹剑南春酒厂有限公司","title":"品牌持有商家"}]
     * title : 基本参数
     */

    private String title;
    private List<SpecificationItemListBean> specification_item_list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SpecificationItemListBean> getSpecification_item_list() {
        return specification_item_list;
    }

    public void setSpecification_item_list(List<SpecificationItemListBean> specification_item_list) {
        this.specification_item_list = specification_item_list;
    }

    public static class SpecificationItemListBean {
        /**
         * val : 浓香型
         * title : 香型
         */

        private String val;
        private String title;

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
