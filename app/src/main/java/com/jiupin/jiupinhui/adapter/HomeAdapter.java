package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.jiupin.jiupinhui.activity.ArticleActivity;
import com.jiupin.jiupinhui.activity.GoodsActivity;
import com.jiupin.jiupinhui.activity.MainActivity;
import com.jiupin.jiupinhui.entity.ArticleEntity;
import com.jiupin.jiupinhui.entity.BannerEntity;
import com.jiupin.jiupinhui.entity.HomeLoveEntity;
import com.jiupin.jiupinhui.entity.HotRecommentEntity;
import com.jiupin.jiupinhui.entity.MainShowEntity;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.TimeUtils;
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
    private List<ArticleEntity> articleList;


    public HomeAdapter(Context context) {
        mealList = new ArrayList<>();
        recommentList = new ArrayList<>();
        bannerList = new ArrayList<>();
        stores = new ArrayList<>();
        articleList = new ArrayList<>();
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
            initHomeLoveViewHolder((HomeLoveViewHolder) holder, position);
        } else if (holder instanceof TitleViewHolder) {
            TitleViewHolder titleViewHolder = (TitleViewHolder) holder;
            if (position == 1) {
                titleViewHolder.tvItemTitle.setText("— 主推套餐 —");
            } else if (position == 3) {
                titleViewHolder.tvItemTitle.setText("— 热门推荐 —");
            } else if (position == 5) {
                titleViewHolder.tvItemTitle.setText("— 热门文章 —");
            } else if (position == 7) {
                titleViewHolder.tvItemTitle.setText("— 猜你喜欢 —");
            }

        } else if (holder instanceof BannerViewHolder) {
            initBannerHolder((BannerViewHolder) holder);
        } else if (holder instanceof MealViewHolder) {
            initMealHolder((MealViewHolder) holder);
        } else if (holder instanceof RecommendViewHolder) {
            initRecommendHolder((RecommendViewHolder) holder);
        } else if (holder instanceof ArticleViewHolder) {
            initArticleHolder((ArticleViewHolder) holder);
        }

    }

    /**
     * 初始化猜你喜欢
     * @param holder
     * @param position
     */
    private void initHomeLoveViewHolder(HomeLoveViewHolder holder, final int position) {
        HomeLoveViewHolder homeLoveViewHolder = holder;
        Glide.with(mContext)
                .load(stores.get(getTruePositonByPosition(position)).getPath())
                .crossFade()
                .into(homeLoveViewHolder.ivGuessLove);
        homeLoveViewHolder.tvGuessLoveDes.setText(stores.get(getTruePositonByPosition(position)).getGoods_name());
        homeLoveViewHolder.tvGuessLovePrice.setText("￥"+stores.get(getTruePositonByPosition(position)).getStore_price() + "");
        homeLoveViewHolder.tvGuessLovePriceNext.setText("￥"+stores.get(getTruePositonByPosition(position)).getGoods_price() + "");
        homeLoveViewHolder.tvBuyNumber.setText(stores.get(getTruePositonByPosition(position)).getPayment_num()+"人付款");
        homeLoveViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodsActivity.class);
                intent.putExtra("id",stores.get(getTruePositonByPosition(position)).getId());
                mContext.startActivity(intent);
            }
        });
    }

    public int getTruePositonByPosition(int position) {
        int type = getItemViewType(position);
        if (type == TYPE_LOVE) {
            return position - 8;
        }
        return 0;
    }

    /**
     * 初始化热门文章
     *
     * @param holder
     */
    private void initArticleHolder(ArticleViewHolder holder) {
        if (articleList.size() > 0) {
            holder.llArticleContainer.removeAllViews();
            for (int i = 0; i < articleList.size(); i++) {
                View view = inflater.inflate(R.layout.item_article_item, null);

                ImageView ivArticle = (ImageView) view.findViewById(R.id.iv_article);
                TextView tvHotArticleDescribe = (TextView) view.findViewById(R.id.tv_hot_article_describe);
                TextView tvHotArticleTime = (TextView) view.findViewById(R.id.tv_hot_article_time);
                Glide.with(mContext)
                        .load(articleList.get(i).getImageUrl())
                        .crossFade()
                        .into(ivArticle);
                tvHotArticleDescribe.setText(articleList.get(i).getTitle());
                tvHotArticleTime.setText(TimeUtils.getTime(articleList.get(i).getCreateTime(), TimeUtils.DATE_FORMAT_DATE));
                final int finalI = i;
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        ToastUtils.showShort(mContext, articleList.get(finalI).getContent() + articleList.get(finalI).getId());
                        Intent intent = new Intent(mContext, ArticleActivity.class);
                        intent.putExtra("url",articleList.get(finalI).getContent() + articleList.get(finalI).getId());
                        ((MainActivity) mContext).startActivity(intent);

                    }
                });

                holder.llArticleContainer.addView(view);
            }
        }
    }

    /**
     * 初始化热门推荐
     *
     * @param holder
     */
    private void initRecommendHolder(RecommendViewHolder holder) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        holder.rvRecommend.setLayoutManager(manager);
        holder.rvRecommend.setAdapter(new HotRecommentAdapter(mContext, recommentList));
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
        holder.rvMeal.setAdapter(new MainShowAdapter(mContext, mealList));

        holder.rvMeal.setNestedScrollingEnabled(false);
    }

    /**
     * 初始化banner
     *
     * @param holder
     */
    private void initBannerHolder(BannerViewHolder holder) {
        if (bannerView == null) {//
            bannerView = new ADBannerView(mContext, true);
            holder.llBanner.addView(bannerView);
        }
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
        } else if (position == 7) {
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
        return 8 + stores.size();
    }


    class HomeLoveViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivGuessLove;
        private TextView tvGuessLoveDes, tvGuessLovePrice, tvGuessLovePriceNext,tvBuyNumber;
        private View view;

        public HomeLoveViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ivGuessLove = (ImageView) itemView.findViewById(R.id.iv_guess_love);
            tvBuyNumber = (TextView) itemView.findViewById(R.id.tv_buy_number);
            tvGuessLoveDes = (TextView) itemView.findViewById(R.id.tv_guess_love_des);
            tvGuessLovePrice = (TextView) itemView.findViewById(R.id.tv_guess_love_price);
            tvGuessLovePriceNext = (TextView) itemView.findViewById(R.id.tv_guess_love_price_next);
        }
    }

    //设置热门文章数据
    public void setArticleData(List<ArticleEntity> articleList) {
        this.articleList = articleList;
        notifyDataSetChanged();
    }


    //设置banner数据
    public void setBannerData(List<BannerEntity> bannerList) {
        LogUtils.d("setBannerData");
        if(bannerView!=null){
            this.bannerList = bannerList;
            bannerView.loadAD(bannerList);
            notifyDataSetChanged();
        }

    }

    //设置猜你喜欢数据
    public void setHomeLoveData(List<HomeLoveEntity.DataBean.ListBean> loveList) {
        this.stores.clear();
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
//        int lastIndex = this.stores.size();
//        if (this.stores.addAll(loveList)) {
//            notifyItemRangeInserted(lastIndex, loveList.size());
//        }
        this.stores.addAll(loveList);
        notifyDataSetChanged();
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
