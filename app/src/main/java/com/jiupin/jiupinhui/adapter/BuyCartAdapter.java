package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;

/**
 * Created by Administrator on 2017/3/27.
 */

public class BuyCartAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private Context mContext;
    int testNumber=1;

    public BuyCartAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.buy_cart_item,viewGroup,false);
        RecyclerView.ViewHolder holder = new BuyCartViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int i) {
        //要有个字段记录控件的显示还是隐藏
        ((BuyCartViewHolder)holder).llCompileState.setVisibility(View.GONE);
        ((BuyCartViewHolder)holder).btnDelete.setVisibility(View.VISIBLE);
        ((BuyCartViewHolder)holder).btnItemCompile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BuyCartViewHolder)holder).llCompileState.setVisibility(View.VISIBLE);
            }
        });
        ((BuyCartViewHolder)holder).btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BuyCartViewHolder)holder).btnDelete.setVisibility(View.GONE);
            }
        });
        ((BuyCartViewHolder)holder).ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testNumber++;
                ((BuyCartViewHolder)holder).tvGoodsNumber.setText(testNumber+"");
            }
        });
        ((BuyCartViewHolder)holder).ivSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testNumber--;
                ((BuyCartViewHolder)holder).tvGoodsNumber.setText(testNumber+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    private class BuyCartViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout llCompileState;
        private Button btnItemCompile;
        private Button btnDelete;
        private ImageView ivAdd;
        private ImageView ivSubtract;
        private TextView tvGoodsNumber;
        public BuyCartViewHolder(View itemView) {
            super(itemView);
            btnItemCompile = (Button) itemView.findViewById(R.id.btn_item_compile);
            llCompileState = (LinearLayout) itemView.findViewById(R.id.ll_compile_state);
            btnDelete = (Button) itemView.findViewById(R.id.btn_delete);
            ivAdd = (ImageView) itemView.findViewById(R.id.iv_add);
            ivSubtract = (ImageView) itemView.findViewById(R.id.iv_subtract);
            tvGoodsNumber = (TextView) itemView.findViewById(R.id.tv_goods_number);
        }
    }
}
