package com.jiupin.jiupinhui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.WineListAdapter;
import com.jiupin.jiupinhui.entity.WineBrandEntity;
import com.jiupin.jiupinhui.entity.WineInfoEntity;
import com.jiupin.jiupinhui.presenter.IWineListActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.WineListActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.ActivityUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IWineListActivityView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WineListActivity extends BaseActivity implements IWineListActivityView{

    @BindView(R.id.iv_wine_show)
    ImageView ivWineShow;
    @BindView(R.id.tv_wine_name)
    TextView tvWineName;
    @BindView(R.id.tv_wine_time)
    TextView tvWineTime;
    @BindView(R.id.lrv_wine)
    LRecyclerView lrvWine;
    private WineBrandEntity wineBrandEntity;
    private int cid;
    private WineListAdapter adapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private int page = 1;
    private IWineListActivityPresenter presenter;
    private boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_list);
        ButterKnife.bind(this);

        wineBrandEntity = (WineBrandEntity) getIntent().getExtras().getSerializable("wineBrand");
        cid = wineBrandEntity.getId();
        presenter = new WineListActivityPresenterImpl(this);
        initView();

        initHeader();
        
        getData();

    }

    private void getData() {
        presenter.getWineListByBrandId(cid+"",page+"",10+"");
    }

    private void initHeader() {
        tvWineName.setText(wineBrandEntity.getName());
        Glide.with(this)
                .load(wineBrandEntity.getThumb_img())
                .crossFade()
                .into(ivWineShow);
    }

    private void initView() {
        lrvWine.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        adapter = new WineListAdapter(this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);

        lrvWine.setAdapter(lRecyclerViewAdapter);

        lrvWine.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //设置底部加载颜色
        lrvWine.setFooterViewColor(android.R.color.darker_gray, android.R.color.darker_gray, android.R.color.white);
        //设置底部加载文字提示
        lrvWine.setFooterViewHint("拼命加载中", "已经全部为你呈现了", "网络不给力啊，点击再试一次吧");

        lrvWine.setLoadMoreEnabled(true);
        lrvWine.setPullRefreshEnabled(false);

        lrvWine.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                LogUtils.d("loadMore");
                page++;
                presenter.getWineListByBrandId(cid + "", page + "",10+"");
            }
        });
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();

    }

    @Override
    public void setWineInfoById(List<WineInfoEntity> wineInfoList) {
        if (ActivityUtils.isFinish(mContext))return;
        if (wineInfoList==null)return;
        if (wineInfoList.size() > 0) {
            adapter.addAll(wineInfoList);
            lrvWine.refreshComplete(10);
        } else {
            if (isFirst) {
                ToastUtils.showShort(this, "暂无");
            }
            lrvWine.setNoMore(true);
        }
        isFirst = false;

    }
}
