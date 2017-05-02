package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.ManageAddressAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 管理地址
 */
public class ManageAddressActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rv_address)
    RecyclerView rvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_address);
        ButterKnife.bind(this);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvAddress.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        rvAddress.setLayoutManager(layout);
        rvAddress.setAdapter(new ManageAddressAdapter(mContext));
    }

    @OnClick({R.id.iv_back,R.id.btn_add_address})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btn_add_address:
                Intent intent = new Intent(mContext,CompileAddressActivity.class);
                intent.putExtra("status",false);
                startActivity(intent);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

}