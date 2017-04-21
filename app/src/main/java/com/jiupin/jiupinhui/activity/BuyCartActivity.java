package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.BuyCartAdapter;

import butterknife.BindView;
import butterknife.OnClick;

public class BuyCartActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_more)
    ImageView ivMore;
    @BindView(R.id.rv_recyclerview)
    RecyclerView rvRecyclerView;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    private BuyCartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_cart);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvRecyclerView.setLayoutManager(layout);

        adapter = new BuyCartAdapter(mContext);
        rvRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.tv_pay})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_pay:
                Intent intent = new Intent(mContext,OrderActivity.class);
                startActivity(intent);
                break;
        }
    }
}
