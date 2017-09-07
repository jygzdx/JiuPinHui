package com.jiupin.jiupinhui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.AddressEntity;
import com.jiupin.jiupinhui.entity.CartEntity;
import com.jiupin.jiupinhui.entity.GoodsBack;
import com.jiupin.jiupinhui.entity.OrderCopyEntity;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.IOrderCopyActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.OrderCopyActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.ActivityUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.StringUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IOrderCopyActivityView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderCopyActivity extends BaseActivity implements IOrderCopyActivityView {

    @BindView(R.id.tv_add_address)
    TextView tvAddAddress;
    @BindView(R.id.tv_consignee_name)
    TextView tvConsigneeName;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ll_address)
    LinearLayout llAddress;
    @BindView(R.id.ll_order_container)
    LinearLayout llOrderContainer;
    @BindView(R.id.tv_order_money)
    TextView tvOrderMoney;

    private static final int REQUEST_ADDRESS_CODE = 101;
    private IOrderCopyActivityPresenter presenter;
    private String addressId = "";
    private List<CartEntity> goodsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_copy);
        ButterKnife.bind(this);
        

        Bundle bundle = getIntent().getExtras();
        goodsList = (List<CartEntity>) bundle.getSerializable("goods");

        presenter = new OrderCopyActivityPresenterImpl(this);
        String token = UserInfoManager.getInstance().getToken(mContext);
        presenter.getDefaultAddress(token);

        initData();
        
    }

    private void initData() {
        //记录单品的数量
        int singleCount = 0;
        //记录单品的价格
        double singlePrice = 0.0;
        //记录套餐的数量
        int mealCount = 0;
        //记录套餐的价格
        double mealPrice = 0.0;
        for (int i = 0; i < goodsList.size(); i++) {
            if (i== 0){
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_order_header,(ViewGroup) tvOrderMoney.getParent(),false);
                ImageView ivLogo = (ImageView) view.findViewById(R.id.iv_logo);
                TextView tvName = (TextView) view.findViewById(R.id.tv_store_name);
                Glide.with(mContext)
                        .load(goodsList.get(i).getStoreLogo())
                        .crossFade()
                        .into(ivLogo);
                tvName.setText(goodsList.get(i).getStoreName());
                llOrderContainer.addView(view);
            }
            if(goodsList.get(i).getIs_meal()==0){//单品
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_order_middle, (ViewGroup) tvOrderMoney.getParent(),false);
                TextView tvGoodsName = (TextView) view.findViewById(R.id.tv_goods_name);
                TextView tvGoodsPrice = (TextView) view.findViewById(R.id.tv_goods_price);
                TextView tvGoodsNumber = (TextView) view.findViewById(R.id.tv_goods_number);
                ImageView ivGoodsPic = (ImageView) view.findViewById(R.id.iv_goods_pic);
                CartEntity cartEntity = goodsList.get(i);
                tvGoodsName.setText(cartEntity.getGoods_name());
                tvGoodsPrice.setText("￥" + cartEntity.getStore_price());
                tvGoodsNumber.setText("x" + cartEntity.getCount());
                Glide.with(this)
                        .load(cartEntity.getPath())
                        .crossFade()
                        .into(ivGoodsPic);
                singleCount = singleCount+cartEntity.getCount();
                singlePrice = singlePrice + cartEntity.getCount()*cartEntity.getStore_price();
                llOrderContainer.addView(view);
            }
           if(singleCount!=0&&i == goodsList.size()-1){
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_order_end,(ViewGroup) tvOrderMoney.getParent(),false);
                TextView tvNum = (TextView)view.findViewById(R.id.tv_total_number);
               TextView tvPrice = (TextView)view.findViewById(R.id.tv_total_price);
               tvNum.setText(singleCount+"");
               tvPrice.setText(singlePrice+"");
               llOrderContainer.addView(view);
            }
        }
        for (int i = 0; i < goodsList.size(); i++) {
            if(goodsList.get(i).getIs_meal()==1){//单品
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_order_middle, (ViewGroup) tvOrderMoney.getParent(),false);
                TextView tvGoodsName = (TextView) view.findViewById(R.id.tv_goods_name);
                TextView tvGoodsPrice = (TextView) view.findViewById(R.id.tv_goods_price);
                TextView tvGoodsNumber = (TextView) view.findViewById(R.id.tv_goods_number);
                ImageView ivGoodsPic = (ImageView) view.findViewById(R.id.iv_goods_pic);
                CartEntity cartEntity = goodsList.get(i);
                tvGoodsName.setText(cartEntity.getGoods_name());
                tvGoodsPrice.setText("￥" + cartEntity.getStore_price());
                tvGoodsNumber.setText("x" + cartEntity.getCount());
                Glide.with(this)
                        .load(cartEntity.getPath())
                        .crossFade()
                        .into(ivGoodsPic);
                mealCount = mealCount+cartEntity.getCount();
                mealPrice = mealPrice + cartEntity.getCount()*cartEntity.getStore_price();
                llOrderContainer.addView(view);
            }
            if(mealCount!=0&&i == goodsList.size()-1){
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_order_end,(ViewGroup) tvOrderMoney.getParent(),false);
                TextView tvNum = (TextView)view.findViewById(R.id.tv_total_number);
                TextView tvPrice = (TextView)view.findViewById(R.id.tv_total_price);
                tvNum.setText(mealCount+"");
                tvPrice.setText(mealPrice+"");
                llOrderContainer.addView(view);
            }
        }

        tvOrderMoney.setText(singlePrice+mealPrice+"");

    }

    @OnClick({R.id.iv_back, R.id.tv_add_address, R.id.ll_address, R.id.tv_submit_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add_address://修改地址,没有默认地址时
                Intent intent1 = new Intent(OrderCopyActivity.this, ManageAddressActivity.class);
                intent1.putExtra("fromActivity", "OrderActivity");
                startActivityForResult(intent1, REQUEST_ADDRESS_CODE);
                break;
            case R.id.ll_address://修改地址
                Intent intent = new Intent(OrderCopyActivity.this, ManageAddressActivity.class);
                intent.putExtra("fromActivity", "OrderActivity");
                startActivityForResult(intent, REQUEST_ADDRESS_CODE);
                break;
            case R.id.tv_submit_order:
                if(StringUtils.isEmpty(addressId)){
                    ToastUtils.showShort(mContext,"请选择收货地址");
                }else{
                    String token = UserInfoManager.getInstance().getToken(mContext);
                    String msg = "";
                    String couponInfoId = "";
                    String order_type = "app商城";
                    String goodList = getGoodsList();
                    LogUtils.d(goodList);
                    String invoiceType = "";
                    String invoice = "";
                    String invoiceCode = "";
                    String invoiceDesc = "";
                    presenter.submitForm(token, msg, couponInfoId, order_type, addressId, goodList, invoiceType,
                            invoice, invoiceCode, invoiceDesc);
                }
                break;
        }
    }

    public String getGoodsList(){
        List<GoodsBack> list = new ArrayList<>();
        for (int i = 0; i < goodsList.size(); i++) {
            if(goodsList.get(i).isSelected()){
                GoodsBack goods = new GoodsBack();
                goods.setId(goodsList.get(i).getId());
                goods.setSpec_id(goodsList.get(i).getSpecIdsString());
                goods.setCount(goodsList.get(i).getCount());
                list.add(goods);
            }
        }
        return new Gson().toJson(list);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADDRESS_CODE && resultCode == Activity.RESULT_OK) {
            llAddress.setVisibility(View.VISIBLE);
            tvAddAddress.setVisibility(View.GONE);
            Bundle bundle = data.getExtras();
            String address = bundle.getString("address");
            String phone = bundle.getString("phone");
            String name = bundle.getString("name");
            addressId = bundle.getString("id");
            tvConsigneeName.setText(name);
            tvAddress.setText("收货地址：" + address);
            tvPhoneNumber.setText(phone);
        }
    }

    @Override
    public void setDefaultAddress(AddressEntity addressEntity) {
        if (ActivityUtils.isFinish(mContext))return;

        if (addressEntity == null) {
            tvAddAddress.setVisibility(View.VISIBLE);
            llAddress.setVisibility(View.GONE);
        } else {
            llAddress.setVisibility(View.VISIBLE);
            tvAddAddress.setVisibility(View.GONE);
            tvConsigneeName.setText(addressEntity.getTrueName());
            tvAddress.setText("收货地址：" + addressEntity.getArea_main().replace(" ", "") + addressEntity.getArea_info());
            tvPhoneNumber.setText(addressEntity.getMobile());
            addressId = addressEntity.getId() + "";
        }

    }

    @Override
    public void submitFormSuccess(OrderCopyEntity orderCopyEntity) {
        if (ActivityUtils.isFinish(mContext))return;

        if (orderCopyEntity==null)return;

        Intent intent = new Intent(OrderCopyActivity.this,FormCopyActivity.class);
        intent.putExtra("form",orderCopyEntity);
        startActivity(intent);

    }
}
