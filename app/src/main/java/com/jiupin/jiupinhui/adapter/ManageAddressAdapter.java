package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.activity.CompileAddressActivity;
import com.jiupin.jiupinhui.utils.LogUtils;

/**
 * 作者：czb on 2017/5/2 11:13
 */

public class ManageAddressAdapter extends RecyclerView.Adapter {

    private final LayoutInflater inflater;
    private final Context mContext;

    public ManageAddressAdapter(Context mContext) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.manage_address_item,null);
        ManageAddressHolder holder = new ManageAddressHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder h, final int i) {
        ManageAddressHolder holder = (ManageAddressHolder) h;
        holder.tvCompileAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CompileAddressActivity.class);
                intent.putExtra("status",true);
                mContext.startActivity(intent);
            }
        });
        holder.tvDeleteAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(i);
            }
        });
    }

    private void showDialog(final int position) {
        View view = inflater.inflate(R.layout.dialog_delete_address, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setView(view);
        final AlertDialog dialog = builder.create();

        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tvEnsure = (TextView) view.findViewById(R.id.tv_ensure);

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
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,getItemCount()-position);
                LogUtils.d("position = "+position);
                //确定
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class ManageAddressHolder extends RecyclerView.ViewHolder{
        TextView tvDeleteAddress,tvCompileAddress;
        public ManageAddressHolder(View itemView) {
            super(itemView);
            tvDeleteAddress = (TextView) itemView.findViewById(R.id.tv_delete_address);
            tvCompileAddress = (TextView) itemView.findViewById(R.id.tv_compile_address);
        }
    }

}
