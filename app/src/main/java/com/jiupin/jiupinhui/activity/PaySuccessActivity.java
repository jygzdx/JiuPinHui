package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.FormParticularEntity;
import com.jiupin.jiupinhui.manage.PopWinManager;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.impl.PaySuccessActivityPresenterImpl;
import com.jiupin.jiupinhui.view.IPaySuccessActivityView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 订单支付成功界面
 */
public class PaySuccessActivity extends BaseActivity implements IPaySuccessActivityView {

    @BindView(R.id.tv_show_status)
    TextView tvShowStatus;
    @BindView(R.id.tv_remain_time)
    TextView tvRemainTime;
    @BindView(R.id.tv_consignee_name)
    TextView tvConsigneeName;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    private PaySuccessActivityPresenterImpl presenter;
    private String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);
        ButterKnife.bind(this);

        orderId = getIntent().getExtras().getString("orderId");
        String token = UserInfoManager.getInstance().getToken(this);
        presenter = new PaySuccessActivityPresenterImpl(this);
        presenter.getFormInfo(orderId, token);
    }

    @Override
    public void getFormSuccess(FormParticularEntity formParticularEntity) {
        if (formParticularEntity != null) {
            tvRemainTime.setText(formParticularEntity.getDeadline());
            tvConsigneeName.setText(formParticularEntity.getAddress().getTrueName());
            tvPhoneNumber.setText(formParticularEntity.getAddress().getMobile());
            tvAddress.setText("收货地址：" + formParticularEntity.getAddress().getArea_main().replace(" ", "")
                    + formParticularEntity.getAddress().getArea_info());

            double price = 0;
            //添加订单商品
            if (formParticularEntity.getCart() != null) {
                List<FormParticularEntity.CartBean> cartBeanList = formParticularEntity.getCart();
                for (int i = 0; i < cartBeanList.size(); i++) {
                    price = price + cartBeanList.get(i).getPrice() * cartBeanList.get(i).getCount();
                }
            }

            tvPrice.setText((price - formParticularEntity.getOrder().getShip_price()) + "");
        }
    }

    @OnClick({R.id.iv_more, R.id.btn_check_form, R.id.btn_goto_home})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_more:
                PopWinManager popWinManager = new PopWinManager(this,(View) view.getParent());
                popWinManager.createPopupWindow();
                break;
            case R.id.btn_check_form:
                Intent intent = new Intent(PaySuccessActivity.this,FormParticularActivity.class);
                String status = Constant.WAIT_DELIVER_GOODS;
                intent.putExtra("status",status);
                intent.putExtra("orderId",orderId);
                startActivity(intent);
                break;
            case R.id.btn_goto_home:
                Intent mainIntent = new Intent(mContext, MainActivity.class);
                mainIntent.putExtra("status", MainActivity.HOME_STATUS);
                mContext.startActivity(mainIntent);
                break;
        }
    }
}
