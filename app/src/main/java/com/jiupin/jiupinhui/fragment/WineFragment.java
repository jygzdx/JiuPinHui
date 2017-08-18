package com.jiupin.jiupinhui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.WineAdapter;
import com.jiupin.jiupinhui.adapter.WineBrandAdapter;
import com.jiupin.jiupinhui.entity.WineBrandEntity;
import com.jiupin.jiupinhui.entity.WineInfoEntity;
import com.jiupin.jiupinhui.presenter.impl.WineFragmentPresenterImpl;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.view.IWineFragmentView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/3/15.
 */

public class WineFragment extends Fragment implements IWineFragmentView {
    private static final String TAG = "WineFragment";
    @BindView(R.id.rv_wine_brand)
    RecyclerView rvWineBrand;
    @BindView(R.id.lrv_Wine_show)
    LRecyclerView lrvWineShow;
    private View view;
    private Unbinder unbinder;
    private WineAdapter wineAdapter;

    private WineBrandAdapter brandAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private WineFragmentPresenterImpl presenter;
    private int brandId = -1;
    private int page = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_wine, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new WineFragmentPresenterImpl(this);

        initBrandRV();

        initWineRV();

        //获取数据
        initData();


        LogUtils.d(TAG, "onCreateView");
        return view;
    }
    /**
     * 初始化品牌recyclerview
     */
    private void initBrandRV() {
        //设置品牌RecyclerView
        RecyclerView.LayoutManager brandManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvWineBrand.setLayoutManager(brandManager);
        brandAdapter = new WineBrandAdapter(getContext());
        rvWineBrand.setAdapter(brandAdapter);


    }

    /**
     * 初始化美酒recyclerview
     */
    private void initWineRV() {
        //设置美酒详细数据RecyclerView
        //reverseLayout->是否从尾部开始显示
        RecyclerView.LayoutManager wineManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        lrvWineShow.setLayoutManager(wineManager);
        wineAdapter = new WineAdapter(getContext());
        lRecyclerViewAdapter = new LRecyclerViewAdapter(wineAdapter);
        lrvWineShow.setAdapter(lRecyclerViewAdapter);
        lrvWineShow.setLoadingMoreProgressStyle(ProgressStyle.LineSpinFadeLoader);
        //设置底部加载颜色
        lrvWineShow.setFooterViewColor(android.R.color.darker_gray, android.R.color.darker_gray, android.R.color.white);
        //设置底部加载文字提示
        lrvWineShow.setFooterViewHint("拼命加载中", "已经全部为你呈现了", "网络不给力啊，点击再试一次吧");

        lrvWineShow.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page= 1;
                wineAdapter.clear();
                lRecyclerViewAdapter.notifyDataSetChanged();
                requestRefreshData();
            }
        });

        lrvWineShow.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                requestLoadData();
            }
        });

        brandAdapter.setOnItemClickListener(new WineBrandAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, WineBrandEntity data) {
                page= 1;
                brandId = data.getId();
                wineAdapter.clear();
                lRecyclerViewAdapter.notifyDataSetChanged();
                requestRefreshData();
            }
        });
    }
//请求加载更多数据
    private void requestLoadData() {
        page++;
        if(brandId == -1){
            presenter.getWineList(page+"",20+"");
        }else{
            presenter.getWineListByBrandId(brandId+"",page+"",20+"");
        }
    }

    private void requestRefreshData() {
        if(brandId == -1){
            presenter.getWineList(page+"",20+"");
        }else{
            presenter.getWineListByBrandId(brandId+"",page+"",20+"");
        }
    }

    private void initData() {
        presenter.getBrandData();
        presenter.getWineList(page+"",20+"");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d(TAG, "onDestroyView");
        unbinder.unbind();
        brandId = -1;
        page = 1;
    }

    @Override
    public void setWineInfo(List<WineInfoEntity> wineInfoList) {
        LogUtils.d("view = "+view+"lrvWineShow = "+lrvWineShow);
        if(view==null){
            return;
        }
        if(wineInfoList!=null){
            if(wineInfoList.size()>0){
                wineAdapter.addAll(wineInfoList);
                lrvWineShow.refreshComplete(20);
            }else{
                lrvWineShow.setNoMore(true);
                lrvWineShow.refreshComplete(0);
            }
        }
    }

    @Override
    public void setWineInfoById(List<WineInfoEntity> wineInfoList) {
        LogUtils.d("view = "+view);
        if(view==null){
            return;
        }
        if(wineInfoList!=null){
            if(wineInfoList.size()>0){
                wineAdapter.addAll(wineInfoList);
                lrvWineShow.refreshComplete(20);
            }else{
                lrvWineShow.setNoMore(true);
                lrvWineShow.refreshComplete(0);
            }
        }
    }

    @Override
    public void setBrandData(List<WineBrandEntity> wineBrandList) {
        if(wineBrandList!=null&&wineBrandList.size()>0){
            brandAdapter.setData(wineBrandList);

        }
    }
}
