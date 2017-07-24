package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.activity.GoodsActivity;
import com.jiupin.jiupinhui.entity.MainShowEntity;
import com.jiupin.jiupinhui.utils.LogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：czb on 2017/6/28 17:34
 */

public class MainShowAdapter extends RecyclerView.Adapter{
    private Context mContext;
    private List<MainShowEntity.DataBean.ListBean> mealList;
    private LayoutInflater inflater;
    public MainShowAdapter(Context context, List<MainShowEntity.DataBean.ListBean> mealList) {
        this.mealList = mealList;
        this.mContext = context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.main_show_item, null);

        MainShowViewHolder holder = new MainShowViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MainShowViewHolder mainShowViewHolder = (MainShowViewHolder) holder;
        final MainShowEntity.DataBean.ListBean bean = mealList.get(position);

        mainShowViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("position = "+position);
                Intent intent = new Intent(mContext, GoodsActivity.class);
                intent.putExtra("id",bean.getId());
                mContext.startActivity(intent);
            }
        });
        
        Glide.with(mContext)
                .load(bean.getPath())
                .crossFade()
                .into(mainShowViewHolder.ivImg);

    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }


    class MainShowViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_img)
        ImageView ivImg;
        View view ;

        MainShowViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }
}
