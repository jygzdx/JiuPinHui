package com.jiupin.jiupinhui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.BuyCartAdapter;

import butterknife.BindView;

public class BuyCartActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_more)
    ImageView ivMore;
    @BindView(R.id.rv_recyclerview)
    RecyclerView rvRecyclerView;
    private BuyCartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_cart);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        rvRecyclerView.setLayoutManager(layout);

        adapter = new BuyCartAdapter(mContext);
        rvRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
