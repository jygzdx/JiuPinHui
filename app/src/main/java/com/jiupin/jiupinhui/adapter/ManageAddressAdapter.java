package com.jiupin.jiupinhui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.activity.CompileAddressActivity;
import com.jiupin.jiupinhui.activity.ManageAddressActivity;
import com.jiupin.jiupinhui.entity.AddressEntity;
import com.jiupin.jiupinhui.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：czb on 2017/5/2 11:13
 */

public class ManageAddressAdapter extends RecyclerView.Adapter {

    private final LayoutInflater inflater;
    private final Context mContext;

    private List<AddressEntity> adds = new ArrayList<>();

    private int radioclick = 0;

    public ManageAddressAdapter(Context mContext) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.manage_address_item, null);
        ManageAddressHolder holder = new ManageAddressHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder h, final int position) {
        ManageAddressHolder holder = (ManageAddressHolder) h;
        final AddressEntity address = adds.get(position);
        holder.tvUserName.setText(address.getTrueName());
        holder.tvUserPhone.setText(address.getMobile());
        final String addressInfo = address.getArea_main().replace(" ", "") + address.getArea_info();
        holder.tvAddress.setText(addressInfo);
        holder.rbDefaultAddress.setChecked(address.getIs_default() == 1 ? true : false);

        holder.rbDefaultAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < adds.size(); i++) {
                    adds.get(i).setIs_default(0);
                }
                address.setIs_default(1);
                notifyDataSetChanged();
                ((ManageAddressActivity)mContext).changeDefaultAddress(address.getId());
            }
        });

        //点击item返回地址数据
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("MyFragment".equals(((ManageAddressActivity)mContext).fromActivity)){
                    return;
                }
                Intent intent = ((ManageAddressActivity)mContext).getIntent();
                Bundle bundle = new Bundle();
                bundle.putString("address",addressInfo);
                bundle.putString("phone", address.getMobile());//添加要返回给页面1的数据
                bundle.putString("name", address.getTrueName());
                bundle.putString("id",address.getId()+"");
                intent.putExtras(bundle);
                ((ManageAddressActivity)mContext).setResult(Activity.RESULT_OK, intent);//返回页面1
                ((ManageAddressActivity)mContext).finish();
            }
        });

        holder.tvCompileAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CompileAddressActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra("status", true);
                bundle.putSerializable("address",address);
                intent.putExtras(bundle);
                ((ManageAddressActivity)mContext).startActivityForResult(intent,ManageAddressActivity.ADD_ADDRESS);
            }
        });
        holder.tvDeleteAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(position);
            }
        });
    }

    private void showDialog(final int position) {
        View view = inflater.inflate(R.layout.dialog_content, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tvEnsure = (TextView) view.findViewById(R.id.tv_ensure);

        tvContent.setText("确定要删除该地址吗？");
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
                //删除地址
                ((ManageAddressActivity) mContext).deleteAddress(adds.get(position).getId());
                LogUtils.d("position = " + position);
                //确定
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return adds.size();
    }

    public void setData(List<AddressEntity> adds) {
        this.adds.clear();
        this.adds.addAll(adds);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        this.adds.remove(position);
        notifyItemRemoved(position);

        if(position != (this.adds.size())){ // 如果移除的是最后一个，忽略
            notifyItemRangeChanged(position,this.adds.size()-position);
        }
    }

    class ManageAddressHolder extends RecyclerView.ViewHolder {
        TextView tvDeleteAddress, tvCompileAddress, tvUserName, tvUserPhone, tvAddress;
        RadioButton rbDefaultAddress;
        View itemView;

        public ManageAddressHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvDeleteAddress = (TextView) itemView.findViewById(R.id.tv_delete_address);
            tvCompileAddress = (TextView) itemView.findViewById(R.id.tv_compile_address);
            tvUserName = (TextView) itemView.findViewById(R.id.tv_user_name);
            tvUserPhone = (TextView) itemView.findViewById(R.id.tv_user_phone);
            tvAddress = (TextView) itemView.findViewById(R.id.tv_address);
            rbDefaultAddress = (RadioButton) itemView.findViewById(R.id.rb_default_address);
        }
    }

}
