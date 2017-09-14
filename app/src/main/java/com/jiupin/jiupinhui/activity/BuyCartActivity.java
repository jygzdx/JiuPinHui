package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.BuyCartAdapter;
import com.jiupin.jiupinhui.entity.AllCartEntity;
import com.jiupin.jiupinhui.entity.CartEntity;
import com.jiupin.jiupinhui.manage.PopWinManager;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.IBuyCartActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.BuyCartActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.ActivityUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IBuyCartActivityView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BuyCartActivity extends BaseActivity implements IBuyCartActivityView {

    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_compile)
    TextView tvCompile;
    @BindView(R.id.rv_recyclerview)
    RecyclerView rvRecyclerview;
    @BindView(R.id.iv_check)
    ImageView ivCheck;
    @BindView(R.id.iv_no_data)
    ImageView ivNoData;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.ll_pay)
    LinearLayout llPay;
    @BindView(R.id.ll_delete)
    LinearLayout llDelete;
    @BindView(R.id.ll_header)
    LinearLayout llHeader;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    /**
     * 编辑购物车
     */
    private boolean isCompile;
    /**
     * 全选
     */
    private boolean isSelected;
    private BuyCartAdapter adapter;
    private IBuyCartActivityPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_cart);
        ButterKnife.bind(this);

        presenter = new BuyCartActivityPresenterImpl(this);
        String token = UserInfoManager.getInstance().getToken(this);
        presenter.getBuyCartList(token);

        initRv();


    }

    private void initRv() {
        RecyclerView.LayoutManager layout = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvRecyclerview.setLayoutManager(layout);

        adapter = new BuyCartAdapter(mContext);
        rvRecyclerview.setAdapter(adapter);
    }


    @OnClick({R.id.iv_back, R.id.tv_compile, R.id.iv_more, R.id.iv_check, R.id.tv_pay, R.id.tv_delete,
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_compile:
                if(adapter.getItemCount()==0){
                    return;
                }
                if (isCompile) {//完成状态
                    isCompile = false;
                    tvCompile.setText("编辑");
                    llPay.setVisibility(View.VISIBLE);
                    llDelete.setVisibility(View.GONE);
                    ivCheck.setClickable(true);
                    ivCheck.setImageResource(R.drawable.radiobotton_unchecked);
                    adapter.unselecteAllData();
                    adapter.successAllData();
                    tvMoney.setText("￥0.00");
                    adapter.isWholeCompile = isCompile;
                } else {//编辑状态
                    isCompile = true;
                    tvCompile.setText("完成");
                    llPay.setVisibility(View.GONE);
                    llDelete.setVisibility(View.VISIBLE);
                    ivCheck.setClickable(false);
                    ivCheck.setImageResource(R.drawable.radiobotton_checked);
                    adapter.selecteAllData();
                    adapter.compileAllData();
                    adapter.isWholeCompile = isCompile;
                }
                break;
            case R.id.iv_more:
                PopWinManager popupWindow = new PopWinManager(this, (View) view.getParent());
                popupWindow.createPopupWindow();
                break;
            case R.id.iv_check:
                if (isSelected) {
                    isSelected = false;
                    ivCheck.setImageResource(R.drawable.radiobotton_unchecked);
                    adapter.unselecteAllData();
                    tvMoney.setText("￥0.00");
                } else {
                    isSelected = true;
                    ivCheck.setImageResource(R.drawable.radiobotton_checked);
                    adapter.selecteAllData();
                    tvMoney.setText("￥" + adapter.getChooseGoodsMoney());
                }
                break;
            case R.id.tv_pay:

                if (adapter.getSelectedSize()<=0){
                    ToastUtils.showShort(mContext,"您还没有选择商品");
                    return;
                }
                Intent intent = new Intent(BuyCartActivity.this,OrderCopyActivity.class);
                intent.putExtra("goods",(Serializable) adapter.getSelectedGoods());
                startActivity(intent);
                break;
            case R.id.tv_delete:
                //删除全部订单
                showDiolog();
                break;
        }
    }

    public void setPayMoneyText() {
        tvMoney.setText("￥" + adapter.getChooseGoodsMoney());
    }

    public void setTitleText() {
        tvTitleName.setText("购物车 (" + adapter.getItemCount() + ")");
    }

    public void setSubmitText() {
        tvPay.setText("结算 (" + adapter.getSelectedSize() + ")");
    }

    /**
     * 删除item
     *
     * @param id      商品ID
     * @param spec_id 规格ID
     */
    public void deleteItem(int position, String id, String spec_id) {
        String token = UserInfoManager.getInstance().getToken(mContext);
        presenter.deleteGoods(token, id, spec_id, position);
    }

    /**
     * 修改商品的数量
     *
     * @param id      商品ID
     * @param spec_id 规格ID
     * @param count   商品数量
     */
    public void notifyGoodsCount(String id, String spec_id, int count,int position) {
        String token = UserInfoManager.getInstance().getToken(mContext);
        presenter.notifyGoodsCount(token,id,spec_id,count,position);
    }

    /**
     * 删除商品dialog
     */
    private void showDiolog() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_content, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tvEnsure = (TextView) view.findViewById(R.id.tv_ensure);
        tvContent.setText("确认要删除这些商品吗？");

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
                String token = UserInfoManager.getInstance().getToken(mContext);
                presenter.emptyCart(token);
                //确定
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void buyCartList(List<CartEntity> cartList) {
        if (ActivityUtils.isFinish(this))
            return;
        if (cartList.size() == 0) {//购物车没有商品
            ToastUtils.showShort(mContext, "您的购物车空空如也");
            ivNoData.setVisibility(View.VISIBLE);
            //头部展示商店的logo
            llHeader.setVisibility(View.GONE);
            llBottom.setVisibility(View.GONE);
            return;
        }
        adapter.setData(cartList);
        setTitleText();
        Glide.with(mContext)
                .load(cartList.get(0).getStoreLogo())
                .crossFade()
                .into(ivLogo);
    }

    @Override
    public void deleteGoods(int position) {
        if (ActivityUtils.isFinish(this))
            return;
        adapter.remove(position);
        //刷新顶部购物车
        setTitleText();
        //刷新底部结算按钮
        if(adapter.getItemCount()==0){
            tvPay.setText("结算 (0)");
            ivNoData.setVisibility(View.VISIBLE);
            llHeader.setVisibility(View.GONE);
            llBottom.setVisibility(View.GONE);
        }else{
            setSubmitText();
        }
    }

    @Override
    public void emptyCartSuccess(String msg) {
        if (ActivityUtils.isFinish(this))
            return;
        adapter.removeAllItem();
        //刷新顶部购物车
        setTitleText();
        //刷新底部结算按钮
        if(adapter.getItemCount()==0){
            tvPay.setText("结算 (0)");
            ivNoData.setVisibility(View.VISIBLE);
            llHeader.setVisibility(View.GONE);
            llBottom.setVisibility(View.GONE);
        }else{
            setSubmitText();
        }
        //改为完成状态
        isCompile = false;
        tvCompile.setText("编辑");
        llPay.setVisibility(View.VISIBLE);
        llDelete.setVisibility(View.GONE);
        ivCheck.setClickable(true);
        ivCheck.setImageResource(R.drawable.radiobotton_unchecked);
        tvMoney.setText("￥0.00");
        adapter.isWholeCompile = isCompile;
    }

    @Override
    public void notifyGoodsCountSuccess(int position, int count) {
        adapter.notifyGoodsCount(position,count);
    }

    @Override
    public void submitGoodsSuccess(List<AllCartEntity.OnSaleBean> goodsList) {
        LogUtils.d("提交成功");
    }
}
