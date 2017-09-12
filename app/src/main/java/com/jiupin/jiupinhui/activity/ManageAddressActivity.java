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
import com.jiupin.jiupinhui.entity.AddressEntity;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.IManageAddressActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.ManageAddressActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IManageAddressActivityView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 管理地址
 */
public class ManageAddressActivity extends BaseActivity implements IManageAddressActivityView{
    private static final String TAG = "ManageAddressActivity";

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rv_address)
    RecyclerView rvAddress;

    private IManageAddressActivityPresenter presenter;
    private ManageAddressAdapter adapter;
    public String fromActivity;
    private String token;
    public static final int ADD_ADDRESS = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_address);
        ButterKnife.bind(this);
        token = UserInfoManager.getInstance().getToken(this);
        fromActivity = getIntent().getExtras().getString("fromActivity");

        presenter = new ManageAddressActivityPresenterImpl(this);
        presenter.getAddressList(token);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvAddress.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        rvAddress.setLayoutManager(layout);
        adapter = new ManageAddressAdapter(mContext);
        rvAddress.setAdapter(adapter);
    }

    @OnClick({R.id.iv_back,R.id.btn_add_address})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btn_add_address:
                Intent intent = new Intent(mContext,CompileAddressActivity.class);
                intent.putExtra("status",false);
                startActivityForResult(intent,ADD_ADDRESS);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ADD_ADDRESS){
            LogUtils.d("startActivityForResult.ADD_ADDRESS");
            token = UserInfoManager.getInstance().getToken(this);
            presenter.getAddressList(token);
        }
    }

    public void changeDefaultAddress(int id){
        token = UserInfoManager.getInstance().getToken(this);
        presenter.changeDefaultAddress(token,id+"");
    }

    @Override
    public void setData(List<AddressEntity> adds) {
        if(adds.size()>0){
            adapter.setData(adds);
        }
    }

    @Override
    public void changeDefaultAddressSuccess() {
        ToastUtils.showShort(this,"修改默认地址成功");
    }

    @Override
    public void deleteAddressSuccess(List<AddressEntity> adds) {
        if(adds.size()>=0){
            adapter.setData(adds);
        }
    }

    public void deleteAddress(int id) {
        token = UserInfoManager.getInstance().getToken(this);
        presenter.deleteAddress(id,token);
    }
}
