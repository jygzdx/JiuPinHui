package com.jiupin.jiupinhui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.activity.WineListActivity;
import com.jiupin.jiupinhui.adapter.WineAdapter;
import com.jiupin.jiupinhui.adapter.WineBrandAdapter;
import com.jiupin.jiupinhui.adapter.WineKindAdapter;
import com.jiupin.jiupinhui.entity.WineBrandEntity;
import com.jiupin.jiupinhui.presenter.impl.WineFragmentPresenterImpl;
import com.jiupin.jiupinhui.rvUtils.HeaderRecyclerAndFooterWrapperAdapter;
import com.jiupin.jiupinhui.rvUtils.ViewHolder;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.view.IWineFragmentView;
import com.jiupin.jiupinhui.widget.MyIndexBar;
import com.jiupin.jiupinhui.widget.MySuspensionDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/3/15.
 */

public class WineFragment extends Fragment implements IWineFragmentView {
    private static final String TAG = "WineFragment";
    @BindView(R.id.rv_wine_kind)
    RecyclerView rvWineKind;
    @BindView(R.id.rv_wine)
    RecyclerView rvWine;
    @BindView(R.id.indexBar)
    MyIndexBar indexBar;
    @BindView(R.id.tvSideBarHint)
    TextView tvSideBarHint;

    private View view;
    private Unbinder unbinder;
    private WineAdapter wineAdapter;

    private WineBrandAdapter brandAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private WineFragmentPresenterImpl presenter;
    private int brandId = -1;
    private int page = 1;
    private boolean isDestroy;
    private LinearLayoutManager mManager;
    private List<WineBrandEntity> wineList = new ArrayList<>();
    private WineAdapter mAdapter;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;
    private MySuspensionDecoration mDecoration;
    private WineKindAdapter mWineKindAdapter;
    private WineBrandAdapter wineBrandAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_wine, container, false);
        unbinder = ButterKnife.bind(this, view);
        isDestroy = false;

        presenter = new WineFragmentPresenterImpl(this);

        initWineKindRv();

        initWineRV();

        LogUtils.d(TAG, "onCreateView");
        return view;
    }

    private void initWineKindRv() {
        rvWineKind.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        mWineKindAdapter = new WineKindAdapter(getContext());
        rvWineKind.setAdapter(mWineKindAdapter);
//        mWineKindAdapter.setOnItemClickListener(new WineKindAdapter.OnRecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(View view, WineBrandEntity data) {
//                mWineKindAdapter.setSelected(position);
//                presenter.getwineBrandKind(data.getId()+"");
//            }
//        });
        mWineKindAdapter.setOnViewClickListener(new WineKindAdapter.OnViewClickListener() {
            @Override
            public void onClick(View view, Object data, int position) {
                mWineKindAdapter.setSelected(position);
                mWineKindAdapter.notifyDataSetChanged();
                presenter.getwineBrandKind(((WineBrandEntity) data).getId()+"");
            }
        });
        presenter.getBrandKind();
    }

    /**
     * 初始化美酒recyclerview
     */
    private void initWineRV() {
        rvWine.setLayoutManager(mManager = new LinearLayoutManager(getContext()));

        mAdapter = new WineAdapter(getContext(), wineList);
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                RecyclerView rvWineBrand = (RecyclerView)holder.getView(R.id.rv_wine_brand);
                rvWineBrand.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                rvWineBrand.setAdapter(wineBrandAdapter = new WineBrandAdapter(getContext(), (List<WineBrandEntity>) o));
                wineBrandAdapter.setOnItemClickListener(new WineBrandAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, WineBrandEntity data) {
                        Intent intent = new Intent(getContext(), WineListActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("wineBrand",data);
                        intent.putExtras(bundle);
                        getContext().startActivity(intent);
                    }
                });
            }
        };

        mAdapter.setOnItemClickListener(new WineAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, WineBrandEntity data) {
                Intent intent = new Intent(getContext(), WineListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("wineBrand",data);
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });


        indexBar.setmPressedShowTextView(tvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager

        //获取数据
        initData();
    }

    private void initData() {
        presenter.getBrandData();

    }

    /**
     * 获取美酒种类
     * @param id
     */
    public void gainWineBrandKind(int id){
        presenter.getwineBrandKind(id+"");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d(TAG, "onDestroyView");
        unbinder.unbind();
        brandId = -1;
        page = 1;
        isDestroy = true;
    }

    @Override
    public void setBrandData(List<WineBrandEntity> wineBrandList) {
        if (isDestroy)
            return;

        mHeaderAdapter.setHeaderView(R.layout.item_wine_header, wineBrandList);
        rvWine.setAdapter(mHeaderAdapter);
        rvWine.addItemDecoration(mDecoration = new MySuspensionDecoration(getContext(), wineList).setHeaderViewCount(mHeaderAdapter.getHeaderViewCount()));

        mDecoration.setColorTitleBg(Color.parseColor("#fffafafa"));
        mDecoration.setColorTitleFont(Color.parseColor("#ff000000"));
        //如果add两个，那么按照先后顺序，依次渲染。
//        rvWine.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));

        mHeaderAdapter.notifyDataSetChanged();


    }

    @Override
    public void setWineKind(List<WineBrandEntity> wineKindList) {
        if (isDestroy)
            return;

        if (wineKindList.size()<=0)return;
        mWineKindAdapter.setData(wineKindList);
        presenter.getwineBrandKind(wineKindList.get(0).getId()+"");
    }

    @Override
    public void setWineBrandKind(List<WineBrandEntity> wineKindList) {
        if (isDestroy)
            return;

        this.wineList = wineKindList;
        indexBar.setmSourceDatas(wineKindList)//设置数据
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount())//设置HeaderView数量
                .invalidate();

        mAdapter.setData(wineKindList);
        mHeaderAdapter.notifyDataSetChanged();
        mDecoration.setmDatas(wineKindList);
    }
}
