package com.jiupin.jiupinhui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.HomeAdapter;
import com.jiupin.jiupinhui.entity.ArticleEntity;
import com.jiupin.jiupinhui.entity.BannerEntity;
import com.jiupin.jiupinhui.entity.HomeLoveEntity;
import com.jiupin.jiupinhui.entity.HotRecommentEntity;
import com.jiupin.jiupinhui.entity.MainShowEntity;
import com.jiupin.jiupinhui.presenter.IHomeFragmentPresenter;
import com.jiupin.jiupinhui.presenter.impl.HomeFragmentPresenterImpl;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.view.IHomeFragmentView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/3/15.
 */

public class HomeFragment extends Fragment implements IHomeFragmentView {
    private static final String TAG = "HomeFragment";
    @BindView(R.id.lrv_home)
    LRecyclerView lrvHome;

    Unbinder unbinder;

    private View view;
    private HomeAdapter adapter;
    private IHomeFragmentPresenter presenter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private int page = 1;
    private int requestSize = -1;
    private int loveCount = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        unbinder = ButterKnife.bind(this, view);

        presenter = new HomeFragmentPresenterImpl(this);

        initView();
        initListener();
        initData();

        LogUtils.d(TAG, "onCreateView");

        return view;
    }

    private void initData() {
        adapter.clear();
        //获取热门推荐商品
        presenter.getHotRecomment();
        //获取主推套餐
        presenter.getMainShow();
        //获取猜你喜欢
        presenter.getHomeLove(1);
        //获取banner数据
        presenter.getBanner();
        //获取文章数据
        presenter.getArticle();
    }

    private void initListener() {

    }

    private void initView() {

        //初始化RecyclerView
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 2);

        lrvHome.setLayoutManager(manager);

//        int spacing = getResources().getDimensionPixelSize(R.dimen.dp_4);
//        GridItemDecoration divider = new GridItemDecoration.Builder(getContext())
//                .setHorizontal(R.dimen.dp_10)
//                .setVertical(R.dimen.dp_10)
//                .setColorResource(R.color.mainBackground)
//                .build();
//        lrvHome.addItemDecoration(divider);
        adapter = new HomeAdapter(getContext());
        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        lrvHome.setAdapter(lRecyclerViewAdapter);

        lrvHome.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //设置底部加载颜色
        lrvHome.setFooterViewColor(android.R.color.darker_gray, android.R.color.darker_gray, android.R.color.white);
        //设置底部加载文字提示
        lrvHome.setFooterViewHint("拼命加载中", "已经全部为你呈现了", "网络不给力啊，点击再试一次吧");

        lrvHome.setLoadMoreEnabled(true);
        lrvHome.setPullRefreshEnabled(false);

        lRecyclerViewAdapter.setSpanSizeLookup(new LRecyclerViewAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                int type = adapter.getItemViewType(position);
                switch (type) {
                    case HomeAdapter.TYPE_TITLE:
                    case HomeAdapter.TYPE_BANNER:
                    case HomeAdapter.TYPE_MEAL:
                    case HomeAdapter.TYPE_RECOMMEND:
                    case HomeAdapter.TYPE_ARTICLE:
                        return 2;
                    default :
                        return 1;
                }
            }
        });

        lrvHome.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                LogUtils.d("loadMore'");
                if(loveCount<12){
                    page++;
                    presenter.getHomeLove(page);
                }else {
                    lrvHome.setNoMore(true);
                }

            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG, "onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d(TAG, "onDestroyview");
        unbinder.unbind();
        page = 1;
        loveCount = 0;
    }


    @Override
    public void setHotRecommentData(HotRecommentEntity hotRecommentEntity) {
        if(hotRecommentEntity!=null){
            if(hotRecommentEntity.getData()!=null){
                if(hotRecommentEntity.getData().getList().size()>0){
                    adapter.setRecommentData(hotRecommentEntity.getData().getList());

                }

            }

        }
    }

    @Override
    public void setMainShow(MainShowEntity mainShowEntity) {
        if(mainShowEntity!=null){
            if(mainShowEntity.getData()!=null){
                if(mainShowEntity.getData().getList().size()>0){
                    adapter.setMealData(mainShowEntity.getData().getList());
                }

            }

        }
    }

    @Override
    public void setHomeLove(HomeLoveEntity homeLoveEntity) {
        if(homeLoveEntity!=null){
            if(homeLoveEntity.getData()!=null){
                if(homeLoveEntity.getData().getList().size()>0){
                    LogUtils.d("size = "+homeLoveEntity.getData().getList().size());
//                    requestSize = homeLoveEntity.getData().getList().size();
                    loveCount = loveCount+4;
                    adapter.addAll(homeLoveEntity.getData().getList());
                    lrvHome.refreshComplete(requestSize);
                }
            }
        }

    }

    @Override
    public void setBannerData(List<BannerEntity> bannerList) {
        if(bannerList!=null){
            if(bannerList.size()>0){
                adapter.setBannerData(bannerList);
            }
        }
    }

    @Override
    public void setArticleData(List<ArticleEntity> articleList) {
        if(articleList!=null){
            if(articleList.size()>0){
                adapter.setArticleData(articleList);
            }
        }
    }

}

