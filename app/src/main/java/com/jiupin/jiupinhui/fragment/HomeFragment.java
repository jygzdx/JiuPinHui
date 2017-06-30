package com.jiupin.jiupinhui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.jdsjlzx.ItemDecoration.GridItemDecoration;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.HomeLoveAdapter;
import com.jiupin.jiupinhui.adapter.HotRecommentAdapter;
import com.jiupin.jiupinhui.adapter.MainShowAdapter;
import com.jiupin.jiupinhui.entity.HomeLoveEntity;
import com.jiupin.jiupinhui.entity.HotRecommentEntity;
import com.jiupin.jiupinhui.entity.MainShowEntity;
import com.jiupin.jiupinhui.presenter.IHomeFragmentPresenter;
import com.jiupin.jiupinhui.presenter.impl.HomeFragmentPresenterImpl;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.view.IHomeFragmentView;
import com.jiupin.jiupinhui.widget.ADBannerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/3/15.
 */

public class HomeFragment extends Fragment implements IHomeFragmentView{
    private static final String TAG = "HomeFragment";
    @BindView(R.id.rv_mian_show)
    RecyclerView rvMianShow;
    @BindView(R.id.rv_hot_recommend)
    RecyclerView rvHotRecommend;
    @BindView(R.id.rv_home_love)
    LRecyclerView rvHomeLove;

    Unbinder unbinder;

    private View view;
    private NestedScrollView nsvScorllView;
    private View llTitleBar;
    private LinearLayout ll_advertis;
    private HomeLoveAdapter adapter;
    private HotRecommentAdapter mHotRecommentAdapter;
    private IHomeFragmentPresenter presenter;
    private MainShowAdapter mMainShowAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private List<HomeLoveEntity.DataBean.ListBean> stores = new ArrayList<>();


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
        //获取热门推荐商品
        presenter.getHotRecomment();
        //获取主推套餐
        presenter.getMainShow();
        //获取猜你喜欢
        presenter.getHomeLove();
    }

    private void initListener() {


        nsvScorllView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int height = ll_advertis.getHeight();
                if (scrollY <= height) {
                    float scale = (float) scrollY / height;
                    //滑动时改变标题栏的透明度
                    llTitleBar.setAlpha(scale);
                }
            }
        });
    }

    private void initView() {
        ll_advertis = (LinearLayout) view.findViewById(R.id.advertis);
        ADBannerView bannerView = new ADBannerView(getContext(), true);
        ll_advertis.addView(bannerView);

        nsvScorllView = (NestedScrollView) view.findViewById(R.id.nv_home_scrollview);
        llTitleBar = view.findViewById(R.id.ll_title_bar);
        initHomeLoveRv();
        initMainShow();
        initHotRecomment();

    }

    private void initHotRecomment() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        rvHotRecommend.setLayoutManager(manager);
        rvHotRecommend.setNestedScrollingEnabled(false);
    }

    private void initMainShow() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        rvMianShow.setLayoutManager(manager);
        rvMianShow.setNestedScrollingEnabled(false);
    }

    private void initHomeLoveRv() {
        //初始化RecyclerView
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 2);

//        ((GridLayoutManager) manager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int i) {
//                switch (adapter.getItemViewType(i)) {
//                    case HomeLoveAdapter.FOOT_VIEW:
//                        LogUtils.d("foot_view"+i);
//                        return 2;
//                    case HomeLoveAdapter.DEFAUL_VIEW:
//                        LogUtils.d("DEFAUL_VIEW"+i);
//                        return 1;
//                }
//                return 0;
//            }
//        });



        rvHomeLove.setLayoutManager(manager);
        rvHomeLove.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

        rvHomeLove.setNestedScrollingEnabled(false);

        //设置底部加载颜色
        rvHomeLove.setFooterViewColor(R.color.colorAccent, android.R.color.holo_blue_dark ,android.R.color.white);
        //设置底部加载文字提示
        rvHomeLove.setFooterViewHint("拼命加载中","已经全部为你呈现了","网络不给力啊，点击再试一次吧");

        int spacing = getResources().getDimensionPixelSize(R.dimen.dp_4);
        GridItemDecoration divider = new GridItemDecoration.Builder(getContext())
                .setHorizontal(R.dimen.dp_4)
                .setVertical(R.dimen.dp_4)
                .setColorResource(R.color.mainBackground)
                .build();
        rvHomeLove.addItemDecoration(divider);
        adapter = new HomeLoveAdapter(getContext(),stores);

        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);

        rvHomeLove.setAdapter(lRecyclerViewAdapter);

        rvHomeLove.refresh();

        rvHomeLove.setLoadMoreEnabled(false);
        rvHomeLove.setPullRefreshEnabled(false);

        lRecyclerViewAdapter.setSpanSizeLookup(new LRecyclerViewAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                switch (adapter.getItemViewType(position)) {
                    case HomeLoveAdapter.FOOT_VIEW:
                        LogUtils.d("foot_view" + position);
                        return 2;
                    case HomeLoveAdapter.DEFAUL_VIEW:
                        LogUtils.d("DEFAUL_VIEW" + position);
                        return 1;
                }
                return 0;
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
    }


    @Override
    public void setHotRecommentData(HotRecommentEntity hotRecommentEntity) {
        if(mHotRecommentAdapter == null){
            mHotRecommentAdapter = new HotRecommentAdapter(getContext(),hotRecommentEntity.getData());
        }

        rvHotRecommend.setAdapter(mHotRecommentAdapter);
    }

    @Override
    public void setMainShow(MainShowEntity mainShowEntity) {
        if(mMainShowAdapter == null){
            mMainShowAdapter = new MainShowAdapter(getContext(),mainShowEntity.getData());
        }

        rvMianShow.setAdapter(mMainShowAdapter);
    }

    @Override
    public void setHomeLove(HomeLoveEntity homeLoveEntity) {

        LogUtils.d("name = "+homeLoveEntity.getData().getList().get(1).getGoods_name());
//        if(adapter ==null){
//            adapter = new HomeLoveAdapter(getContext(),stores);
//
//            lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
//
//
//        }
//        rvHomeLove.refresh();
//
//        rvHomeLove.setLoadMoreEnabled(false);
//        rvHomeLove.setPullRefreshEnabled(false);
//        rvHomeLove.setAdapter(lRecyclerViewAdapter);
        adapter.notifyItemRangeInserted(stores.size(),homeLoveEntity.getData().getList().size());
        stores.addAll(homeLoveEntity.getData().getList());
        adapter.notifyDataSetChanged();
        rvHomeLove.refreshComplete(4);
    }
}

