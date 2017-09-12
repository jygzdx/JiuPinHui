package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.activity.BuyCartActivity;
import com.jiupin.jiupinhui.entity.CartEntity;
import com.jiupin.jiupinhui.entity.GoodsBack;
import com.jiupin.jiupinhui.utils.ArithUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/27.
 */

public class BuyCartAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private Context mContext;
    private List<CartEntity> carts;
    public boolean isWholeCompile;

    public BuyCartAdapter(Context context) {
        this.mContext = context;
        carts = new ArrayList<>();
        inflater = LayoutInflater.from(mContext);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        View view = inflater.inflate(R.layout.buy_cart_item, viewGroup, false);
        RecyclerView.ViewHolder holder = new BuyCartViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final BuyCartViewHolder cartViewHolder = (BuyCartViewHolder) holder;
        final CartEntity cart = carts.get(position);

        cartViewHolder.tvStoreName.setText(cart.getStoreName());
        cartViewHolder.tvGoodsName.setText(cart.getGoods_name());
        cartViewHolder.tvGoodsDownPrice.setText(cart.getStore_price() + "");
        cartViewHolder.tvGoodsDealPrice.setText(cart.getGoods_price() + "");
        cartViewHolder.tvGoodsDealPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        cartViewHolder.tvGoodsStartNumber.setText("x"+cart.getCount());
        cartViewHolder.tvGoodsNumber.setText(cart.getCount() + "");
        Glide.with(mContext)
                .load(cart.getStoreLogo())
                .crossFade()
                .into(cartViewHolder.ivLogo);
        Glide.with(mContext)
                .load(cart.getPath())
                .crossFade()
                .centerCrop()
                .into(cartViewHolder.ivGoodsPic);


        if (cart.isCompile()) {//编辑状态
            cartViewHolder.rlFullInfo.setVisibility(View.GONE);
            cartViewHolder.llCompileState.setVisibility(View.VISIBLE);
            cartViewHolder.btnItemCompile.setText("完成");
        } else {//展示状态
            cartViewHolder.rlFullInfo.setVisibility(View.VISIBLE);
            cartViewHolder.llCompileState.setVisibility(View.GONE);
            cartViewHolder.btnItemCompile.setText("编辑");
        }

        cartViewHolder.ivStoreRadio.setOnClickListener(new View.OnClickListener() {//选择同个店铺里面的所有商品
            @Override
            public void onClick(View v) {
                if (!isWholeCompile) {
                    cart.setSelected(!cart.isSelected());
                    notifyItemChanged(position);
                    ((BuyCartActivity) mContext).setPayMoneyText();
                    ((BuyCartActivity) mContext).setSubmitText();
                }
            }
        });
        cartViewHolder.ivGoodsRadio.setOnClickListener(new View.OnClickListener() {//选择商品
            @Override
            public void onClick(View v) {
                if (!isWholeCompile) {
                    cart.setSelected(!cart.isSelected());
                    notifyItemChanged(position);
                    ((BuyCartActivity) mContext).setPayMoneyText();
                    ((BuyCartActivity) mContext).setSubmitText();
                }
            }
        });

        cartViewHolder.btnItemCompile.setOnClickListener(new View.OnClickListener() {//编辑数据
            @Override
            public void onClick(View v) {
                cart.setCompile(!cart.isCompile());
                notifyItemChanged(position);
                ((BuyCartActivity) mContext).setPayMoneyText();
                ((BuyCartActivity) mContext).setSubmitText();
            }
        });
        cartViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {//删除item
            @Override
            public void onClick(View v) {
                showDiolog(position);

            }
        });
        cartViewHolder.ivAdd.setOnClickListener(new View.OnClickListener() {//添加数量
            @Override
            public void onClick(View v) {
                ((BuyCartActivity) mContext).notifyGoodsCount(carts.get(position).getId() + "",
                        carts.get(position).getSpecIdsString(),cart.getCount()+1,position);
            }
        });
        cartViewHolder.ivSubtract.setOnClickListener(new View.OnClickListener() {//减少数量
            @Override
            public void onClick(View v) {
                if (cart.getCount() > 1) {
                    ((BuyCartActivity) mContext).notifyGoodsCount(carts.get(position).getId() + "",
                            carts.get(position).getSpecIdsString(),cart.getCount()-1,position);
                }
            }
        });


        if (cart.isSelected()) {//选择状态
            cartViewHolder.ivStoreRadio.setImageResource(R.drawable.radiobotton_checked);
            cartViewHolder.ivGoodsRadio.setImageResource(R.drawable.radiobotton_checked);
        } else {
            cartViewHolder.ivStoreRadio.setImageResource(R.drawable.radiobotton_unchecked);
            cartViewHolder.ivGoodsRadio.setImageResource(R.drawable.radiobotton_unchecked);
        }

    }


    @Override
    public int getItemCount() {
        return carts.size();
    }

    public void setData(List<CartEntity> carts) {
        this.carts = carts;
        notifyDataSetChanged();
    }

    public void addAll(List<CartEntity> carts) {
        int lastIndex = this.carts.size();
        if (this.carts.addAll(carts)) {
            notifyItemRangeInserted(lastIndex, carts.size());
        }
    }


    public void clear() {
        carts.clear();
        notifyDataSetChanged();
    }

    public List<GoodsBack> getGoodsList(){
        List<GoodsBack> list = new ArrayList<>();
        for (int i = 0; i < carts.size(); i++) {
            if(carts.get(i).isSelected()){
                GoodsBack goods = new GoodsBack();
                goods.setId(carts.get(i).getId());
                goods.setSpec_id(carts.get(i).getSpecIdsString());
                goods.setCount(carts.get(i).getCount());
                list.add(goods);
            }
        }
        return list;
    }

    /**
     * 刷新商品数量
     * @param position
     * @param count
     */
    public void notifyGoodsCount(int position ,int count){
        carts.get(position).setCount(count);
        notifyDataSetChanged();
    }

    /**
     * 删除选中商品
     *
     * @param position
     */
    public void remove(int position) {
        carts.remove(position);
        notifyItemRemoved(position);
        if (position != carts.size()) {
            notifyItemRangeChanged(position, carts.size() - position);
        }

    }

    /**
     * 编辑所有数据
     */
    public void compileAllData() {
        for (int i = 0; i < carts.size(); i++) {
            carts.get(i).setCompile(true);
        }
        notifyDataSetChanged();
    }

    /**
     * 不编辑所有数据
     */
    public void successAllData() {
        for (int i = 0; i < carts.size(); i++) {
            carts.get(i).setCompile(false);
        }
        notifyDataSetChanged();
    }

    /**
     * 全选所有item
     */
    public void selecteAllData() {
        for (int i = 0; i < carts.size(); i++) {
            carts.get(i).setSelected(true);
        }
        notifyDataSetChanged();
    }

    /**
     * 不全选所有item
     */
    public void unselecteAllData() {
        for (int i = 0; i < carts.size(); i++) {
            carts.get(i).setSelected(false);
        }
        notifyDataSetChanged();
    }

    /**
     * 获取选中的个数
     *
     * @return
     */
    public int getSelectedSize() {
        int size = 0;
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).isSelected()) {
                size++;
            }
        }
        return size;
    }

    /**
     * 获取选中的商品的价格
     */
    public double getChooseGoodsMoney() {
        double money = 0.00d;
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).isSelected()) {
                money = ArithUtils.add(money, carts.get(i).getStore_price()*carts.get(i).getCount());
            }
        }
        return money;
    }

    public List<CartEntity> getSelectedGoods(){
        List<CartEntity> list = new ArrayList<>();
        for (int i = 0; i < carts.size(); i++) {
            if(carts.get(i).isSelected()){
                list.add(carts.get(i));
            }
        }
        return list;
    }

    /**
     * 移除所有的数据
     */
    public void removeAllItem() {
        carts.clear();
        notifyDataSetChanged();
    }

    /**
     * 删除商品dialog
     *
     * @param position
     */
    private void showDiolog(final int position) {

        View view = inflater.inflate(R.layout.dialog_content, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        builder.setView(view);
        final AlertDialog dialog = builder.create();
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tvEnsure = (TextView) view.findViewById(R.id.tv_ensure);
        tvContent.setText("确认要删除该商品吗？");
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消
                dialog.dismiss();
            }
        });
        tvEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除商品
                ((BuyCartActivity) mContext).setTitleText();
                ((BuyCartActivity) mContext).deleteItem(position,carts.get(position).getId() + "", carts.get(position).getSpecIdsString());
                //确定
                dialog.dismiss();
            }

        });

        dialog.show();
    }

    class BuyCartViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_logo)
        ImageView ivLogo;
        @BindView(R.id.tv_store_name)
        TextView tvStoreName;
        @BindView(R.id.btn_item_compile)
        Button btnItemCompile;
        @BindView(R.id.iv_goods_radio)
        ImageView ivGoodsRadio;
        @BindView(R.id.iv_store_radio)
        ImageView ivStoreRadio;
        @BindView(R.id.iv_goods_pic)
        ImageView ivGoodsPic;
        @BindView(R.id.tv_goods_name)
        TextView tvGoodsName;
        @BindView(R.id.tv_goods_down_price)
        TextView tvGoodsDownPrice;
        @BindView(R.id.tv_goods_deal_price)
        TextView tvGoodsDealPrice;
        @BindView(R.id.tv_goods_start_number)
        TextView tvGoodsStartNumber;
        @BindView(R.id.rl_full_info)
        RelativeLayout rlFullInfo;
        @BindView(R.id.iv_subtract)
        ImageView ivSubtract;
        @BindView(R.id.tv_goods_number)
        TextView tvGoodsNumber;
        @BindView(R.id.iv_add)
        ImageView ivAdd;
        @BindView(R.id.btn_delete)
        Button btnDelete;
        @BindView(R.id.ll_compile_state)
        LinearLayout llCompileState;

        public BuyCartViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
