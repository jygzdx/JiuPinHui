package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.BannerEntity;
import com.jiupin.jiupinhui.entity.HomeLoveEntity;
import com.jiupin.jiupinhui.entity.HotRecommentEntity;
import com.jiupin.jiupinhui.entity.MainShowEntity;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.widget.ADBannerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/27.
 */

public class HomeAdapter extends RecyclerView.Adapter {
    private static final String TAG = "HomeAdapter";

    /**
     * 头部type
     */
    public static final int TYPE_TITLE = 0;
    /**
     * 轮播图type
     */
    public static final int TYPE_BANNER = 1;
    /**
     * 套餐type
     */
    public static final int TYPE_MEAL = 2;
    /**
     * 推荐type
     */
    public static final int TYPE_RECOMMEND = 3;
    /**
     * 文章type
     */
    public static final int TYPE_ARTICLE = 4;
    /**
     * 底部猜你喜欢type
     */
    public static final int TYPE_LOVE = 5;

    private List<MainShowEntity.DataBean.ListBean> mealList;
    private List<HotRecommentEntity.DataBean.ListBean> recommentList;
    private List<HomeLoveEntity.DataBean.ListBean> stores;
    private List<BannerEntity> bannerList;
    private LayoutInflater inflater;
    private Context mContext;
    private ADBannerView bannerView;


    public HomeAdapter(Context context) {
        mealList = new ArrayList<>();
        recommentList = new ArrayList<>();
        bannerList = new ArrayList<>();
        stores = new ArrayList<>();
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LogUtils.d("viewType =  " + viewType);
        if (viewType == TYPE_TITLE) {
            View view = inflater.inflate(R.layout.item_home_title, viewGroup, false);
            RecyclerView.ViewHolder holder = new TitleViewHolder(view);
            return holder;
        } else if (viewType == TYPE_BANNER) {
            View view = inflater.inflate(R.layout.item_banner, viewGroup, false);
            RecyclerView.ViewHolder holder = new BannerViewHolder(view);
            LogUtils.d("onCreateViewHolder.BannerViewHolder");
            return holder;
        } else if (viewType == TYPE_MEAL) {
            View view = inflater.inflate(R.layout.item_meal, viewGroup, false);
            RecyclerView.ViewHolder holder = new MealViewHolder(view);
            return holder;
        } else if (viewType == TYPE_RECOMMEND) {
            View view = inflater.inflate(R.layout.item_recommend, viewGroup, false);
            RecyclerView.ViewHolder holder = new RecommendViewHolder(view);
            return holder;
        } else if (viewType == TYPE_ARTICLE) {
            View view = inflater.inflate(R.layout.item_article, viewGroup, false);
            RecyclerView.ViewHolder holder = new ArticleViewHolder(view);
            return holder;
        } else {
            View view = inflater.inflate(R.layout.home_love_item, viewGroup, false);
            RecyclerView.ViewHolder holder = new HomeLoveViewHolder(view);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        LogUtils.d("position =  " + position);

        if (holder instanceof HomeLoveViewHolder) {
            HomeLoveViewHolder homeLoveViewHolder = (HomeLoveViewHolder) holder;
            Glide.with(mContext)
                    .load(stores.get(getTruePositonByPosition(position)).getPath())
                    .crossFade()
                    .into(homeLoveViewHolder.ivGuessLove);
            homeLoveViewHolder.tvGuessLoveDes.setText(stores.get(getTruePositonByPosition(position)).getGoods_name());
            homeLoveViewHolder.tvGuessLovePrice.setText(stores.get(getTruePositonByPosition(position)).getStore_price() + "");
            homeLoveViewHolder.tvGuessLovePriceNext.setText(stores.get(getTruePositonByPosition(position)).getGoods_price() + "");
        } else if (holder instanceof TitleViewHolder) {
            TitleViewHolder titleViewHolder = (TitleViewHolder) holder;
            if (position == 1) {
                titleViewHolder.tvItemTitle.setText("— 主推套餐 —");
            } else if (position == 3) {
                titleViewHolder.tvItemTitle.setText("— 热门推荐 —");
            } else if (position == 5) {
                titleViewHolder.tvItemTitle.setText("— 猜你喜欢 —");
            }

        } else if (holder instanceof BannerViewHolder) {
            initBannerHolder((BannerViewHolder) holder);
            LogUtils.d("onBindViewHolder.BannerViewHolder");
        } else if (holder instanceof MealViewHolder) {
            initMealHolder((MealViewHolder) holder);
        } else if (holder instanceof RecommendViewHolder) {
            initRecommendHolder((RecommendViewHolder) holder);
        } else if (holder instanceof ArticleViewHolder) {
            initArticleHolder((ArticleViewHolder) holder);
        }

    }

    public int getTruePositonByPosition(int position){
        int type = getItemViewType(position);
        if(type == TYPE_LOVE){
            return position-7;
        }
        return 0;
    }

    /**
     * 初始化热门文章
     *
     * @param holder
     */
    private void initArticleHolder(ArticleViewHolder holder) {


    }

    /**
     * 初始化热门推荐
     *
     * @param holder
     */
    private void initRecommendHolder(RecommendViewHolder holder) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        holder.rvRecommend.setLayoutManager(manager);
        holder.rvRecommend.setAdapter(new HotRecommentAdapter(mContext,recommentList));
        holder.rvRecommend.setNestedScrollingEnabled(false);
    }

    /**
     * 初始化推荐套餐
     *
     * @param holder
     */
    private void initMealHolder(MealViewHolder holder) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        holder.rvMeal.setLayoutManager(manager);
        holder.rvMeal.setAdapter(new MainShowAdapter(mContext,mealList));

        holder.rvMeal.setNestedScrollingEnabled(false);
    }

    /**
     * 初始化banner
     *
     * @param holder
     */
    private void initBannerHolder(BannerViewHolder holder) {
        bannerView = new ADBannerView(mContext, true);
        holder.llBanner.addView(bannerView);

}

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER;
        } else if (position == 1) {
            return TYPE_TITLE;
        } else if (position == 3) {
            return TYPE_TITLE;
        } else if (position == 5) {
            return TYPE_TITLE;
        } else if (position == 2) {
            return TYPE_MEAL;
        } else if (position == 4) {
            return TYPE_RECOMMEND;
        } else if (position == 6) {
            return TYPE_ARTICLE;
        } else {
            return TYPE_LOVE;
        }

    }

    @Override
    public int getItemCount() {
        return 7+stores.size();
    }
//
//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
//        if (manager instanceof GridLayoutManager) {
//            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
//            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                @Override
//                public int getSpanSize(int position) {
//                    int type = getItemViewType(position);
//                    switch (type) {
//                        case TYPE_TITLE:
//                        case TYPE_BANNER:
//                        case TYPE_MEAL:
//                        case TYPE_RECOMMEND:
//                        case TYPE_ARTICLE:
//                            return 2;
//                        case TYPE_LOVE:
//                            return 1;
//                        default:
//                            return 2;
//                    }
//                }
//            });
//        }
//    }




    class HomeLoveViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivGuessLove, ivGuessLoveMore;
        private TextView tvGuessLoveDes, tvGuessLovePrice, tvGuessLovePriceNext, tvBuyNumber;


        public HomeLoveViewHolder(View itemView) {
            super(itemView);
            ivGuessLove = (ImageView) itemView.findViewById(R.id.iv_guess_love);
            ivGuessLoveMore = (ImageView) itemView.findViewById(R.id.iv_guess_love_more);

            tvBuyNumber = (TextView) itemView.findViewById(R.id.tv_buy_number);
            tvGuessLoveDes = (TextView) itemView.findViewById(R.id.tv_guess_love_des);
            tvGuessLovePrice = (TextView) itemView.findViewById(R.id.tv_guess_love_price);
            tvGuessLovePriceNext = (TextView) itemView.findViewById(R.id.tv_guess_love_price_next);
        }
    }
    //设置banner数据
    public void setBannerData(List<BannerEntity> bannerList) {
        LogUtils.d("setBannerData");
        this.bannerList = bannerList;
        bannerView.loadAD(bannerList);
    }

    //设置猜你喜欢数据
    public void setHomeLoveData(List<HomeLoveEntity.DataBean.ListBean> loveList) {
        this.stores = loveList;
        notifyDataSetChanged();
    }

    //设置热门推荐数据
    public void setRecommentData(List<HotRecommentEntity.DataBean.ListBean> recommentList) {
        this.recommentList = recommentList;
        notifyDataSetChanged();
    }

    //设这套餐数据
    public void setMealData(List<MainShowEntity.DataBean.ListBean> mealList) {
        this.mealList = mealList;
        notifyDataSetChanged();
    }

    public void addAll(Collection<HomeLoveEntity.DataBean.ListBean> loveList) {
        int lastIndex = this.stores.size();
        if (this.stores.addAll(loveList)) {
            notifyItemRangeInserted(lastIndex, loveList.size());
        }
    }

    public void clear() {
        stores.clear();
        notifyDataSetChanged();
    }

    class TitleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_title)
        TextView tvItemTitle;

        TitleViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_banner)
        LinearLayout llBanner;

        BannerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class MealViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rv_meal)
        RecyclerView rvMeal;

        MealViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rv_recommend)
        RecyclerView rvRecommend;

        RecommendViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_article_container)
        LinearLayout llArticleContainer;

        ArticleViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
